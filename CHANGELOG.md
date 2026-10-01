# ChangeLog

## Release_1.1.0_20260927_build_A

### 功能构建

- 增加预设的运维指令。
  - com.dwarfeng.springtelqos.api.integration.system.UptimeCommand。
  - com.dwarfeng.springtelqos.api.integration.system.JmxRemoteCommand。

- 优化支持实体机制。
  - com.dwarfeng.toolhouse.stack.service.SupportQosService。
  - com.dwarfeng.toolhouse.impl.service.telqos.SupportCommand。
  - 将执行器、可视化器支持维护服务的重置功能迁移至 QoS 服务。

- 优化启停脚本注释，以规避潜在的字符集问题。
  - binres/toolhouse-start.bat。
  - binres/toolhouse-start.sh。
  - binres/toolhouse-stop.sh。

- 优化 impl 模块下的 `logging` 目录结构。
  - 将 `logging/settings-windows.xml` 重命名为 `settings-ref-windows.xml`，以消除文件名的歧义。
  - 更新 `logging/README.md` 中的相关说明。

- 优化 node 模块下的 `logging` 目录结构。
  - 将 `logging/settings-linux.xml` 重命名为 `settings-ref-linux.xml`，以消除文件名的歧义。
  - 将 `logging/settings-windows.xml` 重命名为 `settings-ref-windows.xml`，以消除文件名的歧义。
  - 更新 `logging/README.md` 中的相关说明。

- 优化实体映射器机制。

- SPI 目录结构优化。
  - 将执行器机制的 SPI 接口与抽象类相关代码文件提升至 `sdk` 模块中。
  - 将推送器机制的 SPI 接口与抽象类相关代码文件提升至 `sdk` 模块中。
  - 将可视化器机制的 SPI 接口与抽象类相关代码文件提升至 `sdk` 模块中。
  - 将重置器机制的 SPI 接口与抽象类相关代码文件提升至 `sdk` 模块中。

- 导入运维指令。
  - com.dwarfeng.datamark.service.telqos.*。

- 增加 Hibernate 实体数据标记字段，并应用相关实体侦听器。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateExecutorInfo。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateVisualizerInfo。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateUser。

- 增加依赖。
  - 增加依赖 `dwarfeng-datamark` 以应用其新功能，版本为 `1.0.5.a`。

- 依赖升级。
  - 升级 `subgrade` 依赖版本为 `1.6.2.a` 并解决兼容性问题，以规避漏洞。
  - 升级 `fastjson` 依赖版本为 `1.2.84` 以规避漏洞。
  - 升级 `jetty` 依赖版本为 `9.4.57.v20241219` 以规避漏洞。
  - 升级 `netty` 依赖版本为 `4.2.9.Final` 以规避漏洞。
  - 升级 `zookeeper` 依赖版本为 `3.9.4` 以规避漏洞。
  - 升级 `log4j2` 依赖版本为 `2.25.4` 以规避漏洞。
  - 升级 `dutil` 依赖版本为 `0.4.0.a-beta` 以规避漏洞。
  - 升级 `snowflake` 依赖版本为 `1.7.3.a` 以规避漏洞。
  - 升级 `dwarfeng-ftp` 依赖版本为 `1.3.6.a` 以规避漏洞。
  - 升级 `spring-terminator` 依赖版本为 `1.0.15.a` 以规避漏洞。
  - 升级 `spring-telqos` 依赖版本为 `1.1.16.a` 以规避漏洞。
  - 升级 `jackson` 依赖版本为 `2.21.5` 以规避漏洞。
  - 升级 `groovy` 依赖版本为 `4.0.26` 以规避漏洞。

- 优化部分说明文件中的格式。
  - libext/README.md。
  - optext/README.md。

- `toolhouse-node` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.toolhouse.node.configuration.FastJsonConfiguration。

- `toolhouse-impl` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateCabinet。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateExecutorInfo。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateExecutorSupport。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateFavorite。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateFileInfo。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateFolder。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateInputItem。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateOutputItem。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernatePoca。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateSession。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateTask。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateTool。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateUser。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateVariable。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateVisualizerInfo。
  - com.dwarfeng.toolhouse.impl.bean.entity.HibernateVisualizerSupport。
  - com.dwarfeng.toolhouse.impl.handler.executor.groovy.GroovyExecutorRegistry。
  - com.dwarfeng.toolhouse.impl.service.telqos.ExecuteCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.FileCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.InputItemCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.OutputItemCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.SessionCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.TaskCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.VariableCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.VisualClientCommand。
  - com.dwarfeng.toolhouse.impl.configuration.FastJsonConfiguration。

- `toolhouse-sdk` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.toolhouse.sdk.util.ValidPermissionLevel。
  - com.dwarfeng.toolhouse.sdk.util.ValidTaskItemType。
  - com.dwarfeng.toolhouse.sdk.util.ValidVariableType。

- `toolhouse-stack` 子模块类优化注释、文档注释格式、代码换行格式。
  - com.dwarfeng.toolhouse.stack.bean.dto.VisualizerOverrideCallInfo。

- 优化文件格式。
  - 优化 `opt-*.xml` 文件的格式。
  - 优化 `*.properties` 文件的格式。
  - 优化 `application-context-*.xml` 文件的格式。
  - 优化 `pom.xml` 文件的格式。

- 优化开发环境支持。
  - 在 .gitignore 中添加 VSCode 相关文件的忽略规则。
  - 在 .gitignore 中添加 Cursor IDE 相关文件的忽略规则。
  - 在 .gitignore 中添加 Vibe Coding 相关文件的忽略规则。

### Bug 修复

- (无)

### 功能移除

- (无)

---

## Beta_1.0.0_20250216_build_A

### 功能构建

- 实现运维指令。
  - com.dwarfeng.toolhouse.impl.service.telqos.ResetCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.TaskCheckCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.VisualClientCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.VisualizerCacheCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.InputItemCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.OutputItemCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.VariableCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.FileCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.TaskCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.SessionCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.ExecuteCommand。
  - com.dwarfeng.toolhouse.impl.service.telqos.ExecuteLocalCacheCommand。

- 实现预设推送器。
  - com.dwarfeng.toolhouse.impl.handler.pusher.DrainPusher。
  - com.dwarfeng.toolhouse.impl.handler.pusher.LogPusher。
  - com.dwarfeng.toolhouse.impl.handler.pusher.MultiPusher。

- 实现预设重置器。
  - com.dwarfeng.toolhouse.impl.handler.resetter.CronResetter。
  - com.dwarfeng.toolhouse.impl.handler.resetter.DubboResetter。
  - com.dwarfeng.toolhouse.impl.handler.resetter.FixedDelayResetter。
  - com.dwarfeng.toolhouse.impl.handler.resetter.FixedRateResetter。
  - com.dwarfeng.toolhouse.impl.handler.resetter.NeverResetter。

- 实现预设可视化器。
  - com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.DispatchVisualizer。

- 实现预设执行器。
  - com.dwarfeng.toolhouse.impl.handler.executor.mock.MockExecutorRegistry。
  - com.dwarfeng.toolhouse.impl.handler.executor.groovy.GroovyExecutorRegistry。

- 实现核心机制。
  - 推送机制。
  - 重置机制。
  - 任务检查机制。
  - 可视化机制。
  - 执行机制。

- 增加操作服务。
  - com.dwarfeng.toolhouse.stack.service.InputItemOperateService。
  - com.dwarfeng.toolhouse.stack.service.OutputItemOperateService。
  - com.dwarfeng.toolhouse.stack.service.TaskOperateService。
  - com.dwarfeng.toolhouse.stack.service.VariableOperateService。
  - com.dwarfeng.toolhouse.stack.service.FileOperateService。
  - com.dwarfeng.toolhouse.stack.service.SessionOperateService。
  - com.dwarfeng.toolhouse.stack.service.ExecutorInfoOperateService。
  - com.dwarfeng.toolhouse.stack.service.VisualizerInfoOperateService。
  - com.dwarfeng.toolhouse.stack.service.CabinetOperateService。
  - com.dwarfeng.toolhouse.stack.service.FolderOperateService。
  - com.dwarfeng.toolhouse.stack.service.ToolOperateService。

- 完成 node 模块，打包测试及启动测试通过。

- 建立实体以及维护服务，并通过单元测试。
  - com.dwarfeng.toolhouse.stack.bean.entity.InputItem。
  - com.dwarfeng.toolhouse.stack.bean.entity.OutputItem。
  - com.dwarfeng.toolhouse.stack.bean.entity.Session。
  - com.dwarfeng.toolhouse.stack.bean.entity.Task。
  - com.dwarfeng.toolhouse.stack.bean.entity.Variable。
  - com.dwarfeng.toolhouse.stack.bean.entity.FileInfo。
  - com.dwarfeng.toolhouse.stack.bean.entity.ExecutorInfo。
  - com.dwarfeng.toolhouse.stack.bean.entity.ExecutorSupport。
  - com.dwarfeng.toolhouse.stack.bean.entity.VisualizerInfo。
  - com.dwarfeng.toolhouse.stack.bean.entity.VisualizerSupport。
  - com.dwarfeng.toolhouse.stack.bean.entity.Cabinet。
  - com.dwarfeng.toolhouse.stack.bean.entity.Favorite。
  - com.dwarfeng.toolhouse.stack.bean.entity.Folder。
  - com.dwarfeng.toolhouse.stack.bean.entity.Poca。
  - com.dwarfeng.toolhouse.stack.bean.entity.Tool。
  - com.dwarfeng.toolhouse.stack.bean.entity.User。

- 项目结构建立，程序清理测试通过。

### Bug 修复

- (无)

### 功能移除

- (无)
