package org.icec.web.oa.controller;

import org.beetl.sql.core.engine.PageQuery;
import org.icec.common.base.tips.Tip;
import org.icec.common.web.BaseController;
import org.icec.web.oa.model.OaLeave;
import org.icec.web.oa.service.OaLeaveService;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 请假流程
 * @author xxjin
 *
 */
@Controller
@RequestMapping("oa/leave")
public class LeaveProcessCtrl extends BaseController{
	@Autowired
	private OaLeaveService oaLeaveService;
	/**
	 * 新建请假流程
	 * @return
	 */
	@GetMapping("new")
	public String newLeave() {
		return "oa/leave/leaveEdit";
	}
	
	@PostMapping("saveLeave")
	@ResponseBody
	public Tip saveLeave(OaLeave oaLeave,@CurrentUser SysUser user) {
		oaLeaveService.save(oaLeave,user);
		return SUCC;
	}
	
	/**
	 * 请假流程
	 * @return
	 */
	@GetMapping("myLeave")
	public String myLeave() {
		return "oa/leave/leavemy";
	}
	/**
	 * 请假流程
	 * @return
	 */
	@PostMapping("myLeave")
	@ResponseBody
	public PageQuery<OaLeave> myLeaveQuery(@RequestParam(defaultValue="1") Integer pageNumber, Integer pageSize,@CurrentUser SysUser user) {
		 OaLeave oaLeave =new OaLeave();
		 oaLeave.setDeleted(OaLeave.DEL_FLAG_NORMAL);
		 oaLeave.setUserId(user.getId());
		PageQuery<OaLeave> query=new PageQuery<OaLeave>();
		query.setPageNumber(pageNumber);
		if(pageSize!=null) {
			query.setPageSize(pageSize);
		}
		query.setParas(oaLeave);
		query.setOrderBy("apply_time desc");
		
		return oaLeaveService.pageQuery(query);
	}
	
}
