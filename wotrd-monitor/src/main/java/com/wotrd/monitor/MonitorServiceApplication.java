package com.wotrd.monitor;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName: MonitorServiceApplication
 * @Description: 监控服务
 * @Author: wotrd
 * @Date: 2020/08/31 23:20
 */
@EnableAdminServer
@SpringBootApplication
public class MonitorServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorServiceApplication.class, args);
    }

}
