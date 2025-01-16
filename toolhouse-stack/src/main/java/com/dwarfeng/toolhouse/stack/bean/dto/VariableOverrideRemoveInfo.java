package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 变量超控删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VariableOverrideRemoveInfo implements Dto {

    private static final long serialVersionUID = -6688901083751273236L;

    private LongIdKey sessionKey;
    private String variableStringId;

    public VariableOverrideRemoveInfo() {
    }

    public VariableOverrideRemoveInfo(LongIdKey sessionKey, String variableStringId) {
        this.sessionKey = sessionKey;
        this.variableStringId = variableStringId;
    }

    public LongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(LongIdKey sessionKey) {
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
        return "VariableOverrideRemoveInfo{" +
                "sessionKey=" + sessionKey +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
