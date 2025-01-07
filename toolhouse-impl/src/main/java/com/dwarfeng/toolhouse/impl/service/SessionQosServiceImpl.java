package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.handler.SessionOperateHandler;
import com.dwarfeng.toolhouse.stack.service.SessionQosService;
import org.springframework.stereotype.Service;

@Service
public class SessionQosServiceImpl implements SessionQosService {

    private final SessionOperateHandler sessionOperateHandler;

    private final ServiceExceptionMapper sem;

    public SessionQosServiceImpl(SessionOperateHandler sessionOperateHandler, ServiceExceptionMapper sem) {
        this.sessionOperateHandler = sessionOperateHandler;
        this.sem = sem;
    }

    @Override
    public SessionCreateResult manualCreate(StringIdKey operateUserKey, SessionManualCreateInfo info)
            throws ServiceException {
        try {
            return sessionOperateHandler.manualCreate(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动创建会话时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void manualRemove(StringIdKey operateUserKey, SessionManualRemoveInfo info) throws ServiceException {
        try {
            sessionOperateHandler.manualRemove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动删除会话时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public SessionCreateResult overrideCreate(StringIdKey operateUserKey, SessionOverrideCreateInfo info)
            throws ServiceException {
        try {
            return sessionOperateHandler.overrideCreate(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控创建会话时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideRemove(StringIdKey operateUserKey, SessionOverrideRemoveInfo info) throws ServiceException {
        try {
            sessionOperateHandler.overrideRemove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控删除会话时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public SessionCreateResult systemCreate(SessionSystemCreateInfo info) throws ServiceException {
        try {
            return sessionOperateHandler.systemCreate(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统创建会话时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void systemRemove(SessionSystemRemoveInfo info) throws ServiceException {
        try {
            sessionOperateHandler.systemRemove(info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("系统删除会话时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
