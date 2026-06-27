# wotrd-cloud 近期变更与关键坑点

> 补充 `CLAUDE.md` 的近期上下文。新会话请先读 `CLAUDE.md`，再按需查看本文档。

---

## 1. 文档体系重构

### 1.1 变更信息

- **分支**：`master`
- **关键文件**：`CLAUDE.md`、`doc/README.md`、`doc/CLAUDE-DEPLOY.md`、`doc/CLAUDE-RULE.md`、`doc/CLAUDE-REFERENCE.md`、`doc/CLAUDE-RECENT.md`
- **功能**：将原有单一 `CLAUDE.md` 按 `claude-doc-system` 4+1 模板拆分为导航入口 + 部署/规范/参考/近期文档。

### 1.2 关键坑点

1. `wotrd-ai` 未纳入根 `pom.xml` 模块列表，独立维护，避免与 Spring Boot 2.2.x 模块混用依赖。
2. `wotrd-gateway` 基于 WebFlux，禁止引入 `spring-boot-starter-web`。
3. `wotrd-client` SSO 必须设置 `server.servlet.session.cookie.name=OAUTH2_SESSION`。

## 2. 后续待办

- [ ] 补充最近一次功能迭代或 bug 修复的详细信息。
- [ ] 确认 `wotrd-netty` 是否缺少启动类或已改为纯库模块。
- [ ] 根据实际运行环境更新各中间件地址与端口。
