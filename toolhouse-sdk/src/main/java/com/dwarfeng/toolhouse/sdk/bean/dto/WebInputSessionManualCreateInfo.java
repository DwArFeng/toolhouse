package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.SessionManualCreateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 会话手动创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputSessionManualCreateInfo implements Dto {

    private static final long serialVersionUID = 7231220663660310203L;

    public static SessionManualCreateInfo toStackBean(WebInputSessionManualCreateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SessionManualCreateInfo(
                    WebInputLongIdKey.toStackBean(webInput.getToolKey()),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "tool_key")
    @NotNull
    @Valid
    private WebInputLongIdKey toolKey;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputSessionManualCreateInfo() {
    }

    public WebInputSessionManualCreateInfo(WebInputLongIdKey toolKey, String remark) {
        this.toolKey = toolKey;
        this.remark = remark;
    }

    public WebInputLongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(WebInputLongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputSessionManualCreateInfo{" +
                "toolKey=" + toolKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}
