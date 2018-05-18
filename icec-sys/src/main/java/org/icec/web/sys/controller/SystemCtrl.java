package org.icec.web.sys.controller;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.icec.web.shiro.exception.IncorrectCaptchaException;
import org.icec.web.sys.model.SysGlobal;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysGlobalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.impl.DefaultKaptcha;

@Controller
public class SystemCtrl {
	private static final Logger log = LoggerFactory.getLogger(SystemCtrl.class);
	@Autowired
	private SysGlobalService sysGlobalService;
	@Autowired  
	DefaultKaptcha defaultKaptcha;
    @RequestMapping("kaptcha.jpg")
    public void getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {

        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");

        String capText = defaultKaptcha.createText();
        request.getSession().setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);

        try (ServletOutputStream out = response.getOutputStream()) {
            ImageIO.write(defaultKaptcha.createImage(capText), "jpg", out);
            out.flush();
        } 

      
    }
	
	@GetMapping("sys/login")
	public String login(@ModelAttribute("msg") String msg ,ModelMap model) {
		
		model.addAttribute("msg", msg);
		SysGlobal global = sysGlobalService.getGlobal();
		model.addAttribute("global", global);
		return "sys/login";
	}

	@PostMapping("sys/login")
	public String dologin(HttpServletRequest request, HttpServletResponse response,RedirectAttributes model) {
		if (SecurityUtils.getSubject().isAuthenticated()) {
			SysUser user=(SysUser)SecurityUtils.getSubject().getPrincipal();
			log.info("登入成功"+user.getName());
			return "redirect:/";
		} else {
			log.info("登入失败");
			String exception = (String) request.getAttribute(FormAuthenticationFilter.DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
			// String message =
			// (String)request.getAttribute(FormAuthenticationFilter.DEFAULT_MESSAGE_PARAM);
			if(IncorrectCaptchaException.class.getName().equals(exception)) {
				model.addFlashAttribute("msg", "验证码错误");
			}else if(LockedAccountException.class.getName().equals(exception)) {
				model.addFlashAttribute("msg", "账户已冻结");
			}else {
				model.addFlashAttribute("msg", "用户名或密码错误");
			}
			return "redirect:/sys/login";
		}

	}

	@GetMapping("sys/logout")
	public String logout() {
		SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
		log.info("登出:" + user.getLoginName());
		SecurityUtils.getSubject().logout();
		return "redirect:/sys/login";
	}
}
