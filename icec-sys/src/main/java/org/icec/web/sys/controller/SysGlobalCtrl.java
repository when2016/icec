package org.icec.web.sys.controller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.icec.common.base.tips.Tip;
import org.icec.common.web.BaseController;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysGlobal;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysGlobalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SysGlobalCtrl extends BaseController{
	@Autowired
	private SysGlobalService sysGlobalService;
	/**
	 * 进入修改页面
	 * @param model
	 * @return
	 */
	@RequiresPermissions("global:show")
	@GetMapping("sys/global/edit")
	public String edit(ModelMap model) {
		SysGlobal global=sysGlobalService.getGlobal();
		model.addAttribute("global", global);
		return "sys/global/globalEdit";
	}
	
	/**
	 * 更新全局属性
	 * @param global
	 * @return
	 */
	@RequiresPermissions("global:update")
	@PostMapping("sys/global/update")
	public @ResponseBody Tip update(SysGlobal global, @CurrentUser SysUser optuser,@RequestParam(name = "file", required = false) MultipartFile multiFile) {
		sysGlobalService.update(global,optuser,multiFile);
		 
		return SUCC;
	}
}
