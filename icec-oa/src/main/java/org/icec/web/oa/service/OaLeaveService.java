package org.icec.web.oa.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import org.beetl.sql.core.engine.PageQuery;
import org.icec.web.oa.model.OaLeave;
import org.icec.web.sys.model.SysUser;
import org.icec.common.exception.IcecException;
import org.icec.web.activiti.service.ProcessService;
import org.icec.web.oa.dao.OaLeaveDao;

/*
* 
* gen by icec  2018-03-06
*/
@Service
public class OaLeaveService   {
	@Autowired
	private OaLeaveDao  oaLeaveDao ;
	@Autowired
	private ProcessService processService;
	/**
	*
	*保存
	*/
	@Transactional
	public void save(OaLeave oaLeave,SysUser user){
		oaLeave.setDeleted(OaLeave.DEL_FLAG_NORMAL);
		oaLeave.setUserId(user.getId());
		oaLeave.setApplyTime(new Date());
		oaLeaveDao.insert(oaLeave,true);
		if(oaLeave.getStatus()==1) {//发起流程
			String processinstid=	processService.startProcess("oa_leave", oaLeave.getId()+"", oaLeave.getTitle(), user.getId()+"");
			oaLeave.setProcessid(processinstid);
			oaLeaveDao.updateById(oaLeave);
		}
	}
	
	/**
	*
	*按主键查询
	*
	*/
	public OaLeave get(Integer id){
		return oaLeaveDao.single(id);
	}
	/**
	*
	*分页查询
	*
	*/
	public PageQuery<OaLeave> pageQuery(PageQuery<OaLeave> query){
		return oaLeaveDao.pageQuery(query);
	}
}
