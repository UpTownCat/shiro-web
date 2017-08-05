package com.example.servlet;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/8/5.
 */
@Controller
@RequestMapping("/student")
public class StudentServlet {
    @RequestMapping("/list")
    public String listStudent() {
        return "student_list";
    }
}
