package com.example.controller;

import com.example.service.DsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wotrd
 */
@RestController
public class DsController {

    @Autowired
    private DsService dsService;

    @RequestMapping("get")
    public String get(Long id){
        dsService.get(id);
        return "success";
    }


    @RequestMapping("update")
    public String ds1(Long id, Long count){
        dsService.update(count, id);
        return "success";
    }


}
