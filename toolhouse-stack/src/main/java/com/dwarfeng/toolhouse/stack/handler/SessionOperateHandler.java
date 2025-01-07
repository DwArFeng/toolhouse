package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

/**
 * 会话操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface SessionOperateHandler extends Handler {

    /**
     * 手动创建会话。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           会话手动创建信息。
     * @return 会话创建结果。
     * @throws HandlerException 处理器异常。
     */
    SessionCreateResult manualCreate(StringIdKey operateUserKey, SessionManualCreateInfo info) throws HandlerException;

    /**
     * 手动删除会话。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           会话手动删除信息。
     * @throws HandlerException 处理器异常。
     */
    void manualRemove(StringIdKey operateUserKey, SessionManualRemoveInfo info) throws HandlerException;

    /**
     * 超控创建会话。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           会话手动超控创建信息。
     * @return 会话创建结果。
     * @throws HandlerException 处理器异常。
     */
    SessionCreateResult overrideCreate(StringIdKey operateUserKey, SessionOverrideCreateInfo info)
            throws HandlerException;

    /**
     * 超控删除会话。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           会话手动超控删除信息。
     * @throws HandlerException 处理器异常。
     */
    void overrideRemove(StringIdKey operateUserKey, SessionOverrideRemoveInfo info) throws HandlerException;

    /**
     * 系统创建会话。
     *
     * @param info 会话系统创建信息。
     * @return 会话创建结果。
     * @throws HandlerException 处理器异常。
     */
    SessionCreateResult systemCreate(SessionSystemCreateInfo info) throws HandlerException;

    /**
     * 系统删除会话。
     *
     * @param info 会话系统删除信息。
     * @throws HandlerException 处理器异常。
     */
    void systemRemove(SessionSystemRemoveInfo info) throws HandlerException;
}
