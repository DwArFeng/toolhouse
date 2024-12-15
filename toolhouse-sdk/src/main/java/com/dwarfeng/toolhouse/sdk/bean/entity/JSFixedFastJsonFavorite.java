package com.dwarfeng.toolhouse.sdk.bean.entity;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.Bean;
import com.dwarfeng.toolhouse.sdk.bean.key.JSFixedFastJsonFavoriteKey;
import com.dwarfeng.toolhouse.stack.bean.entity.Favorite;

import java.util.Objects;

/**
 * JSFixed FastJson 收藏。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonFavorite implements Bean {

    private static final long serialVersionUID = -7466199093950756423L;

    public static JSFixedFastJsonFavorite of(Favorite favorite) {
        if (Objects.isNull(favorite)) {
            return null;
        } else {
            return new JSFixedFastJsonFavorite(
                    JSFixedFastJsonFavoriteKey.of(favorite.getKey()),
                    favorite.getRemark()
            );
        }
    }

    @JSONField(name = "key", ordinal = 1)
    private JSFixedFastJsonFavoriteKey key;

    @JSONField(name = "remark", ordinal = 2)
    private String remark;

    public JSFixedFastJsonFavorite() {
    }

    public JSFixedFastJsonFavorite(JSFixedFastJsonFavoriteKey key, String remark) {
        this.key = key;
        this.remark = remark;
    }

    public JSFixedFastJsonFavoriteKey getKey() {
        return key;
    }

    public void setKey(JSFixedFastJsonFavoriteKey key) {
        this.key = key;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonFavorite{" +
                "key=" + key +
                ", remark='" + remark + '\'' +
                '}';
    }
}
