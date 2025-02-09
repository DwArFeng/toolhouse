package com.dwarfeng.toolhouse.stack.exception;

/**
 * 不支持的可视化器类型异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class UnsupportedVisualizerTypeException extends VisualizerException {

    private static final long serialVersionUID = 1850907992390624933L;

    private final String type;

    public UnsupportedVisualizerTypeException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的可视化器类型: " + type;
    }
}
