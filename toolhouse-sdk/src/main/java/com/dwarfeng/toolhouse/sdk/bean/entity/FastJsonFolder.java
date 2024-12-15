package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.stack.bean.entity.Folder;

import java.util.Objects;

/**
 * FastJson 文件夹。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonFolder implements Bean {

    private static final long serialVersionUID = -3328523538766967049L;

    public static FastJsonFolder of(Folder folder) {
        if (Objects.isNull(folder)) {
            return null;
        } else {
            return new FastJsonFolder(
                    FastJsonLongIdKey.of(folder.getKey()),
                    FastJsonLongIdKey.of(folder.getParentKey()),
                    FastJsonLongIdKey.of(folder.getCabinetKey()),
                    folder.getName(),
                    folder.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "parent_key", ordinal = 2)
    private FastJsonLongIdKey parentKey;

    @JSONField(name = "cabinet_key", ordinal = 3)
    private FastJsonLongIdKey cabinetKey;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public FastJsonFolder() {
    }

    public FastJsonFolder(
            FastJsonLongIdKey key, FastJsonLongIdKey parentKey, FastJsonLongIdKey cabinetKey, String name,
            String remark
    ) {
        this.key = key;
        this.parentKey = parentKey;
        this.cabinetKey = cabinetKey;
        this.name = name;
        this.remark = remark;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(FastJsonLongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public FastJsonLongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(FastJsonLongIdKey cabinetKey) {
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
        return "FastJsonFolder{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", cabinetKey=" + cabinetKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
