package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.stack.bean.entity.FileInfo;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 文件信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonFileInfo implements Bean {

    private static final long serialVersionUID = 5349676149081001335L;

    public static FastJsonFileInfo of(FileInfo fileInfo) {
        if (Objects.isNull(fileInfo)) {
            return null;
        } else {
            return new FastJsonFileInfo(
                    FastJsonLongIdKey.of(fileInfo.getKey()),
                    FastJsonLongIdKey.of(fileInfo.getSessionKey()),
                    FastJsonLongIdKey.of(fileInfo.getToolKey()),
                    FastJsonStringIdKey.of(fileInfo.getUserKey()),
                    fileInfo.getOriginName(),
                    fileInfo.getLength(),
                    fileInfo.getCreatedDate(),
                    fileInfo.getModifiedDate(),
                    fileInfo.getInspectedDate(),
                    fileInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "session_key", ordinal = 2)
    private FastJsonLongIdKey sessionKey;

    @JSONField(name = "tool_key", ordinal = 3)
    private FastJsonLongIdKey toolKey;

    @JSONField(name = "user_key", ordinal = 4)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "origin_name", ordinal = 5)
    private String originName;

    @JSONField(name = "length", ordinal = 6)
    private long length;

    @JSONField(name = "created_date", ordinal = 7)
    private Date createdDate;

    @JSONField(name = "modified_date", ordinal = 8)
    private Date modifiedDate;

    @JSONField(name = "inspected_date", ordinal = 9)
    private Date inspectedDate;

    @JSONField(name = "remark", ordinal = 10)
    private String remark;

    public FastJsonFileInfo() {
    }

    public FastJsonFileInfo(
            FastJsonLongIdKey key, FastJsonLongIdKey sessionKey, FastJsonLongIdKey toolKey,
            FastJsonStringIdKey userKey, String originName, long length, Date createdDate, Date modifiedDate,
            Date inspectedDate, String remark
    ) {
        this.key = key;
        this.sessionKey = sessionKey;
        this.toolKey = toolKey;
        this.userKey = userKey;
        this.originName = originName;
        this.length = length;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
        this.inspectedDate = inspectedDate;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(FastJsonLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public FastJsonLongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(FastJsonLongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public FastJsonStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(FastJsonStringIdKey userKey) {
        this.userKey = userKey;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getModifiedDate() {
        return modifiedDate;
    }

    public void setModifiedDate(Date modifiedDate) {
        this.modifiedDate = modifiedDate;
    }

    public Date getInspectedDate() {
        return inspectedDate;
    }

    public void setInspectedDate(Date inspectedDate) {
        this.inspectedDate = inspectedDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonFileInfo{" +
                "key=" + key +
                ", sessionKey=" + sessionKey +
                ", toolKey=" + toolKey +
                ", userKey=" + userKey +
                ", originName='" + originName + '\'' +
                ", length=" + length +
                ", createdDate=" + createdDate +
                ", modifiedDate=" + modifiedDate +
                ", inspectedDate=" + inspectedDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
