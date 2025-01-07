package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.OutputItem;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import com.dwarfeng.toolhouse.stack.cache.OutputItemCache;
import com.dwarfeng.toolhouse.stack.dao.OutputItemDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OutputItemCrudOperation implements BatchCrudOperation<TaskItemKey, OutputItem> {

    private final OutputItemDao outputItemDao;
    private final OutputItemCache outputItemCache;

    @Value("${cache.timeout.entity.output_item}")
    private long outputItemTimeout;

    public OutputItemCrudOperation(
            OutputItemDao outputItemDao,
            OutputItemCache outputItemCache
    ) {
        this.outputItemDao = outputItemDao;
        this.outputItemCache = outputItemCache;
    }

    @Override
    public boolean exists(TaskItemKey key) throws Exception {
        return outputItemCache.exists(key) || outputItemDao.exists(key);
    }

    @Override
    public OutputItem get(TaskItemKey key) throws Exception {
        if (outputItemCache.exists(key)) {
            return outputItemCache.get(key);
        } else {
            if (!outputItemDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            OutputItem outputItem = outputItemDao.get(key);
            outputItemCache.push(outputItem, outputItemTimeout);
            return outputItem;
        }
    }

    @Override
    public TaskItemKey insert(OutputItem outputItem) throws Exception {
        outputItemCache.push(outputItem, outputItemTimeout);
        return outputItemDao.insert(outputItem);
    }

    @Override
    public void update(OutputItem outputItem) throws Exception {
        outputItemCache.push(outputItem, outputItemTimeout);
        outputItemDao.update(outputItem);
    }

    @Override
    public void delete(TaskItemKey key) throws Exception {
        // 删除 输出项 自身。
        outputItemDao.delete(key);
        outputItemCache.delete(key);
    }

    @Override
    public boolean allExists(List<TaskItemKey> keys) throws Exception {
        return outputItemCache.allExists(keys) || outputItemDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<TaskItemKey> keys) throws Exception {
        return outputItemCache.nonExists(keys) && outputItemDao.nonExists(keys);
    }

    @Override
    public List<OutputItem> batchGet(List<TaskItemKey> keys) throws Exception {
        if (outputItemCache.allExists(keys)) {
            return outputItemCache.batchGet(keys);
        } else {
            if (!outputItemDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<OutputItem> outputItems = outputItemDao.batchGet(keys);
            outputItemCache.batchPush(outputItems, outputItemTimeout);
            return outputItems;
        }
    }

    @Override
    public List<TaskItemKey> batchInsert(List<OutputItem> outputItems) throws Exception {
        outputItemCache.batchPush(outputItems, outputItemTimeout);
        return outputItemDao.batchInsert(outputItems);
    }

    @Override
    public void batchUpdate(List<OutputItem> outputItems) throws Exception {
        outputItemCache.batchPush(outputItems, outputItemTimeout);
        outputItemDao.batchUpdate(outputItems);
    }

    @Override
    public void batchDelete(List<TaskItemKey> keys) throws Exception {
        for (TaskItemKey key : keys) {
            delete(key);
        }
    }
}
