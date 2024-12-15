package com.dwarfeng.toolhouse.stack.cache;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.toolhouse.stack.bean.entity.User;

/**
 * 用户缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface UserCache extends BatchBaseCache<StringIdKey, User> {
}
