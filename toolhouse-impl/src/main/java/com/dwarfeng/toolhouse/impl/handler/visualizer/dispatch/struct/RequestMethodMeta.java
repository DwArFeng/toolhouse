package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.struct;

import java.lang.reflect.Method;
import java.util.List;

/**
 * 请求方法元数据。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class RequestMethodMeta {

    private final String functionName;
    private final Object bean;
    private final Method method;
    private final List<RequestParamMeta> requestParamMetas;

    public RequestMethodMeta(
            String functionName, Object bean, Method method, List<RequestParamMeta> requestParamMetas
    ) {
        this.functionName = functionName;
        this.bean = bean;
        this.method = method;
        this.requestParamMetas = requestParamMetas;
    }

    public String getFunctionName() {
        return functionName;
    }

    public Object getBean() {
        return bean;
    }

    public Method getMethod() {
        return method;
    }

    public List<RequestParamMeta> getRequestParamMetas() {
        return requestParamMetas;
    }

    @Override
    public String toString() {
        return "RequestMethodMeta{" +
                "functionName='" + functionName + '\'' +
                ", bean=" + bean +
                ", method=" + method +
                ", requestParamMetas=" + requestParamMetas +
                '}';
    }
}
