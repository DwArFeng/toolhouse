package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.struct;

/**
 * 请求参数元数据。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class RequestParamMeta {

    /**
     * 请求参数的类型。
     */
    private final RequestParamType requestParamType;

    /**
     * 请求参数的类。
     */
    private final Class<?> type;

    public RequestParamMeta(RequestParamType requestParamType, Class<?> type) {
        this.requestParamType = requestParamType;
        this.type = type;
    }

    public RequestParamType getRequestParamType() {
        return requestParamType;
    }

    public Class<?> getType() {
        return type;
    }

    @Override
    public String toString() {
        return "RequestParamMeta{" +
                "requestParamType=" + requestParamType +
                ", type=" + type +
                '}';
    }
}
