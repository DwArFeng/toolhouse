package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.stack.bean.entity.Folder;

import java.util.Objects;

/**
 * JSFixed FastJson 文件夹。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonFolder implements Bean {

    private static final long serialVersionUID = -1686540274830136902L;

    public static JSFixedFastJsonFolder of(Folder folder) {
        if (Objects.isNull(folder)) {
            return null;
        } else {
            return new JSFixedFastJsonFolder(
                    JSFixedFastJsonLongIdKey.of(folder.getKey()),
                    JSFixedFastJsonLongIdKey.of(folder.getParentKey()),
                    JSFixedFastJsonLongIdKey.of(folder.getCabinetKey()),
                    folder.getName(),
                    folder.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "parent_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey parentKey;

    @JSONField(name = "cabinet_key", ordinal = 3)
    private JSFixedFastJsonLongIdKey cabinetKey;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    public JSFixedFastJsonFolder() {
    }

    public JSFixedFastJsonFolder(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey parentKey, JSFixedFastJsonLongIdKey cabinetKey,
            String name, String remark
    ) {
        this.key = key;
        this.parentKey = parentKey;
        this.cabinetKey = cabinetKey;
        this.name = name;
        this.remark = remark;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(JSFixedFastJsonLongIdKey parentKey) {
        this.parentKey = parentKey;
    }

    public JSFixedFastJsonLongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(JSFixedFastJsonLongIdKey cabinetKey) {
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
        return "JSFixedFastJsonFolder{" +
                "key=" + key +
                ", parentKey=" + parentKey +
                ", cabinetKey=" + cabinetKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
