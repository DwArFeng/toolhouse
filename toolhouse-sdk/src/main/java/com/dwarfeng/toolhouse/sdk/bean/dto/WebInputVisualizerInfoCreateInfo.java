package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoCreateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 可视化器信息创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVisualizerInfoCreateInfo implements Dto {

    private static final long serialVersionUID = -3145831090934753156L;

    public static VisualizerInfoCreateInfo toStackBean(WebInputVisualizerInfoCreateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VisualizerInfoCreateInfo(
                    WebInputLongIdKey.toStackBean(webInput.getToolKey()),
                    webInput.getVisualizerStringId(),
                    webInput.isEnabled(),
                    webInput.getType(),
                    webInput.getParam(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "tool_key")
    @NotNull
    @Valid
    private WebInputLongIdKey toolKey;

    @JSONField(name = "visualizer_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String visualizerStringId;

    @JSONField(name = "enabled")
    private boolean enabled;

    @JSONField(name = "type")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String type;

    @JSONField(name = "param")
    private String param;

    @JSONField(name = "remark")
    @Length(max = Constraints.LENGTH_REMARK)
    private String remark;

    public WebInputVisualizerInfoCreateInfo() {
    }

    public WebInputLongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(WebInputLongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public String getVisualizerStringId() {
        return visualizerStringId;
    }

    public void setVisualizerStringId(String visualizerStringId) {
        this.visualizerStringId = visualizerStringId;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParam() {
        return param;
    }

    public void setParam(String param) {
        this.param = param;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "WebInputVisualizerInfoCreateInfo{" +
                "toolKey=" + toolKey +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
