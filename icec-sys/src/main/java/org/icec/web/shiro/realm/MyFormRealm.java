package org.icec.web.shiro.realm;


import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.icec.web.sys.model.SysMenu;
import org.icec.web.sys.model.SysRole;
import org.icec.web.sys.model.SysUser;
import org.icec.web.sys.service.SysMenuService;
import org.icec.web.sys.service.SysRoleService;
import org.icec.web.sys.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class MyFormRealm extends AuthorizingRealm {

    @Autowired
    private SysUserService userRepository;
    @Autowired
	private SysRoleService sysRoleService;
    @Autowired
	private SysMenuService sysMenuService;
    @Override
    public boolean supports(AuthenticationToken token) {
        return token != null && token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = (String)upToken.getPrincipal();
        if(username==null) return null;
        SysUser user = userRepository.findByUserId(upToken.getUsername());
        
        if (user != null) {
        	if( "1".equals(user.getLoginFlag())) {
            	throw new LockedAccountException("账户已冻结");
            }
            SimpleAccount account = new SimpleAccount(user, user.getCredentials(), getName());
            return account;
        }

        return null;
    }
    
        @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        	SysUser user=(SysUser)  principals.getPrimaryPrincipal();
        	// 根据id查询用户的角色 和权限标识
        	List<SysRole> roleList=sysRoleService.findRoleByUserId(user.getId());
        	Set<String> permissionSet = new HashSet<>();
             Set<String> roleNameSet = new HashSet<>();
        	 for(SysRole role:roleList) {
        		 roleNameSet.add(role.getEnname());
        	 }
        	 List<SysMenu> permissionList= sysMenuService.findPermissionsByUserId(user.getId());
        	 for(SysMenu menu:permissionList) {
        		 String permission=menu.getPermission();
        		 if(StringUtils.hasLength(permission)) {//不为空
        			 permissionSet.add(permission); 
        		 }
        		 
        	 }
        	 SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        	 info.addStringPermissions(permissionSet);
             info.addRoles(roleNameSet);
        	return info;
    }
}
