package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.sdk.util.Constraints;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 可视化器键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVisualizerKey implements Key {

    private static final long serialVersionUID = -8912961019856268496L;

    public static VisualizerKey toStackBean(WebInputVisualizerKey webInputVisualizerKey) {
        if (Objects.isNull(webInputVisualizerKey)) {
            return null;
        } else {
            return new VisualizerKey(
                    webInputVisualizerKey.getToolLongId(),
                    webInputVisualizerKey.getVisualizerStringId()
            );
        }
    }

    @JSONField(name = "tool_long_id")
    @NotNull
    private Long toolLongId;

    @JSONField(name = "visualizer_string_id")
    @NotNull
    @NotEmpty
    @Length(max = Constraints.LENGTH_STRING_ID)
    private String visualizerStringId;

    public WebInputVisualizerKey() {
    }

    public Long getToolLongId() {
        return toolLongId;
    }

    public void setToolLongId(Long toolLongId) {
        this.toolLongId = toolLongId;
    }

    public String getVisualizerStringId() {
        return visualizerStringId;
    }

    public void setVisualizerStringId(String visualizerStringId) {
        this.visualizerStringId = visualizerStringId;
    }

    @Override
    public String toString() {
        return "WebInputVisualizerKey{" +
                "toolLongId=" + toolLongId +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                '}';
    }
}
