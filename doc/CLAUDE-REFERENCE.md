# wotrd-cloud Claude 参考手册

本文件存放 `CLAUDE.md` 中迁出的查表类内容：通用基类、命名约定、自定义注解和常用工具路径。

快速返回：[../CLAUDE.md](../CLAUDE.md)

---

## 通用基类

项目目前未抽象出跨模块通用基类。各模块独立维护自身的配置类与工具类。

## 命名约定

| 后缀/前缀 | 含义 | 示例 |
|---|---|---|
| `*Application` | Spring Boot 启动类 | `GatewayApplication`、`AuthApplication` |
| `*Config` | 配置类 | `AuthServerConfig`、`ShardingDsConfig` |
| `*Controller` | Web 控制器 | `SentinelController`、`SocketChannelController` |
| `*Service` | 业务服务 | `SentinelService` |
| `*Mapper` | MyBatis Mapper | `RouteMapper`、`RouteFilterMapper` |
| `wotrd-*` | Maven 模块名 | `wotrd-gateway`、`wotrd-dubbo` |

## 自定义注解 / 启用注解

| 注解 | 来源 / 位置 | 用途 |
|---|---|---|
| `@EnableAdminServer` | Spring Boot Admin | 在 `wotrd-monitor` 启用监控服务端 |
| `@EnableAuthorizationServer` | Spring Security OAuth2 | 在 `wotrd-auth` 启用 OAuth2 授权服务器 |
| `@EnableDubboApiDocs` | Dubbo API Docs | 在 `wotrd-dubbo` 启用 Dubbo 接口文档 |
| `@RefreshScope` | Spring Cloud | 声明需要 Nacos 配置中心动态刷新的 Bean |
| `@Profile("default")` | Spring | `wotrd-dubbo` 启动类限制默认 profile |

## 常用工具路径

| 用途 | 路径 |
|---|---|
| Gateway 路由 SQL | `wotrd-gateway/src/main/resources/sql/gateway_route.sql` |
| OAuth2 schema SQL | `wotrd-auth/src/main/resources/sql/oauth2.sql` |
| Dubbo 日志配置 | `wotrd-dubbo/src/main/resources/logback-config.xml` |
| Nacos 配置导出 | `nacos_config.sql`（项目根目录） |

## 模块主类路径

| 模块 | 主类路径 |
|---|---|
| `wotrd-auth` | `wotrd-auth/src/main/java/com/wotrd/auth/AuthApplication.java` |
| `wotrd-client` | `wotrd-client/src/main/java/com/wotrd/client/ClientApplication.java` |
| `wotrd-dubbo` | `wotrd-dubbo/src/main/java/com/wotrd/dubbo/DubboProviderApplication.java` |
| `wotrd-dynamic-datasource` | `wotrd-dynamic-datasource/src/main/java/com/wotrd/dydatasource/Application.java` |
| `wotrd-feign` | `wotrd-feign/src/main/java/com/wotrd/feign/Application.java` |
| `wotrd-gateway` | `wotrd-gateway/src/main/java/com/wotrd/gateway/GatewayApplication.java` |
| `wotrd-monitor` | `wotrd-monitor/src/main/java/com/wotrd/monitor/MonitorServiceApplication.java` |
| `wotrd-sharding` | `wotrd-sharding/src/main/java/com/wotrd/sharding/Application.java` |
| `wotrd-caffeine` | `wotrd-caffeine/src/main/java/com/wotrd/caffeine/CaffeineApplication.java` |

## 配置文件位置

| 模块 | 主要配置文件 |
|---|---|
| `wotrd-auth` | `wotrd-auth/src/main/resources/application.properties` |
| `wotrd-client` | `wotrd-client/src/main/resources/application.properties` |
| `wotrd-dubbo` | `wotrd-dubbo/src/main/resources/application.properties` |
| `wotrd-dynamic-datasource` | `wotrd-dynamic-datasource/src/main/resources/application.properties` |
| `wotrd-feign` | `wotrd-feign/src/main/resources/application.properties` + `bootstrap.properties` |
| `wotrd-gateway` | `wotrd-gateway/src/main/resources/application.properties` + `application.yml` + `bootstrap.properties` |
| `wotrd-monitor` | `wotrd-monitor/src/main/resources/application.properties` |
| `wotrd-sharding` | `wotrd-sharding/src/main/resources/application.properties` |
| `wotrd-caffeine` | `wotrd-caffeine/src/main/resources/application.properties` |
| `wotrd-netty` | `wotrd-netty/src/main/resources/application.properties` |
