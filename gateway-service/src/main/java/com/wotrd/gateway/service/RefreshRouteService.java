package com.wotrd.gateway.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

/**
 * @ClassName: RefreshRouteService
 * @Description: TODO
 * @Author: wotrd
 * @Date: 2020/7/1 15:44
 */
@Service
public class RefreshRouteService {

    @Autowired
    private ApplicationEventPublisher publisher;

    /**
     * 刷新路由
     */
    public void  refreshRoute(){
        publisher.publishEvent(new RefreshRoutesEvent(this));

    }


}
