package com.dwarfeng.toolhouse.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.Date;

/**
 * 任务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Task implements Entity<LongIdKey> {

    private static final long serialVersionUID = -4579525985281870841L;
    
    private LongIdKey key;
    private LongIdKey sessionKey;

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
     * 任务状态。
     *
     * <p>
     * int 枚举，可能的状态为：
     * <ol>
     *     <li>任务创建</li>
     *     <li>任务进行</li>
     *     <li>任务完成</li>
     *     <li>任务失败</li>
     *     <li>任务过期</li>
     *     <li>任务死亡</li>
     * </ol>
     * 详细值参考 sdk 模块的常量工具类。
     */
    private int status;

    private Date createDate;
    private Date startDate;
    private Date shouldExpireDate;
    private Date shouldDieDate;
    private Date endDate;
    private Long duration;
    private Date expireDate;
    private Date dieDate;

    /**
     * 任务执行是在最前方显示的信息。
     */
    private String frontMessage;

    private String remark;

    public Task() {
    }

    public Task(
            LongIdKey key, LongIdKey sessionKey, LongIdKey toolKey, StringIdKey userKey, int status, Date createDate,
            Date startDate, Date shouldExpireDate, Date shouldDieDate, Date endDate, Long duration, Date expireDate,
            Date dieDate, String frontMessage, String remark
    ) {
        this.key = key;
        this.sessionKey = sessionKey;
        this.toolKey = toolKey;
        this.userKey = userKey;
        this.status = status;
        this.createDate = createDate;
        this.startDate = startDate;
        this.shouldExpireDate = shouldExpireDate;
        this.shouldDieDate = shouldDieDate;
        this.endDate = endDate;
        this.duration = duration;
        this.expireDate = expireDate;
        this.dieDate = dieDate;
        this.frontMessage = frontMessage;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(LongIdKey sessionKey) {
        this.sessionKey = sessionKey;
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

    @Override
    public String toString() {
        return "Task{" +
                "key=" + key +
                ", sessionKey=" + sessionKey +
                ", toolKey=" + toolKey +
                ", userKey=" + userKey +
                ", status=" + status +
                ", createDate=" + createDate +
                ", startDate=" + startDate +
                ", shouldExpireDate=" + shouldExpireDate +
                ", shouldDieDate=" + shouldDieDate +
                ", endDate=" + endDate +
                ", duration=" + duration +
                ", expireDate=" + expireDate +
                ", dieDate=" + dieDate +
                ", frontMessage='" + frontMessage + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
