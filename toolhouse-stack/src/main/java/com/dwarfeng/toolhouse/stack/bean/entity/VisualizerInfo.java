package com.dwarfeng.toolhouse.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;

/**
 * 可视化器信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerInfo implements Entity<VisualizerKey> {

    private static final long serialVersionUID = 2794674259689342876L;

    private VisualizerKey key;
    private boolean enabled;
    private String type;
    private String param;
    private String remark;

    public VisualizerInfo() {
    }

    public VisualizerInfo(VisualizerKey key, boolean enabled, String type, String param, String remark) {
        this.key = key;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    @Override
    public VisualizerKey getKey() {
        return key;
    }

    @Override
    public void setKey(VisualizerKey key) {
        this.key = key;
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
        return "VisualizerInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
