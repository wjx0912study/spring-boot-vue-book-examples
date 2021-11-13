package com.alan.hrsys.controller;

import com.alan.hrsys.entity.Employee;
import com.alan.hrsys.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
public class TestController {
    @Autowired
    EmployeeService employeeService;

    @RequestMapping("test")
    public ModelAndView test(HttpSession session) {
        ModelAndView mv = new ModelAndView("test");
        mv.addObject("msg", "<h1>Hello World</h1>");
        Employee emp = employeeService.searchById(1);
        mv.addObject("emp", emp);
        List<Employee> list = employeeService.search(null);
        mv.addObject("list", list);
        session.setAttribute("role","管理员");

        mv.addObject("className", "active");
        mv.addObject("time", new Date());
        return mv;
    }
}
