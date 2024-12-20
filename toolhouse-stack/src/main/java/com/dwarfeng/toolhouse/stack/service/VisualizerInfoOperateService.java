package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoUpdateInfo;

/**
 * 可视化器信息操作服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface VisualizerInfoOperateService extends Service {

    /**
     * 创建可视化器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器信息创建信息。
     * @throws ServiceException 服务异常。
     */
    void create(StringIdKey operateUserKey, VisualizerInfoCreateInfo info) throws ServiceException;

    /**
     * 更新可视化器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器信息更新信息。
     * @throws ServiceException 服务异常。
     */
    void update(StringIdKey operateUserKey, VisualizerInfoUpdateInfo info) throws ServiceException;

    /**
     * 删除可视化器信息。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           可视化器信息删除信息。
     * @throws ServiceException 服务异常。
     */
    void remove(StringIdKey operateUserKey, VisualizerInfoRemoveInfo info) throws ServiceException;
}
