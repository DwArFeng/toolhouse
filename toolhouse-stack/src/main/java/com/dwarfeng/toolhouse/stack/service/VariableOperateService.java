package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.VariableManualRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VariableManualUpsertInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VariableOverrideRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VariableOverrideUpsertInfo;

/**
 * 变量操作服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VariableOperateService extends Service {

    /**
     * 手动创建/更新变量。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           变量手动创建/更新信息。
     * @throws ServiceException 服务异常。
     */
    void manualUpsert(StringIdKey operateUserKey, VariableManualUpsertInfo info) throws ServiceException;

    /**
     * 手动删除变量。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           变量手动删除信息。
     * @throws ServiceException 服务异常。
     */
    void manualRemove(StringIdKey operateUserKey, VariableManualRemoveInfo info) throws ServiceException;

    /**
     * 超控创建/更新变量。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           变量超控创建/更新信息。
     * @throws ServiceException 服务异常。
     */
    void overrideUpsert(StringIdKey operateUserKey, VariableOverrideUpsertInfo info) throws ServiceException;

    /**
     * 超控删除变量。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           变量超控删除信息。
     * @throws ServiceException 服务异常。
     */
    void overrideRemove(StringIdKey operateUserKey, VariableOverrideRemoveInfo info) throws ServiceException;
}
