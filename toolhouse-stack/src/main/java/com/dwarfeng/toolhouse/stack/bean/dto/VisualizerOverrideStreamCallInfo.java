package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

import java.io.InputStream;

/**
 * 可视化器超控流式调用信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerOverrideStreamCallInfo implements Dto {

    private static final long serialVersionUID = 533898595290763466L;

    /**
     * 会话主键。
     *
     * <p>
     * 必填字段。
     */
    private LongIdKey sessionKey;

    /**
     * 可视化器字符串 ID。
     *
     * <p>
     * 必填字段。
     */
    private String visualizerStringId;

    /**
     * 函数名称。
     */
    private String functionName;

    /**
     * 函数描述。
     *
     * <p>
     * 用于描述函数的作用，为调试人员提供帮助。
     *
     * <p>
     * 非必填字段。
     */
    private String functionDescription;

    /**
     * 请求文本。
     *
     * <p>
     * 非必填字段。
     */
    private String requestText;

    /**
     * 请求文本描述。
     *
     * <p>
     * 用于描述请求文本的作用，为调试人员提供帮助。
     *
     * <p>
     * 非必填字段。
     */
    private String requestTextDescription;

    /**
     * 请求流。
     *
     * <p>
     * 非必填字段。
     */
    private InputStream requestStream;

    /**
     * 请求流描述。
     *
     * <p>
     * 用于描述请求流的作用，为调试人员提供帮助。
     *
     * <p>
     * 非必填字段。
     */
    private String requestStreamDescription;

    public VisualizerOverrideStreamCallInfo() {
    }

    public VisualizerOverrideStreamCallInfo(
            LongIdKey sessionKey, String visualizerStringId, String functionName, String requestText,
            InputStream requestStream
    ) {
        this.sessionKey = sessionKey;
        this.visualizerStringId = visualizerStringId;
        this.functionName = functionName;
        this.requestText = requestText;
        this.requestStream = requestStream;
    }

    public VisualizerOverrideStreamCallInfo(
            LongIdKey sessionKey, String visualizerStringId, String functionName, String functionDescription,
            String requestText, String requestTextDescription, InputStream requestStream,
            String requestStreamDescription
    ) {
        this.sessionKey = sessionKey;
        this.visualizerStringId = visualizerStringId;
        this.functionName = functionName;
        this.functionDescription = functionDescription;
        this.requestText = requestText;
        this.requestTextDescription = requestTextDescription;
        this.requestStream = requestStream;
        this.requestStreamDescription = requestStreamDescription;
    }

    public LongIdKey getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(LongIdKey sessionKey) {
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

    public InputStream getRequestStream() {
        return requestStream;
    }

    public void setRequestStream(InputStream requestStream) {
        this.requestStream = requestStream;
    }

    public String getRequestStreamDescription() {
        return requestStreamDescription;
    }

    public void setRequestStreamDescription(String requestStreamDescription) {
        this.requestStreamDescription = requestStreamDescription;
    }

    @Override
    public String toString() {
        return "VisualizerOverrideStreamCallInfo{" +
                "sessionKey=" + sessionKey +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionDescription='" + functionDescription + '\'' +
                ", requestText='" + requestText + '\'' +
                ", requestTextDescription='" + requestTextDescription + '\'' +
                ", requestStream=" + requestStream +
                ", requestStreamDescription='" + requestStreamDescription + '\'' +
                '}';
    }
}
