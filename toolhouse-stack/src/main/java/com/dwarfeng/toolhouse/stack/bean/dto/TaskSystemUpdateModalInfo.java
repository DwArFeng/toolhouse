package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务系统更新模态信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskSystemUpdateModalInfo implements Dto {

    private static final long serialVersionUID = -1257553366998515304L;
    
    private LongIdKey taskKey;
    private String frontMessage;

    public TaskSystemUpdateModalInfo() {
    }

    public TaskSystemUpdateModalInfo(LongIdKey taskKey, String frontMessage) {
        this.taskKey = taskKey;
        this.frontMessage = frontMessage;
    }

    public LongIdKey getTaskKey() {
        return taskKey;
    }

    public void setTaskKey(LongIdKey taskKey) {
        this.taskKey = taskKey;
    }

    public String getFrontMessage() {
        return frontMessage;
    }

    public void setFrontMessage(String frontMessage) {
        this.frontMessage = frontMessage;
    }

    @Override
    public String toString() {
        return "TaskSystemUpdateModalInfo{" +
                "taskKey=" + taskKey +
                ", frontMessage='" + frontMessage + '\'' +
                '}';
    }
}
