package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;

/**
 * 工具数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ToolDao extends BatchBaseDao<LongIdKey, Tool>, EntireLookupDao<Tool>,
        PresetLookupDao<Tool> {
}
