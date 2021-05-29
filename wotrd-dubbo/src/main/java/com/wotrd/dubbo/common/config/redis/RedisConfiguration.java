package com.wotrd.dubbo.common.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;


/**
 * redis config
 *
 * @author:wotrd
 * @date 2019-12-03 14:37
 */
@Configuration
public class RedisConfiguration {

    @Value("${spring.redis.host}")
    private String redisHost;
    @Value("${spring.redis.port}")
    private Integer redisPort;
    @Value("${spring.redis.password}")
    private String redisPassword;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * redis
     */
    @Bean(name = "wotrdRedisHelper")
    public RedisHelper popupRedisHelper() {
        RedisHelper redisHelper = new RedisHelper();
        redisHelper.setRedisTemplate(redisTemplate);
        return redisHelper;
    }

    /**
     * redis库锁
     *
     * @return
     */
    @Bean(name = "redisDistLock")
    RedisDistLock redisDistLock() {
        RedisDistLock redisDistLock = new RedisDistLock();
        redisDistLock.setRedisHost(redisHost);
        redisDistLock.setRedisPort(redisPort + "");
        redisDistLock.setRedisPassWord(redisPassword);
        redisDistLock.init();
        return redisDistLock;
    }
}
