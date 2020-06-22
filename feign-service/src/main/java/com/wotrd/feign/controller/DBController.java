package com.wotrd.feign.controller;

import com.wotrd.feign.config.GlobalResponse;
import com.wotrd.feign.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: DBController
 * @Description: 数据库操作
 * @Author: wotrd
 * @Date: 2020/6/10 15:00
 */
@RequestMapping("db")
@RestController
public class DBController {

    @Autowired
    private BookService bookService;

    @RequestMapping("list")
    public GlobalResponse list(){
        return GlobalResponse.builder().code("200").msg("success").data(bookService.list()).build();
    }
}
