package com.dwarfeng.toolhouse.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 变量键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VariableKey implements Key {

    private static final long serialVersionUID = -8104970752828991700L;

    private Long sessionLongId;
    private String variableStringId;

    public VariableKey() {
    }

    public VariableKey(Long sessionLongId, String variableStringId) {
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

        VariableKey that = (VariableKey) o;
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
        return "VariableKey{" +
                "sessionLongId=" + sessionLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
