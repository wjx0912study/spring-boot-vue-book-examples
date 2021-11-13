package com.alan.hrsys.controller;

import com.alan.hrsys.entity.SysUser;
import com.alan.hrsys.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @Autowired
    UserDetailsServiceImpl userDetailsServiceImpl;
    @RequestMapping("index")
    public String index() {
        return "index";
    }

}
