package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.bean.key.FastJsonVisualizerKey;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerInfo;

import java.util.Objects;

/**
 * FastJson 可视化器信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVisualizerInfo implements Bean {

    private static final long serialVersionUID = -1228672526448667063L;

    public static FastJsonVisualizerInfo of(VisualizerInfo visualizerInfo) {
        if (Objects.isNull(visualizerInfo)) {
            return null;
        } else {
            return new FastJsonVisualizerInfo(
                    FastJsonVisualizerKey.of(visualizerInfo.getKey()),
                    visualizerInfo.isEnabled(),
                    visualizerInfo.getType(),
                    visualizerInfo.getParam(),
                    visualizerInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonVisualizerKey key;

    @JSONField(name = "enabled", ordinal = 2)
    private boolean enabled;

    @JSONField(name = "type", ordinal = 3)
    private String type;

    @JSONField(name = "param", ordinal = 4)
    private String param;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonVisualizerInfo() {
    }

    public FastJsonVisualizerInfo(
            FastJsonVisualizerKey key, boolean enabled, String type, String param, String remark
    ) {
        this.key = key;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public FastJsonVisualizerKey getKey() {
        return key;
    }

    public void setKey(FastJsonVisualizerKey key) {
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
        return "FastJsonVisualizerInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
