package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Arrays;

/**
 * 文件超控上传信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FileOverrideUploadInfo implements Dto {

    private static final long serialVersionUID = -6497162639396026263L;

    private LongIdKey sessionKey;
    private String originName;
    private byte[] content;

    public FileOverrideUploadInfo() {
    }

    public FileOverrideUploadInfo(LongIdKey sessionKey, String originName, byte[] content) {
        this.sessionKey = sessionKey;
        this.originName = originName;
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

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "FileOverrideUploadInfo{" +
                "sessionKey=" + sessionKey +
                ", originName='" + originName + '\'' +
                ", content=" + Arrays.toString(content) +
                '}';
    }
}
