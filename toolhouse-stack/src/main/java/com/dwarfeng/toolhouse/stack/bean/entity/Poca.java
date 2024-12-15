package com.dwarfeng.toolhouse.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;

/**
 * 工具柜权限。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Poca implements Entity<PocaKey> {

    private static final long serialVersionUID = 6242842820422794353L;

    private PocaKey key;
    private int permissionLevel;
    private String remark;

    public Poca() {
    }

    public Poca(PocaKey key, int permissionLevel, String remark) {
        this.key = key;
        this.permissionLevel = permissionLevel;
        this.remark = remark;
    }

    @Override
    public PocaKey getKey() {
        return key;
    }

    @Override
    public void setKey(PocaKey key) {
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
        return "Poca{" +
                "key=" + key +
                ", permissionLevel=" + permissionLevel +
                ", remark='" + remark + '\'' +
                '}';
    }
}
