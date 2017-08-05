package com.example.realm;

import com.example.bean.User;
import com.example.service.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MyShiroRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// TODO Auto-generated method stub
		String username = (String)principal.getPrimaryPrincipal();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		UserService userService = new UserService();
		User user = userService.getUser(username);
		List<String> roles = user.getRoles();
		List<String> permissions = user.getPermissions();
		Set<String> roleNames = new HashSet<String>();
		Set<String> permissionNames = new HashSet<String>();
		if(roles != null && permissions != null) {
			for(String role : roles) {
				roleNames.add(role);
			}
			for(String permission : permissions) {
				permissionNames.add(permission);
			}
		}
//		roleNames.add("hello");
//		permissionNames.add("damn");
		info.addRoles(roleNames);
		info.addStringPermissions(permissionNames);
		return info;
	}

	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken token) throws AuthenticationException {
		// TODO Auto-generated method stub
		String username = (String)token.getPrincipal();
		UserService userService = new UserService();
		User user = userService.getUser(username);
		if(user == null) {
			throw new UnknownAccountException("the account " + username + " is not exist!");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), getName());
		return info;
	}


}
