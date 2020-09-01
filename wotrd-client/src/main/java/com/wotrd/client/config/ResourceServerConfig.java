package com.wotrd.client.config;

import com.alibaba.fastjson.JSONObject;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

//@Configuration
//@EnableResourceServer
//@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    /**
     * security的鉴权排除的url列表
     */
    private static final String[] EXCLUDED_AUTH_PAGES = {
            "/actuator/**", "/oauth/**", "/css/**", "/js/**", "/images/**", "/webjars/**",
            "**/favicon.ico", "/index", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js"
    };

    /**
     * 配置资源服务器,限制路径
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers(EXCLUDED_AUTH_PAGES)
                .permitAll()
                .anyRequest()
                .authenticated();

    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) {
        resources.authenticationEntryPoint((HttpServletRequest httpServletRequest,
                                            HttpServletResponse httpServletResponse,
                                            AuthenticationException e) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 401);
            map.put("msg", "非法访问资源,访问此资源需要完全身份验证");
            map.put("path", httpServletRequest.getServletPath());
            map.put("timestamp", System.currentTimeMillis());
            httpServletResponse.setContentType("application/json");
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.writeValue(httpServletResponse.getOutputStream(), map);
            } catch (Exception e1) {
                throw new ServletException();
            }
        }).accessDeniedHandler((HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                AccessDeniedException e) -> {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 401);
            map.put("msg", "抱歉,没有权限,请联系管理员李浩东");
            map.put("path", httpServletRequest.getServletPath());
            map.put("timestamp", System.nanoTime());
            httpServletRequest.setCharacterEncoding("UTF-8");
            httpServletResponse.setContentType(MediaType.APPLICATION_JSON.toString());
            httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            httpServletResponse.getWriter().write(JSONObject.toJSONString(map));
        });
    }

}
