package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.Variable;
import com.dwarfeng.toolhouse.stack.bean.key.VariableKey;
import com.dwarfeng.toolhouse.stack.cache.VariableCache;
import com.dwarfeng.toolhouse.stack.dao.VariableDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class VariableCrudOperation implements BatchCrudOperation<VariableKey, Variable> {

    private final VariableDao variableDao;
    private final VariableCache variableCache;

    @Value("${cache.timeout.entity.variable}")
    private long variableTimeout;

    public VariableCrudOperation(
            VariableDao variableDao,
            VariableCache variableCache
    ) {
        this.variableDao = variableDao;
        this.variableCache = variableCache;
    }

    @Override
    public boolean exists(VariableKey key) throws Exception {
        return variableCache.exists(key) || variableDao.exists(key);
    }

    @Override
    public Variable get(VariableKey key) throws Exception {
        if (variableCache.exists(key)) {
            return variableCache.get(key);
        } else {
            if (!variableDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Variable variable = variableDao.get(key);
            variableCache.push(variable, variableTimeout);
            return variable;
        }
    }

    @Override
    public VariableKey insert(Variable variable) throws Exception {
        variableCache.push(variable, variableTimeout);
        return variableDao.insert(variable);
    }

    @Override
    public void update(Variable variable) throws Exception {
        variableCache.push(variable, variableTimeout);
        variableDao.update(variable);
    }

    @Override
    public void delete(VariableKey key) throws Exception {
        // 删除 变量 自身。
        variableDao.delete(key);
        variableCache.delete(key);
    }

    @Override
    public boolean allExists(List<VariableKey> keys) throws Exception {
        return variableCache.allExists(keys) || variableDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<VariableKey> keys) throws Exception {
        return variableCache.nonExists(keys) && variableDao.nonExists(keys);
    }

    @Override
    public List<Variable> batchGet(List<VariableKey> keys) throws Exception {
        if (variableCache.allExists(keys)) {
            return variableCache.batchGet(keys);
        } else {
            if (!variableDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Variable> variables = variableDao.batchGet(keys);
            variableCache.batchPush(variables, variableTimeout);
            return variables;
        }
    }

    @Override
    public List<VariableKey> batchInsert(List<Variable> variables) throws Exception {
        variableCache.batchPush(variables, variableTimeout);
        return variableDao.batchInsert(variables);
    }

    @Override
    public void batchUpdate(List<Variable> variables) throws Exception {
        variableCache.batchPush(variables, variableTimeout);
        variableDao.batchUpdate(variables);
    }

    @Override
    public void batchDelete(List<VariableKey> keys) throws Exception {
        for (VariableKey key : keys) {
            delete(key);
        }
    }
}
