package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.bean.key.FastJsonPocaKey;
import com.dwarfeng.toolhouse.stack.bean.entity.Poca;

import java.util.Objects;

/**
 * FastJson 工具柜权限。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonPoca implements Bean {

    private static final long serialVersionUID = 1657928459339247557L;

    public static FastJsonPoca of(Poca poca) {
        if (Objects.isNull(poca)) {
            return null;
        } else {
            return new FastJsonPoca(FastJsonPocaKey.of(poca.getKey()), poca.getPermissionLevel(), poca.getRemark());
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonPocaKey key;

    @JSONField(name = "permission_level", ordinal = 2)
    private int permissionLevel;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    public FastJsonPoca() {
    }

    public FastJsonPoca(FastJsonPocaKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    public FastJsonPocaKey getKey() {
        return key;
    }

    public void setKey(FastJsonPocaKey key) {
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
        return "FastJsonPoca{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
