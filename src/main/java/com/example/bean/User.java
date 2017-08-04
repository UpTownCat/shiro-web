package com.example.bean;

import java.util.List;

public class User {
	private String username;
	private String password;
	private List<String> roles;
	private List<String> permissions;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}

	public User(String username, String password, List<String> roles,
			List<String> permissions) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
		this.permissions = permissions;
	}

	public User() {
		super();
	}

}
