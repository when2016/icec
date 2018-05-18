package org.icec.web.sys.controller;


import java.util.List;

import org.icec.common.model.JsTreeData;
import org.icec.common.utils.TreeBuild;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysArea;
import org.icec.web.sys.model.SysOffice;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysOfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/office")
public class SysOfficeCtrl {
	@Autowired
	private SysOfficeService sysOfficeService;
	
	/**
	 * 进入添加界面
	 * @return
	 */
	@RequestMapping("add")
	//@RequiresPermissions({"role:edit"})
	public String add(Integer parentId,ModelMap model) {
		if(parentId!=null) {
			SysOffice psysOffice=sysOfficeService.findById(parentId);
			model.addAttribute("psysOffice", psysOffice);
			
		} else {
			model.addAttribute("psysOffice", new SysOffice());
		}
		return "sys/office/officeAdd";
	}
	/**
	 * 保存数据逻辑
	 * @param role
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	//@RequiresPermissions({"role:edit"})
	public Integer save(SysOffice psysOffice,@CurrentUser SysUser user) {
		sysOfficeService.save(psysOffice,user);
		return 1;
	}
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable Integer id ,ModelMap model) {
		SysOffice sysOffice = sysOfficeService.findById(id);
		model.addAttribute("sysOffice", sysOffice);
		return "sys/office/officeEdit";
	}
	/**
	 * 更新数据逻辑
	 * @param user
	 * @return
	 */
	@RequestMapping("update")
	@ResponseBody
	public Integer update(SysOffice sysOffice,@CurrentUser SysUser user) {
		if(sysOffice==null||sysOffice.getId()==null) {
			return 0;
		}
		sysOfficeService.update(sysOffice, user);
		return 1;
	}
	 /**
	  * 删除
	  * @param id
	  * @param user
	  * @return
	  */
	@RequestMapping("delete")
	@ResponseBody
	public Integer delete( Integer id,@CurrentUser SysUser user) {
		 
		sysOfficeService.delete(  id, user);
		   
		return  1;
	}
	/**
	 * 进入查询界面
	 * @return
	 */
	@RequestMapping("list")
	public String list(ModelMap model) {
		model.addAttribute("officeList", sysOfficeService.query());
		return "sys/office/officeList";
	}
	 
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<JsTreeData> treeData(@RequestParam(required=false) String type,@RequestParam(required=false) Integer pId) {
		List<SysOffice> list=null;
		Integer root=0;
		if(!StringUtils.isEmpty(pId)) {
			list = sysOfficeService.findSubOffice(pId);
			root=pId;
		}else {
		  list = sysOfficeService.findByType(type);
		  
		}
		return TreeBuild.buildJsTree(list,root);
	}
}
