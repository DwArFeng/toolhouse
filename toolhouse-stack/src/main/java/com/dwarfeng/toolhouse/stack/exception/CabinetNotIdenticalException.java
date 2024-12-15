package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 笔记工具柜不一致异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CabinetNotIdenticalException extends HandlerException {

    private static final long serialVersionUID = 5210558700913366583L;

    private final LongIdKey parentSetKey;
    private final LongIdKey childSetKey;

    public CabinetNotIdenticalException(LongIdKey parentSetKey, LongIdKey childSetKey) {
        this.parentSetKey = parentSetKey;
        this.childSetKey = childSetKey;
    }

    public CabinetNotIdenticalException(
            Throwable cause, LongIdKey parentSetKey, LongIdKey childSetKey
    ) {
        super(cause);
        this.parentSetKey = parentSetKey;
        this.childSetKey = childSetKey;
    }

    @Override
    public String getMessage() {
        return "父项文件夹所属工具柜 " + parentSetKey + " 与子项文件夹所属工具柜 " + childSetKey + " 不一致";
    }
}
