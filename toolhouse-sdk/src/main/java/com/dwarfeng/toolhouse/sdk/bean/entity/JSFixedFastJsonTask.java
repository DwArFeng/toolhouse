package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 任务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonTask implements Bean {

    private static final long serialVersionUID = 2405620135069358150L;

    public static JSFixedFastJsonTask of(Task task) {
        if (Objects.isNull(task)) {
            return null;
        } else {
            return new JSFixedFastJsonTask(
                    JSFixedFastJsonLongIdKey.of(task.getKey()),
                    JSFixedFastJsonLongIdKey.of(task.getSessionKey()),
                    JSFixedFastJsonLongIdKey.of(task.getToolKey()),
                    FastJsonStringIdKey.of(task.getUserKey()),
                    task.getStatus(),
                    task.getCreateDate(),
                    task.getStartDate(),
                    task.getShouldExpireDate(),
                    task.getShouldDieDate(),
                    task.getEndDate(),
                    task.getDuration(),
                    task.getExpireDate(),
                    task.getDieDate(),
                    task.getFrontMessage(),
                    task.getRemark()
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

    @JSONField(name = "status", ordinal = 5)
    private int status;

    @JSONField(name = "create_date", ordinal = 6)
    private Date createDate;

    @JSONField(name = "start_date", ordinal = 7)
    private Date startDate;

    @JSONField(name = "should_expire_date", ordinal = 8)
    private Date shouldExpireDate;

    @JSONField(name = "should_die_date", ordinal = 9)
    private Date shouldDieDate;

    @JSONField(name = "end_date", ordinal = 10)
    private Date endDate;

    @JSONField(name = "duration", ordinal = 11, serializeUsing = ToStringSerializer.class)
    private Long duration;

    @JSONField(name = "expire_date", ordinal = 12)
    private Date expireDate;

    @JSONField(name = "die_date", ordinal = 13)
    private Date dieDate;

    @JSONField(name = "front_message", ordinal = 14)
    private String frontMessage;

    @JSONField(name = "remark", ordinal = 15)
    private String remark;

    public JSFixedFastJsonTask() {
    }

    public JSFixedFastJsonTask(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey sessionKey, JSFixedFastJsonLongIdKey toolKey,
            FastJsonStringIdKey userKey, int status, Date createDate, Date startDate, Date shouldExpireDate,
            Date shouldDieDate, Date endDate, Long duration, Date expireDate, Date dieDate, String frontMessage,
            String remark
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
        return "JSFixedFastJsonTask{" +
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
