package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.SessionOverrideRemoveInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 会话手动超控删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputSessionOverrideRemoveInfo implements Dto {

    private static final long serialVersionUID = -7995036803441274616L;

    public static SessionOverrideRemoveInfo toStackBean(WebInputSessionOverrideRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SessionOverrideRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSessionKey())
            );
        }
    }

    @JSONField(name = "session_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sessionKey;

    public WebInputSessionOverrideRemoveInfo() {
    }

    public WebInputSessionOverrideRemoveInfo(WebInputLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public WebInputLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(WebInputLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String toString() {
        return "WebInputSessionOverrideRemoveInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
