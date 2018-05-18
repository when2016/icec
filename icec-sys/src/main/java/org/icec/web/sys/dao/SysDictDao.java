package org.icec.web.sys.dao;
import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.icec.web.sys.model.*;

/*
* 
* gen by beetlsql mapper 2017-11-05
*/
public interface SysDictDao extends BaseMapper<SysDict> {
	public List<SysDict> getDictItems(@Param("parentId") Integer parentId);
	public List<SysDict> getDictItemsByType(@Param("type") String type);
	public List<SysDict> getDictItemsByTypeValue(@Param("type") String type,@Param("value") String value);
	
	public PageQuery<SysDict> queryDict(PageQuery<SysDict> query);
}
