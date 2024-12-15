package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.stack.bean.entity.Folder;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.cache.FolderCache;
import com.dwarfeng.toolhouse.stack.dao.FolderDao;
import com.dwarfeng.toolhouse.stack.dao.ToolDao;
import com.dwarfeng.toolhouse.stack.service.FolderMaintainService;
import com.dwarfeng.toolhouse.stack.service.ToolMaintainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;

@Component
public class FolderCrudOperation implements BatchCrudOperation<LongIdKey, Folder> {

    private final FolderDao folderDao;
    private final FolderCache folderCache;

    private final ToolCrudOperation toolCrudOperation;
    private final ToolDao toolDao;

    @Value("${cache.timeout.entity.folder}")
    private long folderTimeout;

    public FolderCrudOperation(
            FolderDao folderDao, FolderCache folderCache,
            ToolCrudOperation toolCrudOperation, ToolDao toolDao
    ) {
        this.folderDao = folderDao;
        this.folderCache = folderCache;
        this.toolCrudOperation = toolCrudOperation;
        this.toolDao = toolDao;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return folderCache.exists(key) || folderDao.exists(key);
    }

    @Override
    public Folder get(LongIdKey key) throws Exception {
        if (folderCache.exists(key)) {
            return folderCache.get(key);
        } else {
            if (!folderDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            Folder folder = folderDao.get(key);
            folderCache.push(folder, folderTimeout);
            return folder;
        }
    }

    @Override
    public LongIdKey insert(Folder folder) throws Exception {
        folderCache.push(folder, folderTimeout);
        return folderDao.insert(folder);
    }

    @Override
    public void update(Folder folder) throws Exception {
        folderCache.push(folder, folderTimeout);
        folderDao.update(folder);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 寻找自身的子孙节点和子孙条目。
        DescendantStruct descendantStruct = findDescendant(key);

        // 依次删除子孙条目和子孙节点。
        List<LongIdKey> toolKeys = descendantStruct.getToolKeys();
        toolCrudOperation.batchDelete(toolKeys);
        List<LongIdKey> folderKeys = descendantStruct.getFolderKeys();
        folderCache.batchDelete(folderKeys);
        folderDao.batchDelete(folderKeys);
    }

    private DescendantStruct findDescendant(LongIdKey key) throws Exception {
        // 本方法使用递归形式，并转化为迭代。

        // 定义结果变量。
        List<LongIdKey> folderKeys = new LinkedList<>();
        List<LongIdKey> toolKeys = new ArrayList<>();

        // 定义一个栈，并初始化。
        Stack<LongIdKey> folderStack = new Stack<>();
        folderStack.push(key);

        // 在栈清空之前，一直执行循环。
        while (!folderStack.isEmpty()) {
            // 从栈中取出当前的节点。
            LongIdKey folderKey = folderStack.pop();
            // 查询节点的子节点。
            List<LongIdKey> childFolderKeys = folderDao.lookup(
                    FolderMaintainService.CHILD_FOR_PARENT, new Object[]{folderKey}
            ).stream().map(Folder::getKey).collect(Collectors.toList());
            // 查询节点的子条目。
            List<LongIdKey> childToolKeys = toolDao.lookup(
                    ToolMaintainService.CHILD_FOR_FOLDER, new Object[]{folderKey}
            ).stream().map(Tool::getKey).collect(Collectors.toList());
            // 将结果添加到结果变量中（插入到最前面）。
            folderKeys.add(0, folderKey);
            toolKeys.addAll(childToolKeys);
            // 向栈中推送节点的子节点。
            childFolderKeys.forEach(folderStack::push);
        }

        // 返回结果。
        return new DescendantStruct(folderKeys, toolKeys);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return folderCache.allExists(keys) || folderDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return folderCache.nonExists(keys) && folderDao.nonExists(keys);
    }

    @Override
    public List<Folder> batchGet(List<LongIdKey> keys) throws Exception {
        if (folderCache.allExists(keys)) {
            return folderCache.batchGet(keys);
        } else {
            if (!folderDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<Folder> folders = folderDao.batchGet(keys);
            folderCache.batchPush(folders, folderTimeout);
            return folders;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<Folder> folders) throws Exception {
        folderCache.batchPush(folders, folderTimeout);
        return folderDao.batchInsert(folders);
    }

    @Override
    public void batchUpdate(List<Folder> folders) throws Exception {
        folderCache.batchPush(folders, folderTimeout);
        folderDao.batchUpdate(folders);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }

    private static final class DescendantStruct {

        private final List<LongIdKey> folderKeys;
        private final List<LongIdKey> toolKeys;

        private DescendantStruct(List<LongIdKey> folderKeys, List<LongIdKey> toolKeys) {
            this.folderKeys = folderKeys;
            this.toolKeys = toolKeys;
        }

        public List<LongIdKey> getFolderKeys() {
            return folderKeys;
        }

        public List<LongIdKey> getToolKeys() {
            return toolKeys;
        }

        @Override
        public String toString() {
            return "DescendantStruct{" +
                    "folderKeys=" + folderKeys +
                    ", toolKeys=" + toolKeys +
                    '}';
        }
    }
}
