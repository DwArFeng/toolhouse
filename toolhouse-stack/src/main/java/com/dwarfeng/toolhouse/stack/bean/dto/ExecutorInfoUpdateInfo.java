package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;

/**
 * 执行器信息更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ExecutorInfoUpdateInfo implements Dto {

    private static final long serialVersionUID = -4564222805107566958L;
    
    private ExecutorKey executorKey;
    private boolean enabled;
    private String type;
    private String param;
    private String remark;

    public ExecutorInfoUpdateInfo() {
    }

    public ExecutorInfoUpdateInfo(
            ExecutorKey executorKey, boolean enabled, String type, String param, String remark
    ) {
        this.executorKey = executorKey;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public ExecutorKey getExecutorKey() {
        return executorKey;
    }

    public void setExecutorKey(ExecutorKey executorKey) {
        this.executorKey = executorKey;
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
        return "ExecutorInfoUpdateInfo{" +
                "executorKey=" + executorKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
