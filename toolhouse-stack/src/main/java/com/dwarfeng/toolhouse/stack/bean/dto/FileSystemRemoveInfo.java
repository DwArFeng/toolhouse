package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件系统删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileSystemRemoveInfo implements Dto {

    private static final long serialVersionUID = -6774337428482027217L;

    private LongIdKey fileInfoKey;

    public FileSystemRemoveInfo() {
    }

    public FileSystemRemoveInfo(LongIdKey fileInfoKey) {
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
        return "FileSystemRemoveInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
