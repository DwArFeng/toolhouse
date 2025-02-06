package com.dwarfeng.toolhouse.impl.handler.executor.groovy;

import com.dwarfeng.toolhouse.impl.handler.executor.AbstractAgent;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * Groovy 执行器执行器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component("groovyExecutorRegistry.groovyAgent")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class GroovyAgent extends AbstractAgent {

    private final Processor processor;

    public GroovyAgent(Processor processor) {
        this.processor = processor;
    }

    @Override
    protected void doExecute() throws Exception {
        processor.execute(context);
    }

    @Override
    public String toString() {
        return "GroovyAgent{" +
                "processor=" + processor +
                ", context=" + context +
                '}';
    }
}
