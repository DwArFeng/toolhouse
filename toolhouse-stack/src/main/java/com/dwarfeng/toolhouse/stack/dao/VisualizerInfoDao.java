package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;

/**
 * 可视化器信息数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizerInfoDao extends BatchBaseDao<VisualizerKey, VisualizerInfo>, EntireLookupDao<VisualizerInfo>,
        PresetLookupDao<VisualizerInfo> {
}
