package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务手动执行信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskManualExecuteInfo implements Dto {

    private static final long serialVersionUID = -6317001149116626316L;

    private LongIdKey taskKey;

    public TaskManualExecuteInfo() {
    }

    public TaskManualExecuteInfo(LongIdKey taskKey) {
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
        return "TaskManualExecuteInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
