package com.dwarfeng.toolhouse.sdk.handler;

import com.dwarfeng.toolhouse.stack.handler.Visualizer;

/**
 * 可视化器提供器。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public interface VisualizerProvider {

    /**
     * 返回提供器是否支持指定的类型。
     *
     * @param type 指定的类型。
     * @return 提供器是否支持指定的类型。
     */
    boolean supportType(String type);

    /**
     * 提供可视化器。
     *
     * <p>
     * 需要注意的是，提供的可视化器应该是单例的，即连续调用多次该方法，返回的可视化器应该是同一个实例。
     *
     * @return 提供的可视化器。
     */
    Visualizer provide();
}
