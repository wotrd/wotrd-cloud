package com.wotrd.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @ClassName: LoginController
 * @Description: 登陆页
 * @Author: wotrd
 * @Date: 2020/5/3 18:15
 */
@Controller
public class LoginController {

    @GetMapping("/login")
    String login() {
        return "login";
    }

}
