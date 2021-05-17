package com.wotrd.dubbo.web;

import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
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


    @RequestMapping("health")
    public String health() {
        //创建es客户端工具，验证环境
        ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
        //验证环境,获取es状态
        return clientUtil.executeHttp("_cluster/state?pretty", ClientInterface.HTTP_GET);
    }

    @RequestMapping("check")
    public String check() {
        //创建es客户端工具，验证环境
        ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
        boolean b = clientUtil.existIndice("");
        clientUtil.countAll("");

        return null;
    }


}
