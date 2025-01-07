package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.bean.key.FastJsonTaskItemKey;
import com.dwarfeng.toolhouse.stack.bean.entity.OutputItem;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 输出项。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonOutputItem implements Bean {

    private static final long serialVersionUID = 8248008285437092674L;

    public static FastJsonOutputItem of(OutputItem outputItem) {
        if (Objects.isNull(outputItem)) {
            return null;
        } else {
            return new FastJsonOutputItem(
                    FastJsonTaskItemKey.of(outputItem.getKey()),
                    FastJsonLongIdKey.of(outputItem.getSessionKey()),
                    FastJsonLongIdKey.of(outputItem.getToolKey()),
                    FastJsonStringIdKey.of(outputItem.getUserKey()),
                    outputItem.getType(),
                    outputItem.getStringValue(),
                    outputItem.getLongValue(),
                    outputItem.getDoubleValue(),
                    outputItem.getBooleanValue(),
                    outputItem.getDateValue(),
                    outputItem.getFileRef(),
                    outputItem.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonTaskItemKey key;

    @JSONField(name = "session_key", ordinal = 2)
    private FastJsonLongIdKey sessionKey;

    @JSONField(name = "tool_key", ordinal = 3)
    private FastJsonLongIdKey toolKey;

    @JSONField(name = "user_key", ordinal = 4)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "type", ordinal = 5)
    private int type;

    @JSONField(name = "string_value", ordinal = 6)
    private String stringValue;

    @JSONField(name = "long_value", ordinal = 7)
    private Long longValue;

    @JSONField(name = "double_value", ordinal = 8)
    private Double doubleValue;

    @JSONField(name = "boolean_value", ordinal = 9)
    private Boolean booleanValue;

    @JSONField(name = "date_value", ordinal = 10)
    private Date dateValue;

    @JSONField(name = "file_ref", ordinal = 11)
    private Long fileRef;

    @JSONField(name = "remark", ordinal = 12)
    private String remark;

    public FastJsonOutputItem() {
    }

    public FastJsonOutputItem(
            FastJsonTaskItemKey key, FastJsonLongIdKey sessionKey, FastJsonLongIdKey toolKey,
            FastJsonStringIdKey userKey, int type, String stringValue, Long longValue, Double doubleValue,
            Boolean booleanValue, Date dateValue, Long fileRef, String remark
    ) {
        this.key = key;
        this.sessionKey = sessionKey;
        this.toolKey = toolKey;
        this.userKey = userKey;
        this.type = type;
        this.stringValue = stringValue;
        this.longValue = longValue;
        this.doubleValue = doubleValue;
        this.booleanValue = booleanValue;
        this.dateValue = dateValue;
        this.fileRef = fileRef;
        this.remark = remark;
    }

    public FastJsonTaskItemKey getKey() {
        return key;
    }

    public void setKey(FastJsonTaskItemKey key) {
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getStringValue() {
        return stringValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Long getLongValue() {
        return longValue;
    }

    public void setLongValue(Long longValue) {
        this.longValue = longValue;
    }

    public Double getDoubleValue() {
        return doubleValue;
    }

    public void setDoubleValue(Double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public Boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(Boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public Date getDateValue() {
        return dateValue;
    }

    public void setDateValue(Date dateValue) {
        this.dateValue = dateValue;
    }

    public Long getFileRef() {
        return fileRef;
    }

    public void setFileRef(Long fileRef) {
        this.fileRef = fileRef;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "FastJsonOutputItem{" +
                "key=" + key +
                ", sessionKey=" + sessionKey +
                ", toolKey=" + toolKey +
                ", userKey=" + userKey +
                ", type=" + type +
                ", stringValue='" + stringValue + '\'' +
                ", longValue=" + longValue +
                ", doubleValue=" + doubleValue +
                ", booleanValue=" + booleanValue +
                ", dateValue=" + dateValue +
                ", fileRef=" + fileRef +
                ", remark='" + remark + '\'' +
                '}';
    }
}
