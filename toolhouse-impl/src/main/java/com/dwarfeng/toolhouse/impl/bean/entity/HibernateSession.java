package com.dwarfeng.toolhouse.impl.bean.entity;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.util.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Entity
@IdClass(HibernateLongIdKey.class)
@Table(name = "tbl_session")
public class HibernateSession implements Bean {

    private static final long serialVersionUID = -1538385705382579489L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "tool_id")
    private Long toolLongId;

    @Column(name = "user_id", length = Constraints.LENGTH_USER)
    private String userStringId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Column(name = "last_active_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastActiveDate;

    @Column(name = "remark", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateTool.class)
    @JoinColumns({ //
            @JoinColumn(name = "tool_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateTool tool;

    @ManyToOne(targetEntity = HibernateUser.class)
    @JoinColumns({ //
            @JoinColumn(name = "user_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateUser user;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateVariable.class, mappedBy = "session")
    private Set<HibernateVariable> variables = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateTask.class, mappedBy = "session")
    private Set<HibernateTask> tasks = new HashSet<>();

    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateFileInfo.class, mappedBy = "session")
    private Set<HibernateFileInfo> fileInfos = new HashSet<>();

    public HibernateSession() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getToolKey() {
        return Optional.ofNullable(toolLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setToolKey(HibernateLongIdKey idKey) {
        this.toolLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateStringIdKey getUserKey() {
        return Optional.ofNullable(userStringId).map(HibernateStringIdKey::new).orElse(null);
    }

    public void setUserKey(HibernateStringIdKey idKey) {
        this.userStringId = Optional.ofNullable(idKey).map(HibernateStringIdKey::getStringId).orElse(null);
    }

    // -----------------------------------------------------------常规属性区-----------------------------------------------------------
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastActiveDate() {
        return lastActiveDate;
    }

    public void setLastActiveDate(Date lastActiveDate) {
        this.lastActiveDate = lastActiveDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public HibernateTool getTool() {
        return tool;
    }

    public void setTool(HibernateTool tool) {
        this.tool = tool;
    }

    public HibernateUser getUser() {
        return user;
    }

    public void setUser(HibernateUser user) {
        this.user = user;
    }

    public Set<HibernateVariable> getVariables() {
        return variables;
    }

    public void setVariables(Set<HibernateVariable> variables) {
        this.variables = variables;
    }

    public Set<HibernateTask> getTasks() {
        return tasks;
    }

    public void setTasks(Set<HibernateTask> tasks) {
        this.tasks = tasks;
    }

    public Set<HibernateFileInfo> getFileInfos() {
        return fileInfos;
    }

    public void setFileInfos(Set<HibernateFileInfo> fileInfos) {
        this.fileInfos = fileInfos;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "toolLongId = " + toolLongId + ", " +
                "userStringId = " + userStringId + ", " +
                "createdDate = " + createdDate + ", " +
                "lastActiveDate = " + lastActiveDate + ", " +
                "remark = " + remark + ", " +
                "tool = " + tool + ", " +
                "user = " + user + ")";
    }
}
