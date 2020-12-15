package com.wotrd.caffeine.web;

import com.wotrd.caffeine.domain.CacheDO;
import com.wotrd.caffeine.service.BootCacheService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @className BootCacheController
 * @description TODO
 * @Author wotrd
 * @date 2020/12/14 21:21
 */
@RequestMapping("boot/cache")
@RestController
public class BootCacheController {

    @Autowired
    private BootCacheService cacheService;

    /**
     * 查询缓存
     *
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public CacheDO cacheDO(@PathVariable("id") Long id) {
        return cacheService.query(id);
    }

    /**
     * 添加缓存
     *
     * @param cacheDO
     */
    @PostMapping
    public void add(@RequestBody CacheDO cacheDO) {
        CacheDO add = cacheService.add(cacheDO);
    }

    /**
     * 修改缓存
     *
     * @param cacheDO
     */
    @PutMapping
    public void update(@RequestBody CacheDO cacheDO) {
        CacheDO add = cacheService.update(cacheDO);
    }

    /**
     * 删除缓存
     *
     * @param id
     */
    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Long id) {
        cacheService.delete(id);
    }
}
