package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;

import java.util.Date;
import java.util.Objects;

/**
 * FastJson 工具柜。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonCabinet implements Bean {

    private static final long serialVersionUID = 6361903885047579407L;

    public static FastJsonCabinet of(Cabinet cabinet) {
        if (Objects.isNull(cabinet)) {
            return null;
        } else {
            return new FastJsonCabinet(
                    FastJsonLongIdKey.of(cabinet.getKey()),
                    cabinet.getName(),
                    cabinet.getRemark(),
                    cabinet.getCreatedDate(),
                    cabinet.getToolCount()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private FastJsonLongIdKey key;

    @JSONField(name = "name", ordinal = 2)
    private String name;

    @JSONField(name = "remark", ordinal = 3)
    private String remark;

    @JSONField(name = "created_date", ordinal = 4)
    private Date createdDate;

    @JSONField(name = "tool_count", ordinal = 5)
    private int toolCount;

    public FastJsonCabinet() {
    }

    public FastJsonCabinet(FastJsonLongIdKey key, String name, String remark, Date createdDate, int toolCount) {
        this.key = key;
        this.name = name;
        this.remark = remark;
        this.createdDate = createdDate;
        this.toolCount = toolCount;
    }

    public FastJsonLongIdKey getKey() {
        return key;
    }

    public void setKey(FastJsonLongIdKey key) {
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
        return "FastJsonCabinet{" +
                "key=" + key +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", createdDate=" + createdDate +
                ", toolCount=" + toolCount +
                '}';
    }
}
