package com.wotrd.feign.config;

import com.xxl.job.core.executor.impl.XxlJobSpringExecutor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: XxlConfig
 * @Description: xxl-job配置
 * @Author: wotrd
 * @Date: 2020/5/2 17:55
 */
@Configuration
public class XxlConfig {

    @ConfigurationProperties(prefix="xxl.job.executor")
    @Bean(initMethod = "start", destroyMethod = "destroy")
    public XxlJobSpringExecutor xxlJobExecutor() {
        XxlJobSpringExecutor xxlJobSpringExecutor = new XxlJobSpringExecutor();
        return xxlJobSpringExecutor;
    }
}
