package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 会话。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonSession implements Bean {

    private static final long serialVersionUID = 7583078373274381984L;

    public static FastJsonSession of(Session session) {
        if (Objects.isNull(session)) {
            return null;
        } else {
            return new FastJsonSession(
                    FastJsonLongIdKey.of(session.getKey()),
                    FastJsonLongIdKey.of(session.getToolKey()),
                    FastJsonStringIdKey.of(session.getUserKey()),
                    session.getCreatedDate(),
                    session.getLastActiveDate(),
                    session.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "tool_key", ordinal = 2)
    private FastJsonLongIdKey toolKey;

    @JSONField(name = "user_key", ordinal = 3)
    private FastJsonStringIdKey userKey;

    @JSONField(name = "created_date", ordinal = 4)
    private Date createdDate;

    @JSONField(name = "last_active_date", ordinal = 5)
    private Date lastActiveDate;

    @JSONField(name = "remark", ordinal = 6)
    private String remark;

    public FastJsonSession() {
    }

    public FastJsonSession(
            FastJsonLongIdKey key, FastJsonLongIdKey toolKey, FastJsonStringIdKey userKey, Date createdDate,
            Date lastActiveDate, String remark
    ) {
        this.key = key;
        this.toolKey = toolKey;
        this.userKey = userKey;
        this.createdDate = createdDate;
        this.lastActiveDate = lastActiveDate;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
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
        return "FastJsonSession{" +
                "key=" + key +
                ", toolKey=" + toolKey +
                ", userKey=" + userKey +
                ", createdDate=" + createdDate +
                ", lastActiveDate=" + lastActiveDate +
                ", remark='" + remark + '\'' +
                '}';
    }
}
