package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 可视化缓存处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizeCacheHandler extends Handler {

    /**
     * 清除缓存。
     *
     * @throws HandlerException 处理器异常。
     */
    void clearCache() throws HandlerException;
}
