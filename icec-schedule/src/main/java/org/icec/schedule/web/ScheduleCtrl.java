package org.icec.schedule.web;



import java.util.List;

import org.apache.shiro.authz.annotation.RequiresRoles;
import org.icec.common.base.tips.Tip;
import org.icec.common.web.BaseController;
import org.icec.schedule.exception.ServiceException;
import org.icec.schedule.service.TaskInfo;
import org.icec.schedule.service.JobTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 定时任务管理
 * @author xxjin
 *
 */
@Controller
public class ScheduleCtrl extends BaseController{
	@Autowired
	private JobTaskService taskService;
	@RequiresRoles("admin")
	@GetMapping("schedule/list")
	public String list(Model model) {
		List<TaskInfo> list=taskService.list();
		model.addAttribute("list", list);
		return "sys/schedule/list";
	}
	@RequiresRoles("admin")
	@PostMapping("schedule/add")
	@ResponseBody
	public Tip add(TaskInfo info) {
		try {
			taskService.addJob(info);
		}catch(ServiceException e) {
			return fail(e.getMessage());
		}
		return SUCC;
	}
	@RequiresRoles("admin")
	@PostMapping("schedule/update")
	@ResponseBody
	public Tip update(TaskInfo info) {
		try {
			taskService.edit(info);
		}catch(ServiceException e) {
			return fail(e.getMessage());
		}
		return SUCC;
	}
	/**
	 * 暂停
	 * @param info
	 * @return
	 */
	@RequiresRoles("admin")
	@PostMapping("schedule/pause")
	@ResponseBody
	public Tip pause(TaskInfo info) {
		try {
			taskService.pause(info.getJobName(),info.getJobGroup());
		}catch(ServiceException e) {
			return fail(e.getMessage());
		}
		return SUCC;
	}
	/**
	 * 暂停
	 * @param info
	 * @return
	 */
	@RequiresRoles("admin")
	@PostMapping("schedule/resume")
	@ResponseBody
	public Tip resume(TaskInfo info) {
		try {
			taskService.resume(info.getJobName(),info.getJobGroup());
		}catch(ServiceException e) {
			return fail(e.getMessage());
		}
		return SUCC;
	}
	/**
	 * 删除任务
	 * @param info
	 * @return
	 */
	@RequiresRoles("admin")
	@PostMapping("schedule/remove")
	@ResponseBody
	public Tip remove(TaskInfo info) {
		try {
			taskService.delete(info.getJobName(),info.getJobGroup());
		}catch(ServiceException e) {
			return fail(e.getMessage());
		}
		return SUCC;
	}
	@RequiresRoles("admin")
	@PostMapping("schedule/runNow")
	@ResponseBody
	public Tip runNow(TaskInfo info) {
		try {
			taskService.runNow(info.getJobName(),info.getJobGroup());
		}catch(ServiceException e) {
			return fail(e.getMessage());
		}
		return SUCC;
	}
}
