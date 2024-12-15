package com.dwarfeng.toolhouse.stack.bean.entity;

import com.dwarfeng.subgrade.stack.bean.entity.Entity;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.util.Date;

/**
 * 工具柜。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class Cabinet implements Entity<LongIdKey> {

    private static final long serialVersionUID = 6284495397635620575L;
    
    private LongIdKey key;
    private String name;
    private String remark;
    private Date createdDate;
    private int toolCount;

    public Cabinet() {
    }

    public Cabinet(LongIdKey key, String name, String remark, Date createdDate, int toolCount) {
        this.key = key;
        this.name = name;
        this.remark = remark;
        this.createdDate = createdDate;
        this.toolCount = toolCount;
    }

    @Override
    public LongIdKey getKey() {
        return key;
    }

    @Override
    public void setKey(LongIdKey key) {
        this.key = key;
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

    public int getToolCount() {
        return toolCount;
    }

    public void setToolCount(int toolCount) {
        this.toolCount = toolCount;
    }

    @Override
    public String toString() {
        return "Cabinet{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", createdDate=" + createdDate +
                ", toolCount=" + toolCount +
                '}';
    }
}
