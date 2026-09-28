package com.dwarfeng.toolhouse.impl.handler.executor.groovy;

import com.dwarfeng.toolhouse.sdk.handler.executor.AbstractExecutor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Groovy 执行器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component("groovyExecutorRegistry.groovyExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyExecutor extends AbstractExecutor {

    private final ApplicationContext ctx;

    private final Processor processor;

    public GroovyExecutor(ApplicationContext ctx, Processor processor) {
        this.ctx = ctx;
        this.processor = processor;
    }

    @Override
    protected Agent doNewExecutor() {
        return ctx.getBean(GroovyAgent.class, processor);
    }

    @Override
    public String toString() {
        return "GroovyExecutor{" +
                "ctx=" + ctx +
                ", processor=" + processor +
                '}';
    }
}
