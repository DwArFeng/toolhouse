package com.dwarfeng.toolhouse.sdk.bean.dto;

import com.alibaba.fastjson.annotation.JSONField;
import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.sdk.bean.key.WebInputVisualizerKey;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoRemoveInfo;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Objects;

/**
 * WebInput 可视化器信息删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class WebInputVisualizerInfoRemoveInfo implements Dto {

    private static final long serialVersionUID = 5456535640307709678L;

    public static VisualizerInfoRemoveInfo toStackBean(WebInputVisualizerInfoRemoveInfo webInput) {
        if (Objects.isNull(webInput)) {
            return null;
        } else {
            return new VisualizerInfoRemoveInfo(
                    WebInputVisualizerKey.toStackBean(webInput.getVisualizerKey())
            );
        }
    }

    @JSONField(name = "visualizer_key")
    @NotNull
    @Valid
    private WebInputVisualizerKey visualizerKey;

    public WebInputVisualizerInfoRemoveInfo() {
    }

    public WebInputVisualizerKey getVisualizerKey() {
        return visualizerKey;
    }

    public void setVisualizerKey(WebInputVisualizerKey visualizerKey) {
        this.visualizerKey = visualizerKey;
    }

    @Override
    public String toString() {
        return "WebInputVisualizerInfoRemoveInfo{" +
                "visualizerKey=" + visualizerKey +
                '}';
    }
}
