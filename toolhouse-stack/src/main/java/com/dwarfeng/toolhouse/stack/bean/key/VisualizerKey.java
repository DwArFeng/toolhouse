package com.dwarfeng.toolhouse.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 可视化器键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerKey implements Key {

    private static final long serialVersionUID = -1087179451142881293L;

    private Long toolLongId;
    private String visualizerStringId;

    public VisualizerKey() {
    }

    public VisualizerKey(Long toolLongId, String visualizerStringId) {
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

        VisualizerKey that = (VisualizerKey) o;
        return Objects.equals(toolLongId, that.toolLongId) &&
                Objects.equals(visualizerStringId, that.visualizerStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(toolLongId);
        result = 31 * result + Objects.hashCode(visualizerStringId);
        return result;
    }

    @Override
    public String toString() {
        return "VisualizerKey{" +
                "toolLongId=" + toolLongId +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                '}';
    }
}
