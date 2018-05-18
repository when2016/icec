package org.icec.web.sys.dao;
import java.util.List;

import org.beetl.sql.core.annotatoin.*;
import org.beetl.sql.core.db.KeyHolder;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.icec.web.sys.model.*;

/*
* 
* gen by beetlsql mapper 2017-12-08
*/
public interface SysFileDao extends BaseMapper<SysFile> {
	List<SysFile> queryFile(@Param("busiType") String busiType,@Param("busiNo")String busiNo);
}
