package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件手动删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileManualRemoveInfo implements Dto {

    private static final long serialVersionUID = 3970480561489604799L;

    private LongIdKey fileInfoKey;

    public FileManualRemoveInfo() {
    }

    public FileManualRemoveInfo(LongIdKey fileInfoKey) {
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
        return "FileManualRemoveInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
