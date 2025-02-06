package com.dwarfeng.toolhouse.impl.handler.executor;

import com.dwarfeng.toolhouse.stack.exception.ExecutorException;
import com.dwarfeng.toolhouse.stack.handler.Executor;

/**
 * 执行器的抽象实现。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public abstract class AbstractExecutor implements Executor {

    @Override
    public Agent newAgent() throws ExecutorException {
        try {
            return doNewExecutor();
        } catch (ExecutorException e) {
            throw e;
        } catch (Exception e) {
            throw new ExecutorException(e);
        }
    }

    protected abstract Agent doNewExecutor() throws Exception;

    @Override
    public String toString() {
        return "AbstractExecutor{}";
    }
}
