package com.dwarfeng.toolhouse.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 执行器键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class HibernateExecutorKey implements Key {

    private static final long serialVersionUID = -1627372409663470180L;

    private Long toolLongId;
    private String executorStringId;

    public HibernateExecutorKey() {
    }

    public HibernateExecutorKey(Long toolLongId, String executorStringId) {
        this.toolLongId = toolLongId;
        this.executorStringId = executorStringId;
    }

    public Long getToolLongId() {
        return toolLongId;
    }

    public void setToolLongId(Long toolLongId) {
        this.toolLongId = toolLongId;
    }

    public String getExecutorStringId() {
        return executorStringId;
    }

    public void setExecutorStringId(String executorStringId) {
        this.executorStringId = executorStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        HibernateExecutorKey that = (HibernateExecutorKey) o;
        return Objects.equals(toolLongId, that.toolLongId) &&
                Objects.equals(executorStringId, that.executorStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(toolLongId);
        result = 31 * result + Objects.hashCode(executorStringId);
        return result;
    }

    @Override
    public String toString() {
        return "HibernateExecutorKey{" +
                "toolLongId=" + toolLongId +
                ", executorStringId='" + executorStringId + '\'' +
                '}';
    }
}
