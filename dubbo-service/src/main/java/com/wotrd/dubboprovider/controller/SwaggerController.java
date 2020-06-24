package com.wotrd.dubboprovider.controller;

import com.wotrd.dubboprovider.api.Book;
import com.wotrd.dubboprovider.api.BookDTO;
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
    @ApiImplicitParam(name = "主键ID", value = "主键ID")
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
    public void delete(@ApiParam(value = "主键ID", required = true) Long id,
                       @ApiParam(value = "用户ID") String userId) {
        log.info("删除图书信息");
    }

}
