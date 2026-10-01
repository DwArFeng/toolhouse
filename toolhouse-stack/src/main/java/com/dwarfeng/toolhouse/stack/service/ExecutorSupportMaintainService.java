package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorSupport;

/**
 * 执行器支持维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecutorSupportMaintainService extends BatchCrudService<StringIdKey, ExecutorSupport>,
        EntireLookupService<ExecutorSupport>, PresetLookupService<ExecutorSupport> {

    String ID_LIKE = "id_like";
    String LABEL_LIKE = "label_like";
}
