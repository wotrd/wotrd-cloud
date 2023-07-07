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
10、wotrd-caffeine：             高性能进程间缓存caffeine
```

# quick start  
1. git clone https://github.com/wotrd/wotrd-cloud.git
2. cd wotrd-cloud  
3. mvn clean package 



# 注意事项  
1、版本问题：   
```版本不对可能出现问题,需要设置依赖管理dependencyManagement便于版本升级和降级```  
```请参照 https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/版本说明```   
2、配置中心：  
```在代码中使用配置中心需要加@RefreshScop注解刷新，数据库配置不需要，bootstrap文件为加载外部配置，优先级高，并且不被覆盖```  
3、服务降级和断路：  
```使用sentinel设置限流和降级处理，sentinel-dashboard添加规则配置到nacos和监控数据同步到influxdb```  
```使用feign和sentinel需要在配置文件中开启 feign.sentinel.enabled=true 不然fallback断路不生效```   
4、网关：  
```gateway是基于webflux实现的，不能加载mvc依赖。```
5、单点登录基于oauth2  
```oauth2是一种认证授权协议，分为授权服务器，资源服务器，用户，客户端。资源服务和授权服务器可以放在一起。```  
```资源服务器需要配置授权服务器的授权鉴权信息，用户通过客户端请求授权后，获取授权码，通过授权码请求token```    
```携带token请求资源接口，这个可以通过网关鉴权转发。```
```单点登录client需要配置 server.servlet.session.cookie.name=OAUTH2SESSION，不然会失败```  
6、elasticsearch
```
1、es6.8.4和springboot2.2.1版本，版本不一致会出现问题
2、使用bboss操作es先自己执行模版，可以按月创建。以后，创建索引自动使用该模版。不然会自动推断类型。查询根据别名匹配查询。
   添加新字段
   PUT /order_2020_07_v2/_mapping/doc
   {
      "properties": {
          "bizOrderStatus": {
            "type": "keyword",
            "doc_values": false,
            "norms": false //计算得分的时候，是否需要把字段长度用作参数
          }
      }
   }
   
   
```
![觉得作者辛苦请打赏](https://github.com/wotrd/wotrd-cloud/blob/master/doc/%E5%BE%AE%E4%BF%A1%E5%9B%BE%E7%89%87_20230707162108.jpg)

