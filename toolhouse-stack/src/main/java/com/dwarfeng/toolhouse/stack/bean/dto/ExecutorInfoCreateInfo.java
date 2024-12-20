package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 执行器信息创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ExecutorInfoCreateInfo implements Dto {

    private static final long serialVersionUID = 961211677417012971L;
    
    private LongIdKey toolKey;
    private String executorStringId;
    private boolean enabled;
    private String type;
    private String param;
    private String remark;

    public ExecutorInfoCreateInfo() {
    }

    public ExecutorInfoCreateInfo(
            LongIdKey toolKey, String executorStringId, boolean enabled, String type, String param, String remark
    ) {
        this.toolKey = toolKey;
        this.executorStringId = executorStringId;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public LongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(LongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public String getExecutorStringId() {
        return executorStringId;
    }

    public void setExecutorStringId(String executorStringId) {
        this.executorStringId = executorStringId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "ExecutorInfoCreateInfo{" +
                "toolKey=" + toolKey +
                ", executorStringId='" + executorStringId + '\'' +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
