package com.wotrd.feign.factory;


import com.wotrd.feign.config.GlobalResponse;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: FeignServiceFactory
 * @Description: 请求断路工厂
 * @Author: wotrd
 * @Date: 2020/6/10 14:47
 */
@Slf4j
public class FeignServiceFactory implements FallbackFactory<GlobalResponse> {

    @Override
    public GlobalResponse create(Throwable cause) {
        log.error("request feign error, msg:{}", cause.getMessage());
        return GlobalResponse.builder().code("201").msg("failed").build();
    }
}
