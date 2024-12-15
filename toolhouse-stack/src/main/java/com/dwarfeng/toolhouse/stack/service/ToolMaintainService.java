package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;

/**
 * 工具维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ToolMaintainService extends BatchCrudService<LongIdKey, Tool>,
        EntireLookupService<Tool>, PresetLookupService<Tool> {

    String CHILD_FOR_FOLDER = "child_for_folder";
    String CHILD_FOR_CABINET = "child_for_cabinet";
    String CHILD_FOR_CABINET_ROOT = "child_for_cabinet_root";
    String NAME_LIKE = "name_like";
    String CHILD_FOR_CABINET_NAME_LIKE = "child_for_cabinet_name_like";
    String CHILD_FOR_FOLDER_NAME_ASC = "child_for_folder_name_asc";
    String CHILD_FOR_FOLDER_NAME_DESC = "child_for_folder_name_desc";
    String CHILD_FOR_CABINET_NAME_ASC = "child_for_cabinet_name_asc";
    String CHILD_FOR_CABINET_NAME_DESC = "child_for_cabinet_name_desc";
    String CHILD_FOR_CABINET_ROOT_NAME_ASC = "child_for_cabinet_root_name_asc";
    String CHILD_FOR_CABINET_ROOT_NAME_DESC = "child_for_cabinet_root_name_desc";
}
