package com.dwarfeng.toolhouse.impl.handler.executor.mock;

import com.alibaba.fastjson.JSON;
import com.dwarfeng.toolhouse.sdk.handler.executor.AbstractExecutorRegistry;
import com.dwarfeng.toolhouse.stack.exception.ExecutorException;
import com.dwarfeng.toolhouse.stack.handler.Executor;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * 模拟执行器注册。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component("mockExecutorRegistry")
public class MockExecutorRegistry extends AbstractExecutorRegistry {

    public static final String EXECUTOR_TYPE = "mock_executor";

    private final ApplicationContext ctx;

    public MockExecutorRegistry(ApplicationContext ctx) {
        super(EXECUTOR_TYPE);
        this.ctx = ctx;
    }

    @Override
    public String provideLabel() {
        return "模拟执行器";
    }

    @Override
    public String provideDescription() {
        return "提供模拟的耗时以及成功率, 用于测试";
    }

    @Override
    public String provideExampleParam() {
        Config config = new Config(12450L, 30000L, 0.5);
        return JSON.toJSONString(config, true);
    }

    @Override
    public Executor makeExecutor(String type, String param) throws ExecutorException {
        try {
            Config config = JSON.parseObject(param, Config.class);
            return ctx.getBean(MockExecutor.class, ctx, config);
        } catch (Exception e) {
            throw new ExecutorException(e);
        }
    }

    @Override
    public String toString() {
        return "MockExecutorRegistry{" +
                "ctx=" + ctx +
                ", executorType='" + executorType + '\'' +
                '}';
    }
}
