package com.wotrd.dydatasource.controller;

import com.wotrd.dydatasource.four.mapper.TUserMapper;
import com.wotrd.dydatasource.service.DsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wotrd
 */
@RestController
public class DsController {

    @Autowired
    private DsService dsService;

    @Autowired
    private TUserMapper userMapper;

    @GetMapping("transfer")
    public String transfer(){
        dsService.transfer();
//        TUser tUser = userMapper.selectByOrgIdAndUserName("yxt_05700081", "傅佳翎");


        return "success";
    }


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
