package com.wotrd.dubbo.service;

import com.wotrd.dubbo.api.User;
import com.wotrd.dubbo.api.UserInfoService;
import org.apache.dubbo.config.annotation.DubboService;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @ClassName: UserInfoServiceImpl
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:42
 */
@DubboService(version = "1.0.1", group = "user_group", timeout = 10000, retries = 3, owner = "wotrd", actives = 3, loadbalance = "roundrobin")
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public User getUserInfo() {
        return User.builder().userName("name").password(UUID.randomUUID().toString().replaceAll("-", ""))
                .createTime(LocalDateTime.now()).build();
    }
}
