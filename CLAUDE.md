# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working in this repository.

快速定位：
- 最近改动 / 新增接口 / 关键坑点 → [doc/CLAUDE-RECENT.md](./doc/CLAUDE-RECENT.md)
- 提交 / 打包 / 部署 → [doc/CLAUDE-DEPLOY.md](./doc/CLAUDE-DEPLOY.md)
- 开发规范 / Git / CR / 安全红线 → [doc/CLAUDE-RULE.md](./doc/CLAUDE-RULE.md)
- 自定义注解 / 通用基类 / 命名约定 / 工具路径 → [doc/CLAUDE-REFERENCE.md](./doc/CLAUDE-REFERENCE.md)

## Project Overview

`wotrd-cloud` 是一个基于 Spring Cloud Alibaba 的中文微服务演示项目，Maven 多模块结构，共 10 个可运行的 Spring Boot 模块（另有 `wotrd-ai` 独立维护）。项目集成 Nacos、OAuth2、Spring Cloud Gateway、Dubbo、Sentinel、Sharding-JDBC，以及 MySQL、Redis、Kafka、Elasticsearch 等数据存储。

## Local Development

常用构建命令：

```bash
# 构建全部
mvn clean package

# 构建指定模块
mvn clean package -pl wotrd-gateway -am

# 运行指定模块
mvn spring-boot:run -pl wotrd-gateway

# 运行模块测试
mvn test -pl wotrd-gateway

# 运行单个测试类
mvn test -pl wotrd-gateway -Dtest=GatewayApplicationTests
```

完整流程见 [doc/CLAUDE-DEPLOY.md](./doc/CLAUDE-DEPLOY.md)。

## Module Architecture

| 模块 | 用途 | 关键说明 |
|---|---|---|
| `wotrd-gateway` | Spring Cloud Gateway | WebFlux，禁止引入 MVC；动态路由从 MySQL 加载 |
| `wotrd-auth` | OAuth2 授权服务器 | JDBC 存储 token/client/code，schema 见 `oauth2.sql` |
| `wotrd-client` | OAuth2 SSO 客户端 | 必须设置 `server.servlet.session.cookie.name=OAUTH2_SESSION` |
| `wotrd-feign` | Feign / Nacos / Redis / Kafka / ES / XXL-Job | 开启 `feign.sentinel.enabled=true` 才支持 fallback |
| `wotrd-dubbo` | Dubbo RPC 提供者 | Dubbo 2.7.7 + COLA + bboss ES 6.2.9 + Redisson |
| `wotrd-monitor` | Spring Boot Admin 服务端 | 通过 Nacos 自动发现服务 |
| `wotrd-sharding` | 分库分表演示 | Sharding-JDBC 4.0.0-RC1，Druid 连接池 |
| `wotrd-dynamic-datasource` | 动态数据源 + 分布式事务 | 四数据源 + Atomikos JTA |
| `wotrd-caffeine` | Caffeine 本地缓存 | Sentinel 规则从 Nacos 加载 |
| `wotrd-netty` | Sentinel / Netty 相关 | Spring Boot 2.5.4，无传统 `*Application` 启动类 |
| `wotrd-ai` | Spring AI | **独立模块**：Java 17 + Spring Boot 4.1.0-RC1，不纳入父 POM |

## Technology Stack

- **Framework**：Spring Boot 2.2.8.RELEASE（`wotrd-ai` 使用 4.1.0-RC1）
- **Cloud**：Spring Cloud Hoxton + Spring Cloud Alibaba 2.2.1
- **Service Discovery / Config**：Nacos
- **RPC**：Dubbo 2.7.7
- **Gateway**：Spring Cloud Gateway（WebFlux）
- **Auth**：Spring Security OAuth2
- **ORM**：MyBatis + PageHelper
- **MQ**：Kafka
- **Cache**：Redis / Caffeine
- **Search**：Elasticsearch 6.8.4（bboss）
- **Sharding**：Sharding-JDBC 4.0.0-RC1
- **Distributed TX**：Atomikos JTA
- **Job**：XXL-Job
- **Test**：JUnit 4（`wotrd-dubbo` 使用 JUnit 5）

## Key Configuration Patterns

- **Nacos 配置中心**：需要外部配置的模块提供 `bootstrap.properties`/`bootstrap.yml`，优先级高于本地 `application` 文件且不被覆盖。
- **@RefreshScope**：读取 Nacos 配置中心值的 Bean 必须加此注解；数据库连接配置不需要。
- **Sentinel**：各模块单独配置 Dashboard 地址；流控规则可推送 Nacos 持久化。
- **版本对齐**：主体使用 Spring Boot 2.2.x + Spring Cloud Hoxton + Spring Cloud Alibaba 2.2.1；升级参考 [Spring Cloud Alibaba 版本说明](https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/版本说明)。

## External Dependencies (Runtime)

配置文件中地址多为 `x.x.x.x` 或 `localhost` 占位符，启动前按需替换：

- **Nacos**（配置 + 发现）— port 8848
- **MySQL** — 多 schema：`oauth2`、`library`、gateway 路由库
- **Redis** — port 6379，database 4
- **Kafka** — port 9092
- **Elasticsearch** — port 9200（`wotrd-dubbo` 经 bboss 访问）
- **Sentinel Dashboard** — port 8080/8081
- **XXL-Job Admin** — port 39061
- **Spring Boot Admin** — 通过 `wotrd-monitor` 暴露

## Important Notes

- `wotrd-gateway` 基于 WebFlux，**禁止**添加 `spring-boot-starter-web`。
- `wotrd-ai` 使用完全不同的 Spring Boot / Spring AI 版本，**不要**与主模块共享依赖管理。
- `wotrd-client` SSO 必须配置 `server.servlet.session.cookie.name=OAUTH2_SESSION`。
- 测试覆盖较薄：多数模块只有 `*ApplicationTests.contextLoads()`。
- See [doc/CLAUDE-RECENT.md](./doc/CLAUDE-RECENT.md) for recent changes and known issues.
- See [doc/CLAUDE-REFERENCE.md](./doc/CLAUDE-REFERENCE.md) for annotation, base class, naming convention, and utility references.
