package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.CabinetUpdateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 工具柜更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputCabinetUpdateInfo implements Dto {

    private static final long serialVersionUID = 2292077709331232332L;

    public static CabinetUpdateInfo toStackBean(WebInputCabinetUpdateInfo webInputCabinetUpdateInfo) {
        if (Objects.isNull(webInputCabinetUpdateInfo)) {
            return null;
        } else {
            return new CabinetUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputCabinetUpdateInfo.getCabinetKey()),
                    webInputCabinetUpdateInfo.getName(),
                    webInputCabinetUpdateInfo.getRemark(),
                    webInputCabinetUpdateInfo.isFavorite()
            );
        }
    }

    @JSONField(name = "cabinet_key")
    @Valid
    @NotNull
    private WebInputLongIdKey cabinetKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "favorite")
    private boolean favorite;

    public WebInputCabinetUpdateInfo() {
    }

    public WebInputLongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(WebInputLongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isFavorite() {
        return favorite;
    }

    public void setFavorite(boolean favorite) {
        this.favorite = favorite;
    }

    @Override
    public String toString() {
        return "WebInputCabinetUpdateInfo{" +
                "cabinetKey=" + cabinetKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
