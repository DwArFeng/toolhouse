package com.dwarfeng.toolhouse.stack.cache;

import com.dwarfeng.subgrade.stack.cache.BatchBaseCache;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorInfo;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;

/**
 * 执行器信息缓存。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecutorInfoCache extends BatchBaseCache<ExecutorKey, ExecutorInfo> {
}
