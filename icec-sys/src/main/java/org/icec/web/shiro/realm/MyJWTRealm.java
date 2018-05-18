package org.icec.web.shiro.realm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAccount;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.icec.web.shiro.jwt.JwtUtil;
import org.icec.web.shiro.jwt.TokenStatus;
import org.icec.web.shiro.token.JWTAuthenticationToken;
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
public class MyJWTRealm extends AuthorizingRealm {

	@Autowired
	private SysUserService userRepository;
	@Autowired
	private SysRoleService sysRoleService;
	@Autowired
	private SysMenuService sysMenuService;
	@Autowired
	private JwtUtil jwtUtil;

	@Override
	public boolean supports(AuthenticationToken token) {
		return token != null && token instanceof JWTAuthenticationToken;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
		JWTAuthenticationToken upToken = (JWTAuthenticationToken) token;
		String userName = jwtUtil.getUsernameFromToken(upToken.getToken());
		if (userName == null)
			return null;
		SysUser user = userRepository.findByUserId(userName);

		if (user != null && jwtUtil.verifyToken(upToken.getToken()) == TokenStatus.VALID) {
			SimpleAccount account = new SimpleAccount(user, upToken.getToken(), getName());
			/*// TODO 根据id查询用户的角色 user.getUserId roleservice.queryRole(id);
			Set<String> roles = new HashSet<>();
			account.addRole(roles);*/

			return account;
		}

		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		SysUser user = (SysUser) principals.getPrimaryPrincipal();
		//  根据id查询用户的角色 user.getUserId roleservice.queryRole(id);
		List<SysRole> roleList = sysRoleService.findRoleByUserId(user.getId());
		Set<String> permissionSet = new HashSet<>();
		Set<String> roleNameSet = new HashSet<>();
		for (SysRole role : roleList) {
			roleNameSet.add(role.getEnname());
		}
		List<SysMenu> permissionList = sysMenuService.findPermissionsByUserId(user.getId());
		for (SysMenu menu : permissionList) {
			String permission = menu.getPermission();
			if (StringUtils.hasLength(permission)) {// 不为空
				permissionSet.add(permission);
			}

		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.addStringPermissions(permissionSet);
		info.addRoles(roleNameSet);
		 
		return info;
	}

}
