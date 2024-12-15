package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.FolderCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.FolderUpdateInfo;
import com.dwarfeng.toolhouse.stack.handler.FolderOperateHandler;
import com.dwarfeng.toolhouse.stack.service.FolderOperateService;
import org.springframework.stereotype.Service;

@Service
public class FolderOperateServiceImpl implements FolderOperateService {

    private final FolderOperateHandler folderOperateHandler;

    private final ServiceExceptionMapper sem;

    public FolderOperateServiceImpl(FolderOperateHandler folderOperateHandler, ServiceExceptionMapper sem) {
        this.folderOperateHandler = folderOperateHandler;
        this.sem = sem;
    }

    @Override
    public LongIdKey createFolder(StringIdKey userKey, FolderCreateInfo folderCreateInfo)
            throws ServiceException {
        try {
            return folderOperateHandler.createFolder(userKey, folderCreateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("创建文件夹时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void updateFolder(StringIdKey userKey, FolderUpdateInfo folderUpdateInfo)
            throws ServiceException {
        try {
            folderOperateHandler.updateFolder(userKey, folderUpdateInfo);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("更新文件夹时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void removeFolder(StringIdKey userKey, LongIdKey folderKey) throws ServiceException {
        try {
            folderOperateHandler.removeFolder(userKey, folderKey);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("删除文件夹时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
