package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;

import java.io.InputStream;

/**
 * 可视化器流式调用结果。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerStreamCallResult implements Dto {

    private static final long serialVersionUID = -5550108026048656138L;

    /**
     * 响应文本。
     *
     * <p>
     * 非必填字段。
     */
    private String responseText;

    /**
     * 响应文本描述。
     *
     * <p>
     * 用于描述响应文本的作用，为调试人员提供帮助。
     *
     * <p>
     * 非必填字段。
     */
    private String responseTextDescription;

    /**
     * 响应流。
     *
     * <p>
     * 非必填字段。
     */
    private InputStream responseStream;

    /**
     * 响应流描述。
     *
     * <p>
     * 用于描述响应流的作用，为调试人员提供帮助。
     *
     * <p>
     * 非必填字段。
     */
    private String responseStreamDescription;

    public VisualizerStreamCallResult() {
    }

    public VisualizerStreamCallResult(String responseText, InputStream responseStream) {
        this.responseText = responseText;
        this.responseStream = responseStream;
    }

    public VisualizerStreamCallResult(
            String responseText, String responseTextDescription, InputStream responseStream,
            String responseStreamDescription
    ) {
        this.responseText = responseText;
        this.responseTextDescription = responseTextDescription;
        this.responseStream = responseStream;
        this.responseStreamDescription = responseStreamDescription;
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

    public InputStream getResponseStream() {
        return responseStream;
    }

    public void setResponseStream(InputStream responseStream) {
        this.responseStream = responseStream;
    }

    public String getResponseStreamDescription() {
        return responseStreamDescription;
    }

    public void setResponseStreamDescription(String responseStreamDescription) {
        this.responseStreamDescription = responseStreamDescription;
    }

    @Override
    public String toString() {
        return "VisualizerStreamCallResult{" +
                "responseText='" + responseText + '\'' +
                ", responseTextDescription='" + responseTextDescription + '\'' +
                ", responseStream=" + responseStream +
                ", responseStreamDescription='" + responseStreamDescription + '\'' +
                '}';
    }
}
