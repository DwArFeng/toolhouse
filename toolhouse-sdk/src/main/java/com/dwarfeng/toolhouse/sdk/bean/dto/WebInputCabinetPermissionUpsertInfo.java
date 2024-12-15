package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.CabinetPermissionUpsertInfo;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 工具柜权限插入或更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputCabinetPermissionUpsertInfo implements Dto {

    private static final long serialVersionUID = 7764697039167042997L;

    public static CabinetPermissionUpsertInfo toStackBean(WebInputCabinetPermissionUpsertInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new CabinetPermissionUpsertInfo(
                    WebInputLongIdKey.toStackBean(webInput.getCabinetKey()),
                    WebInputStringIdKey.toStackBean(webInput.getUserKey()),
                    webInput.getPermissionLevel()
            );
        }
    }

    @JSONField(name = "cabinet_key")
    @Valid
    private WebInputLongIdKey cabinetKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    @JSONField(name = "permission_level")
    private int permissionLevel;

    public WebInputCabinetPermissionUpsertInfo() {
    }

    public WebInputLongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(WebInputLongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public WebInputStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(WebInputStringIdKey userKey) {
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
        return "WebInputCabinetPermissionUpsertInfo{" +
                "cabinetKey=" + cabinetKey +
                ", userKey=" + userKey +
                ", permissionLevel=" + permissionLevel +
                '}';
    }
}
