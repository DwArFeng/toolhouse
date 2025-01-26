package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskSystemStartInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 任务系统开始信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputTaskSystemStartInfo implements Dto {

    private static final long serialVersionUID = 277791665318182409L;

    public static TaskSystemStartInfo toStackBean(WebInputTaskSystemStartInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TaskSystemStartInfo(
                    WebInputLongIdKey.toStackBean(webInput.getTaskKey())
            );
        }
    }

    @JSONField(name = "task_key")
    @NotNull
    @Valid
    private WebInputLongIdKey taskKey;

    public WebInputTaskSystemStartInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        return "WebInputTaskSystemStartInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
