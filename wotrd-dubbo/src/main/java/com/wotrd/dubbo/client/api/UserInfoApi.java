package com.wotrd.dubbo.client.api;

import com.wotrd.dubbo.client.domain.Result;
import com.wotrd.dubbo.client.domain.dto.UserDto;
import com.wotrd.dubbo.client.domain.qo.UserQo;

/**
 * @ClassName: api
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:40
 */
public interface UserInfoApi {

    /**
     * 获取用户信息
     *
     * @return
     */
    Result<UserDto> getUserInfo(Long id, UserQo userQo);
}
