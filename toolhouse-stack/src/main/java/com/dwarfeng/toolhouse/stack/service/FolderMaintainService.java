package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.service.BatchCrudService;
import com.dwarfeng.subgrade.stack.service.EntireLookupService;
import com.dwarfeng.subgrade.stack.service.PresetLookupService;
import com.dwarfeng.toolhouse.stack.bean.entity.Folder;

/**
 * 文件夹维护服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FolderMaintainService extends BatchCrudService<LongIdKey, Folder>,
        EntireLookupService<Folder>, PresetLookupService<Folder> {

    String CHILD_FOR_PARENT = "child_for_parent";
    String CHILD_FOR_CABINET = "child_for_cabinet";
    String CHILD_FOR_CABINET_ROOT = "child_for_cabinet_root";
    String NAME_LIKE = "name_like";
    String CHILD_FOR_CABINET_NAME_LIKE = "child_for_cabinet_name_like";
}
