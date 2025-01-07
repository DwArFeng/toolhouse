package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;

import java.util.Objects;

/**
 * JSFixed FastJson 任务项键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonTaskItemKey implements Key {

    private static final long serialVersionUID = -6162898545833491707L;

    public static JSFixedFastJsonTaskItemKey of(TaskItemKey taskItemKey) {
        if (Objects.isNull(taskItemKey)) {
            return null;
        } else {
            return new JSFixedFastJsonTaskItemKey(
                    taskItemKey.getTaskLongId(),
                    taskItemKey.getItemStringId()
            );
        }
    }

    @JSONField(name = "task_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long taskLongId;

    @JSONField(name = "item_string_id", ordinal = 2)
    private String itemStringId;

    public JSFixedFastJsonTaskItemKey() {
    }

    public JSFixedFastJsonTaskItemKey(Long taskLongId, String itemStringId) {
        this.taskLongId = taskLongId;
        this.itemStringId = itemStringId;
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

        JSFixedFastJsonTaskItemKey that = (JSFixedFastJsonTaskItemKey) o;
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
        return "JSFixedFastJsonTaskItemKey{" +
                "taskLongId=" + taskLongId +
                ", itemStringId='" + itemStringId + '\'' +
                '}';
    }
}
