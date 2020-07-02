package com.wotrd.gateway.controller;

import com.wotrd.gateway.domain.Route;
import com.wotrd.gateway.service.RouteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName: RefreshController
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/6/27 20:37
 */
@RestController
public class RefreshController {

    @Autowired
    private RouteService routeService;

    @RequestMapping("refresh")
    public void refreshRoute() {
        routeService.refresh();
    }

    @GetMapping("route/list")
    public List<Route> routeList() {
        return routeService.routeList();
    }

    @GetMapping("route/delete")
    public void deleteRoute() {
        routeService.deleteRoute();
    }

}
