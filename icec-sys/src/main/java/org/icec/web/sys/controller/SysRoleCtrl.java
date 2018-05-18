package org.icec.web.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.beetl.sql.core.engine.PageQuery;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysRole;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/role")
public class SysRoleCtrl {
	Logger logger=LoggerFactory.getLogger(getClass());
	@Autowired
	private SysRoleService sysRoleService;
	/**
	 * 进入添加界面
	 * @return
	 */
	@RequestMapping("add")
	@RequiresPermissions({"role:edit"})
	public String add() {
		return "sys/role/roleAdd";
	}
	/**
	 * 保存数据逻辑
	 * @param role
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	@RequiresPermissions({"role:edit"})
	public Integer save(SysRole role,@CurrentUser SysUser user) {
		sysRoleService.save(role,user);
		return 1;
	}
	/**
	 * 进入修改界面
	 * @param id
	 * @param model
	 * @return
	 */
	@RequiresPermissions({"role:edit"})
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable Integer id ,ModelMap model) {
		SysRole sysRole = sysRoleService.findById(id);
		model.addAttribute("role", sysRole);
		return "sys/role/roleEdit";
	}
	/**
	 * 更新数据逻辑
	 * @param user
	 * @return
	 */
	@RequiresPermissions({"role:edit"})
	@RequestMapping("update")
	@ResponseBody
	public Integer update(SysRole sysRole,@CurrentUser SysUser optuser) {
		if(sysRole==null||sysRole.getId()==null) {
			return 0;
		}
		sysRoleService.update(sysRole,optuser);
		return 1;
	}
	@RequiresPermissions({"role:edit"})
	@RequestMapping("deleteAll")
	@ResponseBody
	public Integer deleteAll(String ids,@CurrentUser SysUser optuser) {
		if(ids==null) {
			return 0;
		}
		sysRoleService.deleteAll(ids,optuser);
		return 1;
	}
	/**
	 * 进入查询界面
	 * @return
	 */
	@RequestMapping("list")
	public String list() {
		return "sys/role/roleList";
	}
	/**
	 * 查询逻辑
	 * @param pageNumber
	 * @param pageSize
	 * @param user
	 * @return
	 */
	@RequestMapping("query")
	@ResponseBody
	public PageQuery<SysRole> query( @RequestParam(defaultValue="1") Integer pageNumber, Integer pageSize, SysRole role) {
		PageQuery<SysRole> query=new PageQuery<SysRole>();
		query.setPageNumber(pageNumber);
		if(pageSize!=null) {
			query.setPageSize(pageSize);
		}
		query.setParas(role);
		  query=sysRoleService.queryRole(query);
		   
		return  query;
	}
	
	/**
	 * 对角色进行授权
	 * @return
	 */
	@RequiresPermissions({"role:auth"})
	@RequestMapping("auth")
	public String auth(Integer roleId,ModelMap model) {
		model.addAttribute("roleId", roleId);
		return "sys/role/roleAuth";
	}
	@RequiresPermissions({"role:auth"})
	@RequestMapping("authSave")
	@ResponseBody
	public String authSave(Integer roleId,String menuIds) {
		sysRoleService.saveRoleMenu(roleId, menuIds);
		return "1";
	}
	
	@RequestMapping("getRoleValue")
	@ResponseBody
	public Map<String,List<SysRole>> getRoleValue(Integer roleTypeId){
		Map<String,List<SysRole>> result=new HashMap<String,List<SysRole>>();
		result.put("rows", sysRoleService.getRoleValue(roleTypeId));
		return result;
	}
	@RequestMapping("queryUnselect")
	@ResponseBody
	public Map<String,List<SysRole>> queryUnselect(Integer roleTypeId){
		Map<String,List<SysRole>> result=new HashMap<String,List<SysRole>>();
		
		result.put("rows", sysRoleService.queryUnselect(roleTypeId));
		return result;
	}
	
	
	@RequestMapping("roleValueAdd")
	public String roleValueAdd(@RequestParam(required=true) Integer roleTypeId,ModelMap model) {
		model.addAttribute("roleTypeId", roleTypeId);
		return "sys/role/roleValueAdd";
	}
	
	@RequestMapping("saveAll")
	@ResponseBody
	public Integer saveAll(String ids,Integer roleTypeId) {
		if(ids==null) {
			return 0;
		}
		sysRoleService.saveAll(ids,roleTypeId);
		return 1;
	}
}
