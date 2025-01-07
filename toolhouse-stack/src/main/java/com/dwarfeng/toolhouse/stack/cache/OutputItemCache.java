package com.dwarfeng.toolhouse.stack.cache;

import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.toolhouse.stack.bean.entity.OutputItem;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;

/**
 * 输出项缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface OutputItemCache extends BatchBaseCache<TaskItemKey, OutputItem> {
}
