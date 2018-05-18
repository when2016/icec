package org.icec.web.sys.service;

import java.util.Date;
import java.util.List;

import org.beetl.sql.core.engine.PageQuery;
import org.icec.common.utils.CryptoUtils;
import org.icec.web.shiro.credential.BCryptPasswordService;
import org.icec.web.sys.dao.SysRoleDao;
import org.icec.web.sys.dao.SysUserDao;
import org.icec.web.sys.model.SysUser;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class SysUserService {
	@Autowired
	private SysUserDao userDao;
	@Autowired
	private SysRoleDao sysRoleDao;
	@Autowired
	private SysFileService sysFileService;
	private BCryptPasswordService pwService = new BCryptPasswordService();

	/**
	 * 新增用户
	 * 
	 * @param user
	 */
	@Transactional
	public void save(SysUser user, SysUser optuser, Integer[] roleList, MultipartFile multiFile) {
		user.setCreateBy(optuser.getId());
		user.setCreateDate(new Date());
		user.setUpdateBy(optuser.getId());
		user.setUpdateDate(new Date());
		Long result = sysFileService.saveUploadFile("userPhoto", multiFile, optuser);
		user.setPhoto(result + "");
		/**
		 * 密码加密
		 */

		user.setPassword(CryptoUtils.bcrypt(user.getPassword()));
		user.setDelFlag(SysUser.DEL_FLAG_NORMAL);
		userDao.insert(user, true);

		if (roleList != null) {
			for (Integer roleId : roleList) {// 添加用户角色关系表
				sysRoleDao.insertUserRole(user.getId(), roleId);
			}
		}

	}

	/**
	 * 用户更新
	 * 
	 * @param user
	 */
	@Transactional
	public void update(SysUser user, SysUser optuser, Integer[] roleList, MultipartFile multiFile) {
		user.setUpdateBy(optuser.getId());
		user.setUpdateDate(new Date());
		if (StringUtils.hasLength(user.getPassword())) {
			user.setPassword(CryptoUtils.bcrypt(user.getPassword()));
		} else {
			user.setPassword(null);
		}
		Long result = sysFileService.saveUploadFile("userPhoto", multiFile, optuser);
		if(result>0) {
			user.setPhoto(result+"");
		}
		userDao.updateTemplateById(user);
		
		if (roleList != null) {
			sysRoleDao.deleteUserRoleByUserId(user.getId());// 先删除角色
			for (Integer roleId : roleList) {// 添加用户角色关系表
				sysRoleDao.insertUserRole(user.getId(), roleId);
			}
		}
	}

	/**
	 * 删除操作，更新del_flag字段
	 * 
	 * @param ids
	 * @param optuser
	 */
	@Transactional
	public void deleteAll(String ids, SysUser optuser) {
		String[] idarr = ids.split(",");
		for (String id : idarr) {
			SysUser user = new SysUser();
			user.setId(Integer.parseInt(id));
			user.setUpdateBy(optuser.getId());
			user.setUpdateDate(new Date());
			user.setDelFlag(SysUser.DEL_FLAG_DELETE);
			userDao.updateTemplateById(user);
		}

	}

	/**
	 * 根据主键id查询
	 * 
	 * @param id
	 * @return
	 */
	public SysUser findById(Integer id) {

		return userDao.findById(id);
	}

	public PageQuery<SysUser> queryUser(PageQuery<SysUser> query) {
		return userDao.queryUser(query);
	}

	/**
	 * 根据登录名查询
	 * 
	 * @param loginName
	 * @return
	 */
	public SysUser findByUserId(String loginName) {
		if (StringUtils.isEmpty(loginName)) {
			// 不允许为空
			return null;
		}
		SysUser user = new SysUser();
		user.setLoginName(loginName);
		//user.setLoginFlag(SysUser.LOGIN_FLAG_NORMAL);
		user.setDelFlag(SysUser.DEL_FLAG_NORMAL);
		return userDao.templateOne(user);
	}
	/**
	 * 更新用户密码
	 * @param userId
	 * @param newpasswd
	 */
	@Transactional
	public boolean updatePasswd(Integer userId,String oldPasswd,String newpasswd){
		
		SysUser user = this.findById(userId);
		if (BCrypt.checkpw(oldPasswd, user.getPassword())) {
			SysUser updateUser = new SysUser();
			updateUser.setId(userId);
			updateUser.setPassword(CryptoUtils.bcrypt(newpasswd));
			userDao.updateTemplateById(updateUser);
			return true;
		}else{
			return false;
		}
		
	
	}
	/**
	 * 删除本角色下的用户
	 * @param 
	 * @return
	 */
	public void delRole(Integer userId, Integer roleId) {
		// TODO Auto-generated method stub
		userDao.delRole(userId,roleId);
	}
	/**
	 * 查询不是本角色下的用户
	 * @param 
	 * @return
	 */
	public List<SysUser> queryUnselect(Integer roleTypeId,String name,String loginName) {
		if(roleTypeId==null)return null;
		return userDao.queryUnselect(roleTypeId,name,loginName);
	}

}
