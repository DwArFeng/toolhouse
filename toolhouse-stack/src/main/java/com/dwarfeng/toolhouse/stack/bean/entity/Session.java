package com.dwarfeng.toolhouse.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

import java.util.Date;

/**
 * 会话。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Session implements Entity<LongIdKey> {

    private static final long serialVersionUID = 5488992965527686118L;

    private LongIdKey key;
    private LongIdKey toolKey;
    private StringIdKey userKey;

    /**
     * 会话的创建日期。
     */
    private Date createdDate;

    /**
     * 会话的最后活跃日期。
     *
     * <p>
     * 活跃的定义是：
     * <ul>
     *     <li>会话所属的任务新增/删除。</li>
     *     <li>会话所属的任务输入数据更新。</li>
     *     <li>会话所属的任务执行。</li>
     *     <li>会话所属的变量更新/删除。</li>
     * </ul>
     */
    private Date lastActiveDate;

    private String remark;

    public Session() {
    }

    public Session(
            LongIdKey key, LongIdKey toolKey, StringIdKey userKey, Date createdDate, Date lastActiveDate, String remark
    ) {
        this.key = key;
        this.toolKey = toolKey;
        this.userKey = userKey;
        this.createdDate = createdDate;
        this.lastActiveDate = lastActiveDate;
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

    @Override
    public String toString() {
        return "Session{" +
                "key=" + key +
                ", toolKey=" + toolKey +
                ", userKey=" + userKey +
                ", createdDate=" + createdDate +
                ", lastActiveDate=" + lastActiveDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
