package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.bean.key.FastJsonExecutorKey;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorInfo;

import java.util.Objects;

/**
 * JSFixed FastJson 执行器信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonExecutorInfo implements Bean {

    private static final long serialVersionUID = 2134023940362618707L;

    public static JSFixedFastJsonExecutorInfo of(ExecutorInfo executorInfo) {
        if (Objects.isNull(executorInfo)) {
            return null;
        } else {
            return new JSFixedFastJsonExecutorInfo(
                    FastJsonExecutorKey.of(executorInfo.getKey()),
                    executorInfo.isEnabled(),
                    executorInfo.getType(),
                    executorInfo.getParam(),
                    executorInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonExecutorKey key;

    @JSONField(name = "enabled", ordinal = 2)
    private boolean enabled;

    @JSONField(name = "type", ordinal = 3)
    private String type;

    @JSONField(name = "param", ordinal = 4)
    private String param;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonExecutorInfo() {
    }

    public JSFixedFastJsonExecutorInfo(
            FastJsonExecutorKey key, boolean enabled, String type, String param, String remark
    ) {
        this.key = key;
        this.enabled = enabled;
        this.type = type;
        this.param = param;
        this.remark = remark;
    }

    public FastJsonExecutorKey getKey() {
        return key;
    }

    public void setKey(FastJsonExecutorKey key) {
        this.key = key;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonExecutorInfo{" +
                "key=" + key +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
