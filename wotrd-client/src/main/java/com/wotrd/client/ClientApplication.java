package com.wotrd.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;

/**
 * @ClassName: ClientApplication
 * @Description: 资源客户端服务
 * @Author: wotrd
 * @Date: 2020/08/31 23:20
 */
@EnableOAuth2Sso
@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
