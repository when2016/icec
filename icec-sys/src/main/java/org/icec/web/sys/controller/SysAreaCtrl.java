package org.icec.web.sys.controller;

import java.util.List;

import org.icec.common.model.JsTreeData;
import org.icec.common.utils.TreeBuild;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysArea;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/sys/area")
public class SysAreaCtrl {
	@Autowired
	private SysAreaService sysAreaService;
	/**
	 * 进入添加界面
	 * @return
	 */
	@RequestMapping("add")
	//@RequiresPermissions({"role:edit"})
	public String add(Integer parentId,ModelMap model) {
		if(parentId!=null) {
			SysArea parea=sysAreaService.findById(parentId);
			model.addAttribute("parea", parea);
			
		} else {
			model.addAttribute("parea", new SysArea());
		}
		return "sys/area/areaAdd";
	}
	/**
	 * 保存数据逻辑
	 * @param role
	 * @return
	 */
	@RequestMapping("save")
	@ResponseBody
	//@RequiresPermissions({"role:edit"})
	public Integer save(SysArea sysArea,@CurrentUser SysUser user) {
		sysAreaService.save(sysArea,user);
		return 1;
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
		SysArea sysArea = sysAreaService.findById(id);
		model.addAttribute("sysArea", sysArea);
		return "sys/area/areaEdit";
	}
	/**
	 * 更新数据逻辑
	 * @param user
	 * @return
	 */
	//@RequiresPermissions({"user:edit"})
	@RequestMapping("update")
	@ResponseBody
	public Integer update(SysArea sysArea,@CurrentUser SysUser user) {
		if(sysArea==null||sysArea.getId()==null) {
			return 0;
		}
		sysAreaService.update(sysArea, user);
		return 1;
	}
	/**
	 * 进入查询界面
	 * @return
	 */
	@RequestMapping("list")
	public String list(ModelMap model) {
		model.addAttribute("areaList", sysAreaService.query());
		return "sys/area/areaList";
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
		 
		sysAreaService.delete(  id, user);
		   
		return  1;
	}
	
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<JsTreeData> treeData(@RequestParam(required=false) String extId) {
		List<SysArea> list = sysAreaService.findAll();
		 
		return TreeBuild.buildJsTree(list);
	}

}
