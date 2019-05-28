package com.wotrd.ssoserver.config;

import org.springframework.cloud.security.oauth2.sso.OAuth2SsoConfigurerAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

@Configuration
public class OauthConfig extends OAuth2SsoConfigurerAdapter {
    @Override
    public void match(RequestMatchers matchers) {
        matchers.antMatchers("/dashboard/**");
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().anyRequest().authenticated();
    }

}
