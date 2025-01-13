package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 文件系统更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileSystemUpdateInfo implements Dto {

    private static final long serialVersionUID = 6384499293138928278L;

    private LongIdKey fileInfoKey;
    private String originName;
    private byte[] content;

    public FileSystemUpdateInfo() {
    }

    public FileSystemUpdateInfo(LongIdKey fileInfoKey, String originName, byte[] content) {
        this.fileInfoKey = fileInfoKey;
        this.originName = originName;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileSystemUpdateInfo{" +
                "fileInfoKey=" + fileInfoKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
