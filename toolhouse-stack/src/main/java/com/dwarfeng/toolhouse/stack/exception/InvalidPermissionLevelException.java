package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 权限等级无效异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class InvalidPermissionLevelException extends HandlerException {

    private static final long serialVersionUID = -6707882687604162226L;

    private final int permissionLevel;

    public InvalidPermissionLevelException(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public InvalidPermissionLevelException(Throwable cause, int permissionLevel) {
        super(cause);
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String getMessage() {
        return "权限等级 " + permissionLevel + " 无效";
    }
}
