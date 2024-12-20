package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerSupport;

/**
 * 可视化器支持数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizerSupportDao extends BatchBaseDao<StringIdKey, VisualizerSupport>,
        EntireLookupDao<VisualizerSupport>, PresetLookupDao<VisualizerSupport> {
}
