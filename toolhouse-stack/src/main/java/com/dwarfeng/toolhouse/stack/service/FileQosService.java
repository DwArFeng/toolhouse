package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.*;

/**
 * 文件 QOS 服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FileQosService extends Service {

    /**
     * 手动下载文件。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件手动下载信息。
     * @return 文件。
     * @throws ServiceException 服务异常。
     */
    File manualDownloadFile(StringIdKey operateUserKey, FileManualDownloadInfo info) throws ServiceException;

    /**
     * 手动下载文件流。
     *
     * <p>
     * 如果返回的 <code>FileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>FileStream</code> 中的输入流，即其中的 <code>FileStream.content</code>。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件流手动下载信息。
     * @return 文件流。
     * @throws ServiceException 服务异常。
     */
    FileStream manualDownloadFileStream(StringIdKey operateUserKey, FileStreamManualDownloadInfo info)
            throws ServiceException;

    /**
     * 手动上传文件。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件手动上传信息。
     * @return 文件上传结果。
     * @throws ServiceException 服务异常。
     */
    FileUploadResult manualUploadFile(StringIdKey operateUserKey, FileManualUploadInfo info) throws ServiceException;

    /**
     * 手动上传文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>FileStreamManualUploadInfo</code> 中的输入流，
     * 即其中的 <code>FileStreamManualUploadInfo.content</code>。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件流手动上传信息。
     * @return 文件流上传结果。
     * @throws ServiceException 服务异常。
     */
    FileStreamUploadResult manualUploadFileStream(StringIdKey operateUserKey, FileStreamManualUploadInfo info)
            throws ServiceException;

    /**
     * 手动更新文件。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件手动更新信息。
     * @throws ServiceException 服务异常。
     */
    void manualUpdateFile(StringIdKey operateUserKey, FileManualUpdateInfo info) throws ServiceException;

    /**
     * 手动更新文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>FileStreamManualUpdateInfo</code> 中的输入流，
     * 即其中的 <code>FileStreamManualUpdateInfo.content</code>。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件流手动更新信息。
     * @throws ServiceException 服务异常。
     */
    void manualUpdateFileStream(StringIdKey operateUserKey, FileStreamManualUpdateInfo info) throws ServiceException;

    /**
     * 手动删除文件。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件手动删除信息。
     * @throws ServiceException 服务异常。
     */
    void manualRemoveFile(StringIdKey operateUserKey, FileManualRemoveInfo info) throws ServiceException;

    /**
     * 超控下载文件。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件超控下载信息。
     * @return 文件。
     * @throws ServiceException 服务异常。
     */
    File overrideDownloadFile(StringIdKey operateUserKey, FileOverrideDownloadInfo info) throws ServiceException;

    /**
     * 超控下载文件流。
     *
     * <p>
     * 如果返回的 <code>FileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>FileStream</code> 中的输入流，即其中的 <code>FileStream.content</code>。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件流超控下载信息。
     * @return 文件流。
     * @throws ServiceException 服务异常。
     */
    FileStream overrideDownloadFileStream(StringIdKey operateUserKey, FileStreamOverrideDownloadInfo info)
            throws ServiceException;

    /**
     * 超控上传文件。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件超控上传信息。
     * @return 文件上传结果。
     * @throws ServiceException 服务异常。
     */
    FileUploadResult overrideUploadFile(StringIdKey operateUserKey, FileOverrideUploadInfo info)
            throws ServiceException;

    /**
     * 超控上传文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>FileStreamOverrideUploadInfo</code> 中的输入流，
     * 即其中的 <code>FileStreamOverrideUploadInfo.content</code>。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件流超控上传信息。
     * @return 文件流上传结果。
     * @throws ServiceException 服务异常。
     */
    FileStreamUploadResult overrideUploadFileStream(StringIdKey operateUserKey, FileStreamOverrideUploadInfo info)
            throws ServiceException;

    /**
     * 超控更新文件。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件超控更新信息。
     * @throws ServiceException 服务异常。
     */
    void overrideUpdateFile(StringIdKey operateUserKey, FileOverrideUpdateInfo info) throws ServiceException;

    /**
     * 超控更新文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>FileStreamOverrideUpdateInfo</code> 中的输入流，
     * 即其中的 <code>FileStreamOverrideUpdateInfo.content</code>。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件流超控更新信息。
     * @throws ServiceException 服务异常。
     */
    void overrideUpdateFileStream(StringIdKey operateUserKey, FileStreamOverrideUpdateInfo info)
            throws ServiceException;

    /**
     * 超控删除文件。
     *
     * @param operateUserKey 操作用户的键。
     * @param info           文件超控删除信息。
     * @throws ServiceException 服务异常。
     */
    void overrideRemoveFile(StringIdKey operateUserKey, FileOverrideRemoveInfo info) throws ServiceException;

    /**
     * 系统下载文件。
     *
     * @param info 文件系统下载信息。
     * @return 文件。
     * @throws ServiceException 服务异常。
     */
    File systemDownloadFile(FileSystemDownloadInfo info) throws ServiceException;

    /**
     * 系统下载文件流。
     *
     * <p>
     * 如果返回的 <code>FileStream</code> 不为 <code>null</code>，则调用者有义务关闭
     * <code>FileStream</code> 中的输入流，即其中的 <code>FileStream.content</code>。
     *
     * @param info 文件流系统下载信息。
     * @return 文件流。
     * @throws ServiceException 服务异常。
     */
    FileStream systemDownloadFileStream(FileStreamSystemDownloadInfo info) throws ServiceException;

    /**
     * 系统上传文件。
     *
     * @param info 文件系统上传信息。
     * @return 文件上传结果。
     * @throws ServiceException 服务异常。
     */
    FileUploadResult systemUploadFile(FileSystemUploadInfo info) throws ServiceException;

    /**
     * 系统上传文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>FileStreamSystemUploadInfo</code> 中的输入流，
     * 即其中的 <code>FileStreamSystemUploadInfo.content</code>。
     *
     * @param info 文件流系统上传信息。
     * @return 文件流上传结果。
     * @throws ServiceException 服务异常。
     */
    FileStreamUploadResult systemUploadFileStream(FileStreamSystemUploadInfo info) throws ServiceException;

    /**
     * 系统更新文件。
     *
     * @param info 文件系统更新信息。
     * @throws ServiceException 服务异常。
     */
    void systemUpdateFile(FileSystemUpdateInfo info) throws ServiceException;

    /**
     * 系统更新文件流。
     *
     * <p>
     * 调用者有义务关闭 <code>FileStreamSystemUpdateInfo</code> 中的输入流，
     * 即其中的 <code>FileStreamSystemUpdateInfo.content</code>。
     *
     * @param info 文件流系统更新信息。
     * @throws ServiceException 服务异常。
     */
    void systemUpdateFileStream(FileStreamSystemUpdateInfo info) throws ServiceException;

    /**
     * 系统删除文件。
     *
     * @param info 文件系统删除信息。
     * @throws ServiceException 服务异常。
     */
    void systemRemoveFile(FileSystemRemoveInfo info) throws ServiceException;
}
