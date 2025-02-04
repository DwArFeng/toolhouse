package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.handler.InputItemOperateHandler;
import com.dwarfeng.toolhouse.stack.service.InputItemQosService;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
public class InputItemQosServiceImpl implements InputItemQosService {

    private final InputItemOperateHandler inputItemOperateHandler;

    private final ServiceExceptionMapper sem;

    public InputItemQosServiceImpl(InputItemOperateHandler inputItemOperateHandler, ServiceExceptionMapper sem) {
        this.inputItemOperateHandler = inputItemOperateHandler;
        this.sem = sem;
    }

    @Override
    public void manualUpsert(StringIdKey operateUserKey, InputItemManualUpsertInfo info) throws ServiceException {
        try {
            inputItemOperateHandler.manualUpsert(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动创建/更新输入项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void manualRemove(StringIdKey operateUserKey, InputItemManualRemoveInfo info) throws ServiceException {
        try {
            inputItemOperateHandler.manualRemove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动删除输入项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideUpsert(StringIdKey operateUserKey, InputItemOverrideUpsertInfo info) throws ServiceException {
        try {
            inputItemOperateHandler.overrideUpsert(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控创建/更新输入项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideRemove(StringIdKey operateUserKey, InputItemOverrideRemoveInfo info) throws ServiceException {
        try {
            inputItemOperateHandler.overrideRemove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控删除输入项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Nullable
    @Override
    public InputItemInspectResult systemInspect(InputItemSystemInspectInfo info) throws ServiceException {
        try {
            return inputItemOperateHandler.systemInspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统检查输入项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemUpsert(InputItemSystemUpsertInfo info) throws ServiceException {
        try {
            inputItemOperateHandler.systemUpsert(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统创建/更新输入项时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemRemove(InputItemSystemRemoveInfo info) throws ServiceException {
        try {
            inputItemOperateHandler.systemRemove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统删除输入项时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
