package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务系统死亡信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskSystemDieInfo implements Dto {

    private static final long serialVersionUID = 1920758340307466871L;
    
    private LongIdKey taskKey;

    public TaskSystemDieInfo() {
    }

    public TaskSystemDieInfo(LongIdKey taskKey) {
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
        return "TaskSystemDieInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
