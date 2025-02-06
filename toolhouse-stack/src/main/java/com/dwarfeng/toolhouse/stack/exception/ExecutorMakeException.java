package com.dwarfeng.toolhouse.stack.exception;

/**
 * 执行器构造异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ExecutorMakeException extends ExecutorException {

    private static final long serialVersionUID = -671175477019029734L;

    public ExecutorMakeException() {
    }

    public ExecutorMakeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutorMakeException(String message) {
        super(message);
    }

    public ExecutorMakeException(Throwable cause) {
        super(cause);
    }
}
