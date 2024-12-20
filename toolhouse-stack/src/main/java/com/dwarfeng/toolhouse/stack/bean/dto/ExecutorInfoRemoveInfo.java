package com.dwarfeng.toolhouse.stack.bean.dto;

import com.dwarfeng.subgrade.stack.bean.dto.Dto;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;

/**
 * 执行器信息删除信息。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ExecutorInfoRemoveInfo implements Dto {

    private static final long serialVersionUID = 2379065476039215256L;
    
    private ExecutorKey executorKey;

    public ExecutorInfoRemoveInfo() {
    }

    public ExecutorInfoRemoveInfo(ExecutorKey executorKey) {
        this.executorKey = executorKey;
    }

    public ExecutorKey getExecutorKey() {
        return executorKey;
    }

    public void setExecutorKey(ExecutorKey executorKey) {
        this.executorKey = executorKey;
    }

    @Override
    public String toString() {
        return "ExecutorInfoRemoveInfo{" +
                "executorKey=" + executorKey +
                '}';
    }
}
