package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务超控创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskOverrideCreateInfo implements Dto {

    private static final long serialVersionUID = 1824268832817888733L;
    
    private LongIdKey sessionKey;

    public TaskOverrideCreateInfo() {
    }

    public TaskOverrideCreateInfo(LongIdKey sessionKey) {
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
        return "TaskOverrideCreateInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
