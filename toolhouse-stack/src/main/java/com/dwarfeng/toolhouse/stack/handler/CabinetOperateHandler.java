package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;

/**
 * 工具柜操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface CabinetOperateHandler extends Handler {

    /**
     * 创建工具柜。
     *
     * @param userKey           操作者的主键。
     * @param cabinetCreateInfo 工具柜的创建信息。
     * @return 生成的工具柜的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createCabinet(StringIdKey userKey, CabinetCreateInfo cabinetCreateInfo)
            throws HandlerException;

    /**
     * 更新工具柜。
     *
     * @param userKey           操作者的主键。
     * @param cabinetUpdateInfo 工具柜的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateCabinet(StringIdKey userKey, CabinetUpdateInfo cabinetUpdateInfo) throws HandlerException;

    /**
     * 删除工具柜。
     *
     * @param userKey    操作者的主键。
     * @param cabinetKey 工具柜的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeCabinet(StringIdKey userKey, LongIdKey cabinetKey) throws HandlerException;

    /**
     * 添加或更新工具柜的访客权限。
     *
     * @param ownerUserKey                操作者的主键。
     * @param cabinetPermissionUpsertInfo 权限添加信息。
     * @throws HandlerException 处理器异常。
     */
    void upsertPermission(StringIdKey ownerUserKey, CabinetPermissionUpsertInfo cabinetPermissionUpsertInfo)
            throws HandlerException;

    /**
     * 移除工具柜的访客权限。
     *
     * @param ownerUserKey                操作者的主键。
     * @param cabinetPermissionRemoveInfo 权限移除信息。
     * @throws HandlerException 处理器异常。
     */
    void removePermission(StringIdKey ownerUserKey, CabinetPermissionRemoveInfo cabinetPermissionRemoveInfo)
            throws HandlerException;

    /**
     * 改变工具柜的收藏状态。
     *
     * @param operateUserKey 操作者的主键。
     * @param info           工具柜收藏变更信息。
     * @throws HandlerException 处理器异常。
     */
    void changeFavored(StringIdKey operateUserKey, CabinetFavoredChangeInfo info) throws HandlerException;
}
