package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;
import com.dwarfeng.toolhouse.stack.cache.ExecutorInfoCache;
import com.dwarfeng.toolhouse.stack.cache.ToolCache;
import com.dwarfeng.toolhouse.stack.cache.VisualizerInfoCache;
import com.dwarfeng.toolhouse.stack.dao.*;
import com.dwarfeng.toolhouse.stack.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ToolCrudOperation implements BatchCrudOperation<LongIdKey, Tool> {

    private final ToolDao toolDao;
    private final ToolCache toolCache;

    private final VisualizerInfoDao visualizerInfoDao;
    private final VisualizerInfoCache visualizerInfoCache;

    private final ExecutorInfoDao executorInfoDao;
    private final ExecutorInfoCache executorInfoCache;

    private final InputItemCrudOperation inputItemCrudOperation;
    private final InputItemDao inputItemDao;

    private final OutputItemCrudOperation outputItemCrudOperation;
    private final OutputItemDao outputItemDao;

    private final TaskCrudOperation taskCrudOperation;
    private final TaskDao taskDao;

    private final VariableCrudOperation variableCrudOperation;
    private final VariableDao variableDao;

    private final SessionCrudOperation sessionCrudOperation;
    private final SessionDao sessionDao;

    @Value("${cache.timeout.entity.tool}")
    private long toolTimeout;

    public ToolCrudOperation(
            ToolDao toolDao,
            ToolCache toolCache,
            VisualizerInfoDao visualizerInfoDao,
            VisualizerInfoCache visualizerInfoCache,
            ExecutorInfoDao executorInfoDao,
            ExecutorInfoCache executorInfoCache, InputItemCrudOperation inputItemCrudOperation, InputItemDao inputItemDao, OutputItemCrudOperation outputItemCrudOperation, OutputItemDao outputItemDao, TaskCrudOperation taskCrudOperation, TaskDao taskDao, VariableCrudOperation variableCrudOperation, VariableDao variableDao, SessionCrudOperation sessionCrudOperation, SessionDao sessionDao
    ) {
        this.toolDao = toolDao;
        this.toolCache = toolCache;
        this.visualizerInfoDao = visualizerInfoDao;
        this.visualizerInfoCache = visualizerInfoCache;
        this.executorInfoDao = executorInfoDao;
        this.executorInfoCache = executorInfoCache;
        this.inputItemCrudOperation = inputItemCrudOperation;
        this.inputItemDao = inputItemDao;
        this.outputItemCrudOperation = outputItemCrudOperation;
        this.outputItemDao = outputItemDao;
        this.taskCrudOperation = taskCrudOperation;
        this.taskDao = taskDao;
        this.variableCrudOperation = variableCrudOperation;
        this.variableDao = variableDao;
        this.sessionCrudOperation = sessionCrudOperation;
        this.sessionDao = sessionDao;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return toolCache.exists(key) || toolDao.exists(key);
    }

    @Override
    public Tool get(LongIdKey key) throws Exception {
        if (toolCache.exists(key)) {
            return toolCache.get(key);
        } else {
            if (!toolDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Tool tool = toolDao.get(key);
            toolCache.push(tool, toolTimeout);
            return tool;
        }
    }

    @Override
    public LongIdKey insert(Tool tool) throws Exception {
        toolCache.push(tool, toolTimeout);
        return toolDao.insert(tool);
    }

    @Override
    public void update(Tool tool) throws Exception {
        toolCache.push(tool, toolTimeout);
        toolDao.update(tool);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与工具相关的可视化器信息。
        List<VisualizerKey> visualizerKeys = visualizerInfoDao.lookup(
                VisualizerInfoMaintainService.CHILD_FOR_TOOL, new Object[]{key}
        ).stream().map(VisualizerInfo::getKey).collect(Collectors.toList());
        visualizerInfoCache.batchDelete(visualizerKeys);
        visualizerInfoDao.batchDelete(visualizerKeys);

        // 删除与工具相关的执行器信息。
        List<ExecutorKey> executorKeys = executorInfoDao.lookup(
                ExecutorInfoMaintainService.CHILD_FOR_TOOL, new Object[]{key}
        ).stream().map(ExecutorInfo::getKey).collect(Collectors.toList());
        executorInfoCache.batchDelete(executorKeys);
        executorInfoDao.batchDelete(executorKeys);

        // 删除与工具相关的输入项。
        List<TaskItemKey> inputItemKeys = inputItemDao.lookup(InputItemMaintainService.TOOL_KEY_EQ, new Object[]{key})
                .stream().map(InputItem::getKey).collect(Collectors.toList());
        inputItemCrudOperation.batchDelete(inputItemKeys);

        // 删除与工具相关的输出项。
        List<TaskItemKey> outputItemKeys = outputItemDao.lookup(OutputItemMaintainService.TOOL_KEY_EQ, new Object[]{key})
                .stream().map(OutputItem::getKey).collect(Collectors.toList());
        outputItemCrudOperation.batchDelete(outputItemKeys);

        // 删除与工具相关的任务。
        List<LongIdKey> taskKeys = taskDao.lookup(TaskMaintainService.TOOL_KEY_EQ, new Object[]{key})
                .stream().map(Task::getKey).collect(Collectors.toList());
        taskCrudOperation.batchDelete(taskKeys);

        // 删除与工具相关的变量。
        List<VariableKey> variableKeys = variableDao.lookup(VariableMaintainService.TOOL_KEY_EQ, new Object[]{key})
                .stream().map(Variable::getKey).collect(Collectors.toList());
        variableCrudOperation.batchDelete(variableKeys);

        // 删除与工具相关的会话。
        List<LongIdKey> sessionKeys = sessionDao.lookup(SessionMaintainService.CHILD_FOR_TOOL, new Object[]{key})
                .stream().map(Session::getKey).collect(Collectors.toList());
        sessionCrudOperation.batchDelete(sessionKeys);

        // 删除工具自身。
        toolCache.delete(key);
        toolDao.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return toolCache.allExists(keys) || toolDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return toolCache.nonExists(keys) && toolDao.nonExists(keys);
    }

    @Override
    public List<Tool> batchGet(List<LongIdKey> keys) throws Exception {
        if (toolCache.allExists(keys)) {
            return toolCache.batchGet(keys);
        } else {
            if (!toolDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Tool> tools = toolDao.batchGet(keys);
            toolCache.batchPush(tools, toolTimeout);
            return tools;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Tool> tools) throws Exception {
        toolCache.batchPush(tools, toolTimeout);
        return toolDao.batchInsert(tools);
    }

    @Override
    public void batchUpdate(List<Tool> tools) throws Exception {
        toolCache.batchPush(tools, toolTimeout);
        toolDao.batchUpdate(tools);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
