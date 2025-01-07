package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 会话不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class SessionNotExistsException extends HandlerException {

    private static final long serialVersionUID = -1437040208410294198L;

    private final LongIdKey sessionKey;

    public SessionNotExistsException(LongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public SessionNotExistsException(Throwable cause, LongIdKey sessionKey) {
        super(cause);
        this.sessionKey = sessionKey;
    }

    @Override
    public String getMessage() {
        return "会话 " + sessionKey + " 不存在";
    }
}
