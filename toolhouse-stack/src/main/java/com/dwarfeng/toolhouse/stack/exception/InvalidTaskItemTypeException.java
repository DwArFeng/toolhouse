package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 无效的任务项类型异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class InvalidTaskItemTypeException extends HandlerException {

    private static final long serialVersionUID = 877447714422924970L;

    private final int type;

    public InvalidTaskItemTypeException(int type) {
        this.type = type;
    }

    public InvalidTaskItemTypeException(Throwable cause, int type) {
        super(cause);
        this.type = type;
    }

    @Override
    public String getMessage() {
        return "无效的任务项类型: " + type;
    }
}
