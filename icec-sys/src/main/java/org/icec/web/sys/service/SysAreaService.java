package org.icec.web.sys.service;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.icec.web.sys.model.SysArea;
import org.icec.web.sys.model.SysUser;

import java.util.Date;
import java.util.List;

import org.icec.common.utils.TreeBuild;
import org.icec.web.sys.dao.SysAreaDao;

/*
* 
* gen by icec  2017-10-27
*/
@Service
public class SysAreaService {
	@Autowired
	private SysAreaDao sysAreaDao;

	/**
	 *
	 * 保存
	 */
	@Transactional
	public void save(SysArea sysArea, SysUser user) {
		if (StringUtils.isEmpty(sysArea.getParentId())) {
			sysArea.setParentId(0);
			sysArea.setParentIds("0,");
		} else {
			SysArea pArea = sysAreaDao.single(sysArea.getParentId());
			sysArea.setParentIds(pArea.getParentIds() + pArea.getId() + ",");
		}
		sysArea.setCreateBy(user.getId());
		sysArea.setCreateDate(new Date());
		sysArea.setDelFlag(SysArea.DEL_FLAG_NORMAL);
		sysArea.setUpdateBy(user.getId());
		sysArea.setUpdateDate(new Date());
		sysAreaDao.insert(sysArea);
	}

	/**
	 * 更新区域
	 * 
	 * @param sysArea
	 * @param user
	 */
	@Transactional
	public void update(SysArea sysArea, SysUser user) {
		sysArea.setUpdateBy(user.getId());
		sysArea.setUpdateDate(new Date());
		SysArea area = sysAreaDao.single(sysArea.getId());
		SysArea parea = sysAreaDao.single(sysArea.getParentId());
		if (area.getParentId().equals(sysArea.getParentId())) {
			sysAreaDao.updateTemplateById(sysArea);
		} else {
			// 获取修改前的parentIds，用于更新子节点的parentIds
			String oldParentIds = area.getParentIds();
			sysArea.setParentIds(parea.getParentIds() + parea.getId() + ",");
			sysAreaDao.updateTemplateById(sysArea);
			// 查询所有子节点，并更新parentids
			List<SysArea> arealist = sysAreaDao.findByParentIdsLike(oldParentIds + sysArea.getId() + ",");
			for (SysArea e : arealist) {
				if (e.getParentIds() != null && oldParentIds != null) {
					e.setParentIds(e.getParentIds().replace(oldParentIds, sysArea.getParentIds()));
					sysAreaDao.updateTemplateById(e);
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
		SysArea area = sysAreaDao.single(id);
		Date date=new Date();
		area.setUpdateBy(user.getId());
		area.setUpdateDate(date);
		area.setDelFlag(SysArea.DEL_FLAG_DELETE);
		//SysArea area = sysAreaDao.single(sysArea.getId());
		sysAreaDao.updateTemplateById(area);
		// 获取修改前的parentIds，用于更新子节点的parentIds
		String oldParentIds = area.getParentIds();
		
		// 查询所有子节点，并更新parentids
		List<SysArea> arealist = sysAreaDao.findByParentIdsLike(oldParentIds + area.getId() + ",");
		for (SysArea e : arealist) {
				e.setUpdateBy(user.getId());
				e.setUpdateDate(date);
				e.setDelFlag(SysArea.DEL_FLAG_DELETE);
				sysAreaDao.updateTemplateById(e);
		 
		}

	}

	/**
	 * 根据主键id查询
	 * 
	 * @param id
	 * @return
	 */
	public SysArea findById(Integer id) {
		return sysAreaDao.findById(id);
	}

	public List<SysArea> findAll() {
		return sysAreaDao.query();
	}

	public List<SysArea> query() {

		return (List<SysArea>) TreeBuild.buildByRecursive(sysAreaDao.query());
	}

}
