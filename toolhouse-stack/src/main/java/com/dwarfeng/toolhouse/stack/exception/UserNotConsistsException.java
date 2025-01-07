package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 用户不一致异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class UserNotConsistsException extends HandlerException {

    private static final long serialVersionUID = 6570682468681700278L;

    private final StringIdKey expectedUserKey;
    private final StringIdKey actualUserKey;

    public UserNotConsistsException(StringIdKey expectedUserKey, StringIdKey actualUserKey) {
        this.expectedUserKey = expectedUserKey;
        this.actualUserKey = actualUserKey;
    }

    public UserNotConsistsException(Throwable cause, StringIdKey expectedUserKey, StringIdKey actualUserKey) {
        super(cause);
        this.expectedUserKey = expectedUserKey;
        this.actualUserKey = actualUserKey;
    }

    @Override
    public String getMessage() {
        return "用户不一致, 期望的用户键为 " + expectedUserKey + ", 实际的用户键为 " + actualUserKey;
    }
}
