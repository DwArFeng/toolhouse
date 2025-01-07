package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.impl.bean.key.HibernateVariableKey;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity
@IdClass(HibernateVariableKey.class)
@Table(name = "tbl_variable")
public class HibernateVariable implements Bean {

    private static final long serialVersionUID = -6841124855733853492L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "session_id", nullable = false)
    private Long sessionLongId;

    @Id
    @Column(name = "variable_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String variableStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "tool_id")
    private Long toolLongId;

    @Column(name = "user_id", length = Constraints.LENGTH_USER)
    private String userStringId;

    @Column(name = "type", nullable = false)
    private int type;

    @Column(name = "string_value", columnDefinition = "TEXT")
    private String stringValue;

    @Column(name = "long_value")
    private Long longValue;

    @Column(name = "double_value")
    private Double doubleValue;

    @Column(name = "boolean_value")
    private Boolean booleanValue;

    @Column(name = "date_value")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateValue;

    @Column(name = "file_ref")
    private Long fileRef;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateSession.class)
    @JoinColumns({ //
            @JoinColumn(name = "session_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSession session;

    public HibernateVariable() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateVariableKey getKey() {
        return new HibernateVariableKey(sessionLongId, variableStringId);
    }

    public void setKey(HibernateVariableKey key) {
        if (Objects.isNull(key)) {
            this.sessionLongId = null;
            this.variableStringId = null;
        } else {
            this.sessionLongId = key.getSessionLongId();
            this.variableStringId = key.getVariableStringId();
        }
    }

    public HibernateLongIdKey getToolKey() {
        return Optional.of(toolLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setToolKey(HibernateLongIdKey key) {
        this.toolLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateStringIdKey getUserKey() {
        return Optional.ofNullable(userStringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setUserKey(HibernateStringIdKey key) {
        this.userStringId = Optional.ofNullable(key).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getSessionLongId() {
        return sessionLongId;
    }

    public void setSessionLongId(Long sessionLongId) {
        this.sessionLongId = sessionLongId;
    }

    public String getVariableStringId() {
        return variableStringId;
    }

    public void setVariableStringId(String variableStringId) {
        this.variableStringId = variableStringId;
    }

    public Long getToolLongId() {
        return toolLongId;
    }

    public void setToolLongId(Long toolLongId) {
        this.toolLongId = toolLongId;
    }

    public String getUserStringId() {
        return userStringId;
    }

    public void setUserStringId(String userStringId) {
        this.userStringId = userStringId;
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

    public HibernateSession getSession() {
        return session;
    }

    public void setSession(HibernateSession session) {
        this.session = session;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "sessionLongId = " + sessionLongId + ", " +
                "variableStringId = " + variableStringId + ", " +
                "toolLongId = " + toolLongId + ", " +
                "userStringId = " + userStringId + ", " +
                "type = " + type + ", " +
                "stringValue = " + stringValue + ", " +
                "longValue = " + longValue + ", " +
                "doubleValue = " + doubleValue + ", " +
                "booleanValue = " + booleanValue + ", " +
                "dateValue = " + dateValue + ", " +
                "fileRef = " + fileRef + ", " +
                "remark = " + remark + ", " +
                "session = " + session + ")";
    }
}
