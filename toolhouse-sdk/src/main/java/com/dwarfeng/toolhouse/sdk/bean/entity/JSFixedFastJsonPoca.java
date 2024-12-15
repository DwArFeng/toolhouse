package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.bean.key.JSFixedFastJsonPocaKey;
import com.dwarfeng.toolhouse.stack.bean.entity.Poca;

import java.util.Objects;

/**
 * JSFixed FastJson 工具柜权限。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonPoca implements Bean {

    private static final long serialVersionUID = 5907490735757413451L;

    public static JSFixedFastJsonPoca of(Poca poca) {
        if (Objects.isNull(poca)) {
            return null;
        } else {
            return new JSFixedFastJsonPoca(
                    JSFixedFastJsonPocaKey.of(poca.getKey()), poca.getPermissionLevel(), poca.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonPocaKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public JSFixedFastJsonPoca() {
    }

    public JSFixedFastJsonPoca(JSFixedFastJsonPocaKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    public JSFixedFastJsonPocaKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonPocaKey key) {
        this.key = key;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonPoca{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
