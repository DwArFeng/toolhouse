package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerSupport;

/**
 * 可视化器支持维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizerSupportMaintainService extends BatchCrudService<StringIdKey, VisualizerSupport>,
        EntireLookupService<VisualizerSupport>, PresetLookupService<VisualizerSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";

    /**
     * 重置可视化器支持。
     *
     * @throws ServiceException 服务异常。
     */
    void reset() throws ServiceException;
}
