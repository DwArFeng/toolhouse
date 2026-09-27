package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.impl.bean.key.HibernateTaskItemKey;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

@Entity
@IdClass(HibernateTaskItemKey.class)
@Table(name = "tbl_output_item")
public class HibernateOutputItem implements Bean {

    private static final long serialVersionUID = 2266073201755220793L;

    // region 主键

    @Id
    @Column(name = "task_id", nullable = false)
    private Long taskLongId;

    @Id
    @Column(name = "item_id", length = Constraints.LENGTH_STRING_ID, nullable = false)
    private String itemStringId;

    // endregion

    // region 主属性字段

    @Column(name = "session_id")
    private Long sessionLongId;

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

    // endregion

    // region 多对一

    @ManyToOne(targetEntity = HibernateTask.class)
    @JoinColumns({ //
            @JoinColumn(name = "task_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTask task;

    // endregion

    public HibernateOutputItem() {
    }

    // region 映射用属性区

    public HibernateTaskItemKey getKey() {
        return new HibernateTaskItemKey(taskLongId, itemStringId);
    }

    public void setKey(HibernateTaskItemKey key) {
        if (Objects.isNull(key)) {
            this.taskLongId = null;
            this.itemStringId = null;
        } else {
            this.taskLongId = key.getTaskLongId();
            this.itemStringId = key.getItemStringId();
        }
    }

    public HibernateLongIdKey getSessionKey() {
        return Optional.ofNullable(sessionLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setSessionKey(HibernateLongIdKey key) {
        this.sessionLongId = Optional.ofNullable(key).map(HibernateLongIdKey::getLongId).orElse(null);
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

    // endregion

    // region 常规属性区

    public Long getTaskLongId() {
        return taskLongId;
    }

    public void setTaskLongId(Long taskLongId) {
        this.taskLongId = taskLongId;
    }

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    public Long getSessionLongId() {
        return sessionLongId;
    }

    public void setSessionLongId(Long sessionLongId) {
        this.sessionLongId = sessionLongId;
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

    public HibernateTask getTask() {
        return task;
    }

    public void setTask(HibernateTask task) {
        this.task = task;
    }

    // endregion

    @SuppressWarnings("DuplicatedCode")
    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "taskLongId = " + taskLongId + ", " +
                "itemStringId = " + itemStringId + ", " +
                "sessionLongId = " + sessionLongId + ", " +
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
                "task = " + task + ")";
    }
}
