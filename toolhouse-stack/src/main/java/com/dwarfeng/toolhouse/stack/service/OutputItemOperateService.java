package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemManualRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemManualUpsertInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemOverrideRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.OutputItemOverrideUpsertInfo;

/**
 * 输出项操作服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface OutputItemOperateService extends Service {

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
}
