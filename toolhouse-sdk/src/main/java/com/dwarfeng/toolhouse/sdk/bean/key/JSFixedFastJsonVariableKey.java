package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;

import java.util.Objects;

/**
 * JSFixed FastJson 变量键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonVariableKey implements Key {

    private static final long serialVersionUID = -2609512675076399112L;

    public static JSFixedFastJsonVariableKey of(VariableKey variableKey) {
        if (Objects.isNull(variableKey)) {
            return null;
        } else {
            return new JSFixedFastJsonVariableKey(
                    variableKey.getSessionLongId(),
                    variableKey.getVariableStringId()
            );
        }
    }

    @JSONField(name = "session_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long sessionLongId;

    @JSONField(name = "variable_string_id", ordinal = 2)
    private String variableStringId;

    public JSFixedFastJsonVariableKey() {
    }

    public JSFixedFastJsonVariableKey(Long sessionLongId, String variableStringId) {
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

        JSFixedFastJsonVariableKey that = (JSFixedFastJsonVariableKey) o;
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
        return "JSFixedFastJsonVariableKey{" +
                "sessionLongId=" + sessionLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
