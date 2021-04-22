package com.wotrd.dubbo.common.designmode.reflect;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;
import java.util.Map;

/**
 * @description:
 * @Author: wotrd
 * @date: 2021/4/22 23:49
 */
@Configuration
public class AfterConfig implements ApplicationContextAware {

    Map<String, Factory> factoryMap;

    @PostConstruct
    public void init(){

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, Factory> factoryMap = applicationContext.getBeansOfType(Factory.class);
        factoryMap.forEach((name,factory)->{

        });

    }


}
