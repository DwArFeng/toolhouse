package com.dwarfeng.toolhouse.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 工具柜权限主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class PocaKey implements Key {

    private static final long serialVersionUID = -2690944619860749363L;

    private Long cabinetLongId;
    private String userStringId;

    public PocaKey() {
    }

    public PocaKey(Long cabinetLongId, String userStringId) {
        this.cabinetLongId = cabinetLongId;
        this.userStringId = userStringId;
    }

    public Long getCabinetLongId() {
        return cabinetLongId;
    }

    public void setCabinetLongId(Long cabinetLongId) {
        this.cabinetLongId = cabinetLongId;
    }

    public String getUserStringId() {
        return userStringId;
    }

    public void setUserStringId(String userStringId) {
        this.userStringId = userStringId;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        PocaKey pocaKey = (PocaKey) o;
        return Objects.equals(cabinetLongId, pocaKey.cabinetLongId) && Objects.equals(userStringId, pocaKey.userStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(cabinetLongId);
        result = 31 * result + Objects.hashCode(userStringId);
        return result;
    }

    @Override
    public String toString() {
        return "PocaKey{" +
                "cabinetLongId=" + cabinetLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
