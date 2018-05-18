package org.icec.web.activiti.service;

import java.io.InputStream;
import java.util.Map;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

@Service
public class ProcessService {
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private IdentityService identityService;

	/**
	 * 读取资源，通过procDefId
	 * 
	 * @param processDefinitionId
	 *            流程定义ID
	 * @param processInstanceId
	 *            流程实例ID
	 * @param resourceType
	 *            资源类型(xml|image)
	 */
	public InputStream resourceRead(String procDefId, String proInsId, String resType) throws Exception {

		if (StringUtils.isBlank(procDefId)) {
			ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(proInsId)
					.singleResult();
			procDefId = processInstance.getProcessDefinitionId();
		}
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(procDefId).singleResult();

		String resourceName = "";
		if (resType.equals("image")) {
			resourceName = processDefinition.getDiagramResourceName();
		} else if (resType.equals("xml")) {
			resourceName = processDefinition.getResourceName();
		}

		InputStream resourceAsStream = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(),
				resourceName);
		return resourceAsStream;
	}

	/**
	 * 启动流程
	 * 
	 * @param procDefKey
	 * @param businessId
	 * @param title
	 * @param userId
	 * @return
	 */
	@Transactional(readOnly = false)
	public String startProcess(String procDefKey, String businessId, String title, String userId) {
		identityService.setAuthenticatedUserId(userId);
		Map<String, Object> vars = Maps.newHashMap();
		// 设置流程标题
		if (StringUtils.isNotBlank(title)) {
			vars.put("title", title);
		}
		ProcessInstance procIns = runtimeService.startProcessInstanceByKey(procDefKey, procDefKey + ":" + businessId,
				vars);
		return procIns.getProcessInstanceId();
	}
}
