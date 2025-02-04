package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

import javax.annotation.Nullable;

/**
 * 输入项 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface InputItemQosService extends Service {

    /**
     * 手动创建/更新输入项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输入项手动创建/更新信息。
     * @throws ServiceException 服务异常。
     */
    void manualUpsert(StringIdKey operateUserKey, InputItemManualUpsertInfo info) throws ServiceException;

    /**
     * 手动删除输入项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输入项手动删除信息。
     * @throws ServiceException 服务异常。
     */
    void manualRemove(StringIdKey operateUserKey, InputItemManualRemoveInfo info) throws ServiceException;

    /**
     * 超控创建/更新输入项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输入项超控创建/更新信息。
     * @throws ServiceException 服务异常。
     */
    void overrideUpsert(StringIdKey operateUserKey, InputItemOverrideUpsertInfo info) throws ServiceException;

    /**
     * 超控删除输入项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输入项超控删除信息。
     * @throws ServiceException 服务异常。
     */
    void overrideRemove(StringIdKey operateUserKey, InputItemOverrideRemoveInfo info) throws ServiceException;

    /**
     * 系统查询输入项。
     *
     * <p>
     * 如果 {@link InputItemSystemInspectInfo#getTaskKey()} 对应的任务不存在，则抛出异常。<br>
     * 如果 {@link InputItemSystemInspectInfo#getItemStringId()}  对应的输入项不存在，则返回 null。
     *
     * @param info 输入项系统查询信息。
     * @return 输入项系统查询结果。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    InputItemInspectResult systemInspect(InputItemSystemInspectInfo info) throws ServiceException;

    /**
     * 系统创建/更新输入项。
     *
     * @param info 输入项系统创建/更新信息。
     * @throws ServiceException 服务异常。
     */
    void systemUpsert(InputItemSystemUpsertInfo info) throws ServiceException;

    /**
     * 系统删除输入项。
     *
     * @param info 输入项系统删除信息。
     * @throws ServiceException 服务异常。
     */
    void systemRemove(InputItemSystemRemoveInfo info) throws ServiceException;
}
