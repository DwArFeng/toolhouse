package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 任务手动创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class TaskManualCreateInfo implements Dto {

    private static final long serialVersionUID = -3818852302379624983L;

    private LongIdKey sessionKey;

    public TaskManualCreateInfo() {
    }

    public TaskManualCreateInfo(LongIdKey sessionKey) {
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
        return "TaskManualCreateInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
