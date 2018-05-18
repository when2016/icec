package org.icec.web.shiro.resolvers;


import org.apache.shiro.SecurityUtils;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysUser;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

	@Override
	public boolean supportsParameter(MethodParameter parameter) {
		// 如果参数类型是User并且有CurrentUser注解则支持
		if (parameter.getParameterType().isAssignableFrom(SysUser.class)
				&& parameter.hasParameterAnnotation(CurrentUser.class)) {
			return true;
		}
		return false;
	}

	@Override
	public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
			NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
			return user;
		} else {
			throw new MissingServletRequestPartException("未登陆");
		}
	}

}
