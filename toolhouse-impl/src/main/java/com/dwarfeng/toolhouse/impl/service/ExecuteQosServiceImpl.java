package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskManualExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskOverrideExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskSystemExecuteInfo;
import com.dwarfeng.toolhouse.stack.handler.ExecuteHandler;
import com.dwarfeng.toolhouse.stack.handler.ExecuteLocalCacheHandler;
import com.dwarfeng.toolhouse.stack.service.ExecuteQosService;
import com.dwarfeng.toolhouse.stack.struct.ExecuteInfo;
import org.springframework.stereotype.Service;

@Service
public class ExecuteQosServiceImpl implements ExecuteQosService {

    private final ExecuteHandler executeHandler;
    private final ExecuteLocalCacheHandler executeLocalCacheHandler;

    private final ServiceExceptionMapper sem;

    public ExecuteQosServiceImpl(
            ExecuteHandler executeHandler,
            ExecuteLocalCacheHandler executeLocalCacheHandler,
            ServiceExceptionMapper sem
    ) {
        this.executeHandler = executeHandler;
        this.executeLocalCacheHandler = executeLocalCacheHandler;
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
    public void overrideExecuteTask(StringIdKey operateUserKey, TaskOverrideExecuteInfo info) throws ServiceException {
        try {
            executeHandler.overrideExecuteTask(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控执行任务时发生异常", LogLevel.WARN, e, sem);
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
    public ExecuteInfo getExecuteInfo(LongIdKey toolKey) throws ServiceException {
        try {
            return executeLocalCacheHandler.get(toolKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("获取指定工具的执行信息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void clearLocalCache() throws ServiceException {
        try {
            executeLocalCacheHandler.clear();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除本地缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
