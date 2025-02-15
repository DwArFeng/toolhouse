package com.dwarfeng.toolhouse.impl.handler.visualizer.dispatch.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 控制器注解。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
public @interface Controller {

    /**
     * 该命令的 bean 名称。
     *
     * @return 该命令的 bean 名称。
     * @see Component#value()
     */
    @AliasFor(annotation = Component.class)
    String value() default "";

    Class<?>[] groups() default {};
}
