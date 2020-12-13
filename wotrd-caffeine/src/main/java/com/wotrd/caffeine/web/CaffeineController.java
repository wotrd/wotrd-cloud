package com.wotrd.caffeine.web;

import com.wotrd.caffeine.domain.Item;
import com.wotrd.caffeine.service.CaffeineService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @className CaffeineController
 * @description TODO
 * @Author wotrd
 * @date 2020/12/13 4:09 下午
 */
@Slf4j
@RequestMapping("caffeine")
@RestController
public class CaffeineController {

    @Autowired
    private CaffeineService caffeineService;


    @RequestMapping("test")
    public String test() {
        return "hello, caffeine";
    }

    @GetMapping("{id}")
    public String query(@PathVariable("id") Long id) {
        log.info("get by id:{}", id);
        return caffeineService.getById(id);
    }

    @PostMapping
    public String add(@Validated @RequestBody Item caffeine) {
        log.info("add caffeine param:{}", caffeine);
        return caffeineService.add(caffeine);
    }

    @PutMapping
    public String update(@Validated @RequestBody Item caffeine) {
        log.info("update caffeine :{}", caffeine);
        return caffeineService.update(caffeine);
    }


    @DeleteMapping("{id}")
    public String delete(@PathVariable("id") Long id) {
        log.info("delete by id:{}", id);
        return caffeineService.deleteById(id);
    }
}
