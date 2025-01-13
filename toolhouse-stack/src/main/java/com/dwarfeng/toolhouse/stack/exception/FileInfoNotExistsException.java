package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 文件信息不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = -628160660393573237L;

    private final LongIdKey fileInfoKey;

    public FileInfoNotExistsException(LongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    public FileInfoNotExistsException(Throwable cause, LongIdKey fileInfoKey) {
        super(cause);
        this.fileInfoKey = fileInfoKey;
    }

    @Override
    public String getMessage() {
        return "文件信息 " + fileInfoKey + " 不存在";
    }
}
