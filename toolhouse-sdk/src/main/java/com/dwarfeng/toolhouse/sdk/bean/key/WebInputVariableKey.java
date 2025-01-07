package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 变量键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVariableKey implements Key {

    private static final long serialVersionUID = -7828673963210597877L;

    public static VariableKey toStackBean(WebInputVariableKey webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VariableKey(
                    webInput.getSessionLongId(),
                    webInput.getVariableStringId()
            );
        }
    }

    @JSONField(name = "session_long_id")
    @NotNull
    private Long sessionLongId;

    @JSONField(name = "variable_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String variableStringId;

    public WebInputVariableKey() {
    }

    public Long getSessionLongId() {
        return sessionLongId;
    }

    public void setSessionLongId(Long sessionLongId) {
        this.sessionLongId = sessionLongId;
    }

    public String getVariableStringId() {
        return variableStringId;
    }

    public void setVariableStringId(String variableStringId) {
        this.variableStringId = variableStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        WebInputVariableKey that = (WebInputVariableKey) o;
        return Objects.equals(sessionLongId, that.sessionLongId) &&
                Objects.equals(variableStringId, that.variableStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(sessionLongId);
        result = 31 * result + Objects.hashCode(variableStringId);
        return result;
    }

    @Override
    public String toString() {
        return "WebInputVariableKey{" +
                "sessionLongId=" + sessionLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
