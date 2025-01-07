package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.OutputItem;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;

/**
 * 输出项维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface OutputItemMaintainService extends BatchCrudService<TaskItemKey, OutputItem>,
        EntireLookupService<OutputItem>, PresetLookupService<OutputItem> {

    String CHILD_FOR_TASK = "child_for_task";
    String SESSION_KEY_EQ = "session_key_eq";
    String TOOL_KEY_EQ = "tool_key_eq";
    String USER_KEY_EQ = "user_key_eq";
}
