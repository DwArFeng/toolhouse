# ChangeLog

## Release_1.1.0_20260927_build_A

### 功能构建

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
