package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.Variable;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;

/**
 * 变量维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VariableMaintainService extends BatchCrudService<VariableKey, Variable>,
        EntireLookupService<Variable>, PresetLookupService<Variable> {

    String CHILD_FOR_SESSION = "child_for_session";
    String TOOL_KEY_EQ = "tool_key_eq";
    String USER_KEY_EQ = "user_key_eq";
}
