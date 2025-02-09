package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerManualCallInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 可视化器手动调用信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVisualizerManualCallInfo implements Dto {

    private static final long serialVersionUID = -2621194259618439421L;

    public static VisualizerManualCallInfo toStackBean(WebInputVisualizerManualCallInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VisualizerManualCallInfo(
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

    public WebInputVisualizerManualCallInfo() {
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
        return "WebInputVisualizerManualCallInfo{" +
                "sessionKey=" + sessionKey +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionDescription='" + functionDescription + '\'' +
                ", requestText='" + requestText + '\'' +
                ", requestTextDescription='" + requestTextDescription + '\'' +
                '}';
    }
}
