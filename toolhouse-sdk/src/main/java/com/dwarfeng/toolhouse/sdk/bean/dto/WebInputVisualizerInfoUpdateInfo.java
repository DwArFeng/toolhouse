package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.bean.key.WebInputVisualizerKey;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoUpdateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 可视化器信息更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVisualizerInfoUpdateInfo implements Dto {

    private static final long serialVersionUID = 811117027988668138L;

    public static VisualizerInfoUpdateInfo toStackBean(WebInputVisualizerInfoUpdateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VisualizerInfoUpdateInfo(
                    WebInputVisualizerKey.toStackBean(webInput.getVisualizerKey()),
                    webInput.isEnabled(),
                    webInput.getType(),
                    webInput.getParam(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "visualizer_key")
    @NotNull
    @Valid
    private WebInputVisualizerKey visualizerKey;

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

    public WebInputVisualizerInfoUpdateInfo() {
    }

    public WebInputVisualizerKey getVisualizerKey() {
        return visualizerKey;
    }

    public void setVisualizerKey(WebInputVisualizerKey visualizerKey) {
        this.visualizerKey = visualizerKey;
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
        return "WebInputVisualizerInfoUpdateInfo{" +
                "visualizerKey=" + visualizerKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
