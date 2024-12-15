package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 用户对工具柜没有权限异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class UserNotPermittedForCabinetException extends HandlerException {

    private static final long serialVersionUID = 7032475295969389269L;

    private final StringIdKey userKey;
    private final LongIdKey cabinetKey;

    public UserNotPermittedForCabinetException(StringIdKey userKey, LongIdKey cabinetKey) {
        this.userKey = userKey;
        this.cabinetKey = cabinetKey;
    }

    public UserNotPermittedForCabinetException(Throwable cause, StringIdKey userKey, LongIdKey cabinetKey) {
        super(cause);
        this.userKey = userKey;
        this.cabinetKey = cabinetKey;
    }

    @Override
    public String getMessage() {
        return "用户 " + userKey + " 没有操作工具柜 " + cabinetKey + " 的权限";
    }
}
