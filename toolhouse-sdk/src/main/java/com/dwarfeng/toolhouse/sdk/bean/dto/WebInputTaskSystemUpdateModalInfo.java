package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskSystemUpdateModalInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 任务系统更新模态信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputTaskSystemUpdateModalInfo implements Dto {

    private static final long serialVersionUID = 323564553846799775L;

    public static TaskSystemUpdateModalInfo toStackBean(WebInputTaskSystemUpdateModalInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TaskSystemUpdateModalInfo(
                    WebInputLongIdKey.toStackBean(webInput.getTaskKey()),
                    webInput.getFrontMessage()
            );
        }
    }

    @JSONField(name = "task_key")
    @NotNull
    @Valid
    private WebInputLongIdKey taskKey;

    @JSONField(name = "front_message")
    @Length(max = Constraints.LENGTH_MESSAGE)
    private String frontMessage;

    public WebInputTaskSystemUpdateModalInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getFrontMessage() {
        return frontMessage;
    }

    public void setFrontMessage(String frontMessage) {
        this.frontMessage = frontMessage;
    }

    @Override
    public String toString() {
        return "WebInputTaskSystemUpdateModalInfo{" +
                "taskKey=" + taskKey +
                ", frontMessage='" + frontMessage + '\'' +
                '}';
    }
}
