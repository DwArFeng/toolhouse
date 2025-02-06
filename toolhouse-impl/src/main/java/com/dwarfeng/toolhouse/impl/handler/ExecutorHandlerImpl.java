package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.exception.ExecutorException;
import com.dwarfeng.toolhouse.stack.exception.UnsupportedExecutorTypeException;
import com.dwarfeng.toolhouse.stack.handler.Executor;
import com.dwarfeng.toolhouse.stack.handler.ExecutorHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
public class ExecutorHandlerImpl implements ExecutorHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExecutorHandlerImpl.class);

    private final List<ExecutorMaker> executorMakers;

    public ExecutorHandlerImpl(List<ExecutorMaker> executorMakers) {
        this.executorMakers = Optional.ofNullable(executorMakers).orElse(Collections.emptyList());
    }

    @Override
    public Executor make(String type, String param) throws HandlerException {
        try {
            // 生成执行器。
            LOGGER.debug("通过执行器信息构建新的的执行器...");
            ExecutorMaker executorMaker = executorMakers.stream().filter(maker -> maker.supportType(type))
                    .findFirst().orElseThrow(() -> new UnsupportedExecutorTypeException(type));
            Executor executor = executorMaker.makeExecutor(type, param);
            LOGGER.debug("执行器构建成功!");
            LOGGER.debug("执行器: {}", executor);
            return executor;
        } catch (ExecutorException e) {
            throw e;
        } catch (Exception e) {
            throw new ExecutorException(e);
        }
    }
}
