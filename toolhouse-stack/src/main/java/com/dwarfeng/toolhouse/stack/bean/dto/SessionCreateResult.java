package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 会话创建结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class SessionCreateResult implements Dto {

    private static final long serialVersionUID = 7769342181374735876L;

    private LongIdKey sessionKey;

    public SessionCreateResult() {
    }

    public SessionCreateResult(LongIdKey sessionKey) {
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
        return "SessionCreateResult{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
