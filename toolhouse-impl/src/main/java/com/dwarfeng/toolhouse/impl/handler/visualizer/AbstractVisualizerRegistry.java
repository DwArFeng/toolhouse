package com.dwarfeng.toolhouse.impl.handler.visualizer;

/**
 * 抽象可视化器注册。
 *
 * @author DwArFeng
 * @see com.dwarfeng.toolhouse.sdk.handler.visualizer.AbstractVisualizerRegistry
 * @since beta-1.0.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractVisualizerRegistry extends com.dwarfeng.toolhouse.sdk.handler.visualizer.AbstractVisualizerRegistry {

    public AbstractVisualizerRegistry() {
    }

    public AbstractVisualizerRegistry(String visualizerType) {
        super(visualizerType);
    }
}
