package com.dwarfeng.toolhouse.impl.handler.executor;

import com.dwarfeng.toolhouse.impl.handler.ExecutorMaker;
import com.dwarfeng.toolhouse.impl.handler.ExecutorSupporter;

import java.util.Objects;

/**
 * 抽象执行器注册。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public abstract class AbstractExecutorRegistry implements ExecutorMaker, ExecutorSupporter {

    protected String executorType;

    public AbstractExecutorRegistry() {
    }

    public AbstractExecutorRegistry(String executorType) {
        this.executorType = executorType;
    }

    @Override
    public boolean supportType(String type) {
        return Objects.equals(executorType, type);
    }

    @Override
    public String provideType() {
        return executorType;
    }

    public String getExecutorType() {
        return executorType;
    }

    public void setExecutorType(String executorType) {
        this.executorType = executorType;
    }

    @Override
    public String toString() {
        return "AbstractExecutorRegistry{" +
                "executorType='" + executorType + '\'' +
                '}';
    }
}
