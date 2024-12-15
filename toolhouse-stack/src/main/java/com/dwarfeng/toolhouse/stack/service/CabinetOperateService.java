package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

/**
 * 工具柜操作服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CabinetOperateService extends Service {

    /**
     * 创建工具柜。
     *
     * @param userKey           操作者的主键。
     * @param cabinetCreateInfo 工具柜的创建信息。
     * @return 生成的工具柜的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createCabinet(StringIdKey userKey, CabinetCreateInfo cabinetCreateInfo)
            throws ServiceException;

    /**
     * 更新工具柜。
     *
     * @param userKey           操作者的主键。
     * @param cabinetUpdateInfo 工具柜的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateCabinet(StringIdKey userKey, CabinetUpdateInfo cabinetUpdateInfo) throws ServiceException;

    /**
     * 删除工具柜。
     *
     * @param userKey    操作者的主键。
     * @param cabinetKey 工具柜的主键。
     * @throws ServiceException 服务异常。
     */
    void removeCabinet(StringIdKey userKey, LongIdKey cabinetKey) throws ServiceException;

    /**
     * 添加或更新工具柜的访客权限。
     *
     * @param ownerUserKey                操作者的主键。
     * @param cabinetPermissionUpsertInfo 权限添加信息。
     * @throws ServiceException 服务异常。
     */
    void upsertPermission(StringIdKey ownerUserKey, CabinetPermissionUpsertInfo cabinetPermissionUpsertInfo)
            throws ServiceException;

    /**
     * 移除工具柜的访客权限。
     *
     * @param ownerUserKey                操作者的主键。
     * @param cabinetPermissionRemoveInfo 权限移除信息。
     * @throws ServiceException 服务异常。
     */
    void removePermission(StringIdKey ownerUserKey, CabinetPermissionRemoveInfo cabinetPermissionRemoveInfo)
            throws ServiceException;

    /**
     * 改变工具柜的收藏状态。
     *
     * @param operateUserKey 操作者的主键。
     * @param info           工具柜收藏变更信息。
     * @throws ServiceException 服务异常。
     */
    void changeFavored(StringIdKey operateUserKey, CabinetFavoredChangeInfo info) throws ServiceException;
}
