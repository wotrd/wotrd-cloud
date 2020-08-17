package com.wotrd.dubboprovider.aop.web;

import com.example.springbootwebdemo.aop.annocation.UserAccess;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "AopController", tags = "{aop的控制类}", description = "springboot aop 的控制类")
@RequestMapping("aop")
@RestController
public class AopController {

    @ApiOperation(value = "index", notes = "主页", response = String.class)
    @ApiImplicitParams({
            @ApiImplicitParam(value = "name", type = "query", dataType = "String", required = true),
            @ApiImplicitParam(value = "age", type = "query", dataType = "int", required = true)
    })
    @PostMapping("/index")
    public String sayIndex(String name,int age){
        return "hello"+name+age;
    }

    @ApiOperation(value = "hello", notes = "打招呼", response = String.class)
    @GetMapping("/hello")
    public String sayHello(){
        return "hello";
    }

    @ApiOperation(value = "testAnnocation", notes = "注解测试", response = String.class)
    @UserAccess(desc = "ggg")
    @RequestMapping("/test")
    public String testAnnocation(){
        return "hello";
    }
}
