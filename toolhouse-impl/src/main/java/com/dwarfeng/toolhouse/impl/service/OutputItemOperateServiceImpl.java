package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemManualRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemManualUpsertInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemOverrideRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemOverrideUpsertInfo;
import com.dwarfeng.toolhouse.stack.handler.OutputItemOperateHandler;
import com.dwarfeng.toolhouse.stack.service.OutputItemOperateService;
import org.springframework.stereotype.Service;

@Service
public class OutputItemOperateServiceImpl implements OutputItemOperateService {

    private final OutputItemOperateHandler outputItemOperateHandler;

    private final ServiceExceptionMapper sem;

    public OutputItemOperateServiceImpl(OutputItemOperateHandler outputItemOperateHandler, ServiceExceptionMapper sem) {
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
}
