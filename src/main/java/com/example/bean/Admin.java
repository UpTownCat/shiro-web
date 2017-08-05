package com.example.bean;

import org.apache.shiro.spring.LifecycleBeanPostProcessor;

import java.util.List;

/**
 * Created by Administrator on 2017/8/5.
 */
public class Admin {
    private String username;
    private String password;
    private List<String> roles;
    private List<String> permissions;

    public Admin(String username, String password, List<String> roles, List<String> permissions) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.permissions = permissions;
    }

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
}
