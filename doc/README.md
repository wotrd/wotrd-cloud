# wotrd-cloud Claude 上下文文档

本目录存放供 Claude Code 快速构建项目上下文的文档。新会话请先阅读 [`CLAUDE.md`](../CLAUDE.md)，再按需查看其他文件。

| 文件 | 用途 |
|---|---|
| [`CLAUDE.md`](../CLAUDE.md) | 项目速览、模块架构、核心代码模式、快速导航入口 |
| [`CLAUDE-RECENT.md`](./CLAUDE-RECENT.md) | 近期改动、新增接口、关键 bug 与坑点 |
| [`CLAUDE-DEPLOY.md`](./CLAUDE-DEPLOY.md) | Git 工作流、构建、部署、测试验证 |
| [`CLAUDE-RULE.md`](./CLAUDE-RULE.md) | 开发规范、代码风格、接口/数据安全/数据库/测试约定 |
| [`CLAUDE-REFERENCE.md`](./CLAUDE-REFERENCE.md) | 自定义注解、通用基类、命名约定、常用工具路径查表 |

## 使用建议

- **第一次接触项目**：`CLAUDE.md` → `CLAUDE-RULE.md` → 按需查看其他。
- **排查最近问题**：先看 `CLAUDE-RECENT.md`。
- **需要构建/部署**：看 `CLAUDE-DEPLOY.md`。
- **查类/注解/工具位置**：看 `CLAUDE-REFERENCE.md`。

## 维护原则

- `CLAUDE.md` 保持精简（目标 200 行以内），只放核心信息和导航链接。
- 细节、规范、部署、参考表分别放到对应文档，避免膨胀。
- 完成关键功能或修复重要 bug 后，同步更新 `CLAUDE-RECENT.md`。
- 新增开发规范或环境变更时，同步更新 `CLAUDE-RULE.md` 或 `CLAUDE-DEPLOY.md`。
