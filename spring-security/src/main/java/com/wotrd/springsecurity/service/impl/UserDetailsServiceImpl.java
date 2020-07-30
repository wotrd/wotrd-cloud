package com.wotrd.springsecurity.service.impl;

import com.wotrd.springsecurity.mapper.UserMapper;
import com.wotrd.springsecurity.model.UserDO;
import com.wotrd.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * @ClassName: UserDetailsService
 * @Description: 用户鉴权服务
 * @Author: wotrd
 * @Date: 2020/5/3 13:20
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService, UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userMapper.selectByUserName(username);
        if (null == userDO) {
            throw new BadCredentialsException("用户不存在");
        }
//        Collection<? extends GrantedAuthority> authorities = userDO.getAuthorities();

        return userDO;

    }
}
