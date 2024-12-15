package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.CabinetPermissionRemoveInfo;

import javax.validation.Valid;
import java.util.Objects;

/**
 * WebInput 工具柜权限删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputCabinetPermissionRemoveInfo implements Dto {

    private static final long serialVersionUID = -3863002381373546525L;

    public static CabinetPermissionRemoveInfo toStackBean(WebInputCabinetPermissionRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new CabinetPermissionRemoveInfo(
                    WebInputLongIdKey.toStackBean(webInput.getCabinetKey()),
                    WebInputStringIdKey.toStackBean(webInput.getUserKey())
            );
        }
    }

    @JSONField(name = "cabinet_key")
    @Valid
    private WebInputLongIdKey cabinetKey;

    @JSONField(name = "user_key")
    @Valid
    private WebInputStringIdKey userKey;

    public WebInputCabinetPermissionRemoveInfo() {
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

    @Override
    public String toString() {
        return "WebInputCabinetPermissionRemoveInfo{" +
                "cabinetKey=" + cabinetKey +
                ", userKey=" + userKey +
                '}';
    }
}
