package com.wotrd.dubbo.common.config.dubbo;

import org.apache.dubbo.config.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * dubbo API config,手动配置
 * 用于配置daily环境和本地环境的组别、灰度和线上不进行设置
 */
//@Configuration
public class DubboConfiguration {

    @Value("${env}")
    private String env;
    @Value("${dubbo.group}")
    private String dubboGroup;

    @Value("${dubbo_zk_url}")
    private String defaultRegisterHost;
    @Value("${dubbo.server.port}")
    private Integer defaultRegisterPort;
    @Value("${dubbo.register.switch}")
    private boolean defaultZkRegister;
    @Value("${spring.application.name}")
    private String applicationName;

    @Value("${dubbo.local.port}")
    private Integer localPort;
    @Value("${dubbo.name}")
    private String dubboName;
    @Value("${dubbo.serialization}")
    private String dubboSerialization;

    //注册中心类型
    private final String REGISTER_TYPE = "zookeeper:";

    @Bean
    public ApplicationConfig applicationConfig() {
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName(applicationName);
        return applicationConfig;
    }

    @Bean
    public ProtocolConfig protocolConfig() {
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setSerialization(dubboSerialization);
        protocolConfig.setPort(localPort);
        protocolConfig.setName(dubboName);
        protocolConfig.setThreads(300);

        return protocolConfig;
    }

    @Bean
    public ConsumerConfig consumerConfig() {
        ConsumerConfig consumerConfig = new ConsumerConfig();
        if (isUserGroup()) {
            consumerConfig.setGroup(dubboGroup);
        }

        consumerConfig.setRetries(0);
        consumerConfig.setTimeout(3000);
        consumerConfig.setCheck(false);
        consumerConfig.setFilter("dubboLogFilter");

        return consumerConfig;
    }

    @Bean
    public ProviderConfig providerConfig() {
        ProviderConfig providerConfig = new ProviderConfig();
        if (isUserGroup()) {
            providerConfig.setGroup(dubboGroup);
        }
        //dubbo服务的发布服务，可以向多个注册中心发布注册
        providerConfig.setRegistries(this.getPubRegistry());
        providerConfig.setRetries(0);
        providerConfig.setTimeout(3000);
        return providerConfig;
    }

    /**
     * 是否使用组别配置、默认本地和开发环境开启
     * 灰度和线上不开启
     *
     * @return 是否使用
     */
    private boolean isUserGroup() {

        List<String> useDubboGroupEnvList = Arrays.asList("daily", "local", "dev_sh");

        return useDubboGroupEnvList.contains(env);
    }

    /**
     * 获取订阅注册中心地址
     */
    public RegistryConfig getSubRegistry() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(REGISTER_TYPE + "//" + defaultRegisterHost + ":" + defaultRegisterPort);
        registryConfig.setRegister(defaultZkRegister);
        return registryConfig;
    }

    @Bean
    public RegistryConfig registryConfig() {
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress(REGISTER_TYPE + "//" + defaultRegisterHost + ":" + defaultRegisterPort);
        registryConfig.setRegister(defaultZkRegister);
        return registryConfig;
    }

    /**
     * 获取发布的注册中心地址
     */
    public List<RegistryConfig> getPubRegistry() {
        List<RegistryConfig> registryConfigs = new ArrayList<>();
        //添加订阅注册中心地址
        registryConfigs.add(getSubRegistry());
        return registryConfigs;
    }
}
