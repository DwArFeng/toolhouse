package com.dwarfeng.toolhouse.impl.cache;

import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import com.dwarfeng.toolhouse.sdk.bean.entity.FastJsonFavorite;
import com.dwarfeng.toolhouse.stack.bean.entity.Favorite;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.cache.FavoriteCache;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class FavoriteCacheImpl implements FavoriteCache {

    private final RedisBatchBaseCache<FavoriteKey, Favorite, FastJsonFavorite> favoriteBatchBaseDelegate;

    public FavoriteCacheImpl(
            RedisBatchBaseCache<FavoriteKey, Favorite, FastJsonFavorite> favoriteBatchBaseDelegate
    ) {
        this.favoriteBatchBaseDelegate = favoriteBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(FavoriteKey key) throws CacheException {
        return favoriteBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public Favorite get(FavoriteKey key) throws CacheException {
        return favoriteBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(Favorite value, long timeout) throws CacheException {
        favoriteBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(FavoriteKey key) throws CacheException {
        favoriteBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        favoriteBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<FavoriteKey> keys) throws CacheException {
        return favoriteBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<FavoriteKey> keys) throws CacheException {
        return favoriteBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<Favorite> batchGet(@SkipRecord List<FavoriteKey> keys) throws CacheException {
        return favoriteBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<Favorite> entities, long timeout) throws CacheException {
        favoriteBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<FavoriteKey> keys) throws CacheException {
        favoriteBatchBaseDelegate.batchDelete(keys);
    }
}
