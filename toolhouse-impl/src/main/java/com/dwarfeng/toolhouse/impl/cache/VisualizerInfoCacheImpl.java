package com.dwarfeng.toolhouse.impl.cache;

import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import com.dwarfeng.toolhouse.sdk.bean.entity.FastJsonVisualizerInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;
import com.dwarfeng.toolhouse.stack.cache.VisualizerInfoCache;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VisualizerInfoCacheImpl implements VisualizerInfoCache {

    private final RedisBatchBaseCache<VisualizerKey, VisualizerInfo, FastJsonVisualizerInfo> batchBaseCache;

    public VisualizerInfoCacheImpl(RedisBatchBaseCache<VisualizerKey, VisualizerInfo, FastJsonVisualizerInfo> batchBaseCache) {
        this.batchBaseCache = batchBaseCache;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(VisualizerKey key) throws CacheException {
        return batchBaseCache.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public VisualizerInfo get(VisualizerKey key) throws CacheException {
        return batchBaseCache.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(VisualizerInfo value, long timeout) throws CacheException {
        batchBaseCache.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(VisualizerKey key) throws CacheException {
        batchBaseCache.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        batchBaseCache.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<VisualizerKey> keys) throws CacheException {
        return batchBaseCache.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<VisualizerKey> keys) throws CacheException {
        return batchBaseCache.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<VisualizerInfo> batchGet(@SkipRecord List<VisualizerKey> keys) throws CacheException {
        return batchBaseCache.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<VisualizerInfo> entities, long timeout) throws CacheException {
        batchBaseCache.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<VisualizerKey> keys) throws CacheException {
        batchBaseCache.batchDelete(keys);
    }
}
