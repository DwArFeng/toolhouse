package com.dwarfeng.toolhouse.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 工具。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Tool implements Entity<LongIdKey> {

    private static final long serialVersionUID = 9004603946687536921L;

    private LongIdKey key;
    private LongIdKey folderKey;
    private LongIdKey cabinetKey;
    private String name;
    private String remark;
    private Date createdDate;

    public Tool() {
    }

    public Tool(
            LongIdKey key, LongIdKey folderKey, LongIdKey cabinetKey, String name, String remark, Date createdDate
    ) {
        this.key = key;
        this.folderKey = folderKey;
        this.cabinetKey = cabinetKey;
        this.name = name;
        this.remark = remark;
        this.createdDate = createdDate;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getFolderKey() {
        return folderKey;
    }

    public void setFolderKey(LongIdKey folderKey) {
        this.folderKey = folderKey;
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

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    @Override
    public String toString() {
        return "Tool{" +
                "key=" + key +
                ", folderKey=" + folderKey +
                ", cabinetKey=" + cabinetKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", createdDate=" + createdDate +
                '}';
    }
}
