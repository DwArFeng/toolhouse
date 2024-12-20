package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.bean.key.WebInputExecutorKey;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoRemoveInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 执行器信息删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputExecutorInfoRemoveInfo implements Dto {

    private static final long serialVersionUID = 2136511797645972253L;

    public static ExecutorInfoRemoveInfo toStackBean(WebInputExecutorInfoRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new ExecutorInfoRemoveInfo(
                    WebInputExecutorKey.toStackBean(webInput.getExecutorKey())
            );
        }
    }

    @JSONField(name = "executor_key")
    @NotNull
    @Valid
    private WebInputExecutorKey executorKey;

    public WebInputExecutorInfoRemoveInfo() {
    }

    public WebInputExecutorKey getExecutorKey() {
        return executorKey;
    }

    public void setExecutorKey(WebInputExecutorKey executorKey) {
        this.executorKey = executorKey;
    }

    @Override
    public String toString() {
        return "WebInputExecutorInfoRemoveInfo{" +
                "executorKey=" + executorKey +
                '}';
    }
}
