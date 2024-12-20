package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.sdk.bean.key.WebInputLongIdKey;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoCreateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 执行器信息创建信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputExecutorInfoCreateInfo implements Dto {

    private static final long serialVersionUID = -4091492037026327740L;

    public static ExecutorInfoCreateInfo toStackBean(WebInputExecutorInfoCreateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new ExecutorInfoCreateInfo(
                    WebInputLongIdKey.toStackBean(webInput.getToolKey()),
                    webInput.getExecutorStringId(),
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

    @JSONField(name = "executor_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String executorStringId;

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

    public WebInputExecutorInfoCreateInfo() {
    }

    public WebInputLongIdKey getToolKey() {
        return toolKey;
    }

    public void setToolKey(WebInputLongIdKey toolKey) {
        this.toolKey = toolKey;
    }

    public String getExecutorStringId() {
        return executorStringId;
    }

    public void setExecutorStringId(String executorStringId) {
        this.executorStringId = executorStringId;
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
        return "WebInputExecutorInfoCreateInfo{" +
                "toolKey=" + toolKey +
                ", executorStringId='" + executorStringId + '\'' +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
