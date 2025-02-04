package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemOverrideRemoveInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 输出项超控删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputOutputItemOverrideRemoveInfo implements Dto {

    private static final long serialVersionUID = -7368969348320069724L;

    public static OutputItemOverrideRemoveInfo toStackBean(WebInputOutputItemOverrideRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new OutputItemOverrideRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInput.getTaskKey()),
                    webInput.getItemStringId()
            );
        }
    }

    @JSONField(name = "task_key")
    @NotNull
    @Valid
    private WebInputLongIdKey taskKey;

    @JSONField(name = "item_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String itemStringId;

    public WebInputOutputItemOverrideRemoveInfo() {
    }

    public WebInputLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(WebInputLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    @Override
    public String toString() {
        return "WebInputOutputItemOverrideRemoveInfo{" +
                "taskKey=" + taskKey +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
