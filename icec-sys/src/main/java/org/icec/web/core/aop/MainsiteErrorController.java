package org.icec.web.core.aop;

import javax.servlet.http.HttpServletResponse;

import org.icec.common.base.tips.ErrorTip;
import org.icec.common.utils.AjaxUtils;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.WebRequest;

public class MainsiteErrorController implements ErrorController {
	private static final String ERROR_PATH = "/error";

	@RequestMapping(value = ERROR_PATH)
	public String handleError(WebRequest request, HttpServletResponse response) {
		if(AjaxUtils.isAjaxRequest(request)){//ajax 则返回json
	   		 AjaxUtils.writeJson(new ErrorTip(404,"地址不存在"),response);
	   		 return null;
	   	}
		return "error/404";
	}

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}

}
