package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.CabinetCreateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 工具柜创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputCabinetCreateInfo implements Dto {

    private static final long serialVersionUID = 1757646705659365151L;

    public static CabinetCreateInfo toStackBean(WebInputCabinetCreateInfo webInputCabinetCreateInfo) {
        if (Objects.isNull(webInputCabinetCreateInfo)) {
            return null;
        } else {
            return new CabinetCreateInfo(
                    webInputCabinetCreateInfo.getName(),
                    webInputCabinetCreateInfo.getRemark(),
                    webInputCabinetCreateInfo.isFavorite()
            );
        }
    }

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    @JSONField(name = "favorite")
    private boolean favorite;

    public WebInputCabinetCreateInfo() {
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
        return "WebInputCabinetCreateInfo{" +
                "name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                ", favorite=" + favorite +
                '}';
    }
}
