package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;

import java.util.Objects;

/**
 * FastJson 工具柜权限主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonPocaKey implements Key {

    private static final long serialVersionUID = -9064857141078225279L;

    public static FastJsonPocaKey of(PocaKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new FastJsonPocaKey(key.getCabinetLongId(), key.getUserStringId());
        }
    }

    @JSONField(name = "cabinet_long_id", ordinal = 1)
    private Long cabinetLongId;

    @JSONField(name = "user_string_id", ordinal = 2)
    private String userStringId;

    public FastJsonPocaKey() {
    }

    public FastJsonPocaKey(Long cabinetLongId, String userStringId) {
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
    public String toString() {
        return "FastJsonPocaKey{" +
                "cabinetLongId=" + cabinetLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
