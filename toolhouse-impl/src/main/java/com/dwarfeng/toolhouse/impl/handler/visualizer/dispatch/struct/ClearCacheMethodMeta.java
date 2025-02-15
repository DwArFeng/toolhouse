package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.struct;

import java.lang.reflect.Method;

/**
 * 清理缓存方法元数据。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class ClearCacheMethodMeta {

    private final Object bean;
    private final Method method;

    public ClearCacheMethodMeta(Object bean, Method method) {
        this.bean = bean;
        this.method = method;
    }

    public Object getBean() {
        return bean;
    }

    public Method getMethod() {
        return method;
    }

    @Override
    public String toString() {
        return "ClearCacheMethodMeta{" +
                "bean=" + bean +
                ", method=" + method +
                '}';
    }
}
