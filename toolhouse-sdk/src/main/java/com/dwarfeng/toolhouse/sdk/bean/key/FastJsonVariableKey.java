package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;

import java.util.Objects;

/**
 * FastJson 变量键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVariableKey implements Key {

    private static final long serialVersionUID = 5499959403960155437L;

    public static FastJsonVariableKey of(VariableKey variableKey) {
        if (Objects.isNull(variableKey)) {
            return null;
        } else {
            return new FastJsonVariableKey(
                    variableKey.getSessionLongId(),
                    variableKey.getVariableStringId()
            );
        }
    }

    @JSONField(name = "session_long_id", ordinal = 1)
    private Long sessionLongId;

    @JSONField(name = "variable_string_id", ordinal = 2)
    private String variableStringId;

    public FastJsonVariableKey() {
    }

    public FastJsonVariableKey(Long sessionLongId, String variableStringId) {
        this.sessionLongId = sessionLongId;
        this.variableStringId = variableStringId;
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

        FastJsonVariableKey that = (FastJsonVariableKey) o;
        return Objects.equals(sessionLongId, that.sessionLongId) && Objects.equals(variableStringId, that.variableStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(sessionLongId);
        result = 31 * result + Objects.hashCode(variableStringId);
        return result;
    }

    @Override
    public String toString() {
        return "FastJsonVariableKey{" +
                "sessionLongId=" + sessionLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
