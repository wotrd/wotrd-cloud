package com.wotrd.dubbo.common.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;

import java.util.concurrent.TimeUnit;

/**
 * @author Leo.yy   Created on 2018/7/5.
 */
public class RedisDistLock {


    private String redisHost;
    private String redisPort;
    private String redisPassWord;
    private RedissonClient redissonClient;

    public void init() {

        Config config = new Config();
        config.useSingleServer().setAddress("redis://" + redisHost + ":" + redisPort).setPassword(redisPassWord);
        redissonClient = Redisson.create(config);

    }

    /**
     * @param key        key
     * @param waitTime   等待时间单位毫秒
     * @param expireTime 锁自动失效时间 单位毫秒
     * @return 是否锁住
     */
    public boolean tryLock(String key, long waitTime, long expireTime) {
        RLock lock = redissonClient.getLock(key);
        try {
            return lock.tryLock(waitTime, expireTime, TimeUnit.MILLISECONDS);

        } catch (InterruptedException e) {
            throw new RuntimeException("try Get Distributed Lock fail");
        }

    }

    public void unlock(String key) {
        RLock lock = redissonClient.getLock(key);
        lock.unlock();
    }

    public void setRedisHost(String redisHost) {
        this.redisHost = redisHost;
    }

    public void setRedisPort(String redisPort) {
        this.redisPort = redisPort;
    }

    public void setRedisPassWord(String redisPassWord) {
        this.redisPassWord = redisPassWord;
    }

    public RedissonClient getRedissonClient() {
        return redissonClient;
    }
}
