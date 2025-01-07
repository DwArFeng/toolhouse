package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;
import com.dwarfeng.toolhouse.stack.cache.FavoriteCache;
import com.dwarfeng.toolhouse.stack.cache.PocaCache;
import com.dwarfeng.toolhouse.stack.cache.UserCache;
import com.dwarfeng.toolhouse.stack.dao.*;
import com.dwarfeng.toolhouse.stack.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserCrudOperation implements BatchCrudOperation<StringIdKey, User> {

    private final UserDao userDao;
    private final UserCache userCache;

    private final PocaDao pocaDao;
    private final PocaCache pocaCache;

    private final FavoriteDao favoriteDao;
    private final FavoriteCache favoriteCache;

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

    @Value("${cache.timeout.entity.user}")
    private long userTimeout;

    public UserCrudOperation(
            UserDao userDao,
            UserCache userCache,
            PocaDao pocaDao,
            PocaCache pocaCache,
            FavoriteDao favoriteDao,
            FavoriteCache favoriteCache,
            InputItemCrudOperation inputItemCrudOperation,
            InputItemDao inputItemDao,
            OutputItemCrudOperation outputItemCrudOperation,
            OutputItemDao outputItemDao,
            TaskCrudOperation taskCrudOperation,
            TaskDao taskDao,
            VariableCrudOperation variableCrudOperation,
            VariableDao variableDao,
            SessionCrudOperation sessionCrudOperation,
            SessionDao sessionDao
    ) {
        this.userDao = userDao;
        this.userCache = userCache;
        this.pocaDao = pocaDao;
        this.pocaCache = pocaCache;
        this.favoriteDao = favoriteDao;
        this.favoriteCache = favoriteCache;
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
    public boolean exists(StringIdKey key) throws Exception {
        return userCache.exists(key) || userDao.exists(key);
    }

    @Override
    public User get(StringIdKey key) throws Exception {
        if (userCache.exists(key)) {
            return userCache.get(key);
        } else {
            if (!userDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            User user = userDao.get(key);
            userCache.push(user, userTimeout);
            return user;
        }
    }

    @Override
    public StringIdKey insert(User user) throws Exception {
        userCache.push(user, userTimeout);
        return userDao.insert(user);
    }

    @Override
    public void update(User user) throws Exception {
        userCache.push(user, userTimeout);
        userDao.update(user);
    }

    @Override
    public void delete(StringIdKey key) throws Exception {
        // 删除与用户相关的个人最佳权限。
        List<PocaKey> pocaKeys = pocaDao.lookup(PocaMaintainService.CHILD_FOR_USER, new Object[]{key})
                .stream().map(Poca::getKey).collect(Collectors.toList());
        pocaCache.batchDelete(pocaKeys);
        pocaDao.batchDelete(pocaKeys);

        // 删除与用户相关的个人最佳权限。
        List<FavoriteKey> favoriteKeys = favoriteDao.lookup(FavoriteMaintainService.CHILD_FOR_USER, new Object[]{key})
                .stream().map(Favorite::getKey).collect(Collectors.toList());
        favoriteCache.batchDelete(favoriteKeys);
        favoriteDao.batchDelete(favoriteKeys);

        // 删除与用户相关的输入项。
        List<TaskItemKey> inputItemKeys = inputItemDao.lookup(InputItemMaintainService.USER_KEY_EQ, new Object[]{key})
                .stream().map(InputItem::getKey).collect(Collectors.toList());
        inputItemCrudOperation.batchDelete(inputItemKeys);

        // 删除与用户相关的输出项。
        List<TaskItemKey> outputItemKeys = outputItemDao.lookup(OutputItemMaintainService.USER_KEY_EQ, new Object[]{key})
                .stream().map(OutputItem::getKey).collect(Collectors.toList());
        outputItemCrudOperation.batchDelete(outputItemKeys);

        // 删除与用户相关的任务。
        List<LongIdKey> taskKeys = taskDao.lookup(TaskMaintainService.USER_KEY_EQ, new Object[]{key})
                .stream().map(Task::getKey).collect(Collectors.toList());
        taskCrudOperation.batchDelete(taskKeys);

        // 删除与用户相关的变量。
        List<VariableKey> variableKeys = variableDao.lookup(VariableMaintainService.USER_KEY_EQ, new Object[]{key})
                .stream().map(Variable::getKey).collect(Collectors.toList());
        variableCrudOperation.batchDelete(variableKeys);

        // 删除与用户相关的会话。
        List<LongIdKey> sessionKeys = sessionDao.lookup(SessionMaintainService.CHILD_FOR_USER, new Object[]{key})
                .stream().map(Session::getKey).collect(Collectors.toList());
        sessionCrudOperation.batchDelete(sessionKeys);

        // 删除用户实体自身。
        userCache.delete(key);
        userDao.delete(key);
    }

    @Override
    public boolean allExists(List<StringIdKey> keys) throws Exception {
        return userCache.allExists(keys) || userDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<StringIdKey> keys) throws Exception {
        return userCache.nonExists(keys) && userDao.nonExists(keys);
    }

    @Override
    public List<User> batchGet(List<StringIdKey> keys) throws Exception {
        if (userCache.allExists(keys)) {
            return userCache.batchGet(keys);
        } else {
            if (!userDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<User> users = userDao.batchGet(keys);
            userCache.batchPush(users, userTimeout);
            return users;
        }
    }

    @Override
    public List<StringIdKey> batchInsert(List<User> users) throws Exception {
        userCache.batchPush(users, userTimeout);
        return userDao.batchInsert(users);
    }

    @Override
    public void batchUpdate(List<User> users) throws Exception {
        userCache.batchPush(users, userTimeout);
        userDao.batchUpdate(users);
    }

    @Override
    public void batchDelete(List<StringIdKey> keys) throws Exception {
        for (StringIdKey key : keys) {
            delete(key);
        }
    }
}
