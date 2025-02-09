package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerOverrideCallInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 可视化器超控调用信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVisualizerOverrideCallInfo implements Dto {

    private static final long serialVersionUID = 1138590144802217006L;

    public static VisualizerOverrideCallInfo toStackBean(WebInputVisualizerOverrideCallInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VisualizerOverrideCallInfo(
                    WebInputLongIdKey.toStackBean(webInput.getSessionKey()),
                    webInput.getVisualizerStringId(),
                    webInput.getFunctionName(),
                    webInput.getFunctionDescription(),
                    webInput.getRequestText(),
                    webInput.getRequestTextDescription()
            );
        }
    }

    @JSONField(name = "session_key")
    @NotNull
    @Valid
    private WebInputLongIdKey sessionKey;

    @JSONField(name = "visualizer_string_id")
    @NotNull
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String visualizerStringId;

    @JSONField(name = "function_name")
    @NotNull
    private String functionName;

    @JSONField(name = "function_description")
    private String functionDescription;

    @JSONField(name = "request_text")
    private String requestText;

    @JSONField(name = "request_text_description")
    private String requestTextDescription;

    public WebInputVisualizerOverrideCallInfo() {
    }

    public WebInputLongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(WebInputLongIdKey sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getVisualizerStringId() {
        return visualizerStringId;
    }

    public void setVisualizerStringId(String visualizerStringId) {
        this.visualizerStringId = visualizerStringId;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getFunctionDescription() {
        return functionDescription;
    }

    public void setFunctionDescription(String functionDescription) {
        this.functionDescription = functionDescription;
    }

    public String getRequestText() {
        return requestText;
    }

    public void setRequestText(String requestText) {
        this.requestText = requestText;
    }

    public String getRequestTextDescription() {
        return requestTextDescription;
    }

    public void setRequestTextDescription(String requestTextDescription) {
        this.requestTextDescription = requestTextDescription;
    }

    @Override
    public String toString() {
        return "WebInputVisualizerOverrideCallInfo{" +
                "sessionKey=" + sessionKey +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionDescription='" + functionDescription + '\'' +
                ", requestText='" + requestText + '\'' +
                ", requestTextDescription='" + requestTextDescription + '\'' +
                '}';
    }
}
