package com.dwarfeng.toolhouse.stack.cache;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;

/**
 * 会话缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface SessionCache extends BatchBaseCache<LongIdKey, Session> {
}
