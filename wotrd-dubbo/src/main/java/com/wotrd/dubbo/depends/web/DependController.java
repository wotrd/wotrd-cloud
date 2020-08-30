package com.wotrd.dubbo.depends.web;

import com.wotrd.dubbo.depends.service.IDependService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class DependController {

    @Autowired
    private Map<String, IDependService> dependServiceMap;

    @RequestMapping("dependTest")
    public String dependTest(){
        System.out.println("--------------");
        IDependService iDependService = dependServiceMap.get("001DependService");
        iDependService.depend();
        IDependService iDependService1 = dependServiceMap.get("002DependService");
        iDependService.depend();
        return "";
    }
}
