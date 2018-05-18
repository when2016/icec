package org.icec.web.sys.dao;

import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;
import org.icec.web.sys.model.SysMenu;

public interface SysMenuDao extends BaseMapper<SysMenu>{
	public SysMenu findById(@Param("id") Integer id);
	/**
	 * 菜单查询
	 * @param query
	 * @return
	 */
	public List<SysMenu> query();
	/**
	 * 根据parentIds 查询区域
	 * @param parentIds
	 * @return
	 */
	public List<SysMenu> findByParentIdsLike(@Param("parentIds") String parentIds);

	public List<SysMenu> findByRoleId(@Param("roleId") Integer roleId);

	public List<SysMenu> findMenuByUserId(@Param("userId") Integer userId);
	/**
	 * 根据用户id查权限标识
	 * @param userId
	 * @return
	 */
	public List<SysMenu> findPermissionsByUserId(@Param("userId") Integer userId);
}
