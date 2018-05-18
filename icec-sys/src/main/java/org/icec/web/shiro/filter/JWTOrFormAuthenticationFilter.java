package org.icec.web.shiro.filter;

import java.io.IOException;
import java.io.StringReader;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;
import org.icec.web.shiro.exception.IncorrectCaptchaException;
import org.icec.web.shiro.token.JWTAuthenticationToken;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JWTOrFormAuthenticationFilter extends AuthenticatingFilter {
	private static final Logger log = LoggerFactory.getLogger(JWTOrFormAuthenticationFilter.class);
	public static final String USER_ID = "userId";
	public static final String PASSWORD = "password";
	public static final String KAPTCHA = "kaptcha";
	
	public static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";

	protected static final String AUTHORIZATION_HEADER = "Authorization";
	private String failureKeyAttribute = DEFAULT_ERROR_KEY_ATTRIBUTE_NAME;

	public String getFailureKeyAttribute() {
		return failureKeyAttribute;
	}

	public void setFailureKeyAttribute(String failureKeyAttribute) {
		this.failureKeyAttribute = failureKeyAttribute;
	}

	public JWTOrFormAuthenticationFilter() {
		setLoginUrl(DEFAULT_LOGIN_URL);
	}

	@Override
	public void setLoginUrl(String loginUrl) {
		String previous = getLoginUrl();
		if (previous != null) {
			this.appliedPaths.remove(previous);
		}
		super.setLoginUrl(loginUrl);
		this.appliedPaths.put(getLoginUrl(), null);
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		boolean loggedIn = false;

		if (isLoginRequest(request, response)) {// 登录请求
			if (isLoginSubmission(request, response)) {// 是post请求的登录
				if (log.isTraceEnabled()) {
					log.trace("Login submission detected.  Attempting to execute login.");
				}
				loggedIn = executeLogin(request, response);
				return true;// 登录成功或失败都通过，进入system/login controller里面
			} else {// 直接访问的登录页面，直接通过 ==get方式
				if (log.isTraceEnabled()) {
					log.trace("Login page view.");
				}
				// allow them to see the login page ;)
				return true;
			}

		} else {
			if (isLoggedAttempt(request, response)) {// jwt登录尝试
				loggedIn = executeLogin(request, response);
			}
			if (!loggedIn) {
				HttpServletRequest request2 = (HttpServletRequest) request;
				if (isAjax(request2)) {
					HttpServletResponse httpResponse = WebUtils.toHttp(response);
					httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
					return loggedIn;
				}
				saveRequestAndRedirectToLogin(request, response);
			}
			return loggedIn;
		}

	}

	protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
		AuthenticationToken token = createToken(request, response);
		if (token == null) {
			String msg = "createToken method implementation returned null. A valid non-null AuthenticationToken "
					+ "must be created in order to execute a login attempt.";
			throw new IllegalStateException(msg);
		}
		try {
			doCaptchaValidate((HttpServletRequest) request);
			Subject subject = getSubject(request, response);
			subject.login(token);
			return onLoginSuccess(token, subject, request, response);
		} catch (AuthenticationException e) {
			return onLoginFailure(token, e, request, response);
		}
	}

	// 验证码校验
	protected void doCaptchaValidate(HttpServletRequest request) {
		// session中的图形码字符串
		String captcha = (String) request.getSession()
				.getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
		String code=getKaptcha(request);
		// 比对
		if(captcha==null) {
			throw  new IncorrectCaptchaException("验证码错误！");
		}
		if (captcha != null && !captcha.equalsIgnoreCase(code)) {
			throw new IncorrectCaptchaException("验证码错误！");
		}
	}

	@Override
	protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		if (isLoginRequest(request, response)) {
			HttpServletRequest request2 = (HttpServletRequest) request;
			if (isAjax(request2)) {
				String json = IOUtils.toString(request.getInputStream(), "utf-8");

				if (json != null && !json.isEmpty()) {
					try (JsonReader jr = Json.createReader(new StringReader(json))) {
						JsonObject object = jr.readObject();
						String username = object.getString(USER_ID);
						String password = object.getString(PASSWORD);
						return new UsernamePasswordToken(username, password);
					}

				}
			} else {
				String username = getUsername(request);
				String password = getPassword(request);
				return new UsernamePasswordToken(username, password);
			}
		}

		if (isLoggedAttempt(request, response)) {
			String jwtToken = getAuthzHeader(request);
			if (jwtToken != null) {
				return createToken(jwtToken);
			}
		}

		return new UsernamePasswordToken();
	}

	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request,
			ServletResponse response) {
		HttpServletRequest request2 = (HttpServletRequest) request;
		if (isAjax(request2)) {
			HttpServletResponse httpResponse = WebUtils.toHttp(response);
			httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			return false;
		}
		setFailureAttribute(request, e);
		// login failed, let request continue back to the login page:
		return false;
	}

	protected boolean isLoggedAttempt(ServletRequest request, ServletResponse response) {
		String authzHeader = getAuthzHeader(request);
		return authzHeader != null;
	}

	protected String getAuthzHeader(ServletRequest request) {
		HttpServletRequest httpRequest = WebUtils.toHttp(request);
		String paramAuth = httpRequest.getParameter("jtoken");
		if (paramAuth != null)
			return paramAuth;
		return httpRequest.getHeader(AUTHORIZATION_HEADER);
	}

	public JWTAuthenticationToken createToken(String token) {

		return new JWTAuthenticationToken(token);

	}

	/**
	 * 判断ajax请求
	 * 
	 * @param request
	 * @return
	 */
	private boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}

	/**
	 * This default implementation merely returns <code>true</code> if the request
	 * is an HTTP <code>POST</code>, <code>false</code> otherwise. Can be overridden
	 * by subclasses for custom login submission detection behavior.
	 *
	 * @param request
	 *            the incoming ServletRequest
	 * @param response
	 *            the outgoing ServletResponse.
	 * @return <code>true</code> if the request is an HTTP <code>POST</code>,
	 *         <code>false</code> otherwise.
	 */
	protected boolean isLoginSubmission(ServletRequest request, ServletResponse response) {
		return (request instanceof HttpServletRequest)
				&& WebUtils.toHttp(request).getMethod().equalsIgnoreCase(POST_METHOD);
	}

	protected void setFailureAttribute(ServletRequest request, AuthenticationException ae) {
		String className = ae.getClass().getName();
		request.setAttribute(getFailureKeyAttribute(), className);
	}

	protected String getUsername(ServletRequest request) {
		return WebUtils.getCleanParam(request, USER_ID);
	}

	protected String getPassword(ServletRequest request) {
		return WebUtils.getCleanParam(request, PASSWORD);
	}

	protected String getKaptcha(ServletRequest request) {
		return WebUtils.getCleanParam(request, KAPTCHA);
	}
}
