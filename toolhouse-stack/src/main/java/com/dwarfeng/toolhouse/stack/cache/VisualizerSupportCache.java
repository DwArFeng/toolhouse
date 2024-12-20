package com.dwarfeng.toolhouse.stack.cache;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerSupport;

/**
 * 可视化器支持缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizerSupportCache extends BatchBaseCache<StringIdKey, VisualizerSupport> {
}
