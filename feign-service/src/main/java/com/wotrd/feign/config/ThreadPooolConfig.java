package com.wotrd.feign.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.task.AsyncTaskExecutor;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @ClassName: ThreadPooolConfig
 * @Description: Spring线程池配置
 * @Date: 2020/5/2 17:49
 * @Author: wotrd
 */
@EnableAsync
@Configuration
public class ThreadPooolConfig {

    @Bean
    @Primary
    public AsyncTaskExecutor asyncServiceExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        //核心线程数量：低于此值会重新创建
        executor.setCorePoolSize(5);
        //任务队列：核心线程处于工作中，会先放到队列中缓存
        executor.setQueueCapacity(1000);
        //线程池最大数量：任务队列已满时，最多创建的线程数量
        executor.setMaxPoolSize(100);
        //线程超时时间：超出核心线程数量的线程，在空闲一定的时间后退出
        executor.setKeepAliveSeconds(30);
        //线程池已满，队列已满时。新的任务处理策略：丢弃、执行、忽略、
        //AbortPolicy:丢弃
        //DiscardPolicy:忽略
        //CallerRunsPolicy:立即运行
        //DiscardOldestPolicy:压进队列最后一位（踢出第一位）
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        return executor;
    }
}
