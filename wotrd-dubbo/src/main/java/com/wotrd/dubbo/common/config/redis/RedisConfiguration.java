package com.wotrd.dubbo.common.config.redis;

import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;


import java.io.Serializable;

/**
 * redis config
 *
 * @author:wotrd
 * @date 2019-12-03 14:37
 */
@Configuration
public class RedisConfiguration {

    @Value("${wotrd.redis.maxTotal}")
    private Integer redisMaxTotal;
    @Value("${wotrd.redis.maxIdle}")
    private Integer redisMaxIdle;
    @Value("${wotrd.redis.host}")
    private String redisHost;
    @Value("${wotrd.redis.port}")
    private Integer redisPort;
    @Value("${wotrd.redis.password}")
    private String redisPassword;
    @Value("${wotrd.redis.index}")
    private Integer redisIndex;

    /**
     * redis库
     *
     * @return 连接工厂对象
     */
    @Bean(name = "invoiceConnectionFactory")
    @Primary
    public JedisConnectionFactory invoiceConnectionFactory() {
        JedisConnectionFactory connectionFactory = new JedisConnectionFactory();
        RedisStandaloneConfiguration standaloneConfiguration = connectionFactory.getStandaloneConfiguration();
        GenericObjectPoolConfig poolConfig = connectionFactory.getPoolConfig();
        standaloneConfiguration.setHostName(redisHost);
        standaloneConfiguration.setPassword(redisPassword);
        standaloneConfiguration.setPort(redisPort);
        standaloneConfiguration.setDatabase(redisIndex);
        poolConfig.setMaxTotal(redisMaxTotal);
        poolConfig.setMaxIdle(redisMaxIdle);
        //获取可用连接的最大等待时间
        poolConfig.setMaxWaitMillis(1000L);
        return connectionFactory;
    }


    /**
     * redis库
     */
    @Bean(name = "redisTemplate")
    @Primary
    public RedisTemplate<Serializable, Serializable> popupRedisTemplate(@Qualifier("invoiceConnectionFactory") JedisConnectionFactory invoiceConnectionFactory) {
        RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(invoiceConnectionFactory);
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    /**
     * redis
     */
    @Bean(name = "wotrdRedisHelper")
    public RedisHelper popupRedisHelper(RedisTemplate<Serializable, Serializable> redisTemplate) {
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
