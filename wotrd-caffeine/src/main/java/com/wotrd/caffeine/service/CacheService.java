package com.wotrd.caffeine.service;

import com.alibaba.fastjson.JSON;
import com.github.benmanes.caffeine.cache.AsyncLoadingCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.stats.CacheStats;
import com.wotrd.caffeine.domain.CacheDO;
import com.wotrd.caffeine.domain.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * @className CacheService
 * @description 值回收 基于大小，基于时间和参考
 * 1、基于大小回收
 * 这种回收方式假定当超过配置的缓存大小限制时会发生回收。 获取大小有两种方法：缓存中计数对象，或获取权重。
 * cache.estimatedSize()
 * 调用了 cleanUp 方法。 这是因为缓存回收被异步执行，这种方法有助于等待回收的完成。 maximumSize(1) maximumWeight(10)
 * 2、基于时间回收
 * 这种回收策略是基于条目的到期时间，有三种类型：
 * 访问后到期 — 从上次读或写发生后，条目即过期。 expireAfterAccess
 * 写入后到期 — 从上次写入发生之后，条目即过期   expireAfterWrite
 * 自定义策略 — 到期时间由 Expiry 实现独自计算 实现Expiry接口
 * 3、基于引用回收
 * 我们可以将缓存配置为启用缓存键值的垃圾回收。为此，我们将 key 和 value 配置为 弱引用，并且我们可以仅配置软引用以进行垃圾回收。
 * 当没有任何对对象的强引用时，使用 WeakRefence 可以启用对象的垃圾收回收。SoftReference 允许对象根据 JVM 的全局最近最少使用（Least-Recently-Used）的策略进行垃圾回收
 * 我们应该使用 Caffeine.weakKeys()、Caffeine.weakValues() 和 Caffeine.softValues() 来启用每个选项：
 * LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
 * .expireAfterWrite(10, TimeUnit.SECONDS)
 * .weakKeys()
 * .weakValues()
 * .build(k -> DataObject.get("Data for " + k));
 * <p>
 * cache = Caffeine.newBuilder()
 * .expireAfterWrite(10, TimeUnit.SECONDS)
 * .softValues()
 * .build(k -> DataObject.get("Data for " + k));
 * 5、刷新
 * 可以将缓存配置为在定义的时间段后自动刷新条目。让我们看看如何使用 refreshAfterWrite 方法：
 * Caffeine.newBuilder()
 * .refreshAfterWrite(1, TimeUnit.MINUTES)
 * .build(k -> DataObject.get("Data for " + k));
 * 这里我们应该要明白 expireAfter 和 refreshAfter 之间的区别。 当请求过期条目时，执行将发生阻塞，直到 build Function 计算出新值为止。
 * 但是，如果条目可以刷新，则缓存将返回一个旧值，并异步重新加载该值。
 * <p>
 * 6、统计
 * Caffeine 有一种记录缓存使用情况的统计方式：
 * <p>
 * LoadingCache<String, DataObject> cache = Caffeine.newBuilder()
 * .maximumSize(100)
 * .recordStats()
 * .build(k -> DataObject.get("Data for " + k));
 * cache.get("A");
 * cache.get("A");
 * <p>
 * assertEquals(1, cache.stats().hitCount());
 * assertEquals(1, cache.stats().missCount());
 * 我们也可能会传入 recordStats supplier，创建一个 StatsCounter 的实现。每次与统计相关的更改将推送此对象。
 * @Author wotrd
 * @date 2020/12/13 4:12 下午
 */
@Slf4j
@Service
public class CacheService {

    /**
     * 手动创建缓存
     */
    private static final Cache<Long, CacheDO> cache = Caffeine.newBuilder()
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .maximumSize(100)
            .build();
    /**
     * 同步加载,这种加载缓存的方法使用了与用于初始化值的 Function 相似的手动策略的 get 方法
     */
    LoadingCache<Long, CacheDO> syncCache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .build(k -> new CacheDO());
    /**
     * 策略的作用与之前相同，但是以异步方式执行操作，并返回一个包含值的 CompletableFuture
     */
    AsyncLoadingCache<Long, Item> asyncCache = Caffeine.newBuilder()
            .maximumSize(100)
            .expireAfterWrite(1, TimeUnit.MINUTES)
            .buildAsync(k -> new Item());


    /**
     * 根据ID查询
     *
     * @param id
     * @return
     */
    public String getById(Long id) {
        CacheDO cacheDO = cache.getIfPresent(id);
        log.info("get id:{}, result:{}", id, cacheDO);
        //get 方法获取值，该方法将一个参数为 key 的 Function 作为参数传入。如果缓存中不存在该键，则该函数将用于提供回退值，该值在计算后插入缓存中
        //get 方法可以原子方式执行计算。这意味着您只进行一次计算 — 即使多个线程同时请求该值。这就是为什么使用 get 优于 getIfPresent。
        cacheDO = cache.get(id, k -> {
            //从一级缓存获取
            CacheDO cache = new CacheDO();
            cache.setId(100L);
            cache.setName("tom");
            return cache;
        });

        //异步查询
//        asyncCache.get(id).thenAccept(cache2 -> {
//            log.info(JSON.toJSONString(cache2));
//        });
//
//        //批量查询
//        asyncCache.getAll(Arrays.asList(1L, 2L, 3L))
//                .thenAccept(map ->
//                        log.info(JSON.toJSONString(map))
//                );
        return null;
    }

    /**
     * 添加
     *
     * @param cacheDO
     * @return
     */
    public String add(CacheDO cacheDO) {
        cache.put(cacheDO.getId(), cacheDO);
        return null;
    }

    /**
     * 修改
     *
     * @param cacheDO
     * @return
     */
    public String update(CacheDO cacheDO) {
        ConcurrentMap<Long, CacheDO> stringItemConcurrentMap = cache.asMap();
        cache.cleanUp();
        long l = cache.estimatedSize();
        CacheStats stats = cache.stats();
        return null;
    }

    /**
     * 删除
     *
     * @param id
     * @return
     */
    public String deleteById(Long id) {
        cache.invalidate(id);
        return null;
    }

    /**
     * 删除
     *
     * @return
     */
    public String deleteAll() {
        cache.invalidateAll();
        return null;
    }


}
