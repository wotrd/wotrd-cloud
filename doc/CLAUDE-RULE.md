# wotrd-cloud 开发规范

团队开发约定、流程规范和禁止事项，补充 `CLAUDE.md` 中的技术模式说明。

快速返回：[../CLAUDE.md](../CLAUDE.md)

---

## 1. Git 工作流

- 分支命名：`feature/xxx`、`hotfix/xxx`、`release/xxx`（推荐）
- Commit 规范：项目未显式约定
- 提交前检查：建议 `mvn clean package` 通过
- Code Review：项目未显式约定

## 2. 代码风格

- 项目主要使用 Java 8，部分模块使用 Lombok。
- 项目未显式约定统一格式化工具，保持与现有代码风格一致。

## 3. 模块与依赖规范

### 3.1 版本对齐

- 主体技术栈：Spring Boot `2.2.8.RELEASE` + Spring Cloud Hoxton + Spring Cloud Alibaba `2.2.1`。
- `wotrd-ai` 独立使用 Java 17 + Spring Boot `4.1.0-RC1` + Spring AI BOM `1.0.0-M6`，**不得与父 POM 共享依赖版本**。
- 升级时参考 [Spring Cloud Alibaba 版本说明](https://github.com/spring-cloud-incubator/spring-cloud-alibaba/wiki/版本说明)。

### 3.2 Gateway 依赖红线

- `wotrd-gateway` 基于 **WebFlux**，禁止引入 `spring-boot-starter-web`（Spring MVC）。

## 4. 配置中心规范

- 使用 Nacos 配置中心的模块需放置 `bootstrap.properties` 或 `bootstrap.yml`。
- `bootstrap` 文件优先级高于本地 `application` 文件，且不会被覆盖。
- 在代码中读取 Nacos 配置中心值的 Bean 必须加 `@RefreshScope`；数据库连接等配置不需要。

## 5. Sentinel 规范

- 各模块按需配置 Sentinel Dashboard 地址。
- Feign 集成 Sentinel fallback 必须在配置中显式开启：

  ```properties
  feign.sentinel.enabled=true
  ```

- 流控规则可推送至 Nacos 持久化。

## 6. OAuth2 / SSO 规范

- `wotrd-auth` 为 OAuth2 授权服务器，使用 JDBC 存储 token、client details、authorization code。
- `wotrd-client` 作为 SSO 客户端，必须配置：

  ```properties
  server.servlet.session.cookie.name=OAUTH2_SESSION
  ```

  否则单点登录会失败。

## 7. Elasticsearch 规范

- ES 版本需与 Spring Boot 版本对齐：项目使用 ES `6.8.4` 搭配 Spring Boot `2.2.1`。
- `wotrd-dubbo` 使用 bboss 操作 ES；新增索引前应先执行模板，避免自动推断字段类型。
- 按月创建索引后，后续新增字段通过 `_mapping` API 显式添加。

## 8. 数据库规范

- `wotrd-auth`：OAuth2 schema 见 `wotrd-auth/src/main/resources/sql/oauth2.sql`。
- `wotrd-gateway`：动态路由表结构见 `wotrd-gateway/src/main/resources/sql/gateway_route.sql`。
- `wotrd-sharding`：按 `PreciseShardingAlgorithm` 实现 modulo-2 分片。
- `wotrd-dynamic-datasource`：每个数据源独立配置 `SqlSessionFactory` 与 `SqlSessionTemplate`。

## 9. 测试规范

- 单元测试：当前覆盖较薄，新增工具类建议补充单元测试。
- 集成测试：依赖外部中间件时，确保本地环境可用或提供 mock。
- `wotrd-dubbo` 使用 JUnit 5，并排除 vintage engine。

## 10. 日志与监控

- 日志配置参考 `wotrd-dubbo/src/main/resources/logback-config.xml`。
- `wotrd-monitor` 通过 Nacos 自动发现其他服务并监控。

## 11. 文档同步

- 公共组件 / 核心接口 / 重要工具类 → 更新 `doc/CLAUDE-REFERENCE.md`
- 部署方式 / 环境变更 → 更新 `doc/CLAUDE-DEPLOY.md`
- 关键功能 / 重要 bug 修复 → 更新 `doc/CLAUDE-RECENT.md`
- 开发规范变更 → 更新 `doc/CLAUDE-RULE.md`
