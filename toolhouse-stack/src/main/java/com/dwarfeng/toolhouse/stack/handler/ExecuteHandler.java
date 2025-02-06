package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskManualExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskOverrideExecuteInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.TaskSystemExecuteInfo;

import java.util.concurrent.CompletableFuture;

/**
 * 执行处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecuteHandler extends Handler {

    /**
     * 手动执行任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务手动执行信息。
     * @throws HandlerException 处理器异常。
     */
    void manualExecuteTask(StringIdKey operateUserKey, TaskManualExecuteInfo info) throws HandlerException;

    /**
     * 异步手动执行任务。
     *
     * <p>
     * 该方法调用后，会立即返回一个 <code>CompletableFuture</code>。
     * 返回的 <code>CompletableFuture</code> 会在任务执行完成后完成。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务手动执行信息。
     * @return 异步 CompletableFuture。
     * @throws HandlerException 处理器异常。
     */
    CompletableFuture<Void> manualExecuteTaskAsync(StringIdKey operateUserKey, TaskManualExecuteInfo info)
            throws HandlerException;

    /**
     * 超控执行任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务超控执行信息。
     * @throws HandlerException 处理器异常。
     */
    void overrideExecuteTask(StringIdKey operateUserKey, TaskOverrideExecuteInfo info) throws HandlerException;

    /**
     * 异步超控执行任务。
     *
     * <p>
     * 该方法调用后，会立即返回一个 <code>CompletableFuture</code>。
     * 返回的 <code>CompletableFuture</code> 会在任务执行完成后完成。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务超控执行信息。
     * @return 异步 CompletableFuture。
     * @throws HandlerException 处理器异常。
     */
    CompletableFuture<Void> overrideExecuteTaskAsync(StringIdKey operateUserKey, TaskOverrideExecuteInfo info)
            throws HandlerException;

    /**
     * 系统执行任务。
     *
     * @param info 任务系统执行信息。
     * @throws HandlerException 处理器异常。
     */
    void systemExecuteTask(TaskSystemExecuteInfo info) throws HandlerException;

    /**
     * 异步系统执行任务。
     *
     * <p>
     * 该方法调用后，会立即返回一个 <code>CompletableFuture</code>。
     * 返回的 <code>CompletableFuture</code> 会在任务执行完成后完成。
     *
     * @param info 任务系统执行信息。
     * @return 异步 CompletableFuture。
     * @throws HandlerException 处理器异常。
     */
    CompletableFuture<Void> systemExecuteTaskAsync(TaskSystemExecuteInfo info) throws HandlerException;
}
