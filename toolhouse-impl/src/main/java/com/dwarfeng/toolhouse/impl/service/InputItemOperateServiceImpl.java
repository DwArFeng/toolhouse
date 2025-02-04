package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.InputItemManualRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.InputItemManualUpsertInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.InputItemOverrideRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.InputItemOverrideUpsertInfo;
import com.dwarfeng.toolhouse.stack.handler.InputItemOperateHandler;
import com.dwarfeng.toolhouse.stack.service.InputItemOperateService;
import org.springframework.stereotype.Service;

@Service
public class InputItemOperateServiceImpl implements InputItemOperateService {

    private final InputItemOperateHandler inputItemOperateHandler;

    private final ServiceExceptionMapper sem;

    public InputItemOperateServiceImpl(InputItemOperateHandler inputItemOperateHandler, ServiceExceptionMapper sem) {
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
}
