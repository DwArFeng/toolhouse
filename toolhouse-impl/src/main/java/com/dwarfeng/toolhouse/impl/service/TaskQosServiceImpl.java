package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.handler.TaskOperateHandler;
import com.dwarfeng.toolhouse.stack.service.TaskQosService;
import org.springframework.stereotype.Service;

@Service
public class TaskQosServiceImpl implements TaskQosService {

    private final TaskOperateHandler taskOperateHandler;

    private final ServiceExceptionMapper sem;

    public TaskQosServiceImpl(TaskOperateHandler taskOperateHandler, ServiceExceptionMapper sem) {
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

    @Override
    public TaskCreateResult systemCreate(TaskSystemCreateInfo info) throws ServiceException {
        try {
            return taskOperateHandler.systemCreate(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统创建任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemStart(TaskSystemStartInfo info) throws ServiceException {
        try {
            taskOperateHandler.systemStart(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统开始任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemFinish(TaskSystemFinishInfo info) throws ServiceException {
        try {
            taskOperateHandler.systemFinish(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统完成任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemFail(TaskSystemFailInfo info) throws ServiceException {
        try {
            taskOperateHandler.systemFail(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统失败任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemExpire(TaskSystemExpireInfo info) throws ServiceException {
        try {
            taskOperateHandler.systemExpire(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统过期任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemUpdateModal(TaskSystemUpdateModalInfo info) throws ServiceException {
        try {
            taskOperateHandler.systemUpdateModal(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统更新模态时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemDie(TaskSystemDieInfo info) throws ServiceException {
        try {
            taskOperateHandler.systemDie(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统死亡任务时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemBeat(TaskSystemBeatInfo info) throws ServiceException {
        try {
            taskOperateHandler.systemBeat(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统心跳任务时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
