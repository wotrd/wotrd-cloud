package com.wotrd.auth.service;

import com.wotrd.auth.model.entity.UserDO;
import com.wotrd.auth.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public UserDO getUserByUsername(String username){
        return userMapper.getByUsername(username);
    }
}
