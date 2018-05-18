package org.icec.web.sys.controller;

import org.beetl.sql.core.engine.PageQuery;
import org.icec.web.sys.model.SysLog;
import org.icec.web.sys.service.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
@Controller
@RequestMapping("sys/log")
public class SysLogCtrl {
	@Autowired
	private SysLogService sysLogService;
	/**
	 * 进入查询界面
	 * @return
	 */
	@RequestMapping("list")
	public String listInit() {
		return "sys/log/logList";
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
	public PageQuery<SysLog> query( @RequestParam(defaultValue="1") Integer pageNumber, Integer pageSize, SysLog log) {
		PageQuery<SysLog> query=new PageQuery<SysLog>();
		if(StringUtils.isEmpty(log.getType())) {
			log.setType(null);
		}
		query.setPageNumber(pageNumber);
		if(pageSize!=null) {
			query.setPageSize(pageSize);
		}
		query.setParas(log);
		  query=sysLogService.queryUser(query);
		   
		return  query;
	}
}
