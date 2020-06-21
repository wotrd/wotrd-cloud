package com.wotrd.dubboprovider.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.wotrd.dubboprovider.api.User;
import com.wotrd.dubboprovider.api.UserInfoService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DubboController
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:44
 */
@RestController
public class DubboController {


    @Reference(version = "1.0.0", url = "dubbo://127.0.0.1:12345")
    private UserInfoService userInfoService;

    @RequestMapping("userInfo")
    public User userInfo(){
        return userInfoService.getUserInfo();
    }

}
