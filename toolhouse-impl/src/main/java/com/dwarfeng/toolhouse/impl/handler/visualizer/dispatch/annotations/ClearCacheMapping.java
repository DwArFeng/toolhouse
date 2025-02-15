package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.annotations;

import java.lang.annotation.*;

/**
 * 清除缓存映射。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Documented
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ClearCacheMapping {
}
