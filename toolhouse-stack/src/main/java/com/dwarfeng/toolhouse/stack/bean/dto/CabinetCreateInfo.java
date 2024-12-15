package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 工具柜创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CabinetCreateInfo implements Dto {

    private static final long serialVersionUID = 4952153492567003160L;

    private String name;
    private String remark;
    private boolean favorite;

    public CabinetCreateInfo() {
    }

    public CabinetCreateInfo(String name, String remark, boolean favorite) {
        this.name = name;
        this.remark = remark;
        this.favorite = favorite;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "CabinetCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
