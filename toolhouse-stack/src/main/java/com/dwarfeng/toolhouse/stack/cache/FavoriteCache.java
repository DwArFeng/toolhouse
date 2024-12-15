package com.dwarfeng.toolhouse.stack.cache;

import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.toolhouse.stack.bean.entity.Favorite;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;

/**
 * 收藏缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FavoriteCache extends BatchBaseCache<FavoriteKey, Favorite> {
}
