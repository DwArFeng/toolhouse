package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

/**
 * 可视化器调用结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerCallResult implements Dto {

    private static final long serialVersionUID = 4181607699245840862L;

    /**
     * 响应文本。
     *
     * <p>
     * 非必填字段。
     */
    private String responseText;

    /**
     * 响应描述。
     *
     * <p>
     * 用于描述响应的作用，为调试人员提供帮助。
     *
     * <p>
     * 非必填字段。
     */
    private String responseTextDescription;

    public VisualizerCallResult() {
    }

    public VisualizerCallResult(String responseText) {
        this.responseText = responseText;
    }

    public VisualizerCallResult(String responseText, String responseTextDescription) {
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
        return "VisualizerCallResult{" +
                "responseText='" + responseText + '\'' +
                ", responseTextDescription='" + responseTextDescription + '\'' +
                '}';
    }
}
