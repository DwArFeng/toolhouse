package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 支持处理器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface SupportHandler extends Handler {

    /**
     * 重置执行器。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetExecutor() throws HandlerException;

    /**
     * 重置可视化器。
     *
     * @throws HandlerException 处理器异常。
     */
    void resetVisualizer() throws HandlerException;
}
