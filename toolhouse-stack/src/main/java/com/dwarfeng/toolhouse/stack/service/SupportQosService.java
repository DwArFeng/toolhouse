package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;

/**
 * 支持 QoS 服务。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface SupportQosService extends Service {

    /**
     * 重置执行器。
     *
     * @throws ServiceException 服务异常。
     */
    void resetExecutor() throws ServiceException;

    /**
     * 重置可视化器。
     *
     * @throws ServiceException 服务异常。
     */
    void resetVisualizer() throws ServiceException;
}
