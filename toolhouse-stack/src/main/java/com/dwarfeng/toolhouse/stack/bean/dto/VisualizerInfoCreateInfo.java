package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 可视化器信息创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerInfoCreateInfo implements Dto {

    private static final long serialVersionUID = 6717143946422990312L;
    
    private LongIdKey toolKey;
    private String visualizerStringId;
    private boolean enabled;
    private String type;
    private String param;
    private String remark;

    public VisualizerInfoCreateInfo() {
    }

    public VisualizerInfoCreateInfo(
            LongIdKey toolKey, String visualizerStringId, boolean enabled, String type, String param, String remark
    ) {
        this.toolKey = toolKey;
        this.visualizerStringId = visualizerStringId;
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

    public String getVisualizerStringId() {
        return visualizerStringId;
    }

    public void setVisualizerStringId(String visualizerStringId) {
        this.visualizerStringId = visualizerStringId;
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
        return "VisualizerInfoCreateInfo{" +
                "toolKey=" + toolKey +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
