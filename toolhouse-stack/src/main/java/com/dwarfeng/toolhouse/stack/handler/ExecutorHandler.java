package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 执行器执行器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecutorHandler extends Handler {

    /**
     * 根据指定的执行器信息构造一个执行器。
     *
     * @param type  执行器类型。
     * @param param 执行器参数。
     * @return 构造的执行器。
     * @throws HandlerException 处理器异常。
     */
    Executor make(String type, String param) throws HandlerException;
}
