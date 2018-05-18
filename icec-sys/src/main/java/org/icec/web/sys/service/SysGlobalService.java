package org.icec.web.sys.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import org.icec.web.sys.model.SysGlobal;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.dao.SysGlobalDao;

/*
* 
* gen by icec  2018-01-16
*/
@Service
public class SysGlobalService   {
	@Autowired
	private SysGlobalDao  sysGlobalDao ;
	@Autowired
	private SysFileService sysFileService;
	/**
	*
	*更新全局
	*/
	@Transactional
	public void update(SysGlobal sysGlobal,SysUser optuser, MultipartFile multiFile){
		sysGlobal.setId(1);
		sysGlobal.setCreateTime(new Date());
		Long result = sysFileService.saveUploadFile("logo", multiFile, optuser);
		sysGlobal.setLogo(result);
		sysGlobalDao.updateById(sysGlobal);
	}
	
	/**
	*
	*按主键查询
	*
	*/
	public SysGlobal getGlobal(){
		SysGlobal global= sysGlobalDao.single(1);
		if (global == null) {
			throw new IllegalStateException("Global not exist!");
		}
		return global;
	}
}
