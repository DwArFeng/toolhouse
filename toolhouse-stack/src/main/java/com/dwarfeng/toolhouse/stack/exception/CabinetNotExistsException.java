package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 工具柜不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CabinetNotExistsException extends HandlerException {

    private static final long serialVersionUID = 7776074509303130146L;

    private final LongIdKey cabinetKey;

    public CabinetNotExistsException(LongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public CabinetNotExistsException(Throwable cause, LongIdKey cabinetKey) {
        super(cause);
        this.cabinetKey = cabinetKey;
    }

    @Override
    public String getMessage() {
        return "工具柜 " + cabinetKey + " 不存在";
    }
}
