package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

import javax.annotation.Nullable;

/**
 * 变量操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VariableOperateHandler extends Handler {

    /**
     * 手动创建/更新变量。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           变量手动创建/更新信息。
     * @throws HandlerException 处理器异常。
     */
    void manualUpsert(StringIdKey operateUserKey, VariableManualUpsertInfo info) throws HandlerException;

    /**
     * 手动删除变量。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           变量手动删除信息。
     * @throws HandlerException 处理器异常。
     */
    void manualRemove(StringIdKey operateUserKey, VariableManualRemoveInfo info) throws HandlerException;

    /**
     * 超控创建/更新变量。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           变量超控创建/更新信息。
     * @throws HandlerException 处理器异常。
     */
    void overrideUpsert(StringIdKey operateUserKey, VariableOverrideUpsertInfo info) throws HandlerException;

    /**
     * 超控删除变量。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           变量超控删除信息。
     * @throws HandlerException 处理器异常。
     */
    void overrideRemove(StringIdKey operateUserKey, VariableOverrideRemoveInfo info) throws HandlerException;

    /**
     * 系统查询变量。
     *
     * <p>
     * 如果 {@link VariableSystemInspectInfo#getSessionKey()} 对应的会话不存在，则抛出异常。<br>
     * 如果 {@link VariableSystemInspectInfo#getVariableStringId()}  对应的变量不存在，则返回 null。
     *
     * @param info 变量系统查询信息。
     * @return 变量系统查询结果。
     * @throws HandlerException 处理器异常。
     */
    @Nullable
    VariableInspectResult systemInspect(VariableSystemInspectInfo info) throws HandlerException;

    /**
     * 系统创建/更新变量。
     *
     * @param info 变量系统创建/更新信息。
     * @throws HandlerException 处理器异常。
     */
    void systemUpsert(VariableSystemUpsertInfo info) throws HandlerException;

    /**
     * 系统删除变量。
     *
     * @param info 变量系统删除信息。
     * @throws HandlerException 处理器异常。
     */
    void systemRemove(VariableSystemRemoveInfo info) throws HandlerException;
}
