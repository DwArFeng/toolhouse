package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 无效的变量类型异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class InvalidVariableTypeException extends HandlerException {

    private static final long serialVersionUID = -4632550568844485882L;

    private final int type;

    public InvalidVariableTypeException(int type) {
        this.type = type;
    }

    public InvalidVariableTypeException(Throwable cause, int type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "无效的变量类型: " + type;
    }
}
