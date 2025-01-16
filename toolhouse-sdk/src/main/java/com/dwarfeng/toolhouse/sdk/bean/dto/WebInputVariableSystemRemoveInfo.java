package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.VariableSystemRemoveInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 变量系统删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVariableSystemRemoveInfo implements Dto {

    private static final long serialVersionUID = 3306277909068583095L;

    public static VariableSystemRemoveInfo toStackBean(WebInputVariableSystemRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VariableSystemRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSessionKey()),
                    webInput.getVariableStringId()
            );
        }
    }

    @JSONField(name = "session_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sessionKey;

    @JSONField(name = "variable_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String variableStringId;

    public WebInputVariableSystemRemoveInfo() {
    }

    public WebInputLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(WebInputLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getVariableStringId() {
        return variableStringId;
    }

    public void setVariableStringId(String variableStringId) {
        this.variableStringId = variableStringId;
    }

    @Override
    public String toString() {
        return "WebInputVariableSystemRemoveInfo{" +
                "sessionKey=" + sessionKey +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
