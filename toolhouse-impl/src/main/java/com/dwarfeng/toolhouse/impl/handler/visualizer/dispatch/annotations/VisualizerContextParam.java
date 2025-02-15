package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.annotations;

import java.lang.annotation.*;

/**
 * 可视化器上下文参数。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Documented
@Target(ElementType.PARAMETER)
@Retention(RetentionPolicy.RUNTIME)
public @interface VisualizerContextParam {
}
