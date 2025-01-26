package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

/**
 * 任务 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface TaskQosService extends Service {

    /**
     * 手动创建任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务手动创建信息。
     * @return 任务创建结果。
     * @throws ServiceException 服务异常。
     */
    TaskCreateResult manualCreate(StringIdKey operateUserKey, TaskManualCreateInfo info) throws ServiceException;

    /**
     * 超控创建任务。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           任务超控创建信息。
     * @return 任务创建结果。
     * @throws ServiceException 服务异常。
     */
    TaskCreateResult overrideCreate(StringIdKey operateUserKey, TaskOverrideCreateInfo info) throws ServiceException;

    /**
     * 系统创建任务。
     *
     * @param info 任务系统创建信息。
     * @return 任务创建结果。
     * @throws ServiceException 服务异常。
     */
    TaskCreateResult systemCreate(TaskSystemCreateInfo info) throws ServiceException;

    /**
     * 系统开始任务。
     *
     * @param info 任务系统开始信息。
     * @throws ServiceException 服务异常。
     */
    void systemStart(TaskSystemStartInfo info) throws ServiceException;

    /**
     * 系统完成任务。
     *
     * @param info 任务系统完成信息。
     * @throws ServiceException 服务异常。
     */
    void systemFinish(TaskSystemFinishInfo info) throws ServiceException;

    /**
     * 系统失败任务。
     *
     * @param info 任务系统失败信息。
     * @throws ServiceException 服务异常。
     */
    void systemFail(TaskSystemFailInfo info) throws ServiceException;

    /**
     * 系统过期任务。
     *
     * @param info 任务系统过期信息。
     * @throws ServiceException 服务异常。
     */
    void systemExpire(TaskSystemExpireInfo info) throws ServiceException;

    /**
     * 系统更新任务模态。
     *
     * @param info 任务系统更新模态信息。
     * @throws ServiceException 服务异常。
     */
    void systemUpdateModal(TaskSystemUpdateModalInfo info) throws ServiceException;

    /**
     * 系统死亡任务。
     *
     * @param info 任务系统死亡信息。
     * @throws ServiceException 服务异常。
     */
    void systemDie(TaskSystemDieInfo info) throws ServiceException;

    /**
     * 系统心跳任务。
     *
     * @param info 任务系统心跳信息。
     * @throws ServiceException 服务异常。
     */
    void systemBeat(TaskSystemBeatInfo info) throws ServiceException;
}
