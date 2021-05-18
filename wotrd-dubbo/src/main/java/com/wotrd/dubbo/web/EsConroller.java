package com.wotrd.dubbo.web;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.boot.BBossESStarter;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/16 19:00
 */
@RestController
@RequestMapping("es")
public class EsConroller {

    @Autowired
    private BBossESStarter bbossESStarter;

    @RequestMapping("health")
    public String health() {
        //创建es客户端工具，验证环境
        ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
        //验证环境,获取es状态
        return clientUtil.executeHttp("_cluster/state?pretty", ClientInterface.HTTP_GET);
    }

    @RequestMapping("check")
    public String check() {
        //Create a client tool to load configuration files, single instance multithreaded security
//        ClientInterface configClientUtil = bbossESStarter.getConfigRestClient(mappath);
        //Build a create/modify/get/delete document client object, single instance multi-thread security
        ClientInterface clientUtil = bbossESStarter.getRestClient();
        //创建es客户端工具，验证环境
//        ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
//        boolean b = clientUtil.existIndice("test1");
//        long test = clientUtil.countAll("test1");

        return null;
    }


}
