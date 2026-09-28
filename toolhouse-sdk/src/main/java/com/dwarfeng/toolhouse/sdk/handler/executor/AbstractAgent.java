package com.dwarfeng.toolhouse.sdk.handler.executor;

import com.dwarfeng.toolhouse.stack.exception.ExecutorException;
import com.dwarfeng.toolhouse.stack.handler.Executor;
import com.dwarfeng.toolhouse.stack.handler.Executor.Context;

/**
 * 执行器代理人的抽象实现。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
public abstract class AbstractAgent implements Executor.Agent {

    protected Context context;

    @Override
    public void init(Context context) {
        this.context = context;
    }

    @Override
    public void execute() throws ExecutorException {
        try {
            doExecute();
        } catch (ExecutorException e) {
            throw e;
        } catch (Exception e) {
            throw new ExecutorException(e);
        }
    }

    protected abstract void doExecute() throws Exception;

    @Override
    public String toString() {
        return "AbstractAgent{" +
                "context=" + context +
                '}';
    }
}
