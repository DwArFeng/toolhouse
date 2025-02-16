package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;

/**
 * 推送器处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface PushHandler extends Handler {

    /**
     * 执行功能重置时执行的广播操作。
     *
     * @throws HandlerException 处理器异常。
     */
    void executeReset() throws HandlerException;

    /**
     * 可视化功能重置时可视化的广播操作。
     *
     * @throws HandlerException 处理器异常。
     */
    void visualizeReset() throws HandlerException;

    /**
     * 任务完成时执行的推送操作。
     *
     * @param task 相关的任务。
     * @throws HandlerException 处理器异常。
     */
    void taskFinished(Task task) throws HandlerException;

    /**
     * 任务失败时执行的推送操作。
     *
     * @param task 相关的任务。
     * @throws HandlerException 处理器异常。
     */
    void taskFailed(Task task) throws HandlerException;

    /**
     * 任务过期时执行的推送操作。
     *
     * @param task 相关的任务。
     * @throws HandlerException 处理器异常。
     */
    void taskExpired(Task task) throws HandlerException;

    /**
     * 任务死亡时执行的推送操作。
     *
     * @param task 相关的任务。
     * @throws HandlerException 处理器异常。
     */
    void taskDied(Task task) throws HandlerException;
}
