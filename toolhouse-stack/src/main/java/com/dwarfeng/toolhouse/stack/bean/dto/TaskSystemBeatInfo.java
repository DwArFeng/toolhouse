package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务系统心跳信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskSystemBeatInfo implements Dto {

    private static final long serialVersionUID = -2822952132064511570L;
    
    private LongIdKey taskKey;

    public TaskSystemBeatInfo() {
    }

    public TaskSystemBeatInfo(LongIdKey taskKey) {
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
        return "TaskSystemBeatInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
