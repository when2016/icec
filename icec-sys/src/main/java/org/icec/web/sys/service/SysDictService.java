package org.icec.web.sys.service;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.icec.web.sys.model.SysDict;
import org.icec.web.sys.model.SysUser;
import java.util.Date;
import java.util.List;
import org.beetl.sql.core.engine.PageQuery;
import org.icec.web.sys.dao.SysDictDao;

/*
* 
* gen by icec  2017-11-05
*/
@Service
public class SysDictService   {
	@Autowired
	private SysDictDao  sysDictDao ;
	
	public SysDict findById(Integer id){
		return sysDictDao.single(id);
	}
	/**
	*
	*保存
	*/
	@Transactional
	public void save(SysDict sysDict,SysUser optUser){
		sysDict.setCreateBy(optUser.getId());
		sysDict.setCreateDate(new Date());
		sysDict.setUpdateBy(optUser.getId());
		sysDict.setUpdateDate(new Date());
		sysDictDao.insertTemplate(sysDict);
	}
	/**
	 * 字典更新
	 * @param sysDict
	 * @param optuser
	 */
	@Transactional
	public void update(SysDict sysDict,SysUser optuser) {
		sysDict.setUpdateBy(optuser.getId());
		sysDict.setUpdateDate(new Date());
		 
		sysDictDao.updateTemplateById(sysDict);
	}
	
	/**
	 * 字典删除
	 * @param ids
	 * @param optuser
	 */
	@Transactional
	public void deleteAll(String ids,SysUser optuser) {
		String [] idarr=ids.split(",");
		for(String id:idarr) {
			SysDict sysDict=new SysDict();
			sysDict.setId(Integer.parseInt(id));
			sysDict.setUpdateBy(optuser.getId());
			sysDict.setUpdateDate(new Date());
			sysDict.setDelFlag(SysUser.DEL_FLAG_DELETE);
			sysDictDao.updateTemplateById(sysDict);
		}
		 
	}
	
	public PageQuery<SysDict> queryDict(PageQuery<SysDict> query) {
		return sysDictDao.queryDict(query);
	}
	
	
	/**
	 * 查询字典项
	 * @param typeId
	 * @return
	 */
	public List<SysDict> getDictValue(Integer typeId){
		if(typeId==null)return null;
		return sysDictDao.getDictItems(typeId);
	}
	/**
	 * 查询字典项
	 * @param type
	 * @return
	 */
	public List<SysDict> getDictByType(String type){
		if(type==null)return null;
		return sysDictDao.getDictItemsByType(type);
	}
	/**
	 * 获取字典标签值
	 * @param type
	 * @param value
	 * @return
	 */
	public  SysDict getDictByTypeValue(String type,String value) {
		List<SysDict> list=sysDictDao.getDictItemsByTypeValue(type, value);
		 
		return list.size()>0?list.get(0):null;
	}
}
