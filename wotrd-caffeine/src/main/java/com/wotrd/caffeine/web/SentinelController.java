package com.wotrd.caffeine.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/11/4 23:27
 */
@RestController
public class SentinelController {


    @RequestMapping("sentinel")
    public String webStatus(){
        return "success";
    }

}
