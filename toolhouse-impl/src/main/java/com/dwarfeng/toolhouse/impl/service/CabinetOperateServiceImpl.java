package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.handler.CabinetOperateHandler;
import com.dwarfeng.toolhouse.stack.service.CabinetOperateService;
import org.springframework.stereotype.Service;

@Service
public class CabinetOperateServiceImpl implements CabinetOperateService {

    private final CabinetOperateHandler cabinetOperateHandler;

    private final ServiceExceptionMapper sem;

    public CabinetOperateServiceImpl(
            CabinetOperateHandler cabinetOperateHandler, ServiceExceptionMapper sem
    ) {
        this.cabinetOperateHandler = cabinetOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createCabinet(StringIdKey userKey, CabinetCreateInfo cabinetCreateInfo)
            throws ServiceException {
        try {
            return cabinetOperateHandler.createCabinet(userKey, cabinetCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("创建工具柜时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateCabinet(StringIdKey userKey, CabinetUpdateInfo cabinetUpdateInfo)
            throws ServiceException {
        try {
            cabinetOperateHandler.updateCabinet(userKey, cabinetUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新工具柜时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeCabinet(StringIdKey userKey, LongIdKey cabinetKey) throws ServiceException {
        try {
            cabinetOperateHandler.removeCabinet(userKey, cabinetKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除工具柜时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void upsertPermission(
            StringIdKey ownerUserKey, CabinetPermissionUpsertInfo cabinetPermissionUpsertInfo
    ) throws ServiceException {
        try {
            cabinetOperateHandler.upsertPermission(ownerUserKey, cabinetPermissionUpsertInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("添加工具柜的访客权限时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removePermission(
            StringIdKey ownerUserKey, CabinetPermissionRemoveInfo cabinetPermissionRemoveInfo
    ) throws ServiceException {
        try {
            cabinetOperateHandler.removePermission(ownerUserKey, cabinetPermissionRemoveInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("移除工具柜的访客权限时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void changeFavored(StringIdKey operateUserKey, CabinetFavoredChangeInfo info) throws ServiceException {
        try {
            cabinetOperateHandler.changeFavored(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("改变工具柜的收藏状态时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
