package com.wotrd.authserverjdbc.service;

import com.wotrd.authserverjdbc.domain.TbUser;
import com.wotrd.authserverjdbc.mapper.TbUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TbUserService {

    @Autowired
    private TbUserMapper userMapper;

    public TbUser getUserByUsername(String username){
        return userMapper.getByUsername(username);
    }
}
