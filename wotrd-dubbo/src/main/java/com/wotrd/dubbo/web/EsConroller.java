package com.wotrd.dubbo.web;

import com.wotrd.dubbo.manager.EsManager;
import org.frameworkset.elasticsearch.ElasticSearchHelper;
import org.frameworkset.elasticsearch.client.ClientInterface;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/5/16 19:00
 */
@RestController
@RequestMapping("es")
public class EsConroller {

    @Resource
    private EsManager esManager;

    @RequestMapping("health")
    public String health() {
        //创建es客户端工具，验证环境
        ClientInterface clientUtil = ElasticSearchHelper.getRestClientUtil();
        //验证环境,获取es状态
        return clientUtil.executeHttp("_cluster/state?pretty", ClientInterface.HTTP_GET);
    }

    @RequestMapping("check")
    public String check() {
        esManager.addAndUpdateDocument();
        esManager.deleteDocuments();
        esManager.searchAllPararrel();
        esManager.search();
        esManager.testDirectDslQuery();
        return null;
    }




}
