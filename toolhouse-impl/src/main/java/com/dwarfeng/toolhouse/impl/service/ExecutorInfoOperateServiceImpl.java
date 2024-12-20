package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoUpdateInfo;
import com.dwarfeng.toolhouse.stack.handler.ExecutorInfoOperateHandler;
import com.dwarfeng.toolhouse.stack.service.ExecutorInfoOperateService;
import org.springframework.stereotype.Service;

@Service
public class ExecutorInfoOperateServiceImpl implements ExecutorInfoOperateService {

    private final ExecutorInfoOperateHandler executorInfoOperateHandler;

    private final ServiceExceptionMapper sem;

    public ExecutorInfoOperateServiceImpl(
            ExecutorInfoOperateHandler executorInfoOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.executorInfoOperateHandler = executorInfoOperateHandler;
        this.sem = sem;
    }

    @Override
    public void create(StringIdKey operateUserKey, ExecutorInfoCreateInfo info) throws ServiceException {
        try {
            executorInfoOperateHandler.create(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("创建执行器信息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void update(StringIdKey operateUserKey, ExecutorInfoUpdateInfo info) throws ServiceException {
        try {
            executorInfoOperateHandler.update(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新执行器信息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(StringIdKey operateUserKey, ExecutorInfoRemoveInfo info) throws ServiceException {
        try {
            executorInfoOperateHandler.remove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除执行器信息时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
