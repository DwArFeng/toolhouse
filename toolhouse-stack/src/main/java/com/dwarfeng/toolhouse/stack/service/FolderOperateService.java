package com.dwarfeng.toolhouse.stack.service;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.subgrade.stack.service.Service;
import com.dwarfeng.toolhouse.stack.bean.dto.FolderCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.FolderUpdateInfo;

/**
 * 文件夹操作服务。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FolderOperateService extends Service {

    /**
     * 创建文件夹。
     *
     * @param userKey          文件夹的所有者的主键。
     * @param folderCreateInfo 文件夹的创建信息。
     * @return 生成的文件夹的主键。
     * @throws ServiceException 服务异常。
     */
    LongIdKey createFolder(StringIdKey userKey, FolderCreateInfo folderCreateInfo) throws ServiceException;

    /**
     * 更新文件夹。
     *
     * @param userKey          文件夹的所有者的主键。
     * @param folderUpdateInfo 文件夹的更新信息。
     * @throws ServiceException 服务异常。
     */
    void updateFolder(StringIdKey userKey, FolderUpdateInfo folderUpdateInfo) throws ServiceException;

    /**
     * 删除文件夹。
     *
     * @param userKey   文件夹的所有者的主键。
     * @param folderKey 文件夹的主键。
     * @throws ServiceException 服务异常。
     */
    void removeFolder(StringIdKey userKey, LongIdKey folderKey) throws ServiceException;
}
