# nacos-test
基于nacos的微服务框架  

```
1、feign-service：nacos服务注册发现、redis、kafka、sentinel、elasticsearch
2、dubbo-service：dubbo服务注册与发现
3、gateway-service：网关服务
4、sharding-jdbc：分库分表中间件  
5、dynamic-datasource：动态数据源、基于xa使用atomikos实现分布式事物
6、security：安全服务、认证、授权
```

# quick start  
1. git clone https://github.com/wotrd/nacos-test.git  
2. cd nacos-test  
3. mvn clean package  

# 注意事项  
1、版本问题：   
```版本不对可能出现问题,需要设置依赖管理dependencyManagement便于版本升级和降级```  
```请参照 https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/版本说明``` 
2、配置中心：  
```在代码中使用配置中心需要加@RefreshScop注解刷新，数据库配置不需要，bootstrap文件为加载外不配置，优先级高，并且不被覆盖```  
3、服务降级和断路：  
```使用sentinel设置限流和降级处理，需要单独部署sentinel-dashboard面板监控。```  
```使用feign和sentinel需要在配置文件中开启 feign.sentinel.enabled=true 不然fallback断路不生效```   
4、网关：  
```gateway是基于webflux实现的，不能加载mvc依赖。```     
5、用户账户修改：  
```参考 https://nacos.io/en-us/docs/console-guide.html 用户登录管理```    
6、服务监控  
```参考 https://nacos.io/zh-cn/docs/monitor-guide.html```  
7、单点登录基于oauth2  
```oauth2是一种认证授权协议，分为授权服务器，资源服务器，用户，客户端。资源服务和授权服务器可以放在一起。```  
```资源服务器需要配置授权服务器的授权鉴权信息，用户通过客户端请求授权后，获取授权码，通过授权码请求token```    
```携带token请求资源接口，这个可以通过网关鉴权转发。```
```单点登录client需要配置 server.servlet.session.cookie.name=OAUTH2SESSION，不然会失败```
8、elasticsearch6.8.4和springboot2.2.1版本，版本不一致会出现问题


