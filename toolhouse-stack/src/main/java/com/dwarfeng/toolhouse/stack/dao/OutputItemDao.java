package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.toolhouse.stack.bean.entity.OutputItem;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;

/**
 * 输出项数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface OutputItemDao extends BatchBaseDao<TaskItemKey, OutputItem>, EntireLookupDao<OutputItem>,
        PresetLookupDao<OutputItem> {
}
