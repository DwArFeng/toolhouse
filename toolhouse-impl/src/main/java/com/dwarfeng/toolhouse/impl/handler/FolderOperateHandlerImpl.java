package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.dto.FolderCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.FolderUpdateInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;
import com.dwarfeng.toolhouse.stack.bean.entity.Folder;
import com.dwarfeng.toolhouse.stack.handler.FolderOperateHandler;
import com.dwarfeng.toolhouse.stack.service.CabinetMaintainService;
import com.dwarfeng.toolhouse.stack.service.FolderMaintainService;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class FolderOperateHandlerImpl implements FolderOperateHandler {

    private final FolderMaintainService folderMaintainService;
    private final CabinetMaintainService cabinetMaintainService;

    private final HandlerValidator handlerValidator;

    public FolderOperateHandlerImpl(
            FolderMaintainService folderMaintainService,
            CabinetMaintainService cabinetMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.folderMaintainService = folderMaintainService;
        this.cabinetMaintainService = cabinetMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public LongIdKey createFolder(StringIdKey userKey, FolderCreateInfo folderCreateInfo)
            throws HandlerException {
        try {
            LongIdKey cabinetKey = folderCreateInfo.getCabinetKey();
            LongIdKey parentKey = folderCreateInfo.getParentKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 确认工具柜存在。
            handlerValidator.makeSureCabinetExists(cabinetKey);

            // 确认父文件夹存在。
            if (Objects.nonNull(parentKey)) {
                handlerValidator.makeSureFolderExists(parentKey);
            }

            // 确认用户有权限操作指定的工具柜。
            handlerValidator.makeSureUserModifyPermittedForCabinet(userKey, cabinetKey);

            // 确认文件夹与父文件夹的工具柜存在。
            handlerValidator.makeSureCabinetIdenticalForCabinet(parentKey, cabinetKey);

            // 更新工具柜实体的字段。
            Cabinet cabinet = cabinetMaintainService.get(cabinetKey);
            cabinetMaintainService.update(cabinet);

            // 根据 folderCreateInfo 以及创建的规则组合 文件夹 实体。
            Folder folder = new Folder(
                    null, parentKey, cabinetKey, folderCreateInfo.getName(), folderCreateInfo.getRemark()
            );

            // 插入文件夹，并返回文件夹实体的主键。
            return folderMaintainService.insert(folder);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateFolder(StringIdKey userKey, FolderUpdateInfo folderUpdateInfo) throws HandlerException {
        try {
            LongIdKey folderKey = folderUpdateInfo.getKey();
            LongIdKey parentKey = folderUpdateInfo.getParentKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 确认文件夹存在。
            handlerValidator.makeSureFolderExists(folderKey);

            // 确认父文件夹存在。
            if (Objects.nonNull(parentKey)) {
                handlerValidator.makeSureFolderExists(parentKey);
            }

            // 确认工具柜存在。
            Folder folder = folderMaintainService.get(folderKey);
            LongIdKey cabinetKey = folder.getCabinetKey();
            handlerValidator.makeSureCabinetExists(cabinetKey);

            // 确认用户有权限操作指定的文件夹。
            handlerValidator.makeSureUserModifyPermittedForFolder(userKey, folderKey);

            // 确认文件夹与父文件夹的工具柜存在。
            handlerValidator.makeSureCabinetIdenticalForFolder(parentKey, folderKey);

            // 更新工具柜实体的字段。
            Cabinet cabinet = cabinetMaintainService.get(cabinetKey);
            cabinetMaintainService.update(cabinet);

            // 根据 folderUpdateInfo 以及更新的规则设置 文件夹 实体。
            folder.setParentKey(parentKey);
            folder.setName(folderUpdateInfo.getName());
            folder.setRemark(folderUpdateInfo.getRemark());

            // 更新 文件夹 实体。
            folderMaintainService.update(folder);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeFolder(StringIdKey userKey, LongIdKey folderKey) throws HandlerException {
        try {
            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 确认文件夹存在。
            handlerValidator.makeSureFolderExists(folderKey);

            // 确认工具柜存在。
            Folder folder = folderMaintainService.get(folderKey);
            LongIdKey cabinetKey = folder.getCabinetKey();
            handlerValidator.makeSureCabinetExists(cabinetKey);

            // 确认用户有权限操作指定的工具柜节点。
            handlerValidator.makeSureUserModifyPermittedForFolder(userKey, folderKey);

            // 更新工具柜实体的字段。
            Cabinet cabinet = cabinetMaintainService.get(cabinetKey);
            cabinetMaintainService.update(cabinet);

            // 存在删除指定的文件夹。
            folderMaintainService.deleteIfExists(folderKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
