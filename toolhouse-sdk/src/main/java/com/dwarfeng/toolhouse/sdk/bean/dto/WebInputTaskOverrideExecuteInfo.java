package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskOverrideExecuteInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 任务超控执行信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputTaskOverrideExecuteInfo implements Dto {

    private static final long serialVersionUID = 6859186903973725516L;

    public static TaskOverrideExecuteInfo toStackBean(WebInputTaskOverrideExecuteInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TaskOverrideExecuteInfo(
                    WebInputLongIdKey.toStackBean(webInput.getTaskKey())
            );
        }
    }

    @JSONField(name = "task_key")
    @NotNull
    @Valid
    private WebInputLongIdKey taskKey;

    public WebInputTaskOverrideExecuteInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        return "WebInputTaskOverrideExecuteInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
