package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.ToolCreateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 工具创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputToolCreateInfo implements Dto {

    private static final long serialVersionUID = 101539900909758099L;

    public static ToolCreateInfo toStackBean(WebInputToolCreateInfo webInputToolCreateInfo) {
        if (Objects.isNull(webInputToolCreateInfo)) {
            return null;
        } else {
            return new ToolCreateInfo(
                    WebInputLongIdKey.toStackBean(webInputToolCreateInfo.getCabinetKey()),
                    WebInputLongIdKey.toStackBean(webInputToolCreateInfo.getFolderKey()),
                    webInputToolCreateInfo.getName(), webInputToolCreateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "cabinet_key")
    @Valid
    private WebInputLongIdKey cabinetKey;

    @JSONField(name = "folder_key")
    @Valid
    private WebInputLongIdKey folderKey;

    @JSONField(name = "name")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputToolCreateInfo() {
    }

    public WebInputLongIdKey getCabinetKey() {
        return cabinetKey;
    }

    public void setCabinetKey(WebInputLongIdKey cabinetKey) {
        this.cabinetKey = cabinetKey;
    }

    public WebInputLongIdKey getFolderKey() {
        return folderKey;
    }

    public void setFolderKey(WebInputLongIdKey folderKey) {
        this.folderKey = folderKey;
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
        return "WebInputToolCreateInfo{" +
                "cabinetKey=" + cabinetKey +
                ", folderKey=" + folderKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
