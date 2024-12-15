package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;

import java.util.Objects;

/**
 * JSFixed FastJson 收藏主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonFavoriteKey implements Key {

    private static final long serialVersionUID = 4767651121279612595L;

    public static JSFixedFastJsonFavoriteKey of(FavoriteKey favoriteKey) {
        if (Objects.isNull(favoriteKey)) {
            return null;
        } else {
            return new JSFixedFastJsonFavoriteKey(
                    favoriteKey.getCabinetLongId(),
                    favoriteKey.getUserStringId()
            );
        }
    }

    @JSONField(name = "cabinet_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long cabinetLongId;

    @JSONField(name = "user_string_id", ordinal = 2)
    private String userStringId;

    public JSFixedFastJsonFavoriteKey() {
    }

    public JSFixedFastJsonFavoriteKey(Long cabinetLongId, String userStringId) {
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

        JSFixedFastJsonFavoriteKey that = (JSFixedFastJsonFavoriteKey) o;
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
        return "JSFixedFastJsonFavoriteKey{" +
                "cabinetLongId=" + cabinetLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
