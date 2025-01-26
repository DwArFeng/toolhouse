package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskCreateResult;

import java.util.Objects;

/**
 * FastJson 任务创建结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonTaskCreateResult implements Dto {

    private static final long serialVersionUID = 2032580372206774848L;

    public static FastJsonTaskCreateResult of(TaskCreateResult taskCreateResult) {
        if (Objects.isNull(taskCreateResult)) {
            return null;
        } else {
            return new FastJsonTaskCreateResult(
                    FastJsonLongIdKey.of(taskCreateResult.getTaskKey())
            );
        }
    }

    @JSONField(name = "task_key", ordinal = 1)
    private FastJsonLongIdKey taskKey;

    public FastJsonTaskCreateResult() {
    }

    public FastJsonTaskCreateResult(FastJsonLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public FastJsonLongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(FastJsonLongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        return "FastJsonTaskCreateResult{" +
                "taskKey=" + taskKey +
                '}';
    }
}
