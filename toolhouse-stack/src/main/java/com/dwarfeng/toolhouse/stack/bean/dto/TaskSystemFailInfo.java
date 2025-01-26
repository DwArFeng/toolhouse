package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务系统失败信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskSystemFailInfo implements Dto {

    private static final long serialVersionUID = 340766681210478654L;
    
    private LongIdKey taskKey;

    public TaskSystemFailInfo() {
    }

    public TaskSystemFailInfo(LongIdKey taskKey) {
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
        return "TaskSystemFailInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
