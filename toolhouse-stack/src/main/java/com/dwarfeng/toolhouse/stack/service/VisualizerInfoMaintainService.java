package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;

/**
 * 可视化器信息维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizerInfoMaintainService extends BatchCrudService<VisualizerKey, VisualizerInfo>,
        EntireLookupService<VisualizerInfo>, PresetLookupService<VisualizerInfo> {

    String CHILD_FOR_TOOL = "child_for_tool";
    String CHILD_FOR_TOOL_VISUALIZER_ID_ASC = "child_for_tool_visualizer_id_asc";
}
