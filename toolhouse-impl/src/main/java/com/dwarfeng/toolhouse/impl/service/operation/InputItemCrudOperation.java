package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.InputItem;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import com.dwarfeng.toolhouse.stack.cache.InputItemCache;
import com.dwarfeng.toolhouse.stack.dao.InputItemDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class InputItemCrudOperation implements BatchCrudOperation<TaskItemKey, InputItem> {

    private final InputItemDao inputItemDao;
    private final InputItemCache inputItemCache;

    @Value("${cache.timeout.entity.input_item}")
    private long inputItemTimeout;

    public InputItemCrudOperation(
            InputItemDao inputItemDao,
            InputItemCache inputItemCache
    ) {
        this.inputItemDao = inputItemDao;
        this.inputItemCache = inputItemCache;
    }

    @Override
    public boolean exists(TaskItemKey key) throws Exception {
        return inputItemCache.exists(key) || inputItemDao.exists(key);
    }

    @Override
    public InputItem get(TaskItemKey key) throws Exception {
        if (inputItemCache.exists(key)) {
            return inputItemCache.get(key);
        } else {
            if (!inputItemDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            InputItem inputItem = inputItemDao.get(key);
            inputItemCache.push(inputItem, inputItemTimeout);
            return inputItem;
        }
    }

    @Override
    public TaskItemKey insert(InputItem inputItem) throws Exception {
        inputItemCache.push(inputItem, inputItemTimeout);
        return inputItemDao.insert(inputItem);
    }

    @Override
    public void update(InputItem inputItem) throws Exception {
        inputItemCache.push(inputItem, inputItemTimeout);
        inputItemDao.update(inputItem);
    }

    @Override
    public void delete(TaskItemKey key) throws Exception {
        // 删除 输入项 自身。
        inputItemDao.delete(key);
        inputItemCache.delete(key);
    }

    @Override
    public boolean allExists(List<TaskItemKey> keys) throws Exception {
        return inputItemCache.allExists(keys) || inputItemDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<TaskItemKey> keys) throws Exception {
        return inputItemCache.nonExists(keys) && inputItemDao.nonExists(keys);
    }

    @Override
    public List<InputItem> batchGet(List<TaskItemKey> keys) throws Exception {
        if (inputItemCache.allExists(keys)) {
            return inputItemCache.batchGet(keys);
        } else {
            if (!inputItemDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<InputItem> inputItems = inputItemDao.batchGet(keys);
            inputItemCache.batchPush(inputItems, inputItemTimeout);
            return inputItems;
        }
    }

    @Override
    public List<TaskItemKey> batchInsert(List<InputItem> inputItems) throws Exception {
        inputItemCache.batchPush(inputItems, inputItemTimeout);
        return inputItemDao.batchInsert(inputItems);
    }

    @Override
    public void batchUpdate(List<InputItem> inputItems) throws Exception {
        inputItemCache.batchPush(inputItems, inputItemTimeout);
        inputItemDao.batchUpdate(inputItems);
    }

    @Override
    public void batchDelete(List<TaskItemKey> keys) throws Exception {
        for (TaskItemKey key : keys) {
            delete(key);
        }
    }
}
