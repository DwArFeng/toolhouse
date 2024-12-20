package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 执行器键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputExecutorKey implements Key {

    private static final long serialVersionUID = 4708145138592458563L;

    public static ExecutorKey toStackBean(WebInputExecutorKey webInputExecutorKey) {
        if (Objects.isNull(webInputExecutorKey)) {
            return null;
        } else {
            return new ExecutorKey(
                    webInputExecutorKey.getToolLongId(),
                    webInputExecutorKey.getExecutorStringId()
            );
        }
    }

    @JSONField(name = "tool_long_id")
    @NotNull
    private Long toolLongId;

    @JSONField(name = "executor_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String executorStringId;

    public WebInputExecutorKey() {
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
    public String toString() {
        return "WebInputExecutorKey{" +
                "toolLongId=" + toolLongId +
                ", executorStringId='" + executorStringId + '\'' +
                '}';
    }
}
