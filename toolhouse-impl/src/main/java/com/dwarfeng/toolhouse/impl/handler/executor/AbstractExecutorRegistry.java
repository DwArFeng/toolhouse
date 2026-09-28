package com.dwarfeng.toolhouse.impl.handler.executor;

/**
 * 抽象执行器注册。
 *
 * @author DwArFeng
 * @see com.dwarfeng.toolhouse.sdk.handler.executor.AbstractExecutorRegistry
 * @since beta-1.0.0
 * @deprecated 该对象已经被废弃，请使用 sdk 模块下的对应对象代替。
 */
@Deprecated
public abstract class AbstractExecutorRegistry extends com.dwarfeng.toolhouse.sdk.handler.executor.AbstractExecutorRegistry {

    public AbstractExecutorRegistry() {
    }

    public AbstractExecutorRegistry(String executorType) {
        super(executorType);
    }
}
