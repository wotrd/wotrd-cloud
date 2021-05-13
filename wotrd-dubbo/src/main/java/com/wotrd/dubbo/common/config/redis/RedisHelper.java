package com.wotrd.dubbo.common.config.redis;


import com.alibaba.fastjson.JSONObject;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.CollectionUtils;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

public class RedisHelper {

    private RedisTemplate<Serializable, Serializable> redisTemplate;

    //=============================== 基础

    /**
     * 写入redis
     *
     * @param key
     * @param value
     */
    public void setStringObject(String key, String value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 写入redis，设置过期时间，单位秒
     *
     * @param key
     * @param value
     * @param time
     */
    public void setStringObjectSecond(String key, String value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 去读redis
     *
     * @param key
     * @return
     */
    public String getStringObject(String key) {

        Object result = redisTemplate.opsForValue().get(key);

        return result == null ? null : result.toString();
    }

    /**
     * 批量读redis
     *
     * @param keys
     * @return
     */
    public List<String> getStringObjects(List keys) {

        List<Object> result = redisTemplate.opsForValue().multiGet(keys);
        if (result != null) {
            List<String> stringResult = new ArrayList<>();
            for (Object object : result) {
                if (object != null) {
                    stringResult.add(object.toString());
                } else {
                    stringResult.add(null);
                }
            }
            return stringResult;
        }
        return null;
    }

    /**
     * 判断是否存在
     *
     * @param key
     * @return
     */
    public boolean exists(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除
     *
     * @param key key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 批量删除
     *
     * @param keys
     */
    public void remove(final String... keys) {
        for (String key : keys) {
            remove(key);
        }
    }


    //==================================  list

    /**
     * redis设置list，过期时间单位为秒，fastjson转化
     *
     * @param key
     * @param listObject
     * @param <T>
     * @return
     */
    public <T> void setListObjectByFastjson(String key, List<T> listObject, Long timeout) {

        if (CollectionUtils.isEmpty(listObject)) {
            return;
        }

        String[] strings = new String[listObject.size()];

        for (int i = 0; i < listObject.size(); i++) {
            strings[i] = JSONObject.toJSONString(listObject.get(i));
        }

        redisTemplate.opsForList().rightPushAll(key, strings);
        redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
    }

    /**
     * redis读取list，fastjson转化
     *
     * @param key
     * @param tClass
     * @param <T>
     * @return
     */
    public <T> List<T> getListObjectByFastJson(String key, Class<T> tClass, long start, long end) {

        List<Serializable> list = redisTemplate.opsForList().range(key, start, end);

        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<T> result = new ArrayList<>();
        for (Serializable s : list) {
            result.add(JSONObject.parseObject((String) s, tClass));
        }

        return result;
    }

    public <T> List<T> getListObjectByFastJson(String key, Class<T> tClass) {
        return getListObjectByFastJson(key, tClass, 0, -1);
    }

    /**
     * 获取list长度
     *
     * @param key key
     * @return 长度
     */
    public Long getListSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    public RedisTemplate<Serializable, Serializable> getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate<Serializable, Serializable> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void expire(String key, long i) {
        redisTemplate.expire(key, i, TimeUnit.SECONDS);
    }

    /**
     * 添加List
     *
     * @param key
     * @param Object
     */
    public void addListObjects(String key, String Object) {

        redisTemplate.opsForList().leftPush(key, Object);
    }

    public void addRightListObjects(String key, String Object) {

        redisTemplate.opsForList().rightPush(key, Object);
    }


    /**
     * 将 key 中储存的数字值增一。
     * <p>
     * 如果 key 不存在，那么 key 的值会先被初始化为 0 ，然后再执行 INCR 操作。
     *
     * @param key
     * @return
     */
    public Integer incr(final String key) {
        ValueOperations<Serializable, Serializable> valueOper
                = redisTemplate.opsForValue();
        return valueOper.increment(key, 1L).intValue();
    }

    /**
     * 将 key 中储存的数字值增加。
     * 如果 key 不存在，那么 key 的值会先被初始化为 num ，然后再执行 INCR 操作。
     *
     * @param key
     * @param num
     * @return
     */
    public Integer incrBy(final String key, final Long num) {
        ValueOperations<Serializable, Serializable> valueOper
                = redisTemplate.opsForValue();
        return valueOper.increment(key, num).intValue();
    }

    //==================================================  zset

    /**
     * 获取Score指定范围内的Set集合,其中有序集成员按分数值递减(从大到小)顺序排列 分页获取
     *
     * @param key
     * @param minScore
     * @param maxScore
     * @param offset
     * @param count
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Serializable>> reverseRangeByScore(String key, double minScore, double maxScore, long offset, long count) {
        return redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, minScore, maxScore, offset, count);
    }

    /**
     * 获取Score指定范围内的Set集合,其中有序集成员按分数值递减(从大到小)顺序排列 分页获取
     *
     * @param key
     * @param minScore
     * @param maxScore
     * @param offset
     * @param count
     * @return
     */
    public Set<ZSetOperations.TypedTuple<Serializable>> reverseRangeByScoreAndRemove(String key, double minScore, double maxScore, long offset, long count) {
        Set<ZSetOperations.TypedTuple<Serializable>> set = redisTemplate.opsForZSet().reverseRangeByScoreWithScores(key, minScore, maxScore, offset, count);
        if (CollectionUtils.isEmpty(set)) {
            return new HashSet<>();
        }

        List<Serializable> list = set.stream().map(t -> t.getValue()).collect(Collectors.toList());
        redisTemplate.opsForZSet().remove(key, list.toArray());

        return set;
    }


    /**
     * zset添加元素
     *
     * @param key
     * @param set
     */
    public void addZset(String key, Set<ZSetOperations.TypedTuple<Serializable>> set) {
        redisTemplate.opsForZSet().add(key, set);
    }


    /**
     * zset添加元素,并设置过期时间
     *
     * @param key
     * @param set
     * @param expireTime
     */
    public void addZset(String key, Set<ZSetOperations.TypedTuple<Serializable>> set, Long expireTime) {
        redisTemplate.opsForZSet().add(key, set);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * zset移除区间范围内的元素
     *
     * @param key
     * @param maxScore
     * @param minScore
     */
    public void removeZset(String key, double maxScore, double minScore) {
        redisTemplate.opsForZSet().removeRangeByScore(key, maxScore, minScore);
    }

    /**
     * zset指定元素
     *
     * @param key
     * @param objects
     */
    public void removeZset(String key, Object[] objects) {
        redisTemplate.opsForZSet().remove(key, objects);
    }


    /**
     * zset指定位置元素
     *
     * @param key
     * @param start
     * @param end
     */
    public void removeZset(String key, long start, long end) {
        redisTemplate.opsForZSet().removeRange(key, start, end);
    }


    /**
     * 获取长度
     *
     * @param key
     * @return
     */
    public Long getSetSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    //====================================== set

    public Long sAdd(final String key, String... value) {
        try {
            SetOperations<Serializable, Serializable> setOperations = redisTemplate.opsForSet();
            return setOperations.add(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public Long sRemove(final String key, String... value) {
        try {
            SetOperations<Serializable, Serializable> setOperations = redisTemplate.opsForSet();
            return setOperations.remove(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0L;
    }

    public Boolean sContain(final String key, String value) {
        try {
            SetOperations<Serializable, Serializable> setOperations = redisTemplate.opsForSet();
            return setOperations.isMember(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public Set<Object> sMembers(final String key) {
        try {
            SetOperations setOperations = redisTemplate.opsForSet();
            return setOperations.members(key);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new HashSet<>();
    }


}
