package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputStringIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.SessionSystemCreateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * 会话系统创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputSessionSystemCreateInfo implements Dto {

    private static final long serialVersionUID = -6334901404553067194L;

    public static SessionSystemCreateInfo toStackBean(WebInputSessionSystemCreateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new SessionSystemCreateInfo(
                    WebInputLongIdKey.toStackBean(webInput.getToolKey()),
                    WebInputStringIdKey.toStackBean(webInput.getUserKey()),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "tool_key")
    @NotNull
    @Valid
    private WebInputLongIdKey toolKey;

    @JSONField(name = "user_key")
    @NotNull
    @Valid
    private WebInputStringIdKey userKey;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputSessionSystemCreateInfo() {
    }

    public WebInputLongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(WebInputLongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public WebInputStringIdKey getUserKey() {
        return userKey;
    }

    public void setUserKey(WebInputStringIdKey userKey) {
        this.userKey = userKey;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputSessionSystemCreateInfo{" +
                "toolKey=" + toolKey +
                ", userKey=" + userKey +
                ", remark='" + remark + '\'' +
                '}';
    }
}
