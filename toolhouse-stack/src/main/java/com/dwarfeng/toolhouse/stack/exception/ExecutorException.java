package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 执行器异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ExecutorException extends HandlerException {

    private static final long serialVersionUID = -4448959684166881164L;

    public ExecutorException() {
    }

    public ExecutorException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutorException(String message) {
        super(message);
    }

    public ExecutorException(Throwable cause) {
        super(cause);
    }
}
