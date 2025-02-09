package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.handler.VisualizeClientHandler;
import com.dwarfeng.toolhouse.stack.service.VisualizeClientService;
import org.springframework.stereotype.Service;

@Service
public class VisualizeClientServiceImpl implements VisualizeClientService {

    private final VisualizeClientHandler visualizeClientHandler;

    private final ServiceExceptionMapper sem;

    public VisualizeClientServiceImpl(VisualizeClientHandler visualizeClientHandler, ServiceExceptionMapper sem) {
        this.visualizeClientHandler = visualizeClientHandler;
        this.sem = sem;
    }

    @Override
    public VisualizerCallResult manualVisualizerCall(StringIdKey operateUserKey, VisualizerManualCallInfo info)
            throws ServiceException {
        try {
            return visualizeClientHandler.manualVisualizerCall(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动调用可视化器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VisualizerStreamCallResult manualVisualizerStreamCall(
            StringIdKey operateUserKey, VisualizerManualStreamCallInfo info
    ) throws ServiceException {
        try {
            return visualizeClientHandler.manualVisualizerStreamCall(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动流式调用可视化器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VisualizerCallResult overrideVisualizerCall(
            StringIdKey operateUserKey, VisualizerOverrideCallInfo info
    ) throws ServiceException {
        try {
            return visualizeClientHandler.overrideVisualizerCall(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控调用可视化器时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public VisualizerStreamCallResult overrideVisualizerStreamCall(
            StringIdKey operateUserKey, VisualizerOverrideStreamCallInfo info
    ) throws ServiceException {
        try {
            return visualizeClientHandler.overrideVisualizerStreamCall(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控流式调用可视化器时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
