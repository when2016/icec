package org.icec.web.sys.dao;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.icec.web.sys.model.*;

/*
* 
* gen by beetlsql mapper 2017-11-01
*/
public interface SysLogDao extends BaseMapper<SysLog> {
	public PageQuery<SysLog> pageQuery(PageQuery<SysLog> query);
}
