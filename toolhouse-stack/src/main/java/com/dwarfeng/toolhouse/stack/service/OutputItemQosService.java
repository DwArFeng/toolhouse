package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

import javax.annotation.Nullable;

/**
 * 输出项 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface OutputItemQosService extends Service {

    /**
     * 手动创建/更新输出项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输出项手动创建/更新信息。
     * @throws ServiceException 服务异常。
     */
    void manualUpsert(StringIdKey operateUserKey, OutputItemManualUpsertInfo info) throws ServiceException;

    /**
     * 手动删除输出项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输出项手动删除信息。
     * @throws ServiceException 服务异常。
     */
    void manualRemove(StringIdKey operateUserKey, OutputItemManualRemoveInfo info) throws ServiceException;

    /**
     * 超控创建/更新输出项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输出项超控创建/更新信息。
     * @throws ServiceException 服务异常。
     */
    void overrideUpsert(StringIdKey operateUserKey, OutputItemOverrideUpsertInfo info) throws ServiceException;

    /**
     * 超控删除输出项。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           输出项超控删除信息。
     * @throws ServiceException 服务异常。
     */
    void overrideRemove(StringIdKey operateUserKey, OutputItemOverrideRemoveInfo info) throws ServiceException;

    /**
     * 系统查询输出项。
     *
     * <p>
     * 如果 {@link OutputItemSystemInspectInfo#getTaskKey()} 对应的任务不存在，则抛出异常。<br>
     * 如果 {@link OutputItemSystemInspectInfo#getItemStringId()}  对应的输出项不存在，则返回 null。
     *
     * @param info 输出项系统查询信息。
     * @return 输出项系统查询结果。
     * @throws ServiceException 服务异常。
     */
    @Nullable
    OutputItemInspectResult systemInspect(OutputItemSystemInspectInfo info) throws ServiceException;

    /**
     * 系统创建/更新输出项。
     *
     * @param info 输出项系统创建/更新信息。
     * @throws ServiceException 服务异常。
     */
    void systemUpsert(OutputItemSystemUpsertInfo info) throws ServiceException;

    /**
     * 系统删除输出项。
     *
     * @param info 输出项系统删除信息。
     * @throws ServiceException 服务异常。
     */
    void systemRemove(OutputItemSystemRemoveInfo info) throws ServiceException;
}
