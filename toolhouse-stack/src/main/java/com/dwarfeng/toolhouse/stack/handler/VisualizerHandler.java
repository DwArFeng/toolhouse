package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

import java.util.List;

/**
 * 可视化器处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizerHandler extends Handler {

    /**
     * 获取所有的可视化器。
     *
     * @return 所有的可视化器。
     * @throws HandlerException 处理器异常。
     */
    List<Visualizer> all() throws HandlerException;

    /**
     * 寻找指定的可视化器。
     *
     * @param type 可视化器的类型。
     * @return 符合可视化类型的指定的可视化器。
     * @throws HandlerException 执行器。
     */
    Visualizer find(String type) throws HandlerException;
}
