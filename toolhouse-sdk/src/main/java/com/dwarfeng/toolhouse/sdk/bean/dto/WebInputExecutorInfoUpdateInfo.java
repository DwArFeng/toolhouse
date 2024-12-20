package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.bean.key.WebInputExecutorKey;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoUpdateInfo;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 执行器信息更新信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputExecutorInfoUpdateInfo implements Dto {

    private static final long serialVersionUID = -5343908649111891270L;

    public static ExecutorInfoUpdateInfo toStackBean(WebInputExecutorInfoUpdateInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new ExecutorInfoUpdateInfo(
                    WebInputExecutorKey.toStackBean(webInput.getExecutorKey()),
                    webInput.isEnabled(),
                    webInput.getType(),
                    webInput.getParam(),
                    webInput.getRemark()
            );
        }
    }

    @JSONField(name = "executor_key")
    @NotNull
    @Valid
    private WebInputExecutorKey executorKey;

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

    public WebInputExecutorInfoUpdateInfo() {
    }

    public WebInputExecutorKey getExecutorKey() {
        return executorKey;
    }

    public void setExecutorKey(WebInputExecutorKey executorKey) {
        this.executorKey = executorKey;
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
        return "WebInputExecutorInfoUpdateInfo{" +
                "executorKey=" + executorKey +
                ", enabled=" + enabled +
                ", type='" + type + '\'' +
                ", param='" + param + '\'' +
                ", remark='" + remark + '\'' +
                '}';
    }
}
