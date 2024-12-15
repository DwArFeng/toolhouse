package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 工具柜权限删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CabinetPermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = -1445866739545363300L;

    private LongIdKey cabinetKey;
    private StringIdKey userKey;

    public CabinetPermissionRemoveInfo() {
    }

    public CabinetPermissionRemoveInfo(LongIdKey cabinetKey, StringIdKey userKey) {
        this.cabinetKey = cabinetKey;
        this.userKey = userKey;
    }

    public LongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(LongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    @Override
    public String toString() {
        return "CabinetPermissionRemoveInfo{" +
                "cabinetKey=" + cabinetKey +
                ", userKey=" + userKey +
                '}';
    }
}
