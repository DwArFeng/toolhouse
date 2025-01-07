package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.InputItem;
import com.dwarfeng.toolhouse.stack.bean.entity.OutputItem;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import com.dwarfeng.toolhouse.stack.cache.TaskCache;
import com.dwarfeng.toolhouse.stack.dao.InputItemDao;
import com.dwarfeng.toolhouse.stack.dao.OutputItemDao;
import com.dwarfeng.toolhouse.stack.dao.TaskDao;
import com.dwarfeng.toolhouse.stack.service.InputItemMaintainService;
import com.dwarfeng.toolhouse.stack.service.OutputItemMaintainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TaskCrudOperation implements BatchCrudOperation<LongIdKey, Task> {

    private final TaskDao taskDao;
    private final TaskCache taskCache;

    private final InputItemCrudOperation inputItemCrudOperation;
    private final InputItemDao inputItemDao;

    private final OutputItemCrudOperation outputItemCrudOperation;
    private final OutputItemDao outputItemDao;

    @Value("${cache.timeout.entity.task}")
    private long taskTimeout;

    public TaskCrudOperation(
            TaskDao taskDao,
            TaskCache taskCache,
            InputItemCrudOperation inputItemCrudOperation,
            InputItemDao inputItemDao,
            OutputItemCrudOperation outputItemCrudOperation,
            OutputItemDao outputItemDao
    ) {
        this.taskDao = taskDao;
        this.taskCache = taskCache;
        this.inputItemCrudOperation = inputItemCrudOperation;
        this.inputItemDao = inputItemDao;
        this.outputItemCrudOperation = outputItemCrudOperation;
        this.outputItemDao = outputItemDao;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return taskCache.exists(key) || taskDao.exists(key);
    }

    @Override
    public Task get(LongIdKey key) throws Exception {
        if (taskCache.exists(key)) {
            return taskCache.get(key);
        } else {
            if (!taskDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Task task = taskDao.get(key);
            taskCache.push(task, taskTimeout);
            return task;
        }
    }

    @Override
    public LongIdKey insert(Task task) throws Exception {
        taskCache.push(task, taskTimeout);
        return taskDao.insert(task);
    }

    @Override
    public void update(Task task) throws Exception {
        taskCache.push(task, taskTimeout);
        taskDao.update(task);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与 任务 相关的输入项。
        List<TaskItemKey> inputItemKeys = inputItemDao.lookup(
                InputItemMaintainService.CHILD_FOR_TASK, new Object[]{key}
        ).stream().map(InputItem::getKey).collect(Collectors.toList());
        inputItemCrudOperation.batchDelete(inputItemKeys);

        // 删除与 任务 相关的输出项。
        List<TaskItemKey> outputItemKeys = outputItemDao.lookup(
                OutputItemMaintainService.CHILD_FOR_TASK, new Object[]{key}
        ).stream().map(OutputItem::getKey).collect(Collectors.toList());
        outputItemCrudOperation.batchDelete(outputItemKeys);

        // 删除 任务 自身。
        taskDao.delete(key);
        taskCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return taskCache.allExists(keys) || taskDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return taskCache.nonExists(keys) && taskDao.nonExists(keys);
    }

    @Override
    public List<Task> batchGet(List<LongIdKey> keys) throws Exception {
        if (taskCache.allExists(keys)) {
            return taskCache.batchGet(keys);
        } else {
            if (!taskDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Task> tasks = taskDao.batchGet(keys);
            taskCache.batchPush(tasks, taskTimeout);
            return tasks;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Task> tasks) throws Exception {
        taskCache.batchPush(tasks, taskTimeout);
        return taskDao.batchInsert(tasks);
    }

    @Override
    public void batchUpdate(List<Task> tasks) throws Exception {
        taskCache.batchPush(tasks, taskTimeout);
        taskDao.batchUpdate(tasks);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
