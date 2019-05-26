# nacos-test
基于nacos的微服务框架  


``1注册中心 nacos``  
``2配置中心 nacos``  
``3服务提供者 nacos``  
``4服务消费者 feign``   
``5断路器 sential``  
``6网关 zuul``  

# quick start  
1. git clone https://github.com/wotrd/nacos-test.git  
2. cd nacos-test  
3. mvn clean package  

#注意事项  
1版本问题，版本不对可能出现问题  
```需要设置依赖管理dependencyManagement便于版本升级和降级```  
```请参照 https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/版本说明```  
2注册中心和配置中心加密  
3用户账户修改    
4服务降级和断路  
5服务监控  
6用户登录基于oauth2


# git分支  
1master分支，主干分支  
2release分支，发布分支  
3dev分支，开发分支  
4future新特性分支

