package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 工具。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonTool implements Bean {

    private static final long serialVersionUID = 1049251728260317099L;

    public static FastJsonTool of(Tool tool) {
        if (Objects.isNull(tool)) {
            return null;
        } else {
            return new FastJsonTool(
                    FastJsonLongIdKey.of(tool.getKey()),
                    FastJsonLongIdKey.of(tool.getFolderKey()),
                    FastJsonLongIdKey.of(tool.getCabinetKey()),
                    tool.getName(),
                    tool.getRemark(),
                    tool.getCreatedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "folder_key", ordinal = 2)
    private FastJsonLongIdKey folderKey;

    @JSONField(name = "cabinet_key", ordinal = 3)
    private FastJsonLongIdKey cabinetKey;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    @JSONField(name = "created_date", ordinal = 6)
    private Date createdDate;

    public FastJsonTool() {
    }

    public FastJsonTool(
            FastJsonLongIdKey key, FastJsonLongIdKey folderKey, FastJsonLongIdKey cabinetKey, String name,
            String remark, Date createdDate
    ) {
        this.key = key;
        this.folderKey = folderKey;
        this.cabinetKey = cabinetKey;
        this.name = name;
        this.remark = remark;
        this.createdDate = createdDate;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
        this.key = key;
    }

    public FastJsonLongIdKey getFolderKey() {
        return folderKey;
    }

    public void setFolderKey(FastJsonLongIdKey folderKey) {
        this.folderKey = folderKey;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "FastJsonTool{" +
                "key=" + key +
                ", folderKey=" + folderKey +
                ", cabinetKey=" + cabinetKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
