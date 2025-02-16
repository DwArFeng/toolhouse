package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.StartableHandler;

/**
 * 重置处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ResetHandler extends StartableHandler {

    /**
     * 重置执行功能。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetExecute() throws HandlerException;

    /**
     * 重置可视化功能。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetVisualize() throws HandlerException;
}
