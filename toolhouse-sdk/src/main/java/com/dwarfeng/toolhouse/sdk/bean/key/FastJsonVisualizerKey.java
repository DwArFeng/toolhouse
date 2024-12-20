package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;

import java.util.Objects;

/**
 * FastJson 可视化器键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVisualizerKey implements Key {

    private static final long serialVersionUID = 8849000030348042134L;

    public static FastJsonVisualizerKey of(VisualizerKey visualizerKey) {
        if (Objects.isNull(visualizerKey)) {
            return null;
        } else {
            return new FastJsonVisualizerKey(
                    visualizerKey.getToolLongId(),
                    visualizerKey.getVisualizerStringId()
            );
        }
    }

    @JSONField(name = "tool_long_id", ordinal = 1)
    private Long toolLongId;

    @JSONField(name = "visualizer_string_id", ordinal = 2)
    private String visualizerStringId;

    public FastJsonVisualizerKey() {
    }

    public FastJsonVisualizerKey(Long toolLongId, String visualizerStringId) {
        this.toolLongId = toolLongId;
        this.visualizerStringId = visualizerStringId;
    }

    public Long getToolLongId() {
        return toolLongId;
    }

    public void setToolLongId(Long toolLongId) {
        this.toolLongId = toolLongId;
    }

    public String getVisualizerStringId() {
        return visualizerStringId;
    }

    public void setVisualizerStringId(String visualizerStringId) {
        this.visualizerStringId = visualizerStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        FastJsonVisualizerKey that = (FastJsonVisualizerKey) o;
        return Objects.equals(toolLongId, that.toolLongId) && Objects.equals(visualizerStringId, that.visualizerStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(toolLongId);
        result = 31 * result + Objects.hashCode(visualizerStringId);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonVisualizerKey{" +
                "toolLongId=" + toolLongId +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                '}';
    }
}
