package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.SessionCreateResult;

import java.util.Objects;

/**
 * FastJson 会话创建结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonSessionCreateResult implements Dto {

    private static final long serialVersionUID = 1462180290178908354L;

    public static FastJsonSessionCreateResult of(SessionCreateResult sessionCreateResult) {
        if (Objects.isNull(sessionCreateResult)) {
            return null;
        } else {
            return new FastJsonSessionCreateResult(
                    FastJsonLongIdKey.of(sessionCreateResult.getSessionKey())
            );
        }
    }

    @JSONField(name = "session_key", ordinal = 1)
    private FastJsonLongIdKey sessionKey;

    public FastJsonSessionCreateResult() {
    }

    public FastJsonSessionCreateResult(FastJsonLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public FastJsonLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(FastJsonLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String toString() {
        return "FastJsonSessionCreateResult{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
