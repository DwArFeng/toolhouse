package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskCreateResult;

import java.util.Objects;

/**
 * JSFixed FastJson 任务创建结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonTaskCreateResult implements Dto {

    private static final long serialVersionUID = -4017867557369548942L;

    public static JSFixedFastJsonTaskCreateResult of(TaskCreateResult taskCreateResult) {
        if (Objects.isNull(taskCreateResult)) {
            return null;
        } else {
            return new JSFixedFastJsonTaskCreateResult(
                    JSFixedFastJsonLongIdKey.of(taskCreateResult.getTaskKey())
            );
        }
    }

    @JSONField(name = "task_key", ordinal = 1)
    private JSFixedFastJsonLongIdKey taskKey;

    public JSFixedFastJsonTaskCreateResult() {
    }

    public JSFixedFastJsonTaskCreateResult(JSFixedFastJsonLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public JSFixedFastJsonLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(JSFixedFastJsonLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonTaskCreateResult{" +
                "taskKey=" + taskKey +
                '}';
    }
}
