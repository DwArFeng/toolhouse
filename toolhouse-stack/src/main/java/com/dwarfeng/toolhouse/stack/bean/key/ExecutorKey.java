package com.dwarfeng.toolhouse.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 执行器键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ExecutorKey implements Key {

    private static final long serialVersionUID = -7086205965823841764L;

    private Long toolLongId;
    private String executorStringId;

    public ExecutorKey() {
    }

    public ExecutorKey(Long toolLongId, String executorStringId) {
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

        ExecutorKey that = (ExecutorKey) o;
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
        return "ExecutorKey{" +
                "toolLongId=" + toolLongId +
                ", executorStringId='" + executorStringId + '\'' +
                '}';
    }
}
