package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskSystemExpireInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 任务系统过期信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputTaskSystemExpireInfo implements Dto {

    private static final long serialVersionUID = 1438201277085812642L;

    public static TaskSystemExpireInfo toStackBean(WebInputTaskSystemExpireInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TaskSystemExpireInfo(
                    WebInputLongIdKey.toStackBean(webInput.getTaskKey())
            );
        }
    }

    @JSONField(name = "task_key")
    @NotNull
    @Valid
    private WebInputLongIdKey taskKey;

    public WebInputTaskSystemExpireInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        return "WebInputTaskSystemExpireInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
