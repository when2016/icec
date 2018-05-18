package org.icec.web.activiti.controller;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.icec.common.base.tips.Tip;
import org.icec.common.web.BaseController;
import org.icec.web.activiti.ToWeb;
import org.icec.web.activiti.service.ProcessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 流程管理
 * @author xxjin
 *
 */
@Controller
@RequestMapping("workflow/process")
public class ProcessController extends BaseController{
	@Autowired
    private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private HistoryService historyService;
	@Autowired
    private ProcessService processService;
	/**
	 * 挂起流程定义
	 * @param processDefinitionId
	 * @return
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "suspend/{id}")
	@ResponseBody
	public Tip suspend(@PathVariable("id") String processDefinitionId) {
		repositoryService.suspendProcessDefinitionById(processDefinitionId);
		return SUCC;
	}
	/**
	 * 挂起流程定义
	 * @param processDefinitionId
	 * @return
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "activate/{id}")
	@ResponseBody
	public Tip activate(@PathVariable("id") String processDefinitionId) {
		repositoryService.activateProcessDefinitionById(processDefinitionId);
		return SUCC;
	}
	/**
	 * 删除流程部署
	 * @param deploymentId
	 * @return
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "deleteProDeployment/{id}")
	@ResponseBody
	public Tip deleteProDeployment(@PathVariable("id") String deploymentId) {
		repositoryService.deleteDeployment(deploymentId);
		return SUCC;
	}
	/**
	 * 删除流程实例
	 * @param procInsId
	 * @param reason
	 * @return
	 */
	@RequiresPermissions("act:process:edit")
	@RequestMapping(value = "deleteProcIns")
	@ResponseBody
	public Tip deleteProcIns(String procInsId, String reason) {
		if (StringUtils.isBlank(reason)){
			fail( "请填写删除原因");
		}else{
			runtimeService.deleteProcessInstance(procInsId, reason);
		}
		return SUCC;
	}
	@RequiresPermissions("act:process:edit")
	@GetMapping("processDefList")
	public String listProcessDef() {
		return "workflow/processDefList";
	}
	@RequiresPermissions("act:process:edit")
	@PostMapping("processDefList")
	@ResponseBody
	public ToWeb.Rows queryProcessDef(@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer rowSize, @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer page, String category) {
		 ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery()
		    		.latestVersion().orderByProcessDefinitionKey().asc();
		    
		    if (StringUtils.isNotEmpty(category)){
		    	processDefinitionQuery.processDefinitionCategory(category);
			}
		    List list=new ArrayList<>();
		    long count=processDefinitionQuery.count();
		    List<ProcessDefinition> processDefinitionList = processDefinitionQuery.listPage(rowSize * (page - 1)
	                , rowSize);
		    for (ProcessDefinition processDefinition : processDefinitionList) {
	            Map map=new HashMap<>();
	            String deploymentId = processDefinition.getDeploymentId();
	            Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();

	            map.put("id",processDefinition.getId());
	            map.put("category",processDefinition.getCategory());
	            map.put("name",processDefinition.getName());
	            map.put("version",processDefinition.getVersion());
	            map.put("key",processDefinition.getKey());
	            map.put("deploymentId",processDefinition.getDeploymentId());
	            map.put("suspended",processDefinition.isSuspended());
	            map.put("resource",processDefinition.getResourceName());
	            map.put("image",processDefinition.getDiagramResourceName());
	            map.put("deploymentTime",deployment.getDeploymentTime());

	            list.add(map);
	        }
		return  ToWeb.Rows.buildRows().setCurrent(page)
	            .setTotalPages((int) (count/rowSize+1))
	            .setTotalRows(count)
	            .setList(list)
	            .setRowSize(rowSize);
	}
	@RequestMapping(value = "resource")
	public void resourceRead(String procDefId, String proInsId, String resType, HttpServletResponse response) throws Exception {
		InputStream resourceAsStream = processService.resourceRead(procDefId, proInsId, resType);
		byte[] b = new byte[1024];
		int len = -1;
		while ((len = resourceAsStream.read(b, 0, 1024)) != -1) {
			response.getOutputStream().write(b, 0, len);
		}
	}
	
	/**
	 * 运行中的流程
	 * @return
	 */
	@RequiresPermissions("act:process:edit")
	@GetMapping("processRunningList")
	public String listRunningProcess() {
		return "workflow/processRunningList";
	}
	@RequiresPermissions("act:process:edit")
	@PostMapping("processRunningList")
	@ResponseBody
	public ToWeb.Rows queryRunningProcess(@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer rowSize, @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer page, String procInsId, String procDefKey) {
		
		 ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();

		    if (StringUtils.isNotBlank(procInsId)){
			    processInstanceQuery.processInstanceId(procInsId);
		    }
		    
		    if (StringUtils.isNotBlank(procDefKey)){
			    processInstanceQuery.processDefinitionKey(procDefKey);
		    }
		    long count=processInstanceQuery.count();
		    List<ProcessInstance> processDefinitionList = processInstanceQuery.listPage(rowSize * (page - 1)
	                , rowSize);
		     
		return  ToWeb.Rows.buildRows().setCurrent(page)
	            .setTotalPages((int) (count/rowSize+1))
	            .setTotalRows(count)
	            .setList(processDefinitionList)
	            .setRowSize(rowSize);
	}
	/**
	 * 已结束的流程
	 * @return
	 */
	@RequiresPermissions("act:process:edit")
	@GetMapping("processEndList")
	public String listEndProcess() {
		return "workflow/processEndList";
	}
	
	/**
	 * 查询已结束流程
	 * @param rowSize
	 * @param page
	 * @param procInsId
	 * @param procDefKey
	 * @return
	 */
	@RequiresPermissions("act:process:edit")
	@PostMapping("processEndList")
	@ResponseBody
	public ToWeb.Rows queryEndProcess(@RequestParam(value = "pageSize", defaultValue = "10", required = false) Integer rowSize, @RequestParam(value = "pageNumber", defaultValue = "1", required = false) Integer page, String procInsId, String procDefKey) {
		
		 HistoricProcessInstanceQuery historicProcessInstanceQuery = historyService.createHistoricProcessInstanceQuery();
		    if (StringUtils.isNotBlank(procInsId)){
		    	historicProcessInstanceQuery.processInstanceId(procInsId);
		    }
		    
		    if (StringUtils.isNotBlank(procDefKey)){
		    	historicProcessInstanceQuery.processDefinitionKey(procDefKey);
		    }
		    long count=historicProcessInstanceQuery.count();
		    List<HistoricProcessInstance> processDefinitionList = historicProcessInstanceQuery.listPage(rowSize * (page - 1)
	                , rowSize);
		     
		return  ToWeb.Rows.buildRows().setCurrent(page)
	            .setTotalPages((int) (count/rowSize+1))
	            .setTotalRows(count)
	            .setList(processDefinitionList)
	            .setRowSize(rowSize);
	}
	
}
