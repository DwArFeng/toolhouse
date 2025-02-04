package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

import javax.annotation.Nullable;

/**
 * 输出项操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface OutputItemOperateHandler extends Handler {

    /**
     * 手动创建/更新输出项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输出项手动创建/更新信息。
     * @throws HandlerException 处理器异常。
     */
    void manualUpsert(StringIdKey operateUserKey, OutputItemManualUpsertInfo info) throws HandlerException;

    /**
     * 手动删除输出项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输出项手动删除信息。
     * @throws HandlerException 处理器异常。
     */
    void manualRemove(StringIdKey operateUserKey, OutputItemManualRemoveInfo info) throws HandlerException;

    /**
     * 超控创建/更新输出项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输出项超控创建/更新信息。
     * @throws HandlerException 处理器异常。
     */
    void overrideUpsert(StringIdKey operateUserKey, OutputItemOverrideUpsertInfo info) throws HandlerException;

    /**
     * 超控删除输出项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输出项超控删除信息。
     * @throws HandlerException 处理器异常。
     */
    void overrideRemove(StringIdKey operateUserKey, OutputItemOverrideRemoveInfo info) throws HandlerException;

    /**
     * 系统查询输出项。
     *
     * <p>
     * 如果 {@link OutputItemSystemInspectInfo#getTaskKey()} 对应的任务不存在，则抛出异常。<br>
     * 如果 {@link OutputItemSystemInspectInfo#getItemStringId()}  对应的输出项不存在，则返回 null。
     *
     * @param info 输出项系统查询信息。
     * @return 输出项系统查询结果。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    OutputItemInspectResult systemInspect(OutputItemSystemInspectInfo info) throws HandlerException;

    /**
     * 系统创建/更新输出项。
     *
     * @param info 输出项系统创建/更新信息。
     * @throws HandlerException 处理器异常。
     */
    void systemUpsert(OutputItemSystemUpsertInfo info) throws HandlerException;

    /**
     * 系统删除输出项。
     *
     * @param info 输出项系统删除信息。
     * @throws HandlerException 处理器异常。
     */
    void systemRemove(OutputItemSystemRemoveInfo info) throws HandlerException;
}
