package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.cache.ToolCache;
import com.dwarfeng.toolhouse.stack.dao.ToolDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ToolCrudOperation implements BatchCrudOperation<LongIdKey, Tool> {

    private final ToolDao toolDao;
    private final ToolCache toolCache;

    @Value("${cache.timeout.entity.tool}")
    private long toolTimeout;

    public ToolCrudOperation(
            ToolDao toolDao,
            ToolCache toolCache
    ) {
        this.toolDao = toolDao;
        this.toolCache = toolCache;
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
