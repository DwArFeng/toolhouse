package com.dwarfeng.toolhouse.sdk.bean.key;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.ToStringSerializer;
import com.dwarfeng.subgrade.stack.bean.key.Key;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;

import java.util.Objects;

/**
 * JSFixed FastJson 可视化器键。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class JSFixedFastJsonVisualizerKey implements Key {

    private static final long serialVersionUID = -506548643984022639L;

    public static JSFixedFastJsonVisualizerKey of(VisualizerKey visualizerKey) {
        if (Objects.isNull(visualizerKey)) {
            return null;
        } else {
            return new JSFixedFastJsonVisualizerKey(
                    visualizerKey.getToolLongId(),
                    visualizerKey.getVisualizerStringId()
            );
        }
    }

    @JSONField(name = "tool_long_id", ordinal = 1, serializeUsing = ToStringSerializer.class)
    private Long toolLongId;

    @JSONField(name = "visualizer_string_id", ordinal = 2)
    private String visualizerStringId;

    public JSFixedFastJsonVisualizerKey() {
    }

    public JSFixedFastJsonVisualizerKey(Long toolLongId, String visualizerStringId) {
        this.toolLongId = toolLongId;
        this.visualizerStringId = visualizerStringId;
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
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;

        JSFixedFastJsonVisualizerKey that = (JSFixedFastJsonVisualizerKey) o;
        return Objects.equals(toolLongId, that.toolLongId) &&
                Objects.equals(visualizerStringId, that.visualizerStringId);
    }

    @Override
    public int hashCode() {
        int result = Objects.hashCode(toolLongId);
        result = 31 * result + Objects.hashCode(visualizerStringId);
        return result;
    }

    @Override
    public String toString() {
        return "JSFixedFastJsonVisualizerKey{" +
                "toolLongId=" + toolLongId +
                ", visualizerStringId='" + visualizerStringId + '\'' +
                '}';
    }
}
