package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;

/**
 * 可视化器信息更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerInfoUpdateInfo implements Dto {

    private static final long serialVersionUID = -1928502080665224287L;
    
    private VisualizerKey visualizerKey;
    private boolean enabled;
    private String type;
    private String param;
    private String remark;

    public VisualizerInfoUpdateInfo() {
    }

    public VisualizerInfoUpdateInfo(
            VisualizerKey visualizerKey, boolean enabled, String type, String param, String remark
    ) {
        this.visualizerKey = visualizerKey;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public VisualizerKey getVisualizerKey() {
        return visualizerKey;
    }

    public void setVisualizerKey(VisualizerKey visualizerKey) {
        this.visualizerKey = visualizerKey;
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
        return "VisualizerInfoUpdateInfo{" +
                "visualizerKey=" + visualizerKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
