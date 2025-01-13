package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.io.InputStream;

/**
 * 文件流手动更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileStreamManualUpdateInfo implements Dto {

    private static final long serialVersionUID = 3283908390094344157L;

    private LongIdKey fileInfoKey;
    private String originName;
    private long length;
    private InputStream content;

    public FileStreamManualUpdateInfo() {
    }

    public FileStreamManualUpdateInfo(LongIdKey fileInfoKey, String originName, long length, InputStream content) {
        this.fileInfoKey = fileInfoKey;
        this.originName = originName;
        this.length = length;
        this.content = content;
    }

    public LongIdKey getFileInfoKey() {
        return fileInfoKey;
    }

    public void setFileInfoKey(LongIdKey fileInfoKey) {
        this.fileInfoKey = fileInfoKey;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public long getLength() {
        return length;
    }

    public void setLength(long length) {
        this.length = length;
    }

    public InputStream getContent() {
        return content;
    }

    public void setContent(InputStream content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileStreamManualUpdateInfo{" +
                "fileInfoKey=" + fileInfoKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
