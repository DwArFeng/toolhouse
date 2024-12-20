package com.dwarfeng.toolhouse.stack.exception;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;

/**
 * 执行器信息不存在异常。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public class ExecutorInfoNotExistsException extends HandlerException {

    private static final long serialVersionUID = -1228158522938921681L;
    
    private final ExecutorKey executorInfoKey;

    public ExecutorInfoNotExistsException(ExecutorKey executorInfoKey) {
        this.executorInfoKey = executorInfoKey;
    }

    public ExecutorInfoNotExistsException(Throwable cause, ExecutorKey executorInfoKey) {
        super(cause);
        this.executorInfoKey = executorInfoKey;
    }

    @Override
    public String getMessage() {
        return "执行器信息 " + executorInfoKey + " 不存在";
    }
}
