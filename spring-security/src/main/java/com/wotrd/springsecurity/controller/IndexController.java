package com.wotrd.springsecurity.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;

/**
 * @ClassName: TestController
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/5/3 09:39
 */
@RestController
public class IndexController {

    @RequestMapping("index")
    public String index(){

        return "welcome index";
    }

    @RequestMapping("encodePwd")
    public String encodePwd(){
        User user = (User) User.withDefaultPasswordEncoder()
                .username("user")
                .password("password")
                .roles("user")
                .build();
        System.out.println(user.getPassword());
        return "success";
    }

    /**
     * 获取登陆用户
     *
     * @return
     */
    @RequestMapping("loginUser")
    public String loginUser(){

        SecurityContext context = SecurityContextHolder.getContext();
        Authentication authentication = context.getAuthentication();
        String name = authentication.getName();
        //密码
        Object credentials = authentication.getCredentials();
        //userDetails
        Object principal = authentication.getPrincipal();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        return "success";
    }


}
