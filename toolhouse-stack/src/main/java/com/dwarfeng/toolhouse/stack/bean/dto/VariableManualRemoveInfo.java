package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 变量手动删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VariableManualRemoveInfo implements Dto {

    private static final long serialVersionUID = -5927403310600809449L;

    private LongIdKey sessionKey;
    private String variableStringId;

    public VariableManualRemoveInfo() {
    }

    public VariableManualRemoveInfo(LongIdKey sessionKey, String variableStringId) {
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
        return "VariableManualRemoveInfo{" +
                "sessionKey=" + sessionKey +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
