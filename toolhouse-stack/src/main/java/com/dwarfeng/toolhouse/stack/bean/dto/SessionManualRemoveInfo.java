package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 会话手动删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class SessionManualRemoveInfo implements Dto {

    private static final long serialVersionUID = -3722214146047618731L;

    private LongIdKey sessionKey;

    public SessionManualRemoveInfo() {
    }

    public SessionManualRemoveInfo(LongIdKey sessionKey) {
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
        return "SessionManualRemoveInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
