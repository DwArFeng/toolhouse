package com.dwarfeng.toolhouse.impl.handler.executor.mock;

import com.dwarfeng.toolhouse.impl.handler.executor.AbstractExecutor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * 模拟执行器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component("mockExecutorRegistry.mockExecutor")
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MockExecutor extends AbstractExecutor {

    private final ApplicationContext ctx;

    private final Config config;

    public MockExecutor(ApplicationContext ctx, Config config) {
        this.ctx = ctx;
        this.config = config;
    }

    @Override
    protected Agent doNewExecutor() {
        return ctx.getBean(MockAgent.class, config);
    }

    @Override
    public String toString() {
        return "MockExecutor{" +
                "ctx=" + ctx +
                ", config=" + config +
                '}';
    }
}
