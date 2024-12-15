package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 工具状态非法异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class IllegalToolStateException extends HandlerException {

    private static final long serialVersionUID = 5341168113041189343L;

    private final LongIdKey toolKey;

    public IllegalToolStateException(LongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public IllegalToolStateException(Throwable cause, LongIdKey toolKey) {
        super(cause);
        this.toolKey = toolKey;
    }

    @Override
    public String getMessage() {
        return "工具 " + toolKey + " 状态异常: 它是否没绑定工具柜?";
    }
}
