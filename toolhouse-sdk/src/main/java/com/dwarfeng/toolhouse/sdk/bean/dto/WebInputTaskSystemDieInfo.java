package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskSystemDieInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 任务系统死亡信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputTaskSystemDieInfo implements Dto {

    private static final long serialVersionUID = -336849669403496139L;

    public static TaskSystemDieInfo toStackBean(WebInputTaskSystemDieInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TaskSystemDieInfo(
                    WebInputLongIdKey.toStackBean(webInput.getTaskKey())
            );
        }
    }

    @JSONField(name = "task_key")
    @NotNull
    @Valid
    private WebInputLongIdKey taskKey;

    public WebInputTaskSystemDieInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        return "WebInputTaskSystemDieInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
