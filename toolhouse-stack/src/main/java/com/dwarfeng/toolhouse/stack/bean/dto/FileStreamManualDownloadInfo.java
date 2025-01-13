package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件流手动下载信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileStreamManualDownloadInfo implements Dto {

    private static final long serialVersionUID = 4897644680456471797L;

    private LongIdKey fileInfoKey;

    public FileStreamManualDownloadInfo() {
    }

    public FileStreamManualDownloadInfo(LongIdKey fileInfoKey) {
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
        return "FileStreamManualDownloadInfo{" +
                "fileInfoKey=" + fileInfoKey +
                '}';
    }
}
