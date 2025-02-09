package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 可视化缓存 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizeCacheQosService extends Service {

    /**
     * 清除缓存。
     *
     * @throws ServiceException 服务异常。
     */
    void clearCache() throws ServiceException;
}
