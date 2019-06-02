package com.wotrd.authservermemory.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;

@Configuration
//开启认证服务
@EnableAuthorizationServer
public class AuthConfig extends AuthorizationServerConfigurerAdapter {


    /**
     * 配置密码加密，springboot2.x需要配置
     *
     * @return
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("client")   //客户端 client_id
                .secret(passwordEncoder().encode("secret")) //客户端 secret
                .authorizedGrantTypes("authorization_code")  //授权类型，授权码
                .scopes("app")   //范围
                .redirectUris("http://localhost:9005/login") //重定向地址
                .and()
                .withClient("client1")   //客户端 client_id
                .secret(passwordEncoder().encode("secret")) //客户端 secret
                .authorizedGrantTypes("authorization_code")  //授权类型，授权码
                .scopes("app")   //范围
                .redirectUris("http://localhost:9006/login"); //重定向地址

    }

}
