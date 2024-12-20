package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorSupport;

/**
 * 执行器支持数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecutorSupportDao extends BatchBaseDao<StringIdKey, ExecutorSupport>,
        EntireLookupDao<ExecutorSupport>, PresetLookupDao<ExecutorSupport> {
}
