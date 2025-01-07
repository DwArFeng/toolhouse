package com.dwarfeng.toolhouse.stack.cache;

import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.toolhouse.stack.bean.entity.Variable;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;

/**
 * 变量缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VariableCache extends BatchBaseCache<VariableKey, Variable> {
}
