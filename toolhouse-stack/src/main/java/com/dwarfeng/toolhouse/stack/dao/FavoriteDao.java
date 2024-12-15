package com.dwarfeng.toolhouse.stack.dao;

import com.dwarfeng.subgrade.stack.dao.BatchBaseDao;
import com.dwarfeng.subgrade.stack.dao.EntireLookupDao;
import com.dwarfeng.subgrade.stack.dao.PresetLookupDao;
import com.dwarfeng.toolhouse.stack.bean.entity.Favorite;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;

/**
 * 收藏数据访问层。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FavoriteDao extends BatchBaseDao<FavoriteKey, Favorite>, EntireLookupDao<Favorite>,
        PresetLookupDao<Favorite> {
}
