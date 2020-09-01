# wotrd-cloud
基于nacos的spring cloud微服务框架、集成认证授权、动态网关、分库分表、dubbo开箱即用。  

个人博客地址：https://ailijie.top  
qq群：  602107221

```
1、 wotrd-auth：                 认证授权服务
2、 wotrd-client：               客户端服务
3、 wotrd-dubbo：                dubbo服务注册与发现（添加百度富文本ueditor）
4、 wotrd-dynamic-datasource：   动态数据源、基于xa使用atomikos实现分布式事物
5、 wotrd-feign：                nacos服务注册发现、redis、kafka、sentinel、elasticsearch
6、 wotrd-gateway：              动态网关服务
7、 wotrd-monitor：              监控服务
8、 wotrd-sharding：             分库分表中间件  
9、 wotrd-spring-security：      安全服务
```

# quick start  
1. git clone https://github.com/wotrd/wotrd-cloud.git
2. cd wotrd-cloud  
3. mvn clean package 

#版本说明
1、1.0.1-RELEASE 前后端不分离登录鉴权  
2、1.0.2-RELEASE 前后端分离登录鉴权

# 备注 
1、服务降级和断路：  
```使用sentinel设置限流和降级处理，需要单独部署sentinel-dashboard面板监控。```  
```使用feign和sentinel需要在配置文件中开启 feign.sentinel.enabled=true 不然fallback断路不生效```   
2、单点登录基于oauth2  
```oauth2是一种认证授权协议，分为授权服务器，资源服务器，用户，客户端。资源服务和授权服务器可以放在一起。```  
```资源服务器需要配置授权服务器的授权鉴权信息，用户通过客户端请求授权后，获取授权码，通过授权码请求token```    
```携带token请求资源接口，这个可以通过网关鉴权转发。```
```单点登录client需要配置 server.servlet.session.cookie.name=OAUTH2SESSION，不然会失败```  



