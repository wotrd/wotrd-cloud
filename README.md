# nacos-test
基于nacos的微服务框架  


``1注册中心 nacos``  
``2配置中心 nacos``  
``3服务提供者 nacos``  
``4服务消费者 feign``   
``5断路器 sentinel``  
``6网关 gateway``  

# quick start  
1. git clone https://github.com/wotrd/nacos-test.git  
2. cd nacos-test  
3. mvn clean package  

# 注意事项  
1版本问题：   
```版本不对可能出现问题,需要设置依赖管理dependencyManagement便于版本升级和降级```  
```请参照 https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/版本说明```  
2注册中心：  
``建议配置mysql数据库，不然服务重启后，配置信息都会消失``    
3配置中心：  
```在代码中使用配置中心需要加@RefreshScop注解刷新，数据库配置不需要，bootstrap文件为加载外不配置，优先级高，并且不被覆盖```  
4服务降级和断路：  
```使用sentinel设置限流和降级处理，需要单独部署sentinel-dashboard面板监控。```  
```使用feign和sentinel需要在配置文件中开启 feign.sentinel.enabled=true 不然fallback断路不生效```   
5网关：  
```gateway是基于webflux实现的，不能加载mvc依赖。```   
6用户账户修改：  
```参考 https://nacos.io/en-us/docs/console-guide.html 用户登录管理```    
7服务监控  
```参考 https://nacos.io/zh-cn/docs/monitor-guide.html```  
8单点登录基于oauth2


# git分支  
1master分支，主干分支  
2release分支，发布分支  
3dev分支，开发分支  
4future新特性分支


