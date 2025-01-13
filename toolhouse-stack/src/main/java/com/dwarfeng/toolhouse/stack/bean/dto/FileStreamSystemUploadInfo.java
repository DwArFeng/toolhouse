package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.io.InputStream;

/**
 * 文件流系统上传信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileStreamSystemUploadInfo implements Dto {

    private static final long serialVersionUID = -3311381871420454603L;

    private LongIdKey sessionKey;
    private String originName;
    private long length;
    private InputStream content;

    public FileStreamSystemUploadInfo() {
    }

    public FileStreamSystemUploadInfo(LongIdKey sessionKey, String originName, long length, InputStream content) {
        this.sessionKey = sessionKey;
        this.originName = originName;
        this.length = length;
        this.content = content;
    }

    public LongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(LongIdKey sessionKey) {
        this.sessionKey = sessionKey;
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
        return "FileStreamSystemUploadInfo{" +
                "sessionKey=" + sessionKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", content=" + content +
                '}';
    }
}
