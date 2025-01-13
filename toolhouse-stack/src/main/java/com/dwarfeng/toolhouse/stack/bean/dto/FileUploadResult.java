package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件上传结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileUploadResult implements Dto {

    private static final long serialVersionUID = -8664110966716657448L;
    
    private LongIdKey fileInfoKey;

    public FileUploadResult() {
    }

    public FileUploadResult(LongIdKey fileInfoKey) {
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
        return "FileUploadResult{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
