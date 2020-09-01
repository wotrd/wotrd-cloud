package com.wotrd.auth.service;

import com.wotrd.auth.model.entity.PermissionDO;
import com.wotrd.auth.mapper.PermissionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;

    public List<PermissionDO> getByUserid(Long userId) {

        return permissionMapper.queryByUserid(userId);

    }
}
