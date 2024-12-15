package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 文件夹不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FolderNotExistsException extends HandlerException {

    private static final long serialVersionUID = 4373399536056703348L;

    private final LongIdKey folderKey;

    public FolderNotExistsException(LongIdKey folderKey) {
        this.folderKey = folderKey;
    }

    public FolderNotExistsException(Throwable cause, LongIdKey folderKey) {
        super(cause);
        this.folderKey = folderKey;
    }

    @Override
    public String getMessage() {
        return "文件夹 " + folderKey + " 不存在";
    }
}
