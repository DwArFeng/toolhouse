package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件流超控下载信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileStreamOverrideDownloadInfo implements Dto {

    private static final long serialVersionUID = -1713541749026376856L;

    private LongIdKey fileInfoKey;

    public FileStreamOverrideDownloadInfo() {
    }

    public FileStreamOverrideDownloadInfo(LongIdKey fileInfoKey) {
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
        return "FileStreamOverrideDownloadInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
