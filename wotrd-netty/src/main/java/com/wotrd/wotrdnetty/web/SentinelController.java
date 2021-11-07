package com.wotrd.wotrdnetty.web;

import com.wotrd.wotrdnetty.service.SentinelService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description: TODO
 * @Author woniu
 * @date 2021/11/5 9:48 上午
 */
@RestController
public class SentinelController {

    @Resource
    private SentinelService sentinelService;

    @RequestMapping("sentinel")
    public String sentinel(){
        return sentinelService.sentinel();
    }
}
