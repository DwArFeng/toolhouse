package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.JSFixedFastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;

import java.util.Date;
import java.util.Objects;

/**
 * JSFixed FastJson 工具。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonTool implements Bean {

    private static final long serialVersionUID = 1380962206284073483L;

    public static JSFixedFastJsonTool of(Tool tool) {
        if (Objects.isNull(tool)) {
            return null;
        } else {
            return new JSFixedFastJsonTool(
                    JSFixedFastJsonLongIdKey.of(tool.getKey()),
                    JSFixedFastJsonLongIdKey.of(tool.getFolderKey()),
                    JSFixedFastJsonLongIdKey.of(tool.getCabinetKey()),
                    tool.getName(),
                    tool.getRemark(),
                    tool.getCreatedDate()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonLongIdKey key;

    @JSONField(name = "folder_key", ordinal = 2)
    private JSFixedFastJsonLongIdKey folderKey;

    @JSONField(name = "cabinet_key", ordinal = 3)
    private JSFixedFastJsonLongIdKey cabinetKey;

    @JSONField(name = "name", ordinal = 4)
    private String name;

    @JSONField(name = "remark", ordinal = 5)
    private String remark;

    @JSONField(name = "created_date", ordinal = 6)
    private Date createdDate;

    public JSFixedFastJsonTool() {
    }

    public JSFixedFastJsonTool(
            JSFixedFastJsonLongIdKey key, JSFixedFastJsonLongIdKey folderKey, JSFixedFastJsonLongIdKey cabinetKey, String name,
            String remark, Date createdDate
    ) {
        this.key = key;
        this.folderKey = folderKey;
        this.cabinetKey = cabinetKey;
        this.name = name;
        this.remark = remark;
        this.createdDate = createdDate;
    }

    public JSFixedFastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonLongIdKey key) {
        this.key = key;
    }

    public JSFixedFastJsonLongIdKey getFolderKey() {
        return folderKey;
    }

    public void setFolderKey(JSFixedFastJsonLongIdKey folderKey) {
        this.folderKey = folderKey;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonTool{" +
                "key=" + key +
                ", folderKey=" + folderKey +
                ", cabinetKey=" + cabinetKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
