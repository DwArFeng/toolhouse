package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoUpdateInfo;

/**
 * 执行器信息操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecutorInfoOperateHandler extends Handler {

    /**
     * 创建执行器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           执行器信息创建信息。
     * @throws HandlerException 处理器异常。
     */
    void create(StringIdKey operateUserKey, ExecutorInfoCreateInfo info) throws HandlerException;

    /**
     * 更新执行器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           执行器信息更新信息。
     * @throws HandlerException 处理器异常。
     */
    void update(StringIdKey operateUserKey, ExecutorInfoUpdateInfo info) throws HandlerException;

    /**
     * 删除执行器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           执行器信息删除信息。
     * @throws HandlerException 处理器异常。
     */
    void remove(StringIdKey operateUserKey, ExecutorInfoRemoveInfo info) throws HandlerException;
}
