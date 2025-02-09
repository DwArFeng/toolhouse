package com.dwarfeng.toolhouse.stack.exception;

/**
 * 可视化器执行异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerExecutionException extends VisualizerException {

    private static final long serialVersionUID = -4455102847382155948L;

    public VisualizerExecutionException() {
    }

    public VisualizerExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisualizerExecutionException(String message) {
        super(message);
    }

    public VisualizerExecutionException(Throwable cause) {
        super(cause);
    }
}
