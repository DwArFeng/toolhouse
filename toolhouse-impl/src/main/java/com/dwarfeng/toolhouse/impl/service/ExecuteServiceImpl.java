package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskManualExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskOverrideExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskSystemExecuteInfo;
import com.dwarfeng.toolhouse.stack.handler.ExecuteHandler;
import com.dwarfeng.toolhouse.stack.service.ExecuteService;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ExecuteServiceImpl implements ExecuteService {

    private final ExecuteHandler executeHandler;

    private final ServiceExceptionMapper sem;

    public ExecuteServiceImpl(ExecuteHandler executeHandler, ServiceExceptionMapper sem) {
        this.executeHandler = executeHandler;
        this.sem = sem;
    }

    @Override
    public void manualExecuteTask(StringIdKey operateUserKey, TaskManualExecuteInfo info) throws ServiceException {
        try {
            executeHandler.manualExecuteTask(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动执行任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public CompletableFuture<Void> manualExecuteTaskAsync(StringIdKey operateUserKey, TaskManualExecuteInfo info)
            throws ServiceException {
        try {
            return executeHandler.manualExecuteTaskAsync(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("异步手动执行任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideExecuteTask(StringIdKey operateUserKey, TaskOverrideExecuteInfo info) throws ServiceException {
        try {
            executeHandler.overrideExecuteTask(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控执行任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public CompletableFuture<Void> overrideExecuteTaskAsync(StringIdKey operateUserKey, TaskOverrideExecuteInfo info)
            throws ServiceException {
        try {
            return executeHandler.overrideExecuteTaskAsync(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("异步超控执行任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemExecuteTask(TaskSystemExecuteInfo info) throws ServiceException {
        try {
            executeHandler.systemExecuteTask(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统执行任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public CompletableFuture<Void> systemExecuteTaskAsync(TaskSystemExecuteInfo info) throws ServiceException {
        try {
            return executeHandler.systemExecuteTaskAsync(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("异步系统执行任务时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
