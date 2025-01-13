package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.dutil.basic.io.IOUtil;
import com.dwarfeng.ftp.handler.FtpHandler;
import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.FileInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;
import com.dwarfeng.toolhouse.stack.handler.FileOperateHandler;
import com.dwarfeng.toolhouse.stack.service.FileInfoMaintainService;
import com.dwarfeng.toolhouse.stack.service.SessionMaintainService;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

@Component
public class FileOperateHandlerImpl implements FileOperateHandler {

    private final FileInfoMaintainService fileInfoMaintainService;
    private final SessionMaintainService sessionMaintainService;

    private final FtpHandler ftpHandler;

    private final FtpPathResolver ftpPathResolver;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final HandlerValidator handlerValidator;

    public FileOperateHandlerImpl(
            FileInfoMaintainService fileInfoMaintainService,
            SessionMaintainService sessionMaintainService,
            FtpHandler ftpHandler,
            FtpPathResolver ftpPathResolver,
            KeyGenerator<LongIdKey> keyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.fileInfoMaintainService = fileInfoMaintainService;
        this.sessionMaintainService = sessionMaintainService;
        this.ftpHandler = ftpHandler;
        this.ftpPathResolver = ftpPathResolver;
        this.keyGenerator = keyGenerator;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public File manualDownloadFile(StringIdKey operateUserKey, FileManualDownloadInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            handlerValidator.makeSureSessionExists(fileInfo.getSessionKey());
            // 确认用户一致。
            StringIdKey userKey = fileInfo.getUserKey();
            handlerValidator.makeSureUserConsists(userKey, operateUserKey);

            // 下载文件。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            byte[] content = ftpHandler.retrieveFile(filePath, fileName);

            // 更新文件信息。
            Date currentDate = new Date();
            fileInfo.setInspectedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);

            // 拼接 File 并返回。
            return new File(fileInfo.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public FileStream manualDownloadFileStream(StringIdKey operateUserKey, FileStreamManualDownloadInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            handlerValidator.makeSureSessionExists(fileInfo.getSessionKey());
            // 确认用户一致。
            StringIdKey userKey = fileInfo.getUserKey();
            handlerValidator.makeSureUserConsists(userKey, operateUserKey);

            // 下载文件流。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            InputStream content = ftpHandler.openInputStream(filePath, fileName);

            // 更新文件信息。
            Date currentDate = new Date();
            fileInfo.setInspectedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);

            // 拼接 FileStream 并返回。
            return new FileStream(fileInfo.getOriginName(), fileInfo.getLength(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public FileUploadResult manualUploadFile(StringIdKey operateUserKey, FileManualUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String originName = info.getOriginName();
            byte[] content = info.getContent();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户一致。
            Session session = sessionMaintainService.get(sessionKey);
            StringIdKey userKey = session.getUserKey();
            handlerValidator.makeSureUserConsists(userKey, operateUserKey);

            // 分配主键。
            LongIdKey fileInfoKey = keyGenerator.generate();

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            ftpHandler.storeFile(filePath, fileName, content);

            // 根据 FileManualUploadInfo 以及其它信息，构造 FileInfo 实体并插入。
            Date currentDate = new Date();
            // 映射属性。
            FileInfo fileInfo = new FileInfo(
                    fileInfoKey,
                    sessionKey,
                    session.getToolKey(),
                    userKey,
                    originName,
                    content.length,
                    currentDate,
                    currentDate,
                    currentDate,
                    "通过 toolhouse 服务上传/更新文件"
            );
            fileInfoMaintainService.insert(fileInfo);

            // 返回结果。
            return new FileUploadResult(fileInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public FileStreamUploadResult manualUploadFileStream(StringIdKey operateUserKey, FileStreamManualUploadInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String originName = info.getOriginName();
            long length = info.getLength();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户一致。
            Session session = sessionMaintainService.get(sessionKey);
            StringIdKey userKey = session.getUserKey();
            handlerValidator.makeSureUserConsists(userKey, operateUserKey);

            // 分配主键。
            LongIdKey fileInfoKey = keyGenerator.generate();

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(filePath, fileName)) {
                IOUtil.trans(cin, fout, Constants.IO_TRANS_BUFFER_SIZE);
            }

            // 根据 FileStreamManualUploadInfo 以及其它信息，构造 FileInfo 实体并插入。
            Date currentDate = new Date();
            // 映射属性。
            FileInfo fileInfo = new FileInfo(
                    fileInfoKey,
                    sessionKey,
                    session.getToolKey(),
                    userKey,
                    originName,
                    length,
                    currentDate,
                    currentDate,
                    currentDate,
                    "通过 toolhouse 服务上传/更新文件"
            );
            fileInfoMaintainService.insert(fileInfo);

            // 返回结果。
            return new FileStreamUploadResult(fileInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void manualUpdateFile(StringIdKey operateUserKey, FileManualUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();
            String originName = info.getOriginName();
            byte[] content = info.getContent();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            handlerValidator.makeSureSessionExists(fileInfo.getSessionKey());
            // 确认用户一致。
            StringIdKey userKey = fileInfo.getUserKey();
            handlerValidator.makeSureUserConsists(userKey, operateUserKey);

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            ftpHandler.storeFile(filePath, fileName, content);

            // 根据 FileManualUpdateInfo 以及其它信息，更新 FileInfo 实体。
            Date currentDate = new Date();
            // 映射属性。
            fileInfo.setOriginName(originName);
            fileInfo.setLength(content.length);
            fileInfo.setInspectedDate(currentDate);
            fileInfo.setModifiedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void manualUpdateFileStream(StringIdKey operateUserKey, FileStreamManualUpdateInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();
            String originName = info.getOriginName();
            long length = info.getLength();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            handlerValidator.makeSureSessionExists(fileInfo.getSessionKey());
            // 确认用户一致。
            StringIdKey userKey = fileInfo.getUserKey();
            handlerValidator.makeSureUserConsists(userKey, operateUserKey);

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(filePath, fileName)) {
                IOUtil.trans(cin, fout, Constants.IO_TRANS_BUFFER_SIZE);
            }

            // 根据 FileStreamManualUpdateInfo 以及其它信息，更新 FileInfo 实体。
            Date currentDate = new Date();
            // 映射属性。
            fileInfo.setOriginName(originName);
            fileInfo.setLength(length);
            fileInfo.setInspectedDate(currentDate);
            fileInfo.setModifiedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void manualRemoveFile(StringIdKey operateUserKey, FileManualRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            handlerValidator.makeSureSessionExists(fileInfo.getSessionKey());
            // 确认用户一致。
            StringIdKey userKey = fileInfo.getUserKey();
            handlerValidator.makeSureUserConsists(userKey, operateUserKey);

            // 删除文件。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            ftpHandler.deleteFile(filePath, fileName);

            // 删除文件信息。
            fileInfoMaintainService.delete(fileInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public File overrideDownloadFile(StringIdKey operateUserKey, FileOverrideDownloadInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            LongIdKey sessionKey = fileInfo.getSessionKey();
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限查看指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserInspectPermittedForTool(operateUserKey, session.getToolKey());

            // 下载文件。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            byte[] content = ftpHandler.retrieveFile(filePath, fileName);

            // 更新文件信息。
            Date currentDate = new Date();
            fileInfo.setInspectedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);

            // 拼接 File 并返回。
            return new File(fileInfo.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public FileStream overrideDownloadFileStream(StringIdKey operateUserKey, FileStreamOverrideDownloadInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            LongIdKey sessionKey = fileInfo.getSessionKey();
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限查看指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserInspectPermittedForTool(operateUserKey, session.getToolKey());

            // 下载文件流。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            InputStream content = ftpHandler.openInputStream(filePath, fileName);

            // 更新文件信息。
            Date currentDate = new Date();
            fileInfo.setInspectedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);

            // 拼接 FileStream 并返回。
            return new FileStream(fileInfo.getOriginName(), fileInfo.getLength(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public FileUploadResult overrideUploadFile(StringIdKey operateUserKey, FileOverrideUploadInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String originName = info.getOriginName();
            byte[] content = info.getContent();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限操作指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, session.getToolKey());

            // 分配主键。
            LongIdKey fileInfoKey = keyGenerator.generate();

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            ftpHandler.storeFile(filePath, fileName, content);

            // 根据 FileManualUploadInfo 以及其它信息，构造 FileInfo 实体并插入。
            Date currentDate = new Date();
            // 映射属性。
            FileInfo fileInfo = new FileInfo(
                    fileInfoKey,
                    sessionKey,
                    session.getToolKey(),
                    session.getUserKey(),
                    originName,
                    content.length,
                    currentDate,
                    currentDate,
                    currentDate,
                    "通过 toolhouse 服务上传/更新文件"
            );
            fileInfoMaintainService.insert(fileInfo);

            // 返回结果。
            return new FileUploadResult(fileInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public FileStreamUploadResult overrideUploadFileStream(
            StringIdKey operateUserKey, FileStreamOverrideUploadInfo info
    ) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String originName = info.getOriginName();
            long length = info.getLength();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限操作指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, session.getToolKey());

            // 分配主键。
            LongIdKey fileInfoKey = keyGenerator.generate();

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(filePath, fileName)) {
                IOUtil.trans(cin, fout, Constants.IO_TRANS_BUFFER_SIZE);
            }

            // 根据 FileStreamManualUploadInfo 以及其它信息，构造 FileInfo 实体并插入。
            Date currentDate = new Date();
            // 映射属性。
            FileInfo fileInfo = new FileInfo(
                    fileInfoKey,
                    sessionKey,
                    session.getToolKey(),
                    session.getUserKey(),
                    originName,
                    length,
                    currentDate,
                    currentDate,
                    currentDate,
                    "通过 toolhouse 服务上传/更新文件"
            );
            fileInfoMaintainService.insert(fileInfo);

            // 返回结果。
            return new FileStreamUploadResult(fileInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void overrideUpdateFile(StringIdKey operateUserKey, FileOverrideUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();
            String originName = info.getOriginName();
            byte[] content = info.getContent();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            LongIdKey sessionKey = fileInfo.getSessionKey();
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限操作指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, session.getToolKey());

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            ftpHandler.storeFile(filePath, fileName, content);

            // 根据 FileManualUpdateInfo 以及其它信息，更新 FileInfo 实体。
            Date currentDate = new Date();
            // 映射属性。
            fileInfo.setOriginName(originName);
            fileInfo.setLength(content.length);
            fileInfo.setInspectedDate(currentDate);
            fileInfo.setModifiedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void overrideUpdateFileStream(StringIdKey operateUserKey, FileStreamOverrideUpdateInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();
            String originName = info.getOriginName();
            long length = info.getLength();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            LongIdKey sessionKey = fileInfo.getSessionKey();
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限操作指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, session.getToolKey());

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(filePath, fileName)) {
                IOUtil.trans(cin, fout, Constants.IO_TRANS_BUFFER_SIZE);
            }

            // 根据 FileStreamManualUpdateInfo 以及其它信息，更新 FileInfo 实体。
            Date currentDate = new Date();
            // 映射属性。
            fileInfo.setOriginName(originName);
            fileInfo.setLength(length);
            fileInfo.setInspectedDate(currentDate);
            fileInfo.setModifiedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void overrideRemoveFile(StringIdKey operateUserKey, FileOverrideRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);
            // 确认会话存在。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            LongIdKey sessionKey = fileInfo.getSessionKey();
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限操作指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, session.getToolKey());

            // 删除文件。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            ftpHandler.deleteFile(filePath, fileName);

            // 删除文件信息。
            fileInfoMaintainService.delete(fileInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public File systemDownloadFile(FileSystemDownloadInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();

            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);

            // 下载文件。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            byte[] content = ftpHandler.retrieveFile(filePath, fileName);

            // 更新文件信息。
            Date currentDate = new Date();
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            fileInfo.setInspectedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);

            // 拼接 File 并返回。
            return new File(fileInfo.getOriginName(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public FileStream systemDownloadFileStream(FileStreamSystemDownloadInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();

            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);

            // 下载文件流。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            InputStream content = ftpHandler.openInputStream(filePath, fileName);

            // 更新文件信息。
            Date currentDate = new Date();
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            fileInfo.setInspectedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);

            // 拼接 FileStream 并返回。
            return new FileStream(fileInfo.getOriginName(), fileInfo.getLength(), content);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public FileUploadResult systemUploadFile(FileSystemUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String originName = info.getOriginName();
            byte[] content = info.getContent();

            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);

            // 分配主键。
            LongIdKey fileInfoKey = keyGenerator.generate();

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            ftpHandler.storeFile(filePath, fileName, content);

            // 根据 FileManualUploadInfo 以及其它信息，构造 FileInfo 实体并插入。
            Date currentDate = new Date();
            // 映射属性。
            Session session = sessionMaintainService.get(sessionKey);
            FileInfo fileInfo = new FileInfo(
                    fileInfoKey,
                    sessionKey,
                    session.getToolKey(),
                    session.getUserKey(),
                    originName,
                    content.length,
                    currentDate,
                    currentDate,
                    currentDate,
                    "通过 toolhouse 服务上传/更新文件"
            );
            fileInfoMaintainService.insert(fileInfo);

            // 返回结果。
            return new FileUploadResult(fileInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public FileStreamUploadResult systemUploadFileStream(FileStreamSystemUploadInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();
            String originName = info.getOriginName();
            long length = info.getLength();

            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);

            // 分配主键。
            LongIdKey fileInfoKey = keyGenerator.generate();

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(filePath, fileName)) {
                IOUtil.trans(cin, fout, Constants.IO_TRANS_BUFFER_SIZE);
            }

            // 根据 FileStreamManualUploadInfo 以及其它信息，构造 FileInfo 实体并插入。
            Date currentDate = new Date();
            // 映射属性。
            Session session = sessionMaintainService.get(sessionKey);
            FileInfo fileInfo = new FileInfo(
                    fileInfoKey,
                    sessionKey,
                    session.getToolKey(),
                    session.getUserKey(),
                    originName,
                    length,
                    currentDate,
                    currentDate,
                    currentDate,
                    "通过 toolhouse 服务上传/更新文件"
            );
            fileInfoMaintainService.insert(fileInfo);

            // 返回结果。
            return new FileStreamUploadResult(fileInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemUpdateFile(FileSystemUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();
            String originName = info.getOriginName();
            byte[] content = info.getContent();

            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            ftpHandler.storeFile(filePath, fileName, content);

            // 根据 FileManualUpdateInfo 以及其它信息，更新 FileInfo 实体。
            Date currentDate = new Date();
            // 映射属性。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            fileInfo.setOriginName(originName);
            fileInfo.setLength(content.length);
            fileInfo.setInspectedDate(currentDate);
            fileInfo.setModifiedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemUpdateFileStream(FileStreamSystemUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();
            String originName = info.getOriginName();
            long length = info.getLength();

            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);

            // 文件内容并存储（覆盖）。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            InputStream cin = info.getContent();
            try (OutputStream fout = ftpHandler.openOutputStream(filePath, fileName)) {
                IOUtil.trans(cin, fout, Constants.IO_TRANS_BUFFER_SIZE);
            }

            // 根据 FileStreamManualUpdateInfo 以及其它信息，更新 FileInfo 实体。
            Date currentDate = new Date();
            // 映射属性。
            FileInfo fileInfo = fileInfoMaintainService.get(fileInfoKey);
            fileInfo.setOriginName(originName);
            fileInfo.setLength(length);
            fileInfo.setInspectedDate(currentDate);
            fileInfo.setModifiedDate(currentDate);
            fileInfoMaintainService.update(fileInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemRemoveFile(FileSystemRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey fileInfoKey = info.getFileInfoKey();

            // 确认文件信息存在。
            handlerValidator.makeSureFileInfoExists(fileInfoKey);

            // 删除文件。
            String[] filePath = ftpPathResolver.resolvePath(FtpPathResolver.RELATIVE_PATH_FILE);
            String fileName = ftpPathResolver.resolveFileName(fileInfoKey);
            ftpHandler.deleteFile(filePath, fileName);

            // 删除文件信息。
            fileInfoMaintainService.delete(fileInfoKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
