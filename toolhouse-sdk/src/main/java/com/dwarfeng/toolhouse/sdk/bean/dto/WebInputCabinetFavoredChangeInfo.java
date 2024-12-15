package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.CabinetFavoredChangeInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 工具柜收藏变更信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputCabinetFavoredChangeInfo implements Dto {

    private static final long serialVersionUID = -8004185555785797428L;

    public static CabinetFavoredChangeInfo toStackBean(WebInputCabinetFavoredChangeInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new CabinetFavoredChangeInfo(
                    WebInputLongIdKey.toStackBean(webInput.getCabinetKey())
            );
        }
    }

    @JSONField(name = "cabinet_key")
    @NotNull
    @Valid
    private WebInputLongIdKey cabinetKey;

    public WebInputCabinetFavoredChangeInfo() {
    }

    public WebInputCabinetFavoredChangeInfo(WebInputLongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public WebInputLongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(WebInputLongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    @Override
    public String toString() {
        return "WebInputCabinetFavoredChangeInfo{" +
                "cabinetKey=" + cabinetKey +
                '}';
    }
}
