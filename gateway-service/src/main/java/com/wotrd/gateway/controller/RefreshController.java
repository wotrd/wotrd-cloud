package com.wotrd.gateway.controller;

import com.wotrd.gateway.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName: RefreshController
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/27 20:37
 */
@RestController
public class RefreshController {

    @Autowired
    private EventService eventService;

    @RequestMapping("refresh")
    public void refresh(){
        eventService.publish();
    }
}
