package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.bean.key.JSFixedFastJsonTaskItemKey;
import com.dwarfeng.toolhouse.stack.bean.entity.InputItem;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 输入项。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonInputItem implements Bean {

    private static final long serialVersionUID = -5948352430004530001L;

    public static JSFixedFastJsonInputItem of(InputItem inputItem) {
        if (Objects.isNull(inputItem)) {
            return null;
        } else {
            return new JSFixedFastJsonInputItem(
                    JSFixedFastJsonTaskItemKey.of(inputItem.getKey()),
                    JSFixedFastJsonLongIdKey.of(inputItem.getSessionKey()),
                    JSFixedFastJsonLongIdKey.of(inputItem.getToolKey()),
                    FastJsonStringIdKey.of(inputItem.getUserKey()),
                    inputItem.getType(),
                    inputItem.getStringValue(),
                    inputItem.getLongValue(),
                    inputItem.getDoubleValue(),
                    inputItem.getBooleanValue(),
                    inputItem.getDateValue(),
                    inputItem.getFileRef(),
                    inputItem.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonTaskItemKey key;

    @JSONField(name = "session_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey sessionKey;

    @JSONField(name = "tool_key", ordinal = 3)
    private JSFixedFastJsonLongIdKey toolKey;

    @JSONField(name = "user_key", ordinal = 4)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "type", ordinal = 5)
    private int type;

    @JSONField(name = "string_value", ordinal = 6)
    private String stringValue;

    @JSONField(name = "long_value", ordinal = 7, serializeUsing = ToStringSerializer.class)
    private Long longValue;

    @JSONField(name = "double_value", ordinal = 8)
    private Double doubleValue;

    @JSONField(name = "boolean_value", ordinal = 9)
    private Boolean booleanValue;

    @JSONField(name = "date_value", ordinal = 10)
    private Date dateValue;

    @JSONField(name = "file_ref", ordinal = 11, serializeUsing = ToStringSerializer.class)
    private Long fileRef;

    @JSONField(name = "remark", ordinal = 12)
    private String remark;

    public JSFixedFastJsonInputItem() {
    }

    public JSFixedFastJsonInputItem(
            JSFixedFastJsonTaskItemKey key, JSFixedFastJsonLongIdKey sessionKey, JSFixedFastJsonLongIdKey toolKey,
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

    public JSFixedFastJsonTaskItemKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonTaskItemKey key) {
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
        return "JSFixedFastJsonInputItem{" +
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
