package com.wotrd.caffeine.web;

import com.wotrd.caffeine.domain.CacheDO;
import com.wotrd.caffeine.service.CacheService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @className CacheController
 * @description TODO
 * @Author wotrd
 * @date 2020/12/13 4:09 下午
 */
@Slf4j
@RequestMapping("cache")
@RestController
public class CacheController {

    @Autowired
    private CacheService cacheService;

    @GetMapping("{id}")
    public CacheDO query(@PathVariable("id") Long id) {
        log.info("get id:{}", id);
        return cacheService.getById(id);
    }

    @PostMapping
    public CacheDO add(@Validated @RequestBody CacheDO cacheDO) {
        log.info("add param:{}", cacheDO);
        return cacheService.add(cacheDO);
    }

    @PutMapping
    public CacheDO update(@Validated @RequestBody CacheDO cacheDO) {
        log.info("update param:{}", cacheDO);
        return cacheService.update(cacheDO);
    }

    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        log.info("delete id:{}", id);
        return cacheService.deleteById(id);
    }
}
