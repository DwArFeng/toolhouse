package com.dwarfeng.toolhouse.stack.cache;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;

/**
 * 工具缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ToolCache extends BatchBaseCache<LongIdKey, Tool> {
}
