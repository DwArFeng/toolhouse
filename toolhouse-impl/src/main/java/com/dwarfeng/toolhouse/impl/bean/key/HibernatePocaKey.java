package com.dwarfeng.toolhouse.impl.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * Hibernate 工具柜权限主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class HibernatePocaKey implements Key {

    private static final long serialVersionUID = -342485023050824692L;

    private Long cabinetLongId;
    private String userStringId;

    public HibernatePocaKey() {
    }

    public HibernatePocaKey(Long cabinetLongId, String userStringId) {
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

        HibernatePocaKey that = (HibernatePocaKey) o;
        return Objects.equals(cabinetLongId, that.cabinetLongId) && Objects.equals(userStringId, that.userStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(cabinetLongId);
        result = 31 * result + Objects.hashCode(userStringId);
        return result;
    }

    @Override
    public String toString() {
        return "HibernatePocaKey{" +
                "cabinetLongId=" + cabinetLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
