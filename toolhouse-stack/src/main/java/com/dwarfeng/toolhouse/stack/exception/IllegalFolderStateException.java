package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;

/**
 * 文件夹状态非法异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class IllegalFolderStateException extends HandlerException {

    private static final long serialVersionUID = -799364585571227514L;

    private final LongIdKey folderKey;

    public IllegalFolderStateException(LongIdKey folderKey) {
        this.folderKey = folderKey;
    }

    public IllegalFolderStateException(Throwable cause, LongIdKey folderKey) {
        super(cause);
        this.folderKey = folderKey;
    }

    @Override
    public String getMessage() {
        return "文件夹 " + folderKey + " 状态异常: 它是否没绑定工具柜?";
    }
}
