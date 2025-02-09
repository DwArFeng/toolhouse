package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerCallResult;

import java.util.Objects;

/**
 * FastJson 可视化器调用结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class FastJsonVisualizerCallResult implements Dto {

    private static final long serialVersionUID = -4315582189720904131L;

    public static FastJsonVisualizerCallResult of(VisualizerCallResult visualizerCallResult) {
        if (Objects.isNull(visualizerCallResult)) {
            return null;
        } else {
            return new FastJsonVisualizerCallResult(
                    visualizerCallResult.getResponseText(),
                    visualizerCallResult.getResponseTextDescription()
            );
        }
    }

    @JSONField(name = "response_text", ordinal = 1)
    private String responseText;

    @JSONField(name = "response_text_description", ordinal = 2)
    private String responseTextDescription;

    public FastJsonVisualizerCallResult() {
    }

    public FastJsonVisualizerCallResult(String responseText) {
        this.responseText = responseText;
    }

    public FastJsonVisualizerCallResult(String responseText, String responseTextDescription) {
        this.responseText = responseText;
        this.responseTextDescription = responseTextDescription;
    }

    public String getResponseText() {
        return responseText;
    }

    public void setResponseText(String responseText) {
        this.responseText = responseText;
    }

    public String getResponseTextDescription() {
        return responseTextDescription;
    }

    public void setResponseTextDescription(String responseTextDescription) {
        this.responseTextDescription = responseTextDescription;
    }

    @Override
    public String toString() {
        return "FastJsonVisualizerCallResult{" +
                "responseText='" + responseText + '\'' +
                ", responseTextDescription='" + responseTextDescription + '\'' +
                '}';
    }
}
