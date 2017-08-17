package com.example.test;

import com.example.bean.User;
import com.example.test.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2017/8/8.
 */
public class ApplicationTest {
    @Test
    public void testApplication() {
        ApplicationContext context = new ClassPathXmlApplicationContext("my-spring-config.xml");
        User user = (User)context.getBean("user");
        System.out.println(user);
        UserService userService = (UserService) context.getBean("userService");
        userService.saveUser();
    }
}
