package com.dwarfeng.toolhouse.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件夹。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Folder implements Entity<LongIdKey> {

    private static final long serialVersionUID = -1301185959276861084L;

    private LongIdKey key;
    private LongIdKey parentKey;
    private LongIdKey cabinetKey;
    private String name;
    private String remark;

    public Folder() {
    }

    public Folder(LongIdKey key, LongIdKey parentKey, LongIdKey cabinetKey, String name, String remark) {
        this.key = key;
        this.parentKey = parentKey;
        this.cabinetKey = cabinetKey;
        this.name = name;
        this.remark = remark;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(LongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public LongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(LongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "Folder{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", cabinetKey=" + cabinetKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
