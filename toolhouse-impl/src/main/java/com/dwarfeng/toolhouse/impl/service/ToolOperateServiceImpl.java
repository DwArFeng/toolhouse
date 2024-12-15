package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.ToolCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ToolUpdateInfo;
import com.dwarfeng.toolhouse.stack.handler.ToolOperateHandler;
import com.dwarfeng.toolhouse.stack.service.ToolOperateService;
import org.springframework.stereotype.Service;

@Service
public class ToolOperateServiceImpl implements ToolOperateService {

    private final ToolOperateHandler toolOperateHandler;

    private final ServiceExceptionMapper sem;

    public ToolOperateServiceImpl(ToolOperateHandler toolOperateHandler, ServiceExceptionMapper sem) {
        this.toolOperateHandler = toolOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createTool(StringIdKey userKey, ToolCreateInfo toolCreateInfo)
            throws ServiceException {
        try {
            return toolOperateHandler.createTool(userKey, toolCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("创建工具时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateTool(StringIdKey userKey, ToolUpdateInfo toolUpdateInfo)
            throws ServiceException {
        try {
            toolOperateHandler.updateTool(userKey, toolUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新工具时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeTool(StringIdKey userKey, LongIdKey toolKey) throws ServiceException {
        try {
            toolOperateHandler.removeTool(userKey, toolKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除工具时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
