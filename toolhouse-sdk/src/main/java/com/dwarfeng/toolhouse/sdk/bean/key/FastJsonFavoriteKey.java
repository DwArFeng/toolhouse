package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;

import java.util.Objects;

/**
 * FastJson 收藏主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonFavoriteKey implements Key {

    private static final long serialVersionUID = -5415962633313861572L;

    public static FastJsonFavoriteKey of(FavoriteKey favoriteKey) {
        if (Objects.isNull(favoriteKey)) {
            return null;
        } else {
            return new FastJsonFavoriteKey(
                    favoriteKey.getCabinetLongId(),
                    favoriteKey.getUserStringId()
            );
        }
    }

    @JSONField(name = "cabinet_long_id", ordinal = 1)
    private Long cabinetLongId;

    @JSONField(name = "user_string_id", ordinal = 2)
    private String userStringId;

    public FastJsonFavoriteKey() {
    }

    public FastJsonFavoriteKey(Long cabinetLongId, String userStringId) {
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

        FastJsonFavoriteKey that = (FastJsonFavoriteKey) o;
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
        return "FastJsonFavoriteKey{" +
                "cabinetLongId=" + cabinetLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
