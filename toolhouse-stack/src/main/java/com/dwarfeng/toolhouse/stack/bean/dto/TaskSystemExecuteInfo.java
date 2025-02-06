package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务系统执行信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskSystemExecuteInfo implements Dto {

    private static final long serialVersionUID = 4829538886393374736L;

    private LongIdKey taskKey;

    public TaskSystemExecuteInfo() {
    }

    public TaskSystemExecuteInfo(LongIdKey taskKey) {
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
        return "TaskSystemExecuteInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
