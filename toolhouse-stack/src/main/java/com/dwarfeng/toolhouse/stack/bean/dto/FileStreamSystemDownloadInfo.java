package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件流系统下载信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileStreamSystemDownloadInfo implements Dto {

    private static final long serialVersionUID = 8293888696598428653L;

    private LongIdKey fileInfoKey;

    public FileStreamSystemDownloadInfo() {
    }

    public FileStreamSystemDownloadInfo(LongIdKey fileInfoKey) {
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
        return "FileStreamSystemDownloadInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
