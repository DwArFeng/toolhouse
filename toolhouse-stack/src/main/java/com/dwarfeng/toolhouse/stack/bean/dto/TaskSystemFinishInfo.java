package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务系统完成信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskSystemFinishInfo implements Dto {

    private static final long serialVersionUID = 1221273060218729938L;
    
    private LongIdKey taskKey;

    public TaskSystemFinishInfo() {
    }

    public TaskSystemFinishInfo(LongIdKey taskKey) {
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
        return "TaskSystemFinishInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
