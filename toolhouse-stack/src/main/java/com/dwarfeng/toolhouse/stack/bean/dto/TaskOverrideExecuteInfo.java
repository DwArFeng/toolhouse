package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务超控执行信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskOverrideExecuteInfo implements Dto {

    private static final long serialVersionUID = 6648503348633564149L;

    private LongIdKey taskKey;

    public TaskOverrideExecuteInfo() {
    }

    public TaskOverrideExecuteInfo(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    @Override
    public String toString() {
        return "TaskOverrideExecuteInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
