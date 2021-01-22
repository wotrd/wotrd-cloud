package com.wotrd.dubbo.service;

import com.wotrd.dubbo.client.domain.RpcResult;
import com.wotrd.dubbo.client.domain.dto.UserDto;
import com.wotrd.dubbo.client.api.UserInfoApi;
import com.wotrd.dubbo.client.domain.qo.UserQo;
import org.apache.dubbo.apidocs.annotations.ApiDoc;
import org.apache.dubbo.apidocs.annotations.ApiModule;
import org.apache.dubbo.apidocs.annotations.RequestParam;
import org.apache.dubbo.config.annotation.DubboService;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * @ClassName: UserInfoApiImpl
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:42
 */
@ApiModule(value = "UserInfoApi", apiInterface = UserInfoApi.class, version = "v0.1")
@DubboService(version = "1.0.1", group = "user_group", timeout = 10000, retries = 3, owner = "wotrd", actives = 3, loadbalance = "roundrobin")
public class UserInfoApiImpl implements UserInfoApi {

    @ApiDoc(value = "getUserInfo", version = "v0.1", description = "getUserInfo", responseClassDescription="UserDTO")
    @Override
    public RpcResult<UserDto> getUserInfo(@RequestParam(value = "id", required = true) Long id, UserQo userReq) {
        return RpcResult.buildSuccess(UserDto.builder().userName("name").password(UUID.randomUUID().toString().replaceAll("-", ""))
                .createTime(LocalDateTime.now()).build());
    }

}
