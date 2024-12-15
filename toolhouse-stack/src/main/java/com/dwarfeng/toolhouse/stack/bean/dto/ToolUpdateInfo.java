package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 工具更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ToolUpdateInfo implements Dto {

    private static final long serialVersionUID = -3252339879034286542L;

    private LongIdKey key;
    private LongIdKey folderKey;
    private String name;
    private String remark;

    public ToolUpdateInfo() {
    }

    public ToolUpdateInfo(LongIdKey key, LongIdKey folderKey, String name, String remark) {
        this.key = key;
        this.folderKey = folderKey;
        this.name = name;
        this.remark = remark;
    }

    public LongIdKey getKey() {
        return key;
    }

    public void setKey(LongIdKey key) {
        this.key = key;
    }

    public LongIdKey getFolderKey() {
        return folderKey;
    }

    public void setFolderKey(LongIdKey folderKey) {
        this.folderKey = folderKey;
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
        return "ToolUpdateInfo{" +
                "key=" + key +
                ", folderKey=" + folderKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
