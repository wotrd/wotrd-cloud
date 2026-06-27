# wotrd-cloud 构建与部署手册

本文件集中记录项目的提交规范、构建命令、本地运行、部署及测试验证流程。

快速返回：[../CLAUDE.md](../CLAUDE.md)

---

## 1. Git 工作流

- 分支命名：`feature/xxx`、`hotfix/xxx`、`release/xxx`（项目未显式约定，推荐使用）
- Commit 规范：项目未显式约定
- 提交前检查：建议 `mvn clean package` 通过后再提交

## 2. 构建命令

构建整个项目：

```bash
mvn clean package
```

构建指定模块（例如 `wotrd-gateway`）：

```bash
mvn clean package -pl wotrd-gateway -am
```

## 3. 本地运行

每个模块都是独立的 Spring Boot 应用，没有统一入口。开发前需先启动所需基础设施（Nacos、MySQL、Redis 等）。

运行指定模块：

```bash
cd wotrd-gateway && mvn spring-boot:run
# 或
mvn spring-boot:run -pl wotrd-gateway
```

## 4. 测试

运行指定模块的测试：

```bash
mvn test -pl wotrd-gateway
```

运行单个测试类：

```bash
mvn test -pl wotrd-gateway -Dtest=GatewayApplicationTests
```

> 当前测试覆盖较薄：大多数模块只有生成的 `*ApplicationTests.contextLoads()` 空测试；`wotrd-dubbo` 使用 JUnit 5（Jupiter）并排除 vintage engine。

## 5. 外部依赖（运行时）

配置文件中地址多为 `x.x.x.x` 或 `localhost` 占位符，启动前按需替换。

| 服务 | 默认地址/端口 | 说明 |
|---|---|---|
| Nacos（配置 + 注册中心） | `localhost:8848` | 多个模块通过 `bootstrap.properties` 接入 |
| MySQL | `localhost:3306` | 多 schema：`oauth2`、`library`、gateway 路由库等 |
| Redis | `localhost:6379`，database 4 | `wotrd-feign` 等模块使用 |
| Kafka | `localhost:9092` | `wotrd-feign`、`wotrd-dubbo` 使用 |
| Elasticsearch | `localhost:9200` | `wotrd-dubbo` 通过 bboss 操作 |
| Sentinel Dashboard | `localhost:8080` / `8081` | 限流降级规则管理 |
| XXL-Job Admin | `localhost:39061` | `wotrd-feign` 接入 |
| Spring Boot Admin | 通过 `wotrd-monitor` 暴露 | 服务监控 |

## 6. 模块端口与入口

| 模块 | 主类 | 说明 |
|---|---|---|
| `wotrd-auth` | `com.wotrd.auth.AuthApplication` | OAuth2 授权服务 |
| `wotrd-client` | `com.wotrd.client.ClientApplication` | OAuth2 SSO 客户端 |
| `wotrd-dubbo` | `com.wotrd.dubbo.DubboProviderApplication` | Dubbo 服务提供者 |
| `wotrd-dynamic-datasource` | `com.wotrd.dydatasource.Application` | 动态数据源 / Atomikos |
| `wotrd-feign` | `com.wotrd.feign.Application` | Feign / Nacos / Redis / Kafka / XXL-Job |
| `wotrd-gateway` | `com.wotrd.gateway.GatewayApplication` | Spring Cloud Gateway |
| `wotrd-monitor` | `com.wotrd.monitor.MonitorServiceApplication` | Spring Boot Admin |
| `wotrd-sharding` | `com.wotrd.sharding.Application` | Sharding-JDBC 分库分表 |
| `wotrd-caffeine` | `com.wotrd.caffeine.CaffeineApplication` | Caffeine 本地缓存 |
| `wotrd-netty` | 无传统 `*Application` | Sentinel / Netty 相关 Controller |

## 7. 验证示例

- **Gateway 动态路由**：启动 `wotrd-gateway` 和 MySQL（导入 `wotrd-gateway/src/main/resources/sql/gateway_route.sql`），访问网关暴露的端口查看路由。
- **OAuth2 流程**：启动 `wotrd-auth` 与 `wotrd-client`，通过 `wotrd-client` 的 SSO 登录路径测试授权码模式。
- **Dubbo 调用**：启动 `wotrd-dubbo` 与调用方，通过 Nacos 注册中心发现服务。

## 8. 部署

项目未提供容器化或 CI/CD 配置，部署方式为独立运行各 Spring Boot 模块。生产部署时需注意：

- `wotrd-ai` 使用 Spring Boot `4.1.0-RC1`，与父 POM 中 Spring Boot `2.2.8.RELEASE` 不兼容，不能纳入统一构建。
- Gateway 基于 WebFlux，部署时避免引入 `spring-boot-starter-web`（MVC）。
