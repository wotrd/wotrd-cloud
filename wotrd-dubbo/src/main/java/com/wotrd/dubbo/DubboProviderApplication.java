package com.wotrd.dubbo;

import org.apache.dubbo.apidocs.EnableDubboApiDocs;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Profile;


@Profile(value = "default")
@EnableDubboApiDocs
@SpringBootApplication
public class DubboProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubboProviderApplication.class, args);
    }




}
