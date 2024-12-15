package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;

/**
 * 工具柜数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CabinetDao extends BatchBaseDao<LongIdKey, Cabinet>, EntireLookupDao<Cabinet>,
        PresetLookupDao<Cabinet> {
}
