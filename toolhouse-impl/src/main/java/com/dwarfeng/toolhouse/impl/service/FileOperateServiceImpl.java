package com.dwarfeng.toolhouse.impl.service;

import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.exception.ServiceExceptionMapper;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.handler.FileOperateHandler;
import com.dwarfeng.toolhouse.stack.service.FileOperateService;
import org.springframework.stereotype.Service;

@Service
public class FileOperateServiceImpl implements FileOperateService {

    private final FileOperateHandler fileOperateHandler;

    private final ServiceExceptionMapper sem;

    public FileOperateServiceImpl(FileOperateHandler fileOperateHandler, ServiceExceptionMapper sem) {
        this.fileOperateHandler = fileOperateHandler;
        this.sem = sem;
    }

    @Override
    public File manualDownloadFile(StringIdKey operateUserKey, FileManualDownloadInfo info) throws ServiceException {
        try {
            return fileOperateHandler.manualDownloadFile(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动下载文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileStream manualDownloadFileStream(StringIdKey operateUserKey, FileStreamManualDownloadInfo info)
            throws ServiceException {
        try {
            return fileOperateHandler.manualDownloadFileStream(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动下载文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileUploadResult manualUploadFile(StringIdKey operateUserKey, FileManualUploadInfo info)
            throws ServiceException {
        try {
            return fileOperateHandler.manualUploadFile(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动上传文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileStreamUploadResult manualUploadFileStream(StringIdKey operateUserKey, FileStreamManualUploadInfo info)
            throws ServiceException {
        try {
            return fileOperateHandler.manualUploadFileStream(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动上传文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void manualUpdateFile(StringIdKey operateUserKey, FileManualUpdateInfo info) throws ServiceException {
        try {
            fileOperateHandler.manualUpdateFile(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动更新文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void manualUpdateFileStream(StringIdKey operateUserKey, FileStreamManualUpdateInfo info)
            throws ServiceException {
        try {
            fileOperateHandler.manualUpdateFileStream(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动更新文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void manualRemoveFile(StringIdKey operateUserKey, FileManualRemoveInfo info) throws ServiceException {
        try {
            fileOperateHandler.manualRemoveFile(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("手动删除文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public File overrideDownloadFile(StringIdKey operateUserKey, FileOverrideDownloadInfo info)
            throws ServiceException {
        try {
            return fileOperateHandler.overrideDownloadFile(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控下载文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileStream overrideDownloadFileStream(StringIdKey operateUserKey, FileStreamOverrideDownloadInfo info)
            throws ServiceException {
        try {
            return fileOperateHandler.overrideDownloadFileStream(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控下载文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileUploadResult overrideUploadFile(StringIdKey operateUserKey, FileOverrideUploadInfo info)
            throws ServiceException {
        try {
            return fileOperateHandler.overrideUploadFile(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控上传文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public FileStreamUploadResult overrideUploadFileStream(
            StringIdKey operateUserKey, FileStreamOverrideUploadInfo info
    ) throws ServiceException {
        try {
            return fileOperateHandler.overrideUploadFileStream(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控上传文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideUpdateFile(StringIdKey operateUserKey, FileOverrideUpdateInfo info) throws ServiceException {
        try {
            fileOperateHandler.overrideUpdateFile(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控更新文件时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideUpdateFileStream(StringIdKey operateUserKey, FileStreamOverrideUpdateInfo info)
            throws ServiceException {
        try {
            fileOperateHandler.overrideUpdateFileStream(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控更新文件流时发生异常", LogLevel.WARN, e, sem);
        }
    }

    @Override
    public void overrideRemoveFile(StringIdKey operateUserKey, FileOverrideRemoveInfo info) throws ServiceException {
        try {
            fileOperateHandler.overrideRemoveFile(operateUserKey, info);
        } catch (Exception e) {
            throw ServiceExceptionHelper.logParse("超控删除文件时发生异常", LogLevel.WARN, e, sem);
        }
    }
}
