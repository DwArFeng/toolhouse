package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 工具柜收藏变更信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CabinetFavoredChangeInfo implements Dto {

    private static final long serialVersionUID = -9052993667913824210L;

    private LongIdKey cabinetKey;

    public CabinetFavoredChangeInfo() {
    }

    public CabinetFavoredChangeInfo(LongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public LongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(LongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    @Override
    public String toString() {
        return "CabinetFavoredChangeInfo{" +
                "cabinetKey=" + cabinetKey +
                '}';
    }
}
