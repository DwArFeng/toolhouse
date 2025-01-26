package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskOverrideCreateInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 任务超控创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputTaskOverrideCreateInfo implements Dto {

    private static final long serialVersionUID = -4829059831846671376L;

    public static TaskOverrideCreateInfo toStackBean(WebInputTaskOverrideCreateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new TaskOverrideCreateInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSessionKey())
            );
        }
    }

    @JSONField(name = "session_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sessionKey;

    public WebInputTaskOverrideCreateInfo() {
    }

    public WebInputLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(WebInputLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    @Override
    public String toString() {
        return "WebInputTaskOverrideCreateInfo{" +
                "sessionKey=" + sessionKey +
                '}';
    }
}
