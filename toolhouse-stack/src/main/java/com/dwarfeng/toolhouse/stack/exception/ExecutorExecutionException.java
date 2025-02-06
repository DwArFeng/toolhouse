package com.dwarfeng.toolhouse.stack.exception;

/**
 * 执行器执行异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ExecutorExecutionException extends ExecutorException {

    private static final long serialVersionUID = -5999513889812557169L;

    public ExecutorExecutionException() {
    }

    public ExecutorExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public ExecutorExecutionException(String message) {
        super(message);
    }

    public ExecutorExecutionException(Throwable cause) {
        super(cause);
    }
}
