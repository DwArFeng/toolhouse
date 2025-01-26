package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务系统开始信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskSystemStartInfo implements Dto {

    private static final long serialVersionUID = -7608895281649363604L;
    
    private LongIdKey taskKey;

    public TaskSystemStartInfo() {
    }

    public TaskSystemStartInfo(LongIdKey taskKey) {
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
        return "TaskSystemStartInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
