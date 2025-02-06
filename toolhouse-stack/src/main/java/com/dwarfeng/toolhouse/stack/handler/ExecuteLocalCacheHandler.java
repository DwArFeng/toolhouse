package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.handler.LocalCacheHandler;
import com.dwarfeng.toolhouse.stack.struct.ExecuteInfo;

/**
 * 执行用本地缓存处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecuteLocalCacheHandler extends LocalCacheHandler<LongIdKey, ExecuteInfo> {
}
