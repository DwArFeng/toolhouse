package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;
import com.dwarfeng.toolhouse.stack.bean.entity.Variable;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;
import com.dwarfeng.toolhouse.stack.handler.VariableOperateHandler;
import com.dwarfeng.toolhouse.stack.service.SessionMaintainService;
import com.dwarfeng.toolhouse.stack.service.VariableMaintainService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class VariableOperateHandlerImpl implements VariableOperateHandler {

    private final VariableMaintainService variableMaintainService;
    private final SessionMaintainService sessionMaintainService;

    private final HandlerValidator handlerValidator;

    public VariableOperateHandlerImpl(
            VariableMaintainService variableMaintainService,
            SessionMaintainService sessionMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.variableMaintainService = variableMaintainService;
        this.sessionMaintainService = sessionMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void manualUpsert(StringIdKey operateUserKey, VariableManualUpsertInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String variableStringId = info.getVariableStringId();
            int type = info.getType();
            String stringValue = info.getStringValue();
            Long longValue = info.getLongValue();
            Double doubleValue = info.getDoubleValue();
            Boolean booleanValue = info.getBooleanValue();
            Date dateValue = info.getDateValue();
            Long fileRef = info.getFileRef();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户一致。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserConsists(session.getUserKey(), operateUserKey);
            // 确认类型合法。
            handlerValidator.makeSureVariableTypeValid(type);

            // 根据 info 创建 Variable。
            Variable variable = new Variable();
            variable.setKey(new VariableKey(sessionKey.getLongId(), variableStringId));
            variable.setToolKey(session.getToolKey());
            variable.setUserKey(session.getUserKey());
            variable.setType(type);
            variable.setRemark(remark);
            setValue(variable, type, stringValue, longValue, doubleValue, booleanValue, dateValue, fileRef);

            // 调用维护服务插入或更新变量。
            variableMaintainService.insertOrUpdate(variable);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void manualRemove(StringIdKey operateUserKey, VariableManualRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String variableStringId = info.getVariableStringId();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户一致。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserConsists(session.getUserKey(), operateUserKey);

            // 根据 info 构造 VariableKey。
            VariableKey variableKey = new VariableKey(sessionKey.getLongId(), variableStringId);

            // 调用维护服务删除变量（如果存在）。
            variableMaintainService.deleteIfExists(variableKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void overrideUpsert(StringIdKey operateUserKey, VariableOverrideUpsertInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String variableStringId = info.getVariableStringId();
            int type = info.getType();
            String stringValue = info.getStringValue();
            Long longValue = info.getLongValue();
            Double doubleValue = info.getDoubleValue();
            Boolean booleanValue = info.getBooleanValue();
            Date dateValue = info.getDateValue();
            Long fileRef = info.getFileRef();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限操作指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, session.getToolKey());

            // 根据 info 创建 Variable。
            Variable variable = new Variable();
            variable.setKey(new VariableKey(sessionKey.getLongId(), variableStringId));
            variable.setToolKey(session.getToolKey());
            variable.setUserKey(session.getUserKey());
            variable.setType(type);
            variable.setRemark(remark);
            setValue(variable, type, stringValue, longValue, doubleValue, booleanValue, dateValue, fileRef);

            // 调用维护服务插入或更新变量。
            variableMaintainService.insertOrUpdate(variable);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void overrideRemove(StringIdKey operateUserKey, VariableOverrideRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String variableStringId = info.getVariableStringId();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限操作指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, session.getToolKey());

            // 根据 info 构造 VariableKey。
            VariableKey variableKey = new VariableKey(sessionKey.getLongId(), variableStringId);

            // 调用维护服务删除变量（如果存在）。
            variableMaintainService.deleteIfExists(variableKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public VariableInspectResult systemInspect(VariableSystemInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String variableStringId = info.getVariableStringId();

            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);

            // 根据 info 构造 VariableKey。
            VariableKey variableKey = new VariableKey(sessionKey.getLongId(), variableStringId);

            // 调用维护服务获取变量。
            Variable variable = variableMaintainService.getIfExists(variableKey);

            // 如果变量不存在，则返回 null。
            if (variable == null) {
                return null;
            }

            // 构造 VariableInspectResult 并返回。
            return new VariableInspectResult(
                    sessionKey, variableStringId, variable.getType(), variable.getStringValue(),
                    variable.getLongValue(), variable.getDoubleValue(), variable.getBooleanValue(),
                    variable.getDateValue(), variable.getFileRef(), variable.getRemark()
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void systemUpsert(VariableSystemUpsertInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String variableStringId = info.getVariableStringId();
            int type = info.getType();
            String stringValue = info.getStringValue();
            Long longValue = info.getLongValue();
            Double doubleValue = info.getDoubleValue();
            Boolean booleanValue = info.getBooleanValue();
            Date dateValue = info.getDateValue();
            Long fileRef = info.getFileRef();
            String remark = info.getRemark();

            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);

            // 根据 info 创建 Variable。
            Variable variable = new Variable();
            variable.setKey(new VariableKey(sessionKey.getLongId(), variableStringId));
            variable.setToolKey(sessionMaintainService.get(sessionKey).getToolKey());
            variable.setUserKey(sessionMaintainService.get(sessionKey).getUserKey());
            variable.setType(type);
            variable.setRemark(remark);
            setValue(variable, type, stringValue, longValue, doubleValue, booleanValue, dateValue, fileRef);

            // 调用维护服务插入或更新变量。
            variableMaintainService.insertOrUpdate(variable);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemRemove(VariableSystemRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String variableStringId = info.getVariableStringId();

            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);

            // 根据 info 构造 VariableKey。
            VariableKey variableKey = new VariableKey(sessionKey.getLongId(), variableStringId);

            // 调用维护服务删除变量（如果存在）。
            variableMaintainService.deleteIfExists(variableKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void setValue(
            Variable variable, int type, String stringValue, Long longValue, Double doubleValue, Boolean booleanValue,
            Date dateValue, Long fileRef
    ) {
        switch (type) {
            case Constants.VARIABLE_TYPE_STRING:
                variable.setStringValue(stringValue);
                break;
            case Constants.VARIABLE_TYPE_LONG:
                variable.setLongValue(longValue);
                break;
            case Constants.VARIABLE_TYPE_DOUBLE:
                variable.setDoubleValue(doubleValue);
                break;
            case Constants.VARIABLE_TYPE_BOOLEAN:
                variable.setBooleanValue(booleanValue);
                break;
            case Constants.VARIABLE_TYPE_DATE:
                variable.setDateValue(dateValue);
                break;
            case Constants.VARIABLE_TYPE_FILE:
                variable.setFileRef(fileRef);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }
}
