package com.example.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/8/5.
 */
@Controller
@RequestMapping("/teacher")
public class TeacherServlet {
    @RequestMapping("/list")
    public String listTeacher() {
        return "teacher_list";
    }
}
