package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 工具创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ToolCreateInfo implements Dto {

    private static final long serialVersionUID = 6436891315113999069L;

    private LongIdKey cabinetKey;
    private LongIdKey folderKey;
    private String name;
    private String remark;

    public ToolCreateInfo() {
    }

    public ToolCreateInfo(
            LongIdKey cabinetKey, LongIdKey folderKey, String name, String remark
    ) {
        this.cabinetKey = cabinetKey;
        this.folderKey = folderKey;
        this.name = name;
        this.remark = remark;
    }

    public LongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(LongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
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
        return "ToolCreateInfo{" +
                "cabinetKey=" + cabinetKey +
                ", folderKey=" + folderKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
