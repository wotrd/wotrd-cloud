package com.wotrd.dubbo.controller;

import com.wotrd.dubbo.api.Book;
import com.wotrd.dubbo.api.BookDTO;
import io.swagger.annotations.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;


/**
 * @ClassName: SwaggerController
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/20 20:44
 */
@Slf4j
@Api(tags = "swagger查询接口")
@RequestMapping("swagger")
@RestController
public class SwaggerController {

    @GetMapping("list")
    @ApiResponse(code = 500, message = "服务异常")
    @ApiOperation(value = "查询图书列表", response = BookDTO.class)
    public BookDTO list() {
        return null;
    }

    @GetMapping
    @ApiOperation(value = "查询图书信息", response = Book.class, notes = "主键ID不能为空")
    public Book book(@RequestParam Long id) {
        return null;
    }

    @ApiOperation(value = "添加图书信息")
    @PostMapping("add")
    public void add(@RequestBody Book book) {
        log.info("添加图书信息");
    }

    @ApiOperation(value = "修改图书信息")
    @PostMapping("update")
    public void update(@RequestBody Book book) {
        log.info("修改图书信息");
    }

    @ApiOperation(value = "删除图书信息")
    @DeleteMapping("delete")
    public void delete(@RequestParam Long id, @RequestParam(defaultValue = "hello", required = false) String userId) {
        log.info("删除图书信息");
    }

}
