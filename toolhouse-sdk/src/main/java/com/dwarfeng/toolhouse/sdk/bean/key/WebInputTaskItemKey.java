package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput FastJson 任务项键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputTaskItemKey implements Key {

    private static final long serialVersionUID = -5141144342910219518L;

    public static TaskItemKey toStackBean(WebInputTaskItemKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TaskItemKey(
                    webInput.getTaskLongId(),
                    webInput.getItemStringId()
            );
        }
    }

    @JSONField(name = "task_long_id")
    @NotNull
    private Long taskLongId;

    @JSONField(name = "item_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String itemStringId;

    public WebInputTaskItemKey() {
    }

    public Long getTaskLongId() {
        return taskLongId;
    }

    public void setTaskLongId(Long taskLongId) {
        this.taskLongId = taskLongId;
    }

    public String getItemStringId() {
        return itemStringId;
    }

    public void setItemStringId(String itemStringId) {
        this.itemStringId = itemStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        WebInputTaskItemKey that = (WebInputTaskItemKey) o;
        return Objects.equals(taskLongId, that.taskLongId) && Objects.equals(itemStringId, that.itemStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(taskLongId);
        result = 31 * result + Objects.hashCode(itemStringId);
        return result;
    }

    @Override
    public String toString() {
        return "WebInputTaskItemKey{" +
                "taskLongId=" + taskLongId +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
