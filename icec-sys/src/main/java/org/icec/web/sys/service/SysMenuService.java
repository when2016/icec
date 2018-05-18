package org.icec.web.sys.service;

import java.util.Date;
import java.util.List;

import org.beetl.sql.core.annotatoin.Param;
import org.icec.common.utils.TreeBuild;
import org.icec.web.sys.dao.SysMenuDao;
import org.icec.web.sys.model.SysArea;
import org.icec.web.sys.model.SysMenu;
import org.icec.web.sys.model.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class SysMenuService {
	@Autowired
	private SysMenuDao sysMenuDao;
	/**
	 * 保存菜单
	 * @param menu
	 * @param optuser  操作人
	 */
	@Transactional
	public void save(SysMenu menu,SysUser optuser) {
		if (StringUtils.isEmpty(menu.getParentId())) {
			menu.setParentId(0);
			menu.setParentIds("0,");
		} else {
			SysMenu pArea = sysMenuDao.single(menu.getParentId());
			menu.setParentIds(pArea.getParentIds() + pArea.getId() + ",");
		}
		menu.setCreateBy(optuser.getId());
		menu.setCreateDate(new Date());
		menu.setUpdateBy(optuser.getId());
		menu.setUpdateDate(new Date());
		menu.setDelFlag(SysMenu.DEL_FLAG_NORMAL);
		sysMenuDao.insert(menu);
	}
	/**
	 * 更新menu 
	 * @param menu
	 * @param user
	 */
	@Transactional
	public void update(SysMenu menu, SysUser user) {
		menu.setUpdateBy(user.getId());
		menu.setUpdateDate(new Date());
		SysMenu area = sysMenuDao.single(menu.getId());
		SysMenu parea = sysMenuDao.single(menu.getParentId());
		if (area.getParentId().equals(menu.getParentId())) {
			sysMenuDao.updateTemplateById(menu);
		} else {
			// 获取修改前的parentIds，用于更新子节点的parentIds
			String oldParentIds = area.getParentIds();
			menu.setParentIds(parea.getParentIds() + parea.getId() + ",");
			sysMenuDao.updateTemplateById(menu);
			// 查询所有子节点，并更新parentids
			List<SysMenu> menulist = sysMenuDao.findByParentIdsLike(oldParentIds + menu.getId() + ",");
			for (SysMenu e : menulist) {
				if (e.getParentIds() != null && oldParentIds != null) {
					e.setParentIds(e.getParentIds().replace(oldParentIds, menu.getParentIds()));
					sysMenuDao.updateTemplateById(e);
				}
			}

		}

	}

	 /**
	  * 删除menu 
	  * @param id
	  * @param user
	  */
	@Transactional
	public void delete(Integer id, SysUser user) {
		SysMenu menu = sysMenuDao.single(id);
		Date date=new Date();
		menu.setUpdateBy(user.getId());
		menu.setUpdateDate(date);
		menu.setDelFlag(SysArea.DEL_FLAG_DELETE);
		//SysArea area = sysAreaDao.single(sysArea.getId());
		sysMenuDao.updateTemplateById(menu);
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = menu.getParentIds();
		
		// 查询所有子节点，并更新parentids
		List<SysMenu> menulist = sysMenuDao.findByParentIdsLike(oldParentIds + menu.getId() + ",");
		for (SysMenu e : menulist) {
				e.setUpdateBy(user.getId());
				e.setUpdateDate(date);
				e.setDelFlag(SysArea.DEL_FLAG_DELETE);
				sysMenuDao.updateTemplateById(e);
		 
		}

	}
	public SysMenu findById(Integer id) {
		return sysMenuDao.findById(id);
	}
	/**
	 * 菜单查询
	 * @param query
	 * @return
	 */
	public List<SysMenu> query() {
		return (List<SysMenu> )TreeBuild.buildByRecursive(sysMenuDao.query());
	}
	
	public List<SysMenu> findByRoleId(Integer roleId){
		return sysMenuDao.findByRoleId(roleId);
	}
	
	
	public List<SysMenu> findMenuByUserId(Integer userId){
		return sysMenuDao.findMenuByUserId(userId);
	}
	
	public List<SysMenu> findPermissionsByUserId( Integer userId){
		return sysMenuDao.findPermissionsByUserId(userId);
	}
}
