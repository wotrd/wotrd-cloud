package com.wotrd.ssoserver.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class IndexController {

    @RequestMapping("/")
    public String home() {
        return "<html><body><a href='dashboard/'>dashboard</a><br/><a href='login'>login</a></body></html>";
    }

    @RequestMapping("/dashboard")
    public String dashboard() {
        return "<html><body>Dashboard Yay!</body></html>";
    }

}
