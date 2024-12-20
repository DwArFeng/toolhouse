package com.dwarfeng.toolhouse.impl.cache;

import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.SkipRecord;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.CacheException;
import com.dwarfeng.toolhouse.sdk.bean.entity.FastJsonVisualizerSupport;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerSupport;
import com.dwarfeng.toolhouse.stack.cache.VisualizerSupportCache;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class VisualizerSupportCacheImpl implements VisualizerSupportCache {

    private final RedisBatchBaseCache<StringIdKey, VisualizerSupport, FastJsonVisualizerSupport>
            visualizerSupportBatchBaseDelegate;

    public VisualizerSupportCacheImpl(
            RedisBatchBaseCache<StringIdKey, VisualizerSupport, FastJsonVisualizerSupport>
                    visualizerSupportBatchBaseDelegate
    ) {
        this.visualizerSupportBatchBaseDelegate = visualizerSupportBatchBaseDelegate;
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean exists(StringIdKey key) throws CacheException {
        return visualizerSupportBatchBaseDelegate.exists(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public VisualizerSupport get(StringIdKey key) throws CacheException {
        return visualizerSupportBatchBaseDelegate.get(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void push(VisualizerSupport value, long timeout) throws CacheException {
        visualizerSupportBatchBaseDelegate.push(value, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void delete(StringIdKey key) throws CacheException {
        visualizerSupportBatchBaseDelegate.delete(key);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void clear() throws CacheException {
        visualizerSupportBatchBaseDelegate.clear();
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean allExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return visualizerSupportBatchBaseDelegate.allExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public boolean nonExists(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return visualizerSupportBatchBaseDelegate.nonExists(keys);
    }

    @Override
    @BehaviorAnalyse
    @SkipRecord
    @Transactional(transactionManager = "hibernateTransactionManager", readOnly = true, rollbackFor = Exception.class)
    public List<VisualizerSupport> batchGet(@SkipRecord List<StringIdKey> keys) throws CacheException {
        return visualizerSupportBatchBaseDelegate.batchGet(keys);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchPush(@SkipRecord List<VisualizerSupport> entities, long timeout) throws CacheException {
        visualizerSupportBatchBaseDelegate.batchPush(entities, timeout);
    }

    @Override
    @BehaviorAnalyse
    @Transactional(transactionManager = "hibernateTransactionManager", rollbackFor = Exception.class)
    public void batchDelete(@SkipRecord List<StringIdKey> keys) throws CacheException {
        visualizerSupportBatchBaseDelegate.batchDelete(keys);
    }
}
