package org.icec.web.sys.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.beetl.sql.core.engine.PageQuery;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysArea;
import org.icec.web.sys.model.SysDict;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysDictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 字典管理
 * @author jinxx
 *
 */
@Controller
@RequestMapping("sys/dict")
public class SysDictCtrl {
	@Autowired
	private SysDictService sysDictService;
	
	@RequestMapping("add")
	public String add() {
		return "sys/dict/dictAdd";
	}
	@RequestMapping("save")
	@ResponseBody
	public String save(SysDict sysDict,@CurrentUser SysUser currUser) {
		sysDictService.save(sysDict, currUser);
		return "1";
	}
	/**
	 * 进入修改界面
	 * @param id
	 * @param model
	 * @return
	 */
	//@RequiresPermissions({"user:edit"})
	@RequestMapping("edit/{id}")
	public String edit(@PathVariable Integer id ,ModelMap model) {
		SysDict sysDict = sysDictService.findById(id);
		model.addAttribute("dict", sysDict);
		return "sys/dict/dictEdit";
	}
	/**
	 * 更新数据逻辑
	 * @param user
	 * @return
	 */
	//@RequiresPermissions({"user:edit"})
	@RequestMapping("update")
	@ResponseBody
	public Integer update(SysDict sysDict,@CurrentUser SysUser user) {
		if(sysDict==null||sysDict.getId()==null) {
			return 0;
		}
		sysDictService.update(sysDict, user);
		return 1;
	}
	@RequestMapping("deleteAll")
	@ResponseBody
	public Integer deleteAll(String ids,@CurrentUser SysUser optuser) {
		if(ids==null) {
			return 0;
		}
		sysDictService.deleteAll(ids,optuser);
		return 1;
	}
	/**
	 * 进入查询界面
	 * @return
	 */
	@RequestMapping("list")
	public String list() {
		return "sys/dict/dictList";
	}
	@RequestMapping("query")
	@ResponseBody
	public PageQuery<SysDict> query( @RequestParam(defaultValue="1") Integer pageNumber, Integer pageSize, SysDict sysDict) {
		PageQuery<SysDict> query=new PageQuery<SysDict>();
		query.setPageNumber(pageNumber);
		if(pageSize!=null) {
			query.setPageSize(pageSize);
		}
		query.setParas(sysDict);
		  query=sysDictService.queryDict(query);
		   
		return  query;
	}
	
	
	@RequestMapping("getDictValue")
	@ResponseBody
	public Map<String,List<SysDict>> getDictValue(Integer dictTypeId){
		Map<String,List<SysDict>> result=new HashMap<String,List<SysDict>>();
		result.put("rows", sysDictService.getDictValue(dictTypeId));
		return result;
	}
	
	
	//====================================================//
	//  字典项管理
	//=================================
	@RequestMapping("dictValueAdd")
	public String dictValueAdd(@RequestParam(required=true) Integer dictTypeId,ModelMap model) {
		model.addAttribute("dictTypeId", dictTypeId);
		return "sys/dict/dictValueAdd";
	}
	
	@RequestMapping("dictValueEdit/{id}")
	public String dictValueEdit(@PathVariable Integer id ,ModelMap model) {
		SysDict sysDict = sysDictService.findById(id);
		model.addAttribute("dict", sysDict);
		return "sys/dict/dictValueEdit";
	}
}
