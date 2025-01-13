package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件超控下载信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileOverrideDownloadInfo implements Dto {

    private static final long serialVersionUID = -1186141237695778285L;

    private LongIdKey fileInfoKey;

    public FileOverrideDownloadInfo() {
    }

    public FileOverrideDownloadInfo(LongIdKey fileInfoKey) {
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
        return "FileOverrideDownloadInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
