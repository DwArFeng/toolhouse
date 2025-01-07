package com.dwarfeng.toolhouse.stack.cache;

import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.toolhouse.stack.bean.entity.InputItem;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;

/**
 * 输入项缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface InputItemCache extends BatchBaseCache<TaskItemKey, InputItem> {
}
