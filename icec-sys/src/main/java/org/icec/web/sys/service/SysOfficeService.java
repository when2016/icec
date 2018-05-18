package org.icec.web.sys.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.icec.web.sys.model.SysArea;
import org.icec.web.sys.model.SysOffice;
import org.icec.web.sys.model.SysUser;

import java.util.Date;
import java.util.List;

import org.icec.common.utils.TreeBuild;
import org.icec.web.sys.dao.SysOfficeDao;

/*
* 
* gen by icec servicegen 2017-10-24
*/
@Service
public class SysOfficeService   {
	@Autowired
	private SysOfficeDao  sysOfficeDao ;
	
	public SysOffice findById(Integer id) {
		return sysOfficeDao.findById(id);
	}
	/**
	 * 查询所有子节点
	 * @param pId
	 * @return
	 */
	public List<SysOffice> findSubOffice(Integer pId){
		SysOffice entity = sysOfficeDao.single(pId);
		String oldParentIds = entity.getParentIds();
		List<SysOffice> list = sysOfficeDao.findByParentIdsLike(oldParentIds + entity.getId() + ",");
		return list;
	}
	/**
	 * 根据机构类型查询
	 * @param type
	 * @return
	 */
	public List<SysOffice> findByType(String type) {
		return sysOfficeDao.findByType(type);
	}

	/**
	*
	*保存
	*/
	@Transactional
	public void save(SysOffice sysOffice,SysUser user){
		if (StringUtils.isEmpty(sysOffice.getParentId())) {
			sysOffice.setParentId(0);
			sysOffice.setParentIds("0,");
		} else {
			SysOffice pArea = sysOfficeDao.single(sysOffice.getParentId());
			sysOffice.setParentIds(pArea.getParentIds() + pArea.getId() + ",");
		}
		sysOffice.setCreateBy(user.getId());
		sysOffice.setCreateDate(new Date());
		sysOffice.setDelFlag(SysArea.DEL_FLAG_NORMAL);
		sysOffice.setUpdateBy(user.getId());
		sysOffice.setUpdateDate(new Date());
		sysOfficeDao.insert(sysOffice);
	}
	
	/**
	 * 更新区域
	 * 
	 * @param sysArea
	 * @param user
	 */
	@Transactional
	public void update(SysOffice sysOffice, SysUser user) {
		sysOffice.setUpdateBy(user.getId());
		sysOffice.setUpdateDate(new Date());
		SysOffice area = sysOfficeDao.single(sysOffice.getId());
		SysOffice parea = sysOfficeDao.single(sysOffice.getParentId());
		if (area.getParentId().equals(sysOffice.getParentId())) {
			sysOfficeDao.updateTemplateById(sysOffice);
		} else {
			// 获取修改前的parentIds，用于更新子节点的parentIds
			String oldParentIds = area.getParentIds();
			sysOffice.setParentIds(parea.getParentIds() + parea.getId() + ",");
			sysOfficeDao.updateTemplateById(sysOffice);
			// 查询所有子节点，并更新parentids
			List<SysOffice> list = sysOfficeDao.findByParentIdsLike(oldParentIds + sysOffice.getId() + ",");
			for (SysOffice e : list) {
				if (e.getParentIds() != null && oldParentIds != null) {
					e.setParentIds(e.getParentIds().replace(oldParentIds, sysOffice.getParentIds()));
					sysOfficeDao.updateTemplateById(e);
				}
			}

		}

	}

	/**
	 * 删除区域
	 * @param sysArea
	 * @param user
	 */
	@Transactional
	public void delete(Integer id, SysUser user) {
		SysOffice entity = sysOfficeDao.single(id);
		Date date=new Date();
		entity.setUpdateBy(user.getId());
		entity.setUpdateDate(date);
		entity.setDelFlag(SysArea.DEL_FLAG_DELETE);
		//SysArea area = sysAreaDao.single(sysArea.getId());
		sysOfficeDao.updateTemplateById(entity);
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = entity.getParentIds();
		
		// 查询所有子节点，并更新parentids
		List<SysOffice> list = sysOfficeDao.findByParentIdsLike(oldParentIds + entity.getId() + ",");
		for (SysOffice e : list) {
				e.setUpdateBy(user.getId());
				e.setUpdateDate(date);
				e.setDelFlag(SysArea.DEL_FLAG_DELETE);
				sysOfficeDao.updateTemplateById(e);
		 
		}

	}
	
	/**
	 * 查詢
	 * @return
	 */
	public List<SysOffice> query(){
		return (List<SysOffice>) TreeBuild.buildByRecursive(sysOfficeDao.findByType(null));
		 
	}
}
