package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.bean.key.FastJsonVariableKey;
import com.dwarfeng.toolhouse.stack.bean.entity.Variable;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 变量。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVariable implements Bean {

    private static final long serialVersionUID = 740213238972330705L;

    public static FastJsonVariable of(Variable variable) {
        if (Objects.isNull(variable)) {
            return null;
        } else {
            return new FastJsonVariable(
                    FastJsonVariableKey.of(variable.getKey()),
                    FastJsonLongIdKey.of(variable.getToolKey()),
                    FastJsonStringIdKey.of(variable.getUserKey()),
                    variable.getType(),
                    variable.getStringValue(),
                    variable.getLongValue(),
                    variable.getDoubleValue(),
                    variable.getBooleanValue(),
                    variable.getDateValue(),
                    variable.getFileRef(),
                    variable.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonVariableKey key;

    @JSONField(name = "tool_key", ordinal = 2)
    private FastJsonLongIdKey toolKey;

    @JSONField(name = "user_key", ordinal = 3)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "type", ordinal = 4)
    private int type;

    @JSONField(name = "string_value", ordinal = 5)
    private String stringValue;

    @JSONField(name = "long_value", ordinal = 6)
    private Long longValue;

    @JSONField(name = "double_value", ordinal = 7)
    private Double doubleValue;

    @JSONField(name = "boolean_value", ordinal = 8)
    private Boolean booleanValue;

    @JSONField(name = "date_value", ordinal = 9)
    private Date dateValue;

    @JSONField(name = "file_ref", ordinal = 10)
    private Long fileRef;

    @JSONField(name = "remark", ordinal = 11)
    private String remark;

    public FastJsonVariable() {
    }

    public FastJsonVariable(
            FastJsonVariableKey key, FastJsonLongIdKey toolKey, FastJsonStringIdKey userKey, int type,
            String stringValue, Long longValue, Double doubleValue, Boolean booleanValue, Date dateValue,
            Long fileRef, String remark
    ) {
        this.key = key;
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

    public FastJsonVariableKey getKey() {
        return key;
    }

    public void setKey(FastJsonVariableKey key) {
        this.key = key;
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
        return "FastJsonVariable{" +
                "key=" + key +
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
