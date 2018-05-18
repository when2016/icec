package org.icec.web.sys.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.icec.common.model.JsTreeData;
import org.icec.common.model.TreeModel;
import org.icec.common.utils.TreeBuild;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysMenu;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/menu")
public class SysMenuCtrl {
	@Autowired
	private SysMenuService sysMenuService;
	/**
	 * 进入添加界面
	 * @return
	 */
	@RequiresPermissions({"menu:edit"})
	@RequestMapping("add")
	public String add(Integer parentId,ModelMap model) {
		if(parentId!=null) {
			SysMenu parea=sysMenuService.findById(parentId);
			model.addAttribute("pmenu", parea);
			
		} else {
			model.addAttribute("pmenu", new SysMenu());
		}
		return "sys/menu/menuAdd";
	}
	/**
	 * 保存数据逻辑
	 * @param role
	 * @return
	 */
	@RequiresPermissions({"menu:edit"})
	@RequestMapping("save")
	@ResponseBody
	public Integer save(SysMenu menu,@CurrentUser SysUser user) {
		sysMenuService.save(menu,user);
		return 1;
	}
	/**
	 * 进入修改界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"menu:edit"})
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable Integer id ,ModelMap model) {
		SysMenu sysMenu = sysMenuService.findById(id);
		model.addAttribute("menu", sysMenu);
		return "sys/menu/menuEdit";
	}
	/**
	 * 更新数据逻辑
	 * @param user
	 * @return
	 */
	@RequiresPermissions({"menu:edit"})
	@RequiresRoles({"admin"})
	@RequestMapping("update")
	@ResponseBody
	public Integer update(SysMenu sysMenu ,@CurrentUser SysUser user) {
		if(sysMenu==null||sysMenu.getId()==null) {
			return 0;
		}
		sysMenuService.update(sysMenu, user);
		return 1;
	}
	 /**
	  * 删除
	  * @param id
	  * @param user
	  * @return
	  */
	@RequiresPermissions({"user:edit"})
	@RequestMapping("delete")
	@ResponseBody
	public Integer delete( Integer id,@CurrentUser SysUser user) {
		 
		sysMenuService.delete(  id, user);
		   
		return  1;
	}
	/**
	 * 进入查询界面
	 * @return
	 */
	@RequestMapping("list")
	public String list(ModelMap model) {
		model.addAttribute("menuList", sysMenuService.query());
		return "sys/menu/menuList";
	}
	/**
	 * 查询逻辑
	 * @param user
	 * @return
	 */
	@RequestMapping("query")
	@ResponseBody
	public List<SysMenu> query() {
		List<SysMenu> query=new ArrayList<SysMenu>();
		 
		  query=sysMenuService.query();
		   
		return  query;
	}
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<JsTreeData> treeData(@RequestParam(required=false) String extId,Integer roleId) {
		List<SysMenu> list = sysMenuService.query();
		List<JsTreeData> tree=TreeBuild.buildJsTree(list);
		 if(roleId!=null) {//角色不为空，查询出觉得对应的菜单，并设置为选中
			 List<SysMenu> selected= sysMenuService.findByRoleId(roleId);
			 Map<String,SysMenu> tmp=new HashMap<String,SysMenu>();
			 for(SysMenu menu:selected) {
			tmp.put(menu.getId()+"",menu);
			 }
			 for(JsTreeData data:tree) {
				 SysMenu menu= tmp.get(data.getId());
				 if(menu!=null&&data.isLeaf()==true) {
					 data.getState().setSelected(true);
				 }
			 }
		 }
		return tree;
	}
	/**
	 * 用户菜单
	 * @param user
	 */
	@ResponseBody
	@RequestMapping(value = "userMenu")
	public List<TreeModel> userMenu(@CurrentUser SysUser user) {
		List<SysMenu> menuList = sysMenuService.findMenuByUserId(user.getId());
		List<TreeModel> tree = TreeBuild.buildTree(menuList);
		return tree;
	}
}
