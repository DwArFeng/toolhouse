package com.dwarfeng.toolhouse.impl.handler.executor.groovy;

import com.dwarfeng.toolhouse.stack.handler.Executor;

/**
 * Groovy 处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface Processor {

    /**
     * 执行。
     *
     * @param context 执行器的执行器上下文。
     * @throws Exception 方法执行过程中发生的任何异常。
     */
    void execute(Executor.Context context) throws Exception;
}
