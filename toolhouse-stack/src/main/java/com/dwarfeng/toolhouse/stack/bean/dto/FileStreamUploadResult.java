package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件流上传结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileStreamUploadResult implements Dto {

    private static final long serialVersionUID = -7759374133383370746L;

    private LongIdKey fileInfoKey;

    public FileStreamUploadResult() {
    }

    public FileStreamUploadResult(LongIdKey fileInfoKey) {
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
        return "FileStreamUploadResult{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
