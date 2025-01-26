package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskCreateResult;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskManualCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskOverrideCreateInfo;
import com.dwarfeng.toolhouse.stack.handler.TaskOperateHandler;
import com.dwarfeng.toolhouse.stack.service.TaskOperateService;
import org.springframework.stereotype.Service;

@Service
public class TaskOperateServiceImpl implements TaskOperateService {

    private final TaskOperateHandler taskOperateHandler;

    private final ServiceExceptionMapper sem;

    public TaskOperateServiceImpl(TaskOperateHandler taskOperateHandler, ServiceExceptionMapper sem) {
        this.taskOperateHandler = taskOperateHandler;
        this.sem = sem;
    }

    @Override
    public TaskCreateResult manualCreate(StringIdKey operateUserKey, TaskManualCreateInfo info)
            throws ServiceException {
        try {
            return taskOperateHandler.manualCreate(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动创建任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public TaskCreateResult overrideCreate(StringIdKey operateUserKey, TaskOverrideCreateInfo info)
            throws ServiceException {
        try {
            return taskOperateHandler.overrideCreate(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控创建任务时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
