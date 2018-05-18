package org.icec.web.sys.dao;


import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.engine.PageQuery;
import org.beetl.sql.core.mapper.BaseMapper;
import org.icec.web.sys.model.SysUser;

public interface  SysUserDao extends BaseMapper<SysUser>{
	public SysUser findById(@Param("id")Integer id);
	public PageQuery<SysUser> queryUser(PageQuery<SysUser> query);
	/**
	 * 查询非本角色下的用户
	 * @param roleTypeId，name
	 */
	public List<SysUser> queryUnselect(@Param("roleTypeId")Integer roleTypeId,@Param("name")String name,@Param("loginName")String loginName);
	/**
	 * 删除本角色下用户
	 * @param userId，roleId
	 */
	public void delRole(@Param("userId")Integer userId, @Param("roleId")Integer roleId);
	
}
