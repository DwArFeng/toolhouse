package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.toolhouse.stack.bean.entity.Poca;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;

/**
 * 工具柜权限数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface PocaDao extends BatchBaseDao<PocaKey, Poca>, EntireLookupDao<Poca>,
        PresetLookupDao<Poca> {
}
