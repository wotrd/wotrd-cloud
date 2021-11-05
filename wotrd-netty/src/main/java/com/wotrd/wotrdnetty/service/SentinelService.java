package com.wotrd.wotrdnetty.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

/**
 * @description: TODO
 * @Author woniu
 * @date 2021/11/5 9:49 上午
 */
@Service
public class SentinelService {

    @SentinelResource(value = "sentinelService")
    public String sentinel(){
        return "sentinel";
    }
}
