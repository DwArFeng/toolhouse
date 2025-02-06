package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;
import com.dwarfeng.toolhouse.stack.handler.*;
import com.dwarfeng.toolhouse.stack.service.TaskMaintainService;
import com.dwarfeng.toolhouse.stack.struct.ExecuteInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.Future;

@Component
public class ExecuteHandlerImpl implements ExecuteHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecuteHandlerImpl.class);

    private final ApplicationContext ctx;

    private final TaskMaintainService taskMaintainService;

    private final TaskOperateHandler taskOperateHandler;
    private final FileOperateHandler fileOperateHandler;
    private final VariableOperateHandler variableOperateHandler;
    private final InputItemOperateHandler inputItemOperateHandler;
    private final OutputItemOperateHandler outputItemOperateHandler;
    private final ExecuteLocalCacheHandler executeLocalCacheHandler;

    private final HandlerValidator handlerValidator;

    private final ThreadPoolTaskExecutor executor;
    private final ThreadPoolTaskScheduler scheduler;

    @Value("${task.beat_interval}")
    private long beatInterval;

    public ExecuteHandlerImpl(
            ApplicationContext ctx,
            TaskMaintainService taskMaintainService,
            TaskOperateHandler taskOperateHandler,
            FileOperateHandler fileOperateHandler,
            VariableOperateHandler variableOperateHandler,
            InputItemOperateHandler inputItemOperateHandler,
            OutputItemOperateHandler outputItemOperateHandler,
            ExecuteLocalCacheHandler executeLocalCacheHandler,
            HandlerValidator handlerValidator,
            ThreadPoolTaskExecutor executor,
            ThreadPoolTaskScheduler scheduler
    ) {
        this.ctx = ctx;
        this.taskMaintainService = taskMaintainService;
        this.taskOperateHandler = taskOperateHandler;
        this.fileOperateHandler = fileOperateHandler;
        this.variableOperateHandler = variableOperateHandler;
        this.inputItemOperateHandler = inputItemOperateHandler;
        this.outputItemOperateHandler = outputItemOperateHandler;
        this.executeLocalCacheHandler = executeLocalCacheHandler;
        this.handlerValidator = handlerValidator;
        this.executor = executor;
        this.scheduler = scheduler;
    }

    @Override
    public void manualExecuteTask(StringIdKey operateUserKey, TaskManualExecuteInfo info) throws HandlerException {
        try {
            doManualExecuteTask(operateUserKey, info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doManualExecuteTask(StringIdKey operateUserKey, TaskManualExecuteInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();

        // 确认用户存在。
        handlerValidator.makeSureUserExists(operateUserKey);
        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);
        // 确认用户一致。
        Task task = taskMaintainService.get(taskKey);
        handlerValidator.makeSureUserConsists(task.getUserKey(), operateUserKey);

        // 执行任务。
        executeTask0(new TaskSystemExecuteInfo(taskKey));
    }

    @Override
    public CompletableFuture<Void> manualExecuteTaskAsync(StringIdKey operateUserKey, TaskManualExecuteInfo info) {
        return CompletableFuture.supplyAsync(
                () -> {
                    wrappedDoManualExecuteTask(operateUserKey, info);
                    return null;
                },
                executor
        );
    }

    private void wrappedDoManualExecuteTask(StringIdKey operateUserKey, TaskManualExecuteInfo info)
            throws CompletionException {
        try {
            doManualExecuteTask(operateUserKey, info);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    @Override
    public void overrideExecuteTask(StringIdKey operateUserKey, TaskOverrideExecuteInfo info) throws HandlerException {
        try {
            doOverrideExecuteTask(operateUserKey, info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doOverrideExecuteTask(StringIdKey operateUserKey, TaskOverrideExecuteInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();

        // 确认用户存在。
        handlerValidator.makeSureUserExists(operateUserKey);
        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);
        // 确认用户有权限操作指定的工具。
        Task task = taskMaintainService.get(taskKey);
        handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, task.getToolKey());

        // 执行任务。
        executeTask0(new TaskSystemExecuteInfo(taskKey));
    }

    @Override
    public CompletableFuture<Void> overrideExecuteTaskAsync(StringIdKey operateUserKey, TaskOverrideExecuteInfo info) {
        return CompletableFuture.supplyAsync(
                () -> {
                    wrappedDoOverrideExecuteTask(operateUserKey, info);
                    return null;
                },
                executor
        );
    }

    private void wrappedDoOverrideExecuteTask(StringIdKey operateUserKey, TaskOverrideExecuteInfo info)
            throws CompletionException {
        try {
            doOverrideExecuteTask(operateUserKey, info);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    @Override
    public void systemExecuteTask(TaskSystemExecuteInfo info) throws HandlerException {
        try {
            doSystemExecuteTask(info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doSystemExecuteTask(TaskSystemExecuteInfo info) throws Exception {
        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();

        // 确认任务存在。
        handlerValidator.makeSureTaskExists(taskKey);

        // 执行任务。
        executeTask0(info);
    }

    @Override
    public CompletableFuture<Void> systemExecuteTaskAsync(TaskSystemExecuteInfo info) {
        return CompletableFuture.supplyAsync(
                () -> {
                    wrappedDoSystemExecuteTask(info);
                    return null;
                },
                executor
        );
    }

    private void wrappedDoSystemExecuteTask(TaskSystemExecuteInfo info) throws CompletionException {
        try {
            doSystemExecuteTask(info);
        } catch (Exception e) {
            throw new CompletionException(e);
        }
    }

    private void executeTask0(TaskSystemExecuteInfo info) throws Exception {
        // 预定义参数。
        Future<?> beatTaskFuture = null;

        // 展开参数。
        LongIdKey taskKey = info.getTaskKey();

        try {
            // 调用处理器启动任务。
            taskOperateHandler.systemStart(new TaskSystemStartInfo(taskKey));

            // 启动心跳任务。
            beatTaskFuture = startBeatTask(taskKey);

            // 获取任务本身，及其关联的工具主键、会话键。
            Task task = taskMaintainService.get(taskKey);
            LongIdKey toolKey = task.getToolKey();
            LongIdKey sessionKey = task.getSessionKey();

            // 根据工具主键获取执行信息。
            ExecuteInfo executeInfo = executeLocalCacheHandler.get(toolKey);

            // 构造执行器代理人。
            Map<ExecutorKey, Executor.Agent> executorAgentMap = makeExecutorAgentMap(
                    executeInfo, sessionKey, taskKey
            );

            // 定义 CompletableFuture 集合。
            Set<CompletableFuture<?>> completableFutureSet = new HashSet<>();
            // 遍历执行器代理人，初始化 CompletableFuture 集合。
            for (Map.Entry<ExecutorKey, Executor.Agent> entry : executorAgentMap.entrySet()) {
                ExecutorKey executorKey = entry.getKey();
                Executor.Agent executorAgent = entry.getValue();
                ExecutorAgentTask executorAgentTask = ctx.getBean(
                        ExecutorAgentTask.class,
                        toolKey, taskKey, executorKey, executorAgent
                );
                CompletableFuture<Void> future = CompletableFuture.runAsync(executorAgentTask, executor);
                completableFutureSet.add(future);
            }

            // 等待所有代理人任务完成。
            try {
                CompletableFuture.allOf(completableFutureSet.toArray(new CompletableFuture[0])).join();
            } catch (CompletionException e) {
                throw (Exception) e.getCause();
            }

            // 记录日志。
            LOGGER.debug("任务 {} 代理人全部执行完成", taskKey);

            // 将任务状态置为完成。
            taskOperateHandler.systemFinish(new TaskSystemFinishInfo(taskKey));
        } catch (Exception e) {
            // 记录日志。
            String logMessage = "任务执行失败, 任务主键: " + taskKey + ", 异常信息如下: ";
            LOGGER.warn(logMessage, e);

            // 将任务状态置为失败。
            taskOperateHandler.systemFail(new TaskSystemFailInfo(taskKey));
        } finally {
            // 清理心跳任务。
            if (Objects.nonNull(beatTaskFuture)) {
                beatTaskFuture.cancel(true);
            }
        }
    }

    private Future<?> startBeatTask(LongIdKey taskKey) {
        BeatSchedulerTask beatSchedulerTask = ctx.getBean(BeatSchedulerTask.class, taskOperateHandler, taskKey);
        return scheduler.scheduleAtFixedRate(
                beatSchedulerTask,
                new Date(System.currentTimeMillis() + beatInterval),
                beatInterval
        );
    }

    private Map<ExecutorKey, Executor.Agent> makeExecutorAgentMap(
            ExecuteInfo executeInfo, LongIdKey sessionKey, LongIdKey taskKey
    ) throws Exception {
        Map<ExecutorKey, Executor.Agent> result = new HashMap<>();
        for (Map.Entry<ExecutorKey, Executor> entry : executeInfo.getExecutorMap().entrySet()) {
            // 获取 Map 入口的键值。
            ExecutorKey executorKey = entry.getKey();
            Executor executor = entry.getValue();
            // 调用执行器的方法创建新的代理人。
            Executor.Agent executorAgent = executor.newAgent();
            // 生成针对代理人的上下文。
            Executor.Context executorContext = ctx.getBean(
                    InternalExecutorContext.class,
                    executorKey, sessionKey, taskKey, taskOperateHandler, fileOperateHandler, variableOperateHandler,
                    inputItemOperateHandler, outputItemOperateHandler
            );
            // 初始化代理人。
            executorAgent.init(executorContext);
            // 将代理人放入结果集。
            result.put(executorKey, executorAgent);
        }
        return result;
    }

    /**
     * 心跳定时器任务。
     *
     * @author DwArFeng
     * @since beta-1.0.0
     */
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class BeatSchedulerTask implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(BeatSchedulerTask.class);

        private final TaskOperateHandler taskOperateHandler;

        private final LongIdKey taskKey;

        public BeatSchedulerTask(TaskOperateHandler taskOperateHandler, LongIdKey taskKey) {
            this.taskOperateHandler = taskOperateHandler;
            this.taskKey = taskKey;
        }

        @Override
        public void run() {
            try {
                taskOperateHandler.systemBeat(new TaskSystemBeatInfo(taskKey));
            } catch (Exception e) {
                LOGGER.warn("心跳任务执行失败, 任务主键为 {}, 异常信息如下: ", taskKey, e);
            }
        }

        @Override
        public String toString() {
            return "BeatSchedulerTask{" +
                    "taskOperateHandler=" + taskOperateHandler +
                    ", taskKey=" + taskKey +
                    '}';
        }
    }


    /**
     * 执行器上下文的内部实现。
     *
     * @author DwArFeng
     * @since beta-1.0.0
     */
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class InternalExecutorContext implements Executor.Context {

        private final ExecutorKey executorKey;
        private final LongIdKey sessionKey;
        private final LongIdKey taskKey;

        private final TaskOperateHandler taskOperateHandler;
        private final FileOperateHandler fileOperateHandler;
        private final VariableOperateHandler variableOperateHandler;
        private final InputItemOperateHandler inputItemOperateHandler;
        private final OutputItemOperateHandler outputItemOperateHandler;

        public InternalExecutorContext(
                ExecutorKey executorKey,
                LongIdKey sessionKey,
                LongIdKey taskKey,
                TaskOperateHandler taskOperateHandler,
                FileOperateHandler fileOperateHandler,
                VariableOperateHandler variableOperateHandler,
                InputItemOperateHandler inputItemOperateHandler,
                OutputItemOperateHandler outputItemOperateHandler
        ) {
            this.executorKey = executorKey;
            this.sessionKey = sessionKey;
            this.taskKey = taskKey;
            this.taskOperateHandler = taskOperateHandler;
            this.fileOperateHandler = fileOperateHandler;
            this.variableOperateHandler = variableOperateHandler;
            this.inputItemOperateHandler = inputItemOperateHandler;
            this.outputItemOperateHandler = outputItemOperateHandler;
        }

        @Override
        public String getExecutorStringId() {
            return executorKey.getExecutorStringId();
        }

        @Override
        public LongIdKey getSessionKey() {
            return sessionKey;
        }

        @Override
        public LongIdKey getTaskKey() {
            return taskKey;
        }

        @Override
        public void updateTaskModal(TaskSystemUpdateModalInfo info) throws Exception {
            taskOperateHandler.systemUpdateModal(info);
        }

        @Override
        public File downloadFile(FileSystemDownloadInfo info) throws Exception {
            return fileOperateHandler.systemDownloadFile(info);
        }

        @Override
        public FileStream downloadFileStream(FileStreamSystemDownloadInfo info) throws Exception {
            return fileOperateHandler.systemDownloadFileStream(info);
        }

        @Override
        public FileUploadResult uploadFile(FileSystemUploadInfo info) throws Exception {
            return fileOperateHandler.systemUploadFile(info);
        }

        @Override
        public FileStreamUploadResult uploadFileStream(FileStreamSystemUploadInfo info) throws Exception {
            return fileOperateHandler.systemUploadFileStream(info);
        }

        @Override
        public void updateFile(FileSystemUpdateInfo info) throws Exception {
            fileOperateHandler.systemUpdateFile(info);
        }

        @Override
        public void updateFileStream(FileStreamSystemUpdateInfo info) throws Exception {
            fileOperateHandler.systemUpdateFileStream(info);
        }

        @Override
        public void removeFile(FileSystemRemoveInfo info) throws Exception {
            fileOperateHandler.systemRemoveFile(info);
        }

        @Override
        public VariableInspectResult inspectVariable(VariableSystemInspectInfo info) throws Exception {
            return variableOperateHandler.systemInspect(info);
        }

        @Override
        public void upsertVariable(VariableSystemUpsertInfo info) throws Exception {
            variableOperateHandler.systemUpsert(info);
        }

        @Override
        public void removeVariable(VariableSystemRemoveInfo info) throws Exception {
            variableOperateHandler.systemRemove(info);
        }

        @Nullable
        @Override
        public InputItemInspectResult inspectInputItem(InputItemSystemInspectInfo info) throws Exception {
            return inputItemOperateHandler.systemInspect(info);
        }

        @Override
        public void upsertInputItem(InputItemSystemUpsertInfo info) throws Exception {
            inputItemOperateHandler.systemUpsert(info);
        }

        @Override
        public void removeInputItem(InputItemSystemRemoveInfo info) throws Exception {
            inputItemOperateHandler.systemRemove(info);
        }

        @Nullable
        @Override
        public OutputItemInspectResult inspectOutputItem(OutputItemSystemInspectInfo info) throws Exception {
            return outputItemOperateHandler.systemInspect(info);
        }

        @Override
        public void upsertOutputItem(OutputItemSystemUpsertInfo info) throws Exception {
            outputItemOperateHandler.systemUpsert(info);
        }

        @Override
        public void removeOutputItem(OutputItemSystemRemoveInfo info) throws Exception {
            outputItemOperateHandler.systemRemove(info);
        }

        @Override
        public String toString() {
            return "InternalExecutorContext{" +
                    "executorKey=" + executorKey +
                    ", sessionKey=" + sessionKey +
                    ", taskKey=" + taskKey +
                    ", taskOperateHandler=" + taskOperateHandler +
                    ", fileOperateHandler=" + fileOperateHandler +
                    ", variableOperateHandler=" + variableOperateHandler +
                    ", inputItemOperateHandler=" + inputItemOperateHandler +
                    ", outputItemOperateHandler=" + outputItemOperateHandler +
                    '}';
        }
    }

    /**
     * 执行器代理人任务。
     *
     * @author DwArFeng
     * @since beta-1.0.0
     */
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class ExecutorAgentTask implements Runnable {

        private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorAgentTask.class);

        private final LongIdKey toolKey;
        private final LongIdKey taskKey;
        private final ExecutorKey executorKey;

        private final Executor.Agent executorAgent;

        public ExecutorAgentTask(
                LongIdKey toolKey,
                LongIdKey taskKey,
                ExecutorKey executorKey,
                Executor.Agent executorAgent
        ) {
            this.toolKey = toolKey;
            this.taskKey = taskKey;
            this.executorKey = executorKey;
            this.executorAgent = executorAgent;
        }

        @Override
        public void run() {
            // 定义异常。
            Exception occurredException = null;

            // 调用代理人的执行方法。
            try {
                executorAgent.execute();
            } catch (Exception e) {
                // 记录日志。
                String message = "执行器代理人执行调度时发生异常, 工具键: " + toolKey + ", 任务键: " +
                        taskKey + ", 执行器键: " + executorKey + ", 异常信息如下: ";
                LOGGER.warn(message, e);
                // 置位异常。
                occurredException = e;
            }

            // 如果发生异常，则抛出。
            if (Objects.nonNull(occurredException)) {
                throw new CompletionException(occurredException);
            }
        }

        @Override
        public String toString() {
            return "ExecutorAgentTask{" +
                    "toolKey=" + toolKey +
                    ", taskKey=" + taskKey +
                    ", executorKey=" + executorKey +
                    ", executorAgent=" + executorAgent +
                    '}';
        }
    }
}
