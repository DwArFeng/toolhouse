package com.dwarfeng.toolhouse.stack.struct;

import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;
import com.dwarfeng.toolhouse.stack.handler.Executor;

import java.util.Map;

/**
 * 执行信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public final class ExecuteInfo {

    private final Tool tool;
    private final Map<ExecutorKey, ExecutorInfo> executorInfoMap;
    private final Map<ExecutorKey, Executor> executorMap;

    public ExecuteInfo(
            Tool tool, Map<ExecutorKey, ExecutorInfo> executorInfoMap, Map<ExecutorKey, Executor> executorMap
    ) {
        this.tool = tool;
        this.executorInfoMap = executorInfoMap;
        this.executorMap = executorMap;
    }

    public Tool getTool() {
        return tool;
    }

    public Map<ExecutorKey, ExecutorInfo> getExecutorInfoMap() {
        return executorInfoMap;
    }

    public Map<ExecutorKey, Executor> getExecutorMap() {
        return executorMap;
    }

    @Override
    public String toString() {
        return "ExecuteInfo{" +
                "tool=" + tool +
                ", executorInfoMap=" + executorInfoMap +
                ", executorMap=" + executorMap +
                '}';
    }
}
