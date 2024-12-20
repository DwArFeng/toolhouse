package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoUpdateInfo;
import com.dwarfeng.toolhouse.stack.handler.VisualizerInfoOperateHandler;
import com.dwarfeng.toolhouse.stack.service.VisualizerInfoOperateService;
import org.springframework.stereotype.Service;

@Service
public class VisualizerInfoOperateServiceImpl implements VisualizerInfoOperateService {

    private final VisualizerInfoOperateHandler visualizerInfoOperateHandler;

    private final ServiceExceptionMapper sem;

    public VisualizerInfoOperateServiceImpl(
            VisualizerInfoOperateHandler visualizerInfoOperateHandler,
            ServiceExceptionMapper sem
    ) {
        this.visualizerInfoOperateHandler = visualizerInfoOperateHandler;
        this.sem = sem;
    }

    @Override
    public void create(StringIdKey operateUserKey, VisualizerInfoCreateInfo info) throws ServiceException {
        try {
            visualizerInfoOperateHandler.create(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("创建可视化器信息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void update(StringIdKey operateUserKey, VisualizerInfoUpdateInfo info) throws ServiceException {
        try {
            visualizerInfoOperateHandler.update(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新可视化器信息时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void remove(StringIdKey operateUserKey, VisualizerInfoRemoveInfo info) throws ServiceException {
        try {
            visualizerInfoOperateHandler.remove(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除可视化器信息时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
