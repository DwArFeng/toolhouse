package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 可视化器异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerException extends HandlerException {

    private static final long serialVersionUID = 496231375404955760L;

    public VisualizerException() {
    }

    public VisualizerException(String message, Throwable cause) {
        super(message, cause);
    }

    public VisualizerException(String message) {
        super(message);
    }

    public VisualizerException(Throwable cause) {
        super(cause);
    }
}
