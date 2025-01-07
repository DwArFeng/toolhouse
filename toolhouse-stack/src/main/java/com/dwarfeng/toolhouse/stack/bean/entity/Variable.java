package com.dwarfeng.toolhouse.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;

import java.util.Date;

/**
 * 变量。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Variable implements Entity<VariableKey> {

    private static final long serialVersionUID = 5999651420271855026L;

    private VariableKey key;

    /**
     * 父节点或祖父节点所属的工具的主键。
     *
     * <p>
     * 该字段为冗余字段，便于删除工具时快速删除该实体。
     */
    private LongIdKey toolKey;

    /**
     * 父节点或祖父节点所属的用户的主键。
     *
     * <p>
     * 该字段为冗余字段，便于删除用户时快速删除该实体。
     */
    private StringIdKey userKey;

    /**
     * 类型。
     *
     * <p>
     * int 枚举，可能的状态为：
     * <ul>
     *     <li>文本</li>
     *     <li>整数</li>
     *     <li>浮点数</li>
     *     <li>布尔值</li>
     *     <li>日期值</li>
     *     <li>文件</li>
     * </ul>
     * 详细值参考 sdk 模块的常量工具类。
     */
    private int type;

    private String stringValue;
    private Long longValue;
    private Double doubleValue;
    private Boolean booleanValue;
    private Date dateValue;
    private Long fileRef;

    private String remark;

    public Variable() {
    }

    public Variable(
            VariableKey key, LongIdKey toolKey, StringIdKey userKey, int type, String stringValue, Long longValue,
            Double doubleValue, Boolean booleanValue, Date dateValue, Long fileRef, String remark
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

    @Override
    public VariableKey getKey() {
        return key;
    }

    @Override
    public void setKey(VariableKey key) {
        this.key = key;
    }

    public LongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(LongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
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
        return "Variable{" +
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
