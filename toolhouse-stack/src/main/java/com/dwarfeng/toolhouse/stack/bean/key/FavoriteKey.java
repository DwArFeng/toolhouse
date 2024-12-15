package com.dwarfeng.toolhouse.stack.bean.key;

import com.dwarfeng.subgrade.stack.bean.key.Key;

import java.util.Objects;

/**
 * 收藏主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FavoriteKey implements Key {

    private static final long serialVersionUID = 4379444180816814568L;

    private Long cabinetLongId;
    private String userStringId;

    public FavoriteKey() {
    }

    public FavoriteKey(Long cabinetLongId, String userStringId) {
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

        FavoriteKey that = (FavoriteKey) o;
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
        return "FavoriteKey{" +
                "cabinetLongId=" + cabinetLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
