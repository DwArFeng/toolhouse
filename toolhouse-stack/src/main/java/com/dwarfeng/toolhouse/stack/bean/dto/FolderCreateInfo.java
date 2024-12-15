package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 文件夹创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FolderCreateInfo implements Dto {

    private static final long serialVersionUID = 5805780463259428498L;

    private LongIdKey cabinetKey;
    private LongIdKey parentKey;
    private String name;
    private String remark;

    public FolderCreateInfo() {
    }

    public FolderCreateInfo(
            LongIdKey cabinetKey, LongIdKey parentKey, String name, String remark
    ) {
        this.cabinetKey = cabinetKey;
        this.parentKey = parentKey;
        this.name = name;
        this.remark = remark;
    }

    public LongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(LongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public LongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(LongIdKey parentKey) {
        this.parentKey = parentKey;
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
        return "FolderCreateInfo{" +
                "cabinetKey=" + cabinetKey +
                ", parentKey=" + parentKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
