package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.stack.bean.entity.FileInfo;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 文件信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonFileInfo implements Bean {

    private static final long serialVersionUID = 109988009457191373L;

    public static JSFixedFastJsonFileInfo of(FileInfo fileInfo) {
        if (Objects.isNull(fileInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonFileInfo(
                    JSFixedFastJsonLongIdKey.of(fileInfo.getKey()),
                    JSFixedFastJsonLongIdKey.of(fileInfo.getSessionKey()),
                    JSFixedFastJsonLongIdKey.of(fileInfo.getToolKey()),
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
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "session_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey sessionKey;

    @JSONField(name = "tool_key", ordinal = 3)
    private JSFixedFastJsonLongIdKey toolKey;

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

    public JSFixedFastJsonFileInfo() {
    }

    public JSFixedFastJsonFileInfo(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey sessionKey, JSFixedFastJsonLongIdKey toolKey,
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

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(JSFixedFastJsonLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public JSFixedFastJsonLongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(JSFixedFastJsonLongIdKey toolKey) {
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
        return "JSFixedFastJsonFileInfo{" +
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
