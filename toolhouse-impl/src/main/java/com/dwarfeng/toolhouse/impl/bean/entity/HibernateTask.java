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
@Table(name = "tbl_task")
public class HibernateTask implements Bean {

    private static final long serialVersionUID = 6946932631283405709L;

    // -----------------------------------------------------------主键-----------------------------------------------------------
    @Id
    @Column(name = "id", nullable = false, unique = true)
    private Long longId;

    // -----------------------------------------------------------外键-----------------------------------------------------------
    @Column(name = "session_id")
    private Long sessionLongId;

    // -----------------------------------------------------------主属性字段-----------------------------------------------------------
    @Column(name = "tool_id")
    private Long toolLongId;

    @Column(name = "user_id", length = Constraints.LENGTH_USER)
    private String userStringId;

    @Column(name = "status", nullable = false)
    private int status;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "start_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(name = "should_expire_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shouldExpireDate;

    @Column(name = "should_die_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date shouldDieDate;

    @Column(name = "end_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endDate;

    @Column(name = "duration")
    private Long duration;

    @Column(name = "expire_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expireDate;

    @Column(name = "die_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dieDate;

    @Column(name = "front_message", length = Constraints.LENGTH_MESSAGE)
    private String frontMessage;

    @Column(name = "back_message", length = Constraints.LENGTH_REMARK)
    private String remark;

    // -----------------------------------------------------------多对一-----------------------------------------------------------
    @ManyToOne(targetEntity = HibernateSession.class)
    @JoinColumns({ //
            @JoinColumn(name = "session_id", referencedColumnName = "id", insertable = false, updatable = false), //
    })
    private HibernateSession session;

    // -----------------------------------------------------------一对多-----------------------------------------------------------
    @OneToMany(cascade = CascadeType.MERGE, targetEntity = HibernateInputItem.class, mappedBy = "task")
    private Set<HibernateInputItem> inputItems = new HashSet<>();

    public HibernateTask() {
    }

    // -----------------------------------------------------------映射用属性区-----------------------------------------------------------
    public HibernateLongIdKey getKey() {
        return Optional.ofNullable(longId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setKey(HibernateLongIdKey idKey) {
        this.longId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
    }

    public HibernateLongIdKey getSessionKey() {
        return Optional.ofNullable(sessionLongId).map(HibernateLongIdKey::new).orElse(null);
    }

    public void setSessionKey(HibernateLongIdKey idKey) {
        this.sessionLongId = Optional.ofNullable(idKey).map(HibernateLongIdKey::getLongId).orElse(null);
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
    public Long getLongId() {
        return longId;
    }

    public void setLongId(Long longId) {
        this.longId = longId;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getShouldExpireDate() {
        return shouldExpireDate;
    }

    public void setShouldExpireDate(Date shouldExpireDate) {
        this.shouldExpireDate = shouldExpireDate;
    }

    public Date getShouldDieDate() {
        return shouldDieDate;
    }

    public void setShouldDieDate(Date shouldDieDate) {
        this.shouldDieDate = shouldDieDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public Date getDieDate() {
        return dieDate;
    }

    public void setDieDate(Date dieDate) {
        this.dieDate = dieDate;
    }

    public String getFrontMessage() {
        return frontMessage;
    }

    public void setFrontMessage(String frontMessage) {
        this.frontMessage = frontMessage;
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

    public Set<HibernateInputItem> getInputItems() {
        return inputItems;
    }

    public void setInputItems(Set<HibernateInputItem> inputItems) {
        this.inputItems = inputItems;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "longId = " + longId + ", " +
                "sessionLongId = " + sessionLongId + ", " +
                "toolLongId = " + toolLongId + ", " +
                "userStringId = " + userStringId + ", " +
                "status = " + status + ", " +
                "createDate = " + createDate + ", " +
                "startDate = " + startDate + ", " +
                "shouldExpireDate = " + shouldExpireDate + ", " +
                "shouldDieDate = " + shouldDieDate + ", " +
                "endDate = " + endDate + ", " +
                "duration = " + duration + ", " +
                "expireDate = " + expireDate + ", " +
                "dieDate = " + dieDate + ", " +
                "frontMessage = " + frontMessage + ", " +
                "remark = " + remark + ", " +
                "session = " + session + ")";
    }
}
