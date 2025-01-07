package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 会话手动创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class SessionOverrideCreateInfo implements Dto {

    private static final long serialVersionUID = -7369308619714068695L;

    private LongIdKey toolKey;
    private StringIdKey userKey;
    private String remark;

    public SessionOverrideCreateInfo() {
    }

    public SessionOverrideCreateInfo(LongIdKey toolKey, StringIdKey userKey, String remark) {
        this.toolKey = toolKey;
        this.userKey = userKey;
        this.remark = remark;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SessionOverrideCreateInfo{" +
                "toolKey=" + toolKey +
                ", userKey=" + userKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}
