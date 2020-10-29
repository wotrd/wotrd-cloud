package com.wotrd.auth;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName: LoginPageController
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/9/4 00:18
 */
@RequestMapping("auth")
@Controller
public class LoginPageController {

    @RequestMapping("page")
    public String loginPage(){
        return "auth-page";
    }
}
