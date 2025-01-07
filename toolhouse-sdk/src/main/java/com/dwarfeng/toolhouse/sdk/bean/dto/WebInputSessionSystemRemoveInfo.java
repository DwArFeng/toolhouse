package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.SessionSystemRemoveInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 会话系统删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputSessionSystemRemoveInfo implements Dto {

    private static final long serialVersionUID = -4237397443296133529L;

    public static SessionSystemRemoveInfo toStackBean(WebInputSessionSystemRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SessionSystemRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSessionKey())
            );
        }
    }

    @JSONField(name = "session_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sessionKey;

    public WebInputSessionSystemRemoveInfo() {
    }

    public WebInputLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(WebInputLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String toString() {
        return "WebInputSessionSystemRemoveInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
