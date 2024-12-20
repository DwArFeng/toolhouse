package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorInfo;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;

/**
 * 执行器信息数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecutorInfoDao extends BatchBaseDao<ExecutorKey, ExecutorInfo>, EntireLookupDao<ExecutorInfo>,
        PresetLookupDao<ExecutorInfo> {
}
