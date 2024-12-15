package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.Poca;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;

/**
 * 工具柜权限维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface PocaMaintainService extends BatchCrudService<PocaKey, Poca>,
        EntireLookupService<Poca>, PresetLookupService<Poca> {

    String CHILD_FOR_CABINET = "child_for_cabinet";
    String CHILD_FOR_USER = "child_for_user";
    String CHILD_FOR_CABINET_PERMISSION_LEVEL_EQUALS = "child_for_cabinet_permission_level_equals";
    String CHILD_FOR_USER_PERMISSION_LEVEL_EQUALS = "child_for_user_permission_level_equals";

    /**
     * 获取工具柜集合对应的工具柜权限子项。
     *
     * <p>
     * 参数列表：
     * <ol>
     *     <li>Collection&lt;LongIdKey&gt; 工具柜的键组成的集合。</li>
     * </ol>
     *
     * @since beta-1.0.0
     */
    String CHILD_FOR_CABINET_SET = "child_for_cabinet_set";
}
