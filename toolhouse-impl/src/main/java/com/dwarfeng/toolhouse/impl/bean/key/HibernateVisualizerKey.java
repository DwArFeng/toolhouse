package com.dwarfeng.toolhouse.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 可视化器键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class HibernateVisualizerKey implements Key {

    private static final long serialVersionUID = 7156784234888588485L;

    private Long toolLongId;
    private String visualizerStringId;

    public HibernateVisualizerKey() {
    }

    public HibernateVisualizerKey(Long toolLongId, String visualizerStringId) {
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

        HibernateVisualizerKey that = (HibernateVisualizerKey) o;
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
        return "HibernateVisualizerKey{" +
                "toolLongId=" + toolLongId +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                '}';
    }
}
