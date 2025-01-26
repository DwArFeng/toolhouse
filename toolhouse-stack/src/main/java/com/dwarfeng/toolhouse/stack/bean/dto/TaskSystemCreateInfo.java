package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务系统创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskSystemCreateInfo implements Dto {

    private static final long serialVersionUID = 2246266309449681256L;
    
    private LongIdKey sessionKey;

    public TaskSystemCreateInfo() {
    }

    public TaskSystemCreateInfo(LongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public LongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(LongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String toString() {
        return "TaskSystemCreateInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
