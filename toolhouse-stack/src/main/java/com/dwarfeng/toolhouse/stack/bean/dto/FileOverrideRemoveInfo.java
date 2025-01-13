package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件超控移除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileOverrideRemoveInfo implements Dto {

    private static final long serialVersionUID = 7503581602144132765L;

    private LongIdKey fileInfoKey;

    public FileOverrideRemoveInfo() {
    }

    public FileOverrideRemoveInfo(LongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    public LongIdKey getFileInfoKey() {
        return fileInfoKey;
    }

    public void setFileInfoKey(LongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    @Override
    public String toString() {
        return "FileOverrideRemoveInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
