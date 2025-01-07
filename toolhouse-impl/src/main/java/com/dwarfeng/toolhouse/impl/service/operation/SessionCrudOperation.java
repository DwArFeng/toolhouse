package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;
import com.dwarfeng.toolhouse.stack.cache.SessionCache;
import com.dwarfeng.toolhouse.stack.dao.*;
import com.dwarfeng.toolhouse.stack.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class SessionCrudOperation implements BatchCrudOperation<LongIdKey, Session> {

    private final SessionDao sessionDao;
    private final SessionCache sessionCache;

    private final InputItemCrudOperation inputItemCrudOperation;
    private final InputItemDao inputItemDao;

    private final OutputItemCrudOperation outputItemCrudOperation;
    private final OutputItemDao outputItemDao;

    private final TaskCrudOperation taskCrudOperation;
    private final TaskDao taskDao;

    private final VariableCrudOperation variableCrudOperation;
    private final VariableDao variableDao;

    private final FileInfoCrudOperation fileInfoCrudOperation;
    private final FileInfoDao fileInfoDao;

    @Value("${cache.timeout.entity.session}")
    private long sessionTimeout;

    public SessionCrudOperation(
            SessionDao sessionDao,
            SessionCache sessionCache,
            InputItemCrudOperation inputItemCrudOperation,
            InputItemDao inputItemDao,
            OutputItemCrudOperation outputItemCrudOperation,
            OutputItemDao outputItemDao,
            TaskCrudOperation taskCrudOperation,
            TaskDao taskDao,
            VariableCrudOperation variableCrudOperation,
            VariableDao variableDao,
            FileInfoCrudOperation fileInfoCrudOperation,
            FileInfoDao fileInfoDao
    ) {
        this.sessionDao = sessionDao;
        this.sessionCache = sessionCache;
        this.inputItemCrudOperation = inputItemCrudOperation;
        this.inputItemDao = inputItemDao;
        this.outputItemCrudOperation = outputItemCrudOperation;
        this.outputItemDao = outputItemDao;
        this.taskCrudOperation = taskCrudOperation;
        this.taskDao = taskDao;
        this.variableCrudOperation = variableCrudOperation;
        this.variableDao = variableDao;
        this.fileInfoCrudOperation = fileInfoCrudOperation;
        this.fileInfoDao = fileInfoDao;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return sessionCache.exists(key) || sessionDao.exists(key);
    }

    @Override
    public Session get(LongIdKey key) throws Exception {
        if (sessionCache.exists(key)) {
            return sessionCache.get(key);
        } else {
            if (!sessionDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Session session = sessionDao.get(key);
            sessionCache.push(session, sessionTimeout);
            return session;
        }
    }

    @Override
    public LongIdKey insert(Session session) throws Exception {
        sessionCache.push(session, sessionTimeout);
        return sessionDao.insert(session);
    }

    @Override
    public void update(Session session) throws Exception {
        sessionCache.push(session, sessionTimeout);
        sessionDao.update(session);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 删除与 会话 相关的输入项。
        List<TaskItemKey> inputItemKeys = inputItemDao.lookup(
                InputItemMaintainService.SESSION_KEY_EQ, new Object[]{key}
        ).stream().map(InputItem::getKey).collect(Collectors.toList());
        inputItemCrudOperation.batchDelete(inputItemKeys);

        // 删除与 会话 相关的输出项。
        List<TaskItemKey> outputItemKeys = outputItemDao.lookup(
                OutputItemMaintainService.SESSION_KEY_EQ, new Object[]{key}
        ).stream().map(OutputItem::getKey).collect(Collectors.toList());
        outputItemCrudOperation.batchDelete(outputItemKeys);

        // 删除与 会话 相关的任务。
        List<LongIdKey> taskKeys = taskDao.lookup(TaskMaintainService.CHILD_FOR_SESSION, new Object[]{key})
                .stream().map(Task::getKey).collect(Collectors.toList());
        taskCrudOperation.batchDelete(taskKeys);

        // 删除与 会话 相关的变量。
        List<VariableKey> variableKeys = variableDao.lookup(
                VariableMaintainService.CHILD_FOR_SESSION, new Object[]{key}
        ).stream().map(Variable::getKey).collect(Collectors.toList());
        variableCrudOperation.batchDelete(variableKeys);

        // 删除与 会话 相关的文件信息。
        List<LongIdKey> fileInfoKeys = fileInfoDao.lookup(FileInfoMaintainService.CHILD_FOR_SESSION, new Object[]{key})
                .stream().map(FileInfo::getKey).collect(Collectors.toList());
        fileInfoCrudOperation.batchDelete(fileInfoKeys);

        // 删除 会话 自身。
        sessionDao.delete(key);
        sessionCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return sessionCache.allExists(keys) || sessionDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return sessionCache.nonExists(keys) && sessionDao.nonExists(keys);
    }

    @Override
    public List<Session> batchGet(List<LongIdKey> keys) throws Exception {
        if (sessionCache.allExists(keys)) {
            return sessionCache.batchGet(keys);
        } else {
            if (!sessionDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Session> sessions = sessionDao.batchGet(keys);
            sessionCache.batchPush(sessions, sessionTimeout);
            return sessions;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Session> sessions) throws Exception {
        sessionCache.batchPush(sessions, sessionTimeout);
        return sessionDao.batchInsert(sessions);
    }

    @Override
    public void batchUpdate(List<Session> sessions) throws Exception {
        sessionCache.batchPush(sessions, sessionTimeout);
        sessionDao.batchUpdate(sessions);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
