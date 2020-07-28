package com.wotrd.authserver.service;

import com.wotrd.authserver.domain.TbUser;
import com.wotrd.authserver.mapper.TbUserMapper;
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
