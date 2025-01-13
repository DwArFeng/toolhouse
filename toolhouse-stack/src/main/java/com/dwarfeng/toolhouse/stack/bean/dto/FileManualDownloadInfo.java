package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件手动下载信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileManualDownloadInfo implements Dto {

    private static final long serialVersionUID = 8251403453887934028L;

    private LongIdKey fileInfoKey;

    public FileManualDownloadInfo() {
    }

    public FileManualDownloadInfo(LongIdKey fileInfoKey) {
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
        return "FileManualDownloadInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
