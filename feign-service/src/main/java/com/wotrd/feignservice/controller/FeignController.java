package com.wotrd.feignservice.controller;

import com.wotrd.feignservice.service.IHelloNacos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FeignController {


    @Autowired
    private IHelloNacos helloNacos;

    @RequestMapping("sayNacos")
    public String sayHello(){
        String result = helloNacos.helloNacos();
        return "Return : " + result;
    }

    @RequestMapping("echo/{word}")
    public String echo(@PathVariable("word") String word){
        String result = helloNacos.echo(word);
        return "Return : " + result;
    }

}
