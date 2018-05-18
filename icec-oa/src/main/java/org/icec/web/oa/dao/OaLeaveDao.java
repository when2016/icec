package org.icec.web.oa.dao;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.icec.web.oa.model.OaLeave;


/* 
* 
* gen by icec mapper 2018-03-06
*/
public interface OaLeaveDao extends BaseMapper<OaLeave> {
	/*
	*
	*分页查询
	*
	*/
	public PageQuery<OaLeave> pageQuery(PageQuery<OaLeave> query);
}
