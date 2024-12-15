package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.ToolUpdateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 工具更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputToolUpdateInfo implements Dto {

    private static final long serialVersionUID = 8738616835543497667L;

    public static ToolUpdateInfo toStackBean(WebInputToolUpdateInfo webInputToolUpdateInfo) {
        if (Objects.isNull(webInputToolUpdateInfo)) {
            return null;
        } else {
            return new ToolUpdateInfo(
                    WebInputLongIdKey.toStackBean(webInputToolUpdateInfo.getKey()),
                    WebInputLongIdKey.toStackBean(webInputToolUpdateInfo.getFolderKey()),
                    webInputToolUpdateInfo.getName(), webInputToolUpdateInfo.getRemark()
            );
        }
    }

    @JSONField(name = "key")
    @Valid
    private WebInputLongIdKey key;

    @JSONField(name = "folder_key")
    @Valid
    private WebInputLongIdKey folderKey;

    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_NAME)
    private String name;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputToolUpdateInfo() {
    }

    public WebInputLongIdKey getKey() {
        return key;
    }

    public void setKey(WebInputLongIdKey key) {
        this.key = key;
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
        return "WebInputToolUpdateInfo{" +
                "key=" + key +
                ", folderKey=" + folderKey +
                ", name='" + name + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
