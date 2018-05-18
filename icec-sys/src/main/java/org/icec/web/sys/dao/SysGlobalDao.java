package org.icec.web.sys.dao;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.icec.web.sys.model.SysGlobal;


/* 
* 
* gen by icec mapper 2018-01-16
*/
public interface SysGlobalDao extends BaseMapper<SysGlobal> {
	/*
	*
	*分页查询
	*
	*/
	public PageQuery<SysGlobal> pageQuery(PageQuery<SysGlobal> query);
}
