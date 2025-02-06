package com.dwarfeng.toolhouse.stack.exception;

/**
 * 不支持的执行器类型异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class UnsupportedExecutorTypeException extends ExecutorException {

    private static final long serialVersionUID = -281401979625768025L;

    private final String type;

    public UnsupportedExecutorTypeException(String type) {
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "不支持的执行器类型: " + type;
    }
}
