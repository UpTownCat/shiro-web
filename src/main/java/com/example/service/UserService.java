package com.example.service;

import com.example.bean.User;

import java.util.*;

public class UserService {
//	private static Map<String, User> repository = new HashMap<String, User>();
	private static Map<String, User> repository;

	/**
	 * 初始化权限设置
	 */
	static {
		User user1 = new User("uptowncat", "123456", null, null);
		List<String> roles1 = new ArrayList<String>();
		roles1.add("admin");
		List<String> permissions = new ArrayList<String>();
		permissions.add("edit");
		user1.setPermissions(permissions);
		user1.setRoles(roles1);
		repository = new HashMap<String, User>();
		repository.put("uptowncat", user1);
	}
	
	public static User getUser(String username) {
		System.out.println("获取" + username + " 的用户！");
		return repository.get(username);
	}

	public static void addUser(User user) {
		repository.put(user.getUsername(), user);
	}

	public static Collection<User> getAll() {
		return repository.values();
	}

	public static void remove(String username) {
		repository.remove(username);
	}
}
