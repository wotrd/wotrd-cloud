package com.wotrd.dubboprovider.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.wotrd.dubboprovider.api.User;
import com.wotrd.dubboprovider.api.UserInfoService;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @ClassName: UserInfoServiceImpl
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:42
 */
@Component
@Service(version = "1.0.0", timeout = 10000, interfaceClass = UserInfoService.class)
public class UserInfoServiceImpl implements UserInfoService {

    @Override
    public User getUserInfo() {
        return User.builder().userName("name").password(UUID.randomUUID().toString().replaceAll("-", ""))
                .createTime(LocalDateTime.now()).build();
    }
}
