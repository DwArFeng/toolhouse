package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskManualExecuteInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 任务手动执行信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputTaskManualExecuteInfo implements Dto {

    private static final long serialVersionUID = 3604165509105448990L;

    public static TaskManualExecuteInfo toStackBean(WebInputTaskManualExecuteInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TaskManualExecuteInfo(
                    WebInputLongIdKey.toStackBean(webInput.getTaskKey())
            );
        }
    }

    @JSONField(name = "task_key")
    @NotNull
    @Valid
    private WebInputLongIdKey taskKey;

    public WebInputTaskManualExecuteInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        return "WebInputTaskManualExecuteInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
