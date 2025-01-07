package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 会话手动超控删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class SessionOverrideRemoveInfo implements Dto {

    private static final long serialVersionUID = -7423767567588281391L;

    private LongIdKey sessionKey;

    public SessionOverrideRemoveInfo() {
    }

    public SessionOverrideRemoveInfo(LongIdKey sessionKey) {
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
        return "SessionOverrideRemoveInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
