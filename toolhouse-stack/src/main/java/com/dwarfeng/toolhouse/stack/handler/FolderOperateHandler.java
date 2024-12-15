package com.dwarfeng.toolhouse.stack.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.handler.Handler;
import com.dwarfeng.toolhouse.stack.bean.dto.FolderCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.FolderUpdateInfo;

/**
 * 文件夹操作处理器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
public interface FolderOperateHandler extends Handler {

    /**
     * 创建文件夹。
     *
     * @param userKey          文件夹的所有者的主键。
     * @param folderCreateInfo 文件夹的创建信息。
     * @return 生成的文件夹的主键。
     * @throws HandlerException 处理器异常。
     */
    LongIdKey createFolder(StringIdKey userKey, FolderCreateInfo folderCreateInfo) throws HandlerException;

    /**
     * 更新文件夹。
     *
     * @param userKey          文件夹的所有者的主键。
     * @param folderUpdateInfo 文件夹的更新信息。
     * @throws HandlerException 处理器异常。
     */
    void updateFolder(StringIdKey userKey, FolderUpdateInfo folderUpdateInfo) throws HandlerException;

    /**
     * 删除文件夹。
     *
     * @param userKey   文件夹的所有者的主键。
     * @param folderKey 文件夹的主键。
     * @throws HandlerException 处理器异常。
     */
    void removeFolder(StringIdKey userKey, LongIdKey folderKey) throws HandlerException;
}
