package org.icec.web.activiti.controller;

import java.util.List;

import org.icec.web.activiti.service.ActTaskService;
import org.icec.web.activiti.vo.Act;
import org.icec.web.shiro.annotation.CurrentUser;
import org.icec.web.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("workflow/task")
public class ActTaskCtrl {
	@Autowired
	private ActTaskService actTaskService;

	/**
	 * 获取流程表单
	 * 
	 * @param taskId
	 *            任务ID
	 * @param procInsId
	 *            流程实例ID
	 * @param procDefId
	 *            流程定义ID
	 */
	@RequestMapping(value = "form")
	public String form(String taskId, String procInsId, String procDefId) {

		// 获取流程XML上的表单KEY
		String formKey = actTaskService.getFormKey(procDefId, procInsId);
		StringBuilder formUrl = new StringBuilder();
		formUrl.append(formKey).append(formUrl.indexOf("?") == -1 ? "?" : "&");
		formUrl.append("act.taskId=").append(taskId != null ? taskId : "");
		formUrl.append("&act.procInsId=").append(procInsId != null ? procInsId : "");
		formUrl.append("&act.procDefId=").append(procDefId != null ? procDefId : "");
		return "redirect:" + formUrl;
	}
	/**
	 * 获取待办列表
	 * @return
	 */
	@RequestMapping(value = {"todo", ""})
	public String todoList( Act act,  Model model,@CurrentUser SysUser user) throws Exception {
		List<Act>  list = actTaskService.todoList(act,user.getId()+"");
		model.addAttribute("list", list);
		return "workflow/taskTodoList";
	}

}