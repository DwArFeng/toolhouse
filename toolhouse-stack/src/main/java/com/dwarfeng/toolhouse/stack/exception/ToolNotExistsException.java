package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 工具不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ToolNotExistsException extends HandlerException {

    private static final long serialVersionUID = 933907970966882113L;

    private final LongIdKey toolKey;

    public ToolNotExistsException(LongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public ToolNotExistsException(Throwable cause, LongIdKey toolKey) {
        super(cause);
        this.toolKey = toolKey;
    }

    @Override
    public String getMessage() {
        return "工具 " + toolKey + " 不存在";
    }
}
