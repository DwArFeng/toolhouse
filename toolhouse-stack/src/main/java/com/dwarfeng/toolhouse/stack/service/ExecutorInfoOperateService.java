package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoUpdateInfo;

/**
 * 执行器信息操作服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface ExecutorInfoOperateService extends Service {

    /**
     * 创建执行器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           执行器信息创建信息。
     * @throws ServiceException 服务异常。
     */
    void create(StringIdKey operateUserKey, ExecutorInfoCreateInfo info) throws ServiceException;

    /**
     * 更新执行器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           执行器信息更新信息。
     * @throws ServiceException 服务异常。
     */
    void update(StringIdKey operateUserKey, ExecutorInfoUpdateInfo info) throws ServiceException;

    /**
     * 删除执行器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           执行器信息删除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(StringIdKey operateUserKey, ExecutorInfoRemoveInfo info) throws ServiceException;
}
