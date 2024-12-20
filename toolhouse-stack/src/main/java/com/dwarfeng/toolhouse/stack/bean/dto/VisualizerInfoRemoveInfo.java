package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;

/**
 * 可视化器信息删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class VisualizerInfoRemoveInfo implements Dto {

    private static final long serialVersionUID = -4901217184407130358L;
    
    private VisualizerKey visualizerKey;

    public VisualizerInfoRemoveInfo() {
    }

    public VisualizerInfoRemoveInfo(VisualizerKey visualizerKey) {
        this.visualizerKey = visualizerKey;
    }

    public VisualizerKey getVisualizerKey() {
        return visualizerKey;
    }

    public void setVisualizerKey(VisualizerKey visualizerKey) {
        this.visualizerKey = visualizerKey;
    }

    @Override
    public String toString() {
        return "VisualizerInfoRemoveInfo{" +
                "visualizerKey=" + visualizerKey +
                '}';
    }
}
