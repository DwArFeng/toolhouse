package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.InputItemManualRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.InputItemManualUpsertInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.InputItemOverrideRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.InputItemOverrideUpsertInfo;

/**
 * 输入项操作服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface InputItemOperateService extends Service {

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
}
