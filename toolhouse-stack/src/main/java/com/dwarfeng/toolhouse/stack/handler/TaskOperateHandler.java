package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

/**
 * 任务操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface TaskOperateHandler extends Handler {

    /**
     * 手动创建任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务手动创建信息。
     * @return 任务创建结果。
     * @throws HandlerException 处理器异常。
     */
    TaskCreateResult manualCreate(StringIdKey operateUserKey, TaskManualCreateInfo info) throws HandlerException;

    /**
     * 超控创建任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务超控创建信息。
     * @return 任务创建结果。
     * @throws HandlerException 处理器异常。
     */
    TaskCreateResult overrideCreate(StringIdKey operateUserKey, TaskOverrideCreateInfo info) throws HandlerException;

    /**
     * 系统创建任务。
     *
     * @param info 任务系统创建信息。
     * @return 任务创建结果。
     * @throws HandlerException 处理器异常。
     */
    TaskCreateResult systemCreate(TaskSystemCreateInfo info) throws HandlerException;

    /**
     * 系统开始任务。
     *
     * @param info 任务系统开始信息。
     * @throws HandlerException 处理器异常。
     */
    void systemStart(TaskSystemStartInfo info) throws HandlerException;

    /**
     * 系统完成任务。
     *
     * @param info 任务系统完成信息。
     * @throws HandlerException 处理器异常。
     */
    void systemFinish(TaskSystemFinishInfo info) throws HandlerException;

    /**
     * 系统失败任务。
     *
     * @param info 任务系统失败信息。
     * @throws HandlerException 处理器异常。
     */
    void systemFail(TaskSystemFailInfo info) throws HandlerException;

    /**
     * 系统过期任务。
     *
     * @param info 任务系统过期信息。
     * @throws HandlerException 处理器异常。
     */
    void systemExpire(TaskSystemExpireInfo info) throws HandlerException;

    /**
     * 系统更新任务模态。
     *
     * @param info 任务系统更新模态信息。
     * @throws HandlerException 处理器异常。
     */
    void systemUpdateModal(TaskSystemUpdateModalInfo info) throws HandlerException;

    /**
     * 系统死亡任务。
     *
     * @param info 任务系统死亡信息。
     * @throws HandlerException 处理器异常。
     */
    void systemDie(TaskSystemDieInfo info) throws HandlerException;

    /**
     * 系统心跳任务。
     *
     * @param info 任务系统心跳信息。
     * @throws HandlerException 处理器异常。
     */
    void systemBeat(TaskSystemBeatInfo info) throws HandlerException;
}
