package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件系统下载信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileSystemDownloadInfo implements Dto {

    private static final long serialVersionUID = 7009095975366371338L;

    private LongIdKey fileInfoKey;

    public FileSystemDownloadInfo() {
    }

    public FileSystemDownloadInfo(LongIdKey fileInfoKey) {
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
        return "FileSystemDownloadInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
