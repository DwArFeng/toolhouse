package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;
import com.dwarfeng.toolhouse.stack.handler.SessionOperateHandler;
import com.dwarfeng.toolhouse.stack.service.SessionMaintainService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SessionOperateHandlerImpl implements SessionOperateHandler {

    private final SessionMaintainService sessionMaintainService;

    private final HandlerValidator handlerValidator;

    public SessionOperateHandlerImpl(
            SessionMaintainService sessionMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.sessionMaintainService = sessionMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public SessionCreateResult manualCreate(StringIdKey operateUserKey, SessionManualCreateInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey toolKey = info.getToolKey();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认工具存在。
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限查看指定的工具。
            handlerValidator.makeSureUserInspectPermittedForTool(operateUserKey, toolKey);

            // 根据 info 以及创建的规则组合 会话 实体。
            Date currentDate = new Date();
            Session session = new Session(
                    null, toolKey, operateUserKey, currentDate, currentDate, remark
            );

            // 插入会话实体。
            LongIdKey sessionKey = sessionMaintainService.insert(session);

            // 返回结果。
            return new SessionCreateResult(sessionKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void manualRemove(StringIdKey operateUserKey, SessionManualRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户一致。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserConsists(session.getUserKey(), operateUserKey);

            // 删除会话实体。
            sessionMaintainService.delete(sessionKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public SessionCreateResult overrideCreate(StringIdKey operateUserKey, SessionOverrideCreateInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey toolKey = info.getToolKey();
            StringIdKey userKey = info.getUserKey();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            handlerValidator.makeSureUserExists(userKey);
            // 确认工具存在。
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, toolKey);

            // 根据 info 以及创建的规则组合 会话 实体。
            Date currentDate = new Date();
            Session session = new Session(
                    null, toolKey, userKey, currentDate, currentDate, remark
            );

            // 插入会话实体。
            LongIdKey sessionKey = sessionMaintainService.insert(session);

            // 返回结果。
            return new SessionCreateResult(sessionKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void overrideRemove(StringIdKey operateUserKey, SessionOverrideRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认会话对应的工具存在。
            Session session = sessionMaintainService.get(sessionKey);
            LongIdKey toolKey = session.getToolKey();
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, toolKey);

            // 删除会话实体。
            sessionMaintainService.delete(sessionKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public SessionCreateResult systemCreate(SessionSystemCreateInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey toolKey = info.getToolKey();
            StringIdKey userKey = info.getUserKey();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);
            // 确认工具存在。
            handlerValidator.makeSureToolExists(toolKey);

            // 根据 info 以及创建的规则组合 会话 实体。
            Date currentDate = new Date();
            Session session = new Session(
                    null, toolKey, userKey, currentDate, currentDate, remark
            );

            // 插入会话实体。
            LongIdKey sessionKey = sessionMaintainService.insert(session);

            // 返回结果。
            return new SessionCreateResult(sessionKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemRemove(SessionSystemRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);

            // 删除会话实体。
            sessionMaintainService.delete(sessionKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
