package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;

/**
 * 可视化器系统调用信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerSystemCallInfo implements Dto {

    private static final long serialVersionUID = 7069659179387160103L;

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

    public VisualizerSystemCallInfo() {
    }

    public VisualizerSystemCallInfo(
            LongIdKey sessionKey, String visualizerStringId, String functionName, String requestText
    ) {
        this.sessionKey = sessionKey;
        this.visualizerStringId = visualizerStringId;
        this.functionName = functionName;
        this.requestText = requestText;
    }

    public VisualizerSystemCallInfo(
            LongIdKey sessionKey, String visualizerStringId, String functionName, String functionDescription,
            String requestText, String requestTextDescription
    ) {
        this.sessionKey = sessionKey;
        this.visualizerStringId = visualizerStringId;
        this.functionName = functionName;
        this.functionDescription = functionDescription;
        this.requestText = requestText;
        this.requestTextDescription = requestTextDescription;
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

    @Override
    public String toString() {
        return "VisualizerSystemCallInfo{" +
                "sessionKey=" + sessionKey +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                ", functionName='" + functionName + '\'' +
                ", functionDescription='" + functionDescription + '\'' +
                ", requestText='" + requestText + '\'' +
                ", requestTextDescription='" + requestTextDescription + '\'' +
                '}';
    }
}
