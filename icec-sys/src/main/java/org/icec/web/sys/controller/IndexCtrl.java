package org.icec.web.sys.controller;

import java.util.List;

import org.icec.common.exception.IcecException;
import org.icec.common.model.TreeModel;
import org.icec.common.utils.TreeBuild;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysGlobal;
import org.icec.web.sys.model.SysMenu;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysGlobalService;
import org.icec.web.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexCtrl {
	@Autowired
	private SysGlobalService sysGlobalService;
	@Autowired
	private SysMenuService sysMenuService;

	@GetMapping({ "/", "/index" })
	public String index(@CurrentUser SysUser user, ModelMap model) {
		model.addAttribute("user", user);
		List<SysMenu> menuList = sysMenuService.findMenuByUserId(user.getId());
		List<TreeModel> tree = TreeBuild.buildTree(menuList);
		model.addAttribute("menuList", tree);
		SysGlobal global = sysGlobalService.getGlobal();
		model.addAttribute("global", global);
		return "sys/index";
	}
	@GetMapping({  "/index2" })
	public String index2(@CurrentUser SysUser user, ModelMap model) {
		model.addAttribute("user", user);
		List<SysMenu> menuList = sysMenuService.findMenuByUserId(user.getId());
		List<TreeModel> tree = TreeBuild.buildTree(menuList);
		model.addAttribute("menuList", tree);
		SysGlobal global = sysGlobalService.getGlobal();
		model.addAttribute("global", global);
		return "sys/index2";
	}
	@GetMapping({ "/home" })
	public String home(@CurrentUser SysUser user, ModelMap model) {
		model.addAttribute("user", user);

		return "sys/home";
	}

	@RequestMapping({ "/error2" })
	public String error() {
		throw new IcecException(500, "Sam 错误");
	}


	@GetMapping({ "/h5", "/ueditor" })
	public String h5(@CurrentUser SysUser user, ModelMap model) {
		model.addAttribute("user", user);
		List<SysMenu> menuList = sysMenuService.findMenuByUserId(user.getId());
		List<TreeModel> tree = TreeBuild.buildTree(menuList);
		model.addAttribute("menuList", tree);
		SysGlobal global = sysGlobalService.getGlobal();
		model.addAttribute("global", global);
		return "h5/demo";
	}

}
