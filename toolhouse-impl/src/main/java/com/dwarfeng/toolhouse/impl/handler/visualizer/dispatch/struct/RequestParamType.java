package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.struct;

/**
 * 参数类型。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public enum RequestParamType {

    /**
     * 可视化器上下文。
     */
    VISUALIZER_CONTEXT,

    /**
     * 可视化器调用者上下文。
     */
    VISUALIZER_CALLER_CONTEXT,

    /**
     * 请求文本。
     */
    REQUEST_TEXT,

    /**
     * 请求流。
     */
    REQUEST_STREAM,

    /**
     * 结果上下文。
     */
    RESULT_CONTEXT,
}
