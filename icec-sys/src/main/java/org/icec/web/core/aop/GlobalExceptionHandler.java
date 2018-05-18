package org.icec.web.core.aop;
import org.apache.shiro.authz.AuthorizationException;
import org.icec.common.base.tips.ErrorTip;
import org.icec.common.exception.IcecException;
import org.icec.common.utils.AjaxUtils;
import org.icec.common.utils.HttpKit;
import org.icec.web.core.log.LogManager;
import org.icec.web.core.log.LogTaskFactory;
import org.icec.web.sys.model.SysLog;
import org.icec.web.sys.utils.ShiroKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.UndeclaredThrowableException;


/**
 * 全局的的异常拦截器（拦截所有的控制器）（带有@RequestMapping注解的方法上都会拦截）
 *
 *  
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    GlobalExceptionHandler(){
    	log.debug("GlobalExceptionHandler enable");
    }
   /**
     * 拦截业务异常
     *
     * 
     */
    @ExceptionHandler(IcecException.class)
    public String  notFount(IcecException e,WebRequest request, HttpServletResponse response) {
    	saveException(e);
    	log.error("业务异常:", e);
    	if(AjaxUtils.isAjaxRequest(request)){//ajax 则返回json
    		 AjaxUtils.writeJson(new ErrorTip(500,e.getMessage()),response);
    		 return null;
    	}
    	 return "/error/500";
    }


     /**
     * 无权访问该资源
     *
     *  
     */ 
    @ExceptionHandler(AuthorizationException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String credentials(AuthorizationException e,WebRequest request, HttpServletResponse response,Model model) {
    	saveException(e);
        log.error("权限异常!", e);
        if(AjaxUtils.isAjaxRequest(request)){//ajax 则返回json
      		 AjaxUtils.writeJson(new ErrorTip(500,"无权限访问资源"),response);
      	}
        model.addAttribute("error", "无权限访问资源");
        return "/error/500";
    }
    /**
     * 拦截未知的运行时异常
     *
     *  
     */ 
    @ExceptionHandler(RuntimeException.class)
    public String notFount(RuntimeException e,WebRequest request, HttpServletResponse response,Model model) {
    	saveException(e);
    	log.error("运行时异常:", e);
    	if(AjaxUtils.isAjaxRequest(request)){//ajax 则返回json
   		 AjaxUtils.writeJson(new ErrorTip(500,e.getMessage()),response);
   		 return null;
   	}model.addAttribute("error",e.getMessage());
        return "/error/500";
    }

    private void saveException(RuntimeException e) {
    	HttpServletRequest request=HttpKit.getRequest();
    	String requestUrl=request.getServletPath();
    	SysLog sysLog = new SysLog();
    	sysLog.setTitle(e.getMessage());
		sysLog.setRequestUri(requestUrl);
		sysLog.setMethod(request.getMethod());
		sysLog.setRemoteAddr(request.getRemoteAddr());
		sysLog.setUserAgent(request.getHeader("user-agent"));
		
		if(ShiroKit.isUser()) {
			sysLog.setCreateBy(ShiroKit.getUser().getLoginName());
		}else {
			sysLog.setCreateBy("");
		}
        LogManager.me().executeLog(LogTaskFactory.exceptionLog(sysLog, e));
        
    }

  
    private void assertAjax(HttpServletRequest request, HttpServletResponse response) {
        if (request.getHeader("x-requested-with") != null
                && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
            //如果是ajax请求响应头会有，x-requested-with
            response.setHeader("sessionstatus", "timeout");//在响应头设置session状态
        }
    }
 
}
