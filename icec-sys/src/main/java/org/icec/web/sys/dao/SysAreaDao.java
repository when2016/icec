package org.icec.web.sys.dao;
import java.util.List;

import org.beetl.sql.core.annotatoin.*;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.icec.web.sys.model.*;

/*
* 
* gen by beetlsql mapper 2017-10-27
*/
public interface SysAreaDao extends BaseMapper<SysArea> {
	public SysArea findById(@Param("id") Integer id);
	/**
	 * 查询所有的区域
	 * @return
	 */
	public List<SysArea> query();
	/**
	 * 根据parentIds 查询区域
	 * @param parentIds
	 * @return
	 */
	public List<SysArea> findByParentIdsLike(@Param("parentIds") String parentIds);
}
