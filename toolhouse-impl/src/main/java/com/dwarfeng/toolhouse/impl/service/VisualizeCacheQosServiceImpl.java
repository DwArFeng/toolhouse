package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.handler.VisualizeCacheHandler;
import com.dwarfeng.toolhouse.stack.service.VisualizeCacheQosService;
import org.springframework.stereotype.Service;

@Service
public class VisualizeCacheQosServiceImpl implements VisualizeCacheQosService {

    private final VisualizeCacheHandler visualizeCacheHandler;

    private final ServiceExceptionMapper sem;

    public VisualizeCacheQosServiceImpl(VisualizeCacheHandler visualizeCacheHandler, ServiceExceptionMapper sem) {
        this.visualizeCacheHandler = visualizeCacheHandler;
        this.sem = sem;
    }

    @Override
    public void clearCache() throws ServiceException {
        try {
            visualizeCacheHandler.clearCache();
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("清除缓存时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
