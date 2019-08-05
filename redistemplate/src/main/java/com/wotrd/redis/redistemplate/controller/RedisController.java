package com.wotrd.redis.redistemplate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.core.types.RedisClientInfo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequestMapping("redis")
@RestController
public class RedisController {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @PostMapping("/valueOperations")
    public String valueOperations() {

        ValueOperations<String, String> valueOperations = stringRedisTemplate.opsForValue();
        valueOperations.set("strRedis", "StringRedisTemplate");
        valueOperations.set("count", "1");
        valueOperations.set("count1", "1");
        return valueOperations.get("strRedis");
    }


    @PostMapping("/addCount")
    public String addCount() {

//        Long count = stringRedisTemplate.boundValueOps("count").increment(1);//val +1
        Long count = stringRedisTemplate.opsForValue().increment("count", 1);//val +1

        Long time = stringRedisTemplate.getExpire("count");//根据key获取过期时间

        return "count=" + count + "time=" + time;

    }

    @PostMapping("/listOperations")
    public String listRestTemplate() {

        //将数据添加到key对应的现有数据的左边
        Long redisList = stringRedisTemplate.opsForList().leftPush("redisList", "3");
        stringRedisTemplate.opsForList().leftPush("redisList", "4");
        //将数据添加到key对应的现有数据的右边
        Long size = stringRedisTemplate.opsForList().size("redisList");
        //从左往右遍历
        String leftPop = stringRedisTemplate.opsForList().leftPop("redisList");
        //从右往左遍历
        String rightPop = stringRedisTemplate.opsForList().rightPop("redisList");
        //查询全部元素
        List<String> range = stringRedisTemplate.opsForList().range("redisList", 0, -1);
        //查询前三个元素
        List<String> range1 = stringRedisTemplate.opsForList().range("redisList", 0, 3);
        //从左往右删除list中元素A  (1:从左往右 -1:从右往左 0:删除全部)
        Long remove = stringRedisTemplate.opsForList().remove("key", 1, "A");

        log.info("redisList----" + redisList);
        log.info("size----" + size);
        log.info("leftPop----" + leftPop);
        log.info("rightPop----" + rightPop);
        log.info("range----" + range);
        log.info("range1----" + range1);
        log.info("remove----" + remove);
        return "";
    }

    @PostMapping("/hashOperations")
    public void hashOperations() {

        //判断key对应的map中是否存在hash
        Boolean aBoolean = stringRedisTemplate.opsForHash().hasKey("hash", "hash1");
        //往key对应的map中新增(key1,value1)
        stringRedisTemplate.opsForHash().put("hash", "hash1", "value1");
        //获取key对应的map中hash1的值
        Object o = stringRedisTemplate.opsForHash().get("hash", "hash1");
        //删除key对应的map中多个子hash(可变参数)
        Long delete = stringRedisTemplate.opsForHash().delete("hash", "key1", "key2", "key3");
        //获取hash对应的map
        Map<Object, Object> hash = stringRedisTemplate.opsForHash().entries("hash");
        //获取hash对应的map中全部子hash集合
        Set<Object> hash1 = stringRedisTemplate.opsForHash().keys("hash");
        //获取hash对应的map中全部value集合
        List<Object> hash2 = stringRedisTemplate.opsForHash().values("hash");

    }

    @PostMapping("/deleteOperations")
    public String valueDelResitTest() {
        stringRedisTemplate.delete("key");
        return "";
    }


    @PostMapping("/setOperations")
    public void setOperations() {

        SetOperations<String, String> set = stringRedisTemplate.opsForSet();
        set.add("set1", "22");
        set.add("set1", "33");
        set.add("set1", "44");
        Set<String> resultSet = stringRedisTemplate.opsForSet().members("set1");

        stringRedisTemplate.opsForSet().add("set2", "1", "2", "3");//向指定key中存放set集合

        Set<String> resultSet1 = stringRedisTemplate.opsForSet().members("set2");

        log.info("resultSet:" + resultSet);
        log.info("resultSet1:" + resultSet1);

    }


    @RequestMapping("/redisLock")
    public String redisLock() {

        String lockKey = "key";
        String lockValue = lockKey + System.currentTimeMillis();
        // value需要记住用于解锁
        while (true) {

            Boolean ifPresent = stringRedisTemplate.opsForValue().
                    setIfAbsent("redis-lock:" + lockKey, lockValue, 3, TimeUnit.SECONDS);

            if (ifPresent) {
                log.info("get redis-lock success");
                break;
            }
        }

        return "";
    }

    @RequestMapping("/releaseLock")
    public boolean releaseLock() {

        String lockKey = "key";
        String lockValue = lockKey + System.currentTimeMillis();
        boolean result = false;
        // value需要记住用于解锁
        stringRedisTemplate.watch("redis-lock:" + lockKey);
        String value = stringRedisTemplate.opsForValue().get("redis-lock:" + lockKey);
        if (null == value){
            result = true;
        }else if (value.equals(lockValue)) {
            stringRedisTemplate.delete("redis-lock:" + lockKey);
            result = true;
        }
        stringRedisTemplate.unwatch();

        return result;
    }

    @RequestMapping("/transaction")
    public String transaction() {

//        SessionCallback sessionCallback = new SessionCallback() {
//            @Override
//            public Object execute(RedisOperations redisOperations) throws DataAccessException {
//                redisOperations.multi();
//                redisOperations.opsForValue().increment("count",1);
//                redisOperations.opsForValue().increment("count1",1);
//                return redisOperations.exec();
//            }
//        };
//
//        stringRedisTemplate.execute(sessionCallback);

        stringRedisTemplate.setEnableTransactionSupport(true);
        try {
            stringRedisTemplate.multi();//开启事务
            stringRedisTemplate.opsForValue().increment("count", 1);
            stringRedisTemplate.opsForValue().increment("count1", 2);
            //提交
            stringRedisTemplate.exec();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            //开启回滚
            stringRedisTemplate.discard();
        }

        return "";
    }

    @PostMapping("/getClientInfo")
    public String getClientInfo() {

        //获取连接redis数据库信息
        List<RedisClientInfo> clientList = stringRedisTemplate.getClientList();
        for (RedisClientInfo clientInfo : clientList) {
            System.out.printf("--" + clientInfo.toString() + "events=" + clientInfo.getEvents() + "\n"
                    + "port=" + clientInfo.getAddressPort() + "name=" + clientInfo.getName() + clientInfo.getFileDescriptor() +
                    clientInfo.getFlags() + clientInfo.getLastCommand() + clientInfo.getAge() + clientInfo.getBufferLength() +
                    clientInfo.getDatabaseId());
        }
        return "test";

    }
}
