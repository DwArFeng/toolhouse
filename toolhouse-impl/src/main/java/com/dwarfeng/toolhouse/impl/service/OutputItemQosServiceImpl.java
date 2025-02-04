package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.handler.OutputItemOperateHandler;
import com.dwarfeng.toolhouse.stack.service.OutputItemQosService;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
public class OutputItemQosServiceImpl implements OutputItemQosService {

    private final OutputItemOperateHandler outputItemOperateHandler;

    private final ServiceExceptionMapper sem;

    public OutputItemQosServiceImpl(OutputItemOperateHandler outputItemOperateHandler, ServiceExceptionMapper sem) {
        this.outputItemOperateHandler = outputItemOperateHandler;
        this.sem = sem;
    }

    @Override
    public void manualUpsert(StringIdKey operateUserKey, OutputItemManualUpsertInfo info) throws ServiceException {
        try {
            outputItemOperateHandler.manualUpsert(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动创建/更新输出项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void manualRemove(StringIdKey operateUserKey, OutputItemManualRemoveInfo info) throws ServiceException {
        try {
            outputItemOperateHandler.manualRemove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动删除输出项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideUpsert(StringIdKey operateUserKey, OutputItemOverrideUpsertInfo info) throws ServiceException {
        try {
            outputItemOperateHandler.overrideUpsert(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控创建/更新输出项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideRemove(StringIdKey operateUserKey, OutputItemOverrideRemoveInfo info) throws ServiceException {
        try {
            outputItemOperateHandler.overrideRemove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控删除输出项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Nullable
    @Override
    public OutputItemInspectResult systemInspect(OutputItemSystemInspectInfo info) throws ServiceException {
        try {
            return outputItemOperateHandler.systemInspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统检查输出项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemUpsert(OutputItemSystemUpsertInfo info) throws ServiceException {
        try {
            outputItemOperateHandler.systemUpsert(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统创建/更新输出项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemRemove(OutputItemSystemRemoveInfo info) throws ServiceException {
        try {
            outputItemOperateHandler.systemRemove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统删除输出项时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
