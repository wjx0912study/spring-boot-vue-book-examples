package com.alan.hrsys.controller;

import com.alan.hrsys.util.ServerResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("/login")
    public ServerResponse loginPage() {
        ServerResponse sr = new ServerResponse();
        return sr;
    }
}
