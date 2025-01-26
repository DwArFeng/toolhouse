package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务系统过期信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskSystemExpireInfo implements Dto {

    private static final long serialVersionUID = 855347358646449980L;
    
    private LongIdKey taskKey;

    public TaskSystemExpireInfo() {
    }

    public TaskSystemExpireInfo(LongIdKey taskKey) {
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
        return "TaskSystemExpireInfo{" +
                "taskKey=" + taskKey +
                '}';
    }
}
