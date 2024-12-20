package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorInfo;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;

/**
 * 执行器信息维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecutorInfoMaintainService extends BatchCrudService<ExecutorKey, ExecutorInfo>,
        EntireLookupService<ExecutorInfo>, PresetLookupService<ExecutorInfo> {

    String CHILD_FOR_TOOL = "child_for_tool";
    String CHILD_FOR_TOOL_EXECUTOR_ID_ASC = "child_for_tool_executor_id_asc";
}
