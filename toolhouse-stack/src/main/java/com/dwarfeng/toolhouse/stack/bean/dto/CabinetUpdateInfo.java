package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 工具柜更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CabinetUpdateInfo implements Dto {

    private static final long serialVersionUID = 5588974522609150815L;

    private LongIdKey cabinetKey;
    private String name;
    private String remark;
    private boolean favorite;

    public CabinetUpdateInfo() {
    }

    public CabinetUpdateInfo(LongIdKey cabinetKey, String name, String remark, boolean favorite) {
        this.cabinetKey = cabinetKey;
        this.name = name;
        this.remark = remark;
        this.favorite = favorite;
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

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "CabinetUpdateInfo{" +
                "cabinetKey=" + cabinetKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
