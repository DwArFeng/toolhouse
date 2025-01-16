package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 变量系统删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VariableSystemRemoveInfo implements Dto {

    private static final long serialVersionUID = -3227643736041792869L;

    private LongIdKey sessionKey;
    private String variableStringId;

    public VariableSystemRemoveInfo() {
    }

    public VariableSystemRemoveInfo(LongIdKey sessionKey, String variableStringId) {
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
        return "VariableSystemRemoveInfo{" +
                "sessionKey=" + sessionKey +
                ", variableStringId='" + variableStringId + '\'' +
                '}';
    }
}
