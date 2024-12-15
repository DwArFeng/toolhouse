package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;

/**
 * 工具柜权限插入或更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class CabinetPermissionUpsertInfo implements Dto {

    private static final long serialVersionUID = 4269236949130054736L;

    private LongIdKey cabinetKey;
    private StringIdKey userKey;
    private int permissionLevel;

    public CabinetPermissionUpsertInfo() {
    }

    public CabinetPermissionUpsertInfo(LongIdKey CabinetKey, StringIdKey userKey, int permissionLevel) {
        this.cabinetKey = CabinetKey;
        this.userKey = userKey;
        this.permissionLevel = permissionLevel;
    }

    public LongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(LongIdKey CabinetKey) {
        this.cabinetKey = CabinetKey;
    }

    public StringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(StringIdKey userKey) {
        this.userKey = userKey;
    }

    public int getPermissionLevel() {
        return permissionLevel;
    }

    public void setPermissionLevel(int permissionLevel) {
        this.permissionLevel = permissionLevel;
    }

    @Override
    public String toString() {
        return "CabinetPermissionUpsertInfo{" +
                "cabinetKey=" + cabinetKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
