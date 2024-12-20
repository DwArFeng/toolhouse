package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;

import java.util.Objects;

/**
 * FastJson 执行器键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonExecutorKey implements Key {

    private static final long serialVersionUID = 1856192932569993887L;

    public static FastJsonExecutorKey of(ExecutorKey executorKey) {
        if (Objects.isNull(executorKey)) {
            return null;
        } else {
            return new FastJsonExecutorKey(
                    executorKey.getToolLongId(),
                    executorKey.getExecutorStringId()
            );
        }
    }

    @JSONField(name = "tool_long_id", ordinal = 1)
    private Long toolLongId;

    @JSONField(name = "executor_string_id", ordinal = 2)
    private String executorStringId;

    public FastJsonExecutorKey() {
    }

    public FastJsonExecutorKey(Long toolLongId, String executorStringId) {
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

        FastJsonExecutorKey that = (FastJsonExecutorKey) o;
        return Objects.equals(toolLongId, that.toolLongId) && Objects.equals(executorStringId, that.executorStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(toolLongId);
        result = 31 * result + Objects.hashCode(executorStringId);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonExecutorKey{" +
                "toolLongId=" + toolLongId +
                ", executorStringId='" + executorStringId + '\'' +
                '}';
    }
}
