package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
import com.dwarfeng.toolhouse.stack.cache.CabinetCache;
import com.dwarfeng.toolhouse.stack.cache.FavoriteCache;
import com.dwarfeng.toolhouse.stack.cache.PocaCache;
import com.dwarfeng.toolhouse.stack.dao.*;
import com.dwarfeng.toolhouse.stack.service.FavoriteMaintainService;
import com.dwarfeng.toolhouse.stack.service.FolderMaintainService;
import com.dwarfeng.toolhouse.stack.service.PocaMaintainService;
import com.dwarfeng.toolhouse.stack.service.ToolMaintainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CabinetCrudOperation implements BatchCrudOperation<LongIdKey, Cabinet> {

    private final CabinetDao cabinetDao;
    private final CabinetCache cabinetCache;

    private final PocaDao pocaDao;
    private final PocaCache pocaCache;

    private final ToolDao toolDao;
    private final ToolCrudOperation toolCrudOperation;

    private final FolderDao folderDao;
    private final FolderCrudOperation folderCrudOperation;

    private final FavoriteDao favoriteDao;
    private final FavoriteCache favoriteCache;

    @Value("${cache.timeout.entity.cabinet}")
    private long cabinetTimeout;

    public CabinetCrudOperation(
            CabinetDao cabinetDao,
            CabinetCache cabinetCache,
            PocaDao pocaDao,
            PocaCache pocaCache,
            ToolDao toolDao,
            ToolCrudOperation toolCrudOperation,
            FolderDao folderDao,
            FolderCrudOperation folderCrudOperation,
            FavoriteDao favoriteDao,
            FavoriteCache favoriteCache
    ) {
        this.cabinetDao = cabinetDao;
        this.cabinetCache = cabinetCache;
        this.pocaDao = pocaDao;
        this.pocaCache = pocaCache;
        this.toolDao = toolDao;
        this.toolCrudOperation = toolCrudOperation;
        this.folderDao = folderDao;
        this.folderCrudOperation = folderCrudOperation;
        this.favoriteDao = favoriteDao;
        this.favoriteCache = favoriteCache;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return cabinetCache.exists(key) || cabinetDao.exists(key);
    }

    @Override
    public Cabinet get(LongIdKey key) throws Exception {
        if (cabinetCache.exists(key)) {
            return cabinetCache.get(key);
        } else {
            if (!cabinetDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Cabinet cabinet = cabinetDao.get(key);
            cabinetCache.push(cabinet, cabinetTimeout);
            return cabinet;
        }
    }

    @Override
    public LongIdKey insert(Cabinet cabinet) throws Exception {
        cabinetCache.push(cabinet, cabinetTimeout);
        return cabinetDao.insert(cabinet);
    }

    @Override
    public void update(Cabinet cabinet) throws Exception {
        cabinetCache.push(cabinet, cabinetTimeout);
        cabinetDao.update(cabinet);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与个人最佳集合相关的个人最佳集合项目。
        List<LongIdKey> toolKeys = toolDao.lookup(
                ToolMaintainService.CHILD_FOR_CABINET, new Object[]{key}
        ).stream().map(Tool::getKey).collect(Collectors.toList());
        toolCrudOperation.batchDelete(toolKeys);

        // 删除与个人最佳集合相关的个人最佳集合节点。
        List<LongIdKey> folderKeys = folderDao.lookup(
                FolderMaintainService.CHILD_FOR_CABINET_ROOT, new Object[]{key}
        ).stream().map(Folder::getKey).collect(Collectors.toList());
        folderCrudOperation.batchDelete(folderKeys);

        // 删除与个人最佳集合相关的个人最佳集合权限。
        List<PocaKey> pocaKeys = pocaDao.lookup(PocaMaintainService.CHILD_FOR_CABINET, new Object[]{key})
                .stream().map(Poca::getKey).collect(Collectors.toList());
        pocaCache.batchDelete(pocaKeys);
        pocaDao.batchDelete(pocaKeys);

        // 删除与个人最佳集合相关的个人最佳集合权限。
        List<FavoriteKey> favoriteKeys = favoriteDao.lookup(
                FavoriteMaintainService.CHILD_FOR_CABINET, new Object[]{key}
        ).stream().map(Favorite::getKey).collect(Collectors.toList());
        favoriteCache.batchDelete(favoriteKeys);
        favoriteDao.batchDelete(favoriteKeys);

        // 删除个人最佳集合实体自身。
        cabinetCache.delete(key);
        cabinetDao.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return cabinetCache.allExists(keys) || cabinetDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return cabinetCache.nonExists(keys) && cabinetDao.nonExists(keys);
    }

    @Override
    public List<Cabinet> batchGet(List<LongIdKey> keys) throws Exception {
        if (cabinetCache.allExists(keys)) {
            return cabinetCache.batchGet(keys);
        } else {
            if (!cabinetDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Cabinet> cabinets = cabinetDao.batchGet(keys);
            cabinetCache.batchPush(cabinets, cabinetTimeout);
            return cabinets;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Cabinet> cabinets) throws Exception {
        cabinetCache.batchPush(cabinets, cabinetTimeout);
        return cabinetDao.batchInsert(cabinets);
    }

    @Override
    public void batchUpdate(List<Cabinet> cabinets) throws Exception {
        cabinetCache.batchPush(cabinets, cabinetTimeout);
        cabinetDao.batchUpdate(cabinets);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
