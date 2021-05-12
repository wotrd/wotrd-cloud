package com.wotrd.dubbo.web;

import com.wotrd.dubbo.client.domain.Result;
import com.wotrd.dubbo.client.api.UserInfoApi;
import com.wotrd.dubbo.client.domain.qo.UserQo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DubboController
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:44
 */
@Slf4j
@Api(tags = "dubbo查询接口测试")
@RestController
public class DubboController {

    @DubboReference(version = "1.0.1", group = "user_group", timeout = 10000)
    private UserInfoApi userInfoApi;

    @ApiOperation(value = "查询用户信息")
    @GetMapping("userInfo")
    public Result userInfo(UserQo userQo) {
        Long id = 1L;
        Result result = userInfoApi.getUserInfo(id, userQo);
        log.info("rpc返回{}", result);
        return result;
    }

    @ApiOperation(value = "测试慢接口")
    @GetMapping("testSlowApi")
    public Result testSlowApi() {
        System.out.println("start");
        try {
            Thread.sleep(1000000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return Result.buildSuccess();
    }


}
