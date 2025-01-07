package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.SessionCreateResult;

import java.util.Objects;

/**
 * JSFixed FastJson 会话创建结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonSessionCreateResult implements Dto {

    private static final long serialVersionUID = -1222998450592268104L;

    public static JSFixedFastJsonSessionCreateResult of(SessionCreateResult sessionCreateResult) {
        if (Objects.isNull(sessionCreateResult)) {
            return null;
        } else {
            return new JSFixedFastJsonSessionCreateResult(
                    JSFixedFastJsonLongIdKey.of(sessionCreateResult.getSessionKey())
            );
        }
    }

    @JSONField(name = "session_key", ordinal = 1)
    private JSFixedFastJsonLongIdKey sessionKey;

    public JSFixedFastJsonSessionCreateResult() {
    }

    public JSFixedFastJsonSessionCreateResult(JSFixedFastJsonLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public JSFixedFastJsonLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(JSFixedFastJsonLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonSessionCreateResult{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
