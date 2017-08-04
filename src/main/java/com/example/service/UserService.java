package com.example.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.bean.User;

public class UserService {
	private static Map<String, User> repository;
	
	{
		User user1 = new User("uptowncat", "123456", null, null);
		List<String> roles1 = new ArrayList<String>();
		roles1.add("human");
		roles1.add("admin");
		List<String> permissions = new ArrayList<String>();
		permissions.add("edit");
		user1.setPermissions(permissions);
		user1.setRoles(roles1);
		repository.put("uptowncat", user1);
	}
	
	public User getUser(String username) {
		return repository.get(username);
	}
}
