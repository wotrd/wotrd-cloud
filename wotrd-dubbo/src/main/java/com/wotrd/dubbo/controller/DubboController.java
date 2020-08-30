package com.wotrd.dubbo.controller;

import com.wotrd.dubbo.api.User;
import com.wotrd.dubbo.api.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DubboController
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:44
 */
@Slf4j
@Api(tags = "dubbo查询接口测试")
@RestController
public class DubboController {

    @DubboReference(version = "1.0.1", group = "user_group", timeout = 10000)
    private UserInfoService userInfoService;

    @ApiOperation(value = "查询用户信息")
    @GetMapping("userInfo")
    public User userInfo(Long id) {
        User userInfo = userInfoService.getUserInfo();
        log.info("rpc返回{}", userInfo);
        return userInfo;
    }


}
