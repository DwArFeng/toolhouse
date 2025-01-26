package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskManualCreateInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 任务手动创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputTaskManualCreateInfo implements Dto {

    private static final long serialVersionUID = -2822462289580104970L;

    public static TaskManualCreateInfo toStackBean(WebInputTaskManualCreateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TaskManualCreateInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSessionKey())
            );
        }
    }

    @JSONField(name = "session_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sessionKey;

    public WebInputTaskManualCreateInfo() {
    }

    public WebInputLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(WebInputLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String toString() {
        return "WebInputTaskManualCreateInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
