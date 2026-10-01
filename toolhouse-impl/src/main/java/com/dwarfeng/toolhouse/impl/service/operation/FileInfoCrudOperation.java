package com.dwarfeng.toolhouse.impl.service.operation;

import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes;
import com.dwarfeng.subgrade.sdk.service.custom.operation.BatchCrudOperation;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.impl.handler.FtpPathResolver;
import com.dwarfeng.toolhouse.stack.bean.entity.FileInfo;
import com.dwarfeng.toolhouse.stack.cache.FileInfoCache;
import com.dwarfeng.toolhouse.stack.dao.FileInfoDao;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FileInfoCrudOperation implements BatchCrudOperation<LongIdKey, FileInfo> {

    private final FileInfoDao fileInfoDao;
    private final FileInfoCache fileInfoCache;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    @Value("${cache.timeout.entity.file_info}")
    private long fileInfoTimeout;

    public FileInfoCrudOperation(
            FileInfoDao fileInfoDao,
            FileInfoCache fileInfoCache,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver
    ) {
        this.fileInfoDao = fileInfoDao;
        this.fileInfoCache = fileInfoCache;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
    }

    @Override
    public boolean exists(LongIdKey key) throws Exception {
        return fileInfoCache.exists(key) || fileInfoDao.exists(key);
    }

    @Override
    public FileInfo get(LongIdKey key) throws Exception {
        if (fileInfoCache.exists(key)) {
            return fileInfoCache.get(key);
        } else {
            if (!fileInfoDao.exists(key)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            FileInfo fileInfo = fileInfoDao.get(key);
            fileInfoCache.push(fileInfo, fileInfoTimeout);
            return fileInfo;
        }
    }

    @Override
    public LongIdKey insert(FileInfo fileInfo) throws Exception {
        fileInfoCache.push(fileInfo, fileInfoTimeout);
        return fileInfoDao.insert(fileInfo);
    }

    @Override
    public void update(FileInfo fileInfo) throws Exception {
        fileInfoCache.push(fileInfo, fileInfoTimeout);
        fileInfoDao.update(fileInfo);
    }

    @Override
    public void delete(LongIdKey key) throws Exception {
        // 如果存在 文件，则删除 文件。
        String[] path = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
        String fileName = Long.toString(key.getLongId());
        if (ftpHandler.existsFile(path, fileName)) {
            ftpHandler.deleteFile(path, fileName);
        }

        // 删除 文件信息 自身。
        fileInfoDao.delete(key);
        fileInfoCache.delete(key);
    }

    @Override
    public boolean allExists(List<LongIdKey> keys) throws Exception {
        return fileInfoCache.allExists(keys) || fileInfoDao.allExists(keys);
    }

    @Override
    public boolean nonExists(List<LongIdKey> keys) throws Exception {
        return fileInfoCache.nonExists(keys) && fileInfoDao.nonExists(keys);
    }

    @Override
    public List<FileInfo> batchGet(List<LongIdKey> keys) throws Exception {
        if (fileInfoCache.allExists(keys)) {
            return fileInfoCache.batchGet(keys);
        } else {
            if (!fileInfoDao.allExists(keys)) {
                throw new ServiceException(ServiceExceptionCodes.ENTITY_NOT_EXIST);
            }
            List<FileInfo> fileInfos = fileInfoDao.batchGet(keys);
            fileInfoCache.batchPush(fileInfos, fileInfoTimeout);
            return fileInfos;
        }
    }

    @Override
    public List<LongIdKey> batchInsert(List<FileInfo> fileInfos) throws Exception {
        fileInfoCache.batchPush(fileInfos, fileInfoTimeout);
        return fileInfoDao.batchInsert(fileInfos);
    }

    @Override
    public void batchUpdate(List<FileInfo> fileInfos) throws Exception {
        fileInfoCache.batchPush(fileInfos, fileInfoTimeout);
        fileInfoDao.batchUpdate(fileInfos);
    }

    @Override
    public void batchDelete(List<LongIdKey> keys) throws Exception {
        for (LongIdKey key : keys) {
            delete(key);
        }
    }
}
