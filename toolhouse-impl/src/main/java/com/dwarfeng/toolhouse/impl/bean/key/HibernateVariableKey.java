package com.dwarfeng.toolhouse.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 变量键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class HibernateVariableKey implements Key {

    private static final long serialVersionUID = -2605819316187709966L;

    private Long sessionLongId;
    private String variableStringId;

    public HibernateVariableKey() {
    }

    public HibernateVariableKey(Long sessionLongId, String variableStringId) {
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

        HibernateVariableKey that = (HibernateVariableKey) o;
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
        return "HibernateVariableKey{" +
                "sessionLongId=" + sessionLongId +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
