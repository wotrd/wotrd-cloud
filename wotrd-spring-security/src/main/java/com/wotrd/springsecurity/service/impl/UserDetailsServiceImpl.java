package com.wotrd.springsecurity.service.impl;

import com.wotrd.springsecurity.mapper.UserMapper;
import com.wotrd.springsecurity.mapper.UserRoleMapper;
import com.wotrd.springsecurity.model.UserDO;
import com.wotrd.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * @ClassName: UserDetailsService
 * @Description: 用户鉴权服务
 * @Author: wotrd
 * @Date: 2020/5/3 13:20
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDO userDO = userMapper.selectByUserName(username);
        if (null == userDO) {
            throw new UsernameNotFoundException("用户不存在");
        }
        Collection<? extends GrantedAuthority> authorities = userDO.getAuthorities();
        //查询角色
        List grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_AMIN , system:user:delete");
        userDO.getAuthorities().addAll(grantedAuthorities);
        //sysUser.setAccountNonLocked(true或false);
        return userDO;

    }
}
