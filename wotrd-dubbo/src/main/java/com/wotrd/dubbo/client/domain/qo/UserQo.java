package com.wotrd.dubbo.client.domain.qo;


import org.apache.dubbo.apidocs.annotations.RequestParam;


/**
 * @className UserReq
 * @description TODO
 * @Author wotrd
 * @date 2021/1/22 17:43
 */
public class UserQo {

    @RequestParam(value = "用户名字", required = true, description = "please enter your full name", example = "Zhang San")
    private Long userName;

}
