package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;

import java.util.Objects;

/**
 * JSFixed FastJson 工具柜权限主键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonPocaKey implements Key {

    private static final long serialVersionUID = 8970224263938140928L;

    public static JSFixedFastJsonPocaKey of(PocaKey key) {
        if (Objects.isNull(key)) {
            return null;
        } else {
            return new JSFixedFastJsonPocaKey(key.getCabinetLongId(), key.getUserStringId());
        }
    }

    @JSONField(name = "cabinet_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long cabinetLongId;

    @JSONField(name = "user_string_id", ordinal = 2)
    private String userStringId;

    public JSFixedFastJsonPocaKey() {
    }

    public JSFixedFastJsonPocaKey(Long cabinetLongId, String userStringId) {
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
        return "JSFixedFastJsonPocaKey{" +
                "cabinetLongId=" + cabinetLongId +
                ", userStringId='" + userStringId + '\'' +
                '}';
    }
}
