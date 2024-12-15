package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.FolderCreateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 文件夹创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputFolderCreateInfo implements Dto {

    private static final long serialVersionUID = 2649628005645406668L;

    public static FolderCreateInfo toStackBean(WebInputFolderCreateInfo webInputFolderCreateInfo) {
        if (Objects.isNull(webInputFolderCreateInfo)) {
            return null;
        } else {
            return new FolderCreateInfo(
                    WebInputLongIdKey.toStackBean(webInputFolderCreateInfo.getCabinetKey()),
                    WebInputLongIdKey.toStackBean(webInputFolderCreateInfo.getParentKey()),
                    webInputFolderCreateInfo.getName(), webInputFolderCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "cabinet_key")
    @Valid
    private WebInputLongIdKey cabinetKey;

    @JSONField(name = "parent_key")
    @Valid
    private WebInputLongIdKey parentKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputFolderCreateInfo() {
    }

    public WebInputLongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(WebInputLongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public WebInputLongIdKey getParentKey() {
        return parentKey;
    }

    public void setParentKey(WebInputLongIdKey parentKey) {
        this.parentKey = parentKey;
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

    @Override
    public String toString() {
        return "WebInputFolderCreateInfo{" +
                "cabinetKey=" + cabinetKey +
                ", parentKey=" + parentKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
