package com.example.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import com.example.bean.User;
import com.example.service.UserService;

public class MyShiroRealm extends AuthorizingRealm{

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		// TODO Auto-generated method stub
		
		return null;
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
