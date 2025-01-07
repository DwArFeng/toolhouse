package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.SessionManualRemoveInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 会话手动删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputSessionManualRemoveInfo implements Dto {

    private static final long serialVersionUID = -6900168978978748271L;

    public static SessionManualRemoveInfo toStackBean(WebInputSessionManualRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SessionManualRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSessionKey())
            );
        }
    }

    @JSONField(name = "session_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sessionKey;

    public WebInputSessionManualRemoveInfo() {
    }

    public WebInputSessionManualRemoveInfo(WebInputLongIdKey sessionKey) {
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
        return "WebInputSessionManualRemoveInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
