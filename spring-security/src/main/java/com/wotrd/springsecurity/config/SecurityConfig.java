package com.wotrd.springsecurity.config;

import com.wotrd.springsecurity.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collection;

/**
 * @ClassName: SecuriyConfig
 * @Description: security全局配置类
 * @Author: wotrd
 * @Date: 2020/5/2 23:57
 */
//@Configuration
//@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    /**
     * 配置密码编码类
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 配置认证
     *
     * @param auth
     * @throws Exception
     */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    /**
     * 配置http拦截
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/index")
                .permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .loginProcessingUrl("/form/login")
                .permitAll()
                .defaultSuccessUrl("https://baidu.com")
                .and()
                .logout()
                .permitAll()
                .and()
                .authorizeRequests()
                .and()
                .csrf().disable()
                .authenticationProvider(authenticationProvider());

    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        return new AuthenticationProvider() {
            @Override
            public Authentication authenticate(Authentication authentication) throws AuthenticationException {
                boolean authenticated = authentication.isAuthenticated();
                Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                Object credentials = authentication.getCredentials();
                Object details = authentication.getDetails();
                Object principal = authentication.getPrincipal();
                return authentication;
            }

            /**
             * 支持认证
             *
             * @param authentication
             * @return
             */
            @Override
            public boolean supports(Class<?> authentication) {
                return true;
            }
        };

    }


}
