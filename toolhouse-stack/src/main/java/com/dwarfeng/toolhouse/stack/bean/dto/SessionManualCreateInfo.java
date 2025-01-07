package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 会话手动创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class SessionManualCreateInfo implements Dto {

    private static final long serialVersionUID = 4512734939001370094L;

    private LongIdKey toolKey;
    private String remark;

    public SessionManualCreateInfo() {
    }

    public SessionManualCreateInfo(LongIdKey toolKey, String remark) {
        this.toolKey = toolKey;
        this.remark = remark;
    }

    public LongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(LongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "SessionManualCreateInfo{" +
                "toolKey=" + toolKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}
