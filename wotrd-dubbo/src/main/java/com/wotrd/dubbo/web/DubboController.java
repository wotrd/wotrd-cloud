package com.wotrd.dubbo.web;

import com.wotrd.dubbo.client.domain.Result;
import com.wotrd.dubbo.client.api.UserInfoApi;
import com.wotrd.dubbo.client.domain.qo.UserQo;
import com.wotrd.dubbo.common.config.redis.RedisDistLock;
import com.wotrd.dubbo.common.config.redis.RedisHelper;
import com.wotrd.dubbo.common.retry.MyRetryParam;
import com.wotrd.dubbo.common.retry.TaskManager;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

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

    @Reference(version = "1.0.1", group = "user_group", timeout = 10000)
    private UserInfoApi userInfoApi;

    @ApiOperation(value = "查询用户信息")
    @GetMapping("userInfo")
    public Result userInfo() {
        Long id = 1L;
        Result result = userInfoApi.getUserInfo(id, null);
//        log.info("rpc返回{}", result);
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

    @Resource
    private TaskManager taskManager;

    @ApiOperation(value = "测试慢接口")
    @GetMapping("retryTest")
    public Result retryTest() {
        System.out.println("retryTest start");
        taskManager.addRetry(new MyRetryParam());
        return Result.buildSuccess();
    }

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private RedisHelper redisHelper;
    @Autowired
    private RedisDistLock redisDistLock;


    @ApiOperation(value = "测试redis接口")
    @GetMapping("redisTest")
    public Result redisTest() {
        System.out.println("redis test start");
        redisTemplate.opsForValue().set("name", "tom");
        Object name = redisTemplate.opsForValue().get("name");
        redisHelper.setStringObject("key", "hah");
        String key = redisHelper.getStringObject("key");
        boolean lockKey = redisDistLock.tryLock("lock_key", 30, 100);
        boolean lockKey1 = redisDistLock.tryLock("lock_key", 30, 100);
        redisDistLock.unlock("lock_key");
        return Result.buildSuccess();
    }

    @ApiOperation(value = "测试redis接口")
    @GetMapping("lockTest")
    public Result lockTest(String key) {
        boolean locked = redisDistLock.tryLock(key, 0, 100, TimeUnit.MINUTES);
        if (!locked){
            throw new RuntimeException("获取锁失败");
        }
        redisDistLock.unlock(key);
        return Result.buildSuccess();
    }

}
