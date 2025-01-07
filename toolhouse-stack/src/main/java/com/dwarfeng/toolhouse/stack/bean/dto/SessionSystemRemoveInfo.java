package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 会话系统删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class SessionSystemRemoveInfo implements Dto {

    private static final long serialVersionUID = 8049880533562677756L;
    
    private LongIdKey sessionKey;

    public SessionSystemRemoveInfo() {
    }

    public SessionSystemRemoveInfo(LongIdKey sessionKey) {
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
        return "SessionSystemRemoveInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
