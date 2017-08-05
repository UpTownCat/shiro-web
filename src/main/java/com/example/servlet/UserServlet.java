package com.example.servlet;

import com.example.bean.User;
import com.example.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserServlet {

	@RequestMapping("/login")
	public String loginUser(String username, String password) {
		UsernamePasswordToken token = new UsernamePasswordToken(username, password);
		token.setRememberMe(true);
		Subject subject = SecurityUtils.getSubject();
		try {
			subject.login(token);
		}catch (Exception e) {
			return "redirect:/login-fail.jsp";
		}
		return "user_detail";
	}

	@RequestMapping("/list")
	public String listUser(Map<String, Collection<User>> map) {
		Collection<User> users = UserService.getAll();
		map.put("users", users);
		return "user_list";
	}

	@RequestMapping("/add")
	public String addUser(User user) {
		UserService.addUser(user);
		User user2 = UserService.getUser(user.getUsername());
		return "redirect:/user/list";
	}

	@RequestMapping("/delete")
	public String deleteUser(String username) {
		UserService.remove(username);
		return "redirect:/user/list";
	}

}
