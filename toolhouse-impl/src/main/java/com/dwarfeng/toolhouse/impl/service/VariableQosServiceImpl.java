package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.handler.VariableOperateHandler;
import com.dwarfeng.toolhouse.stack.service.VariableQosService;
import org.springframework.stereotype.Service;

import javax.annotation.Nullable;

@Service
public class VariableQosServiceImpl implements VariableQosService {

    private final VariableOperateHandler variableOperateHandler;

    private final ServiceExceptionMapper sem;

    public VariableQosServiceImpl(VariableOperateHandler variableOperateHandler, ServiceExceptionMapper sem) {
        this.variableOperateHandler = variableOperateHandler;
        this.sem = sem;
    }

    @Override
    public void manualUpsert(StringIdKey operateUserKey, VariableManualUpsertInfo info) throws ServiceException {
        try {
            variableOperateHandler.manualUpsert(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动创建/更新变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void manualRemove(StringIdKey operateUserKey, VariableManualRemoveInfo info) throws ServiceException {
        try {
            variableOperateHandler.manualRemove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动删除变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideUpsert(StringIdKey operateUserKey, VariableOverrideUpsertInfo info) throws ServiceException {
        try {
            variableOperateHandler.overrideUpsert(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控创建/更新变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideRemove(StringIdKey operateUserKey, VariableOverrideRemoveInfo info) throws ServiceException {
        try {
            variableOperateHandler.overrideRemove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控删除变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Nullable
    @Override
    public VariableInspectResult systemInspect(VariableSystemInspectInfo info) throws ServiceException {
        try {
            return variableOperateHandler.systemInspect(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统查询变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemUpsert(VariableSystemUpsertInfo info) throws ServiceException {
        try {
            variableOperateHandler.systemUpsert(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统创建/更新变量时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemRemove(VariableSystemRemoveInfo info) throws ServiceException {
        try {
            variableOperateHandler.systemRemove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统删除变量时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
