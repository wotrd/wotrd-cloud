package com.wotrd.caffeine.service;

import com.wotrd.caffeine.domain.CacheDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @className BootCacheService
 * @description TODO
 * @Author wotrd
 * @date 2020/12/14 21:22
 */
@Slf4j
@Service
@CacheConfig(cacheNames = "caffeineCacheManager")
public class BootCacheService {

    /**
     * 模拟db存储数据
     */
    private Map<Long, CacheDO> dbMap = new HashMap<>();

    /**
     * 查询缓存
     *
     * @param id
     */
    @Cacheable(key = "#id")
    public CacheDO query(Long id) {
        log.info("query id:{}", id);
        return dbMap.get(id);
    }

    /**
     * 添加缓存
     *
     * @param cacheDO
     */
    @CachePut(key = "#cacheDO.id")
    public CacheDO add(CacheDO cacheDO) {
        log.info("add:{}", cacheDO);
        dbMap.put(cacheDO.getId(), cacheDO);
        return cacheDO;
    }

    /**
     * 修改缓存
     *
     * @param cacheDO
     */
    @CachePut(key = "#cacheDO.id")
    public CacheDO update(CacheDO cacheDO) {
        log.info("update:{}", cacheDO);
        dbMap.put(cacheDO.getId(), cacheDO);
        return cacheDO;
    }

    /**
     * 删除缓存
     *
     * @param id
     */
    @CacheEvict(key = "#id")
    public void delete(Long id) {
        dbMap.remove(id);
        log.info("remove:{}", id);
    }


}
