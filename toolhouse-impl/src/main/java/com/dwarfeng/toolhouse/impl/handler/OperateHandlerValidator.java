package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.bean.entity.Folder;
import com.dwarfeng.toolhouse.stack.bean.entity.Poca;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
import com.dwarfeng.toolhouse.stack.exception.*;
import com.dwarfeng.toolhouse.stack.service.*;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * 操作处理器验证器。
 *
 * <p>
 * 为操作处理器提供公共的验证方法。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Component
public class OperateHandlerValidator {

    private final UserMaintainService userMaintainService;
    private final PocaMaintainService pocaMaintainService;
    private final CabinetMaintainService cabinetMaintainService;
    private final FolderMaintainService folderMaintainService;
    private final ToolMaintainService toolMaintainService;

    public OperateHandlerValidator(
            UserMaintainService userMaintainService,
            PocaMaintainService pocaMaintainService,
            CabinetMaintainService cabinetMaintainService,
            FolderMaintainService folderMaintainService,
            ToolMaintainService toolMaintainService
    ) {
        this.userMaintainService = userMaintainService;
        this.pocaMaintainService = pocaMaintainService;
        this.cabinetMaintainService = cabinetMaintainService;
        this.folderMaintainService = folderMaintainService;
        this.toolMaintainService = toolMaintainService;
    }

    public void makeSureUserExists(StringIdKey userKey) throws HandlerException {
        try {
            if (Objects.isNull(userKey) || !userMaintainService.exists(userKey)) {
                throw new UserNotExistsException(userKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureCabinetExists(LongIdKey cabinetKey) throws HandlerException {
        try {
            if (Objects.isNull(cabinetKey) || !cabinetMaintainService.exists(cabinetKey)) {
                throw new CabinetNotExistsException(cabinetKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureFolderExists(LongIdKey folderKey) throws HandlerException {
        try {
            if (Objects.isNull(folderKey) || !folderMaintainService.exists(folderKey)) {
                throw new FolderNotExistsException(folderKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureToolExists(LongIdKey toolKey) throws HandlerException {
        try {
            if (Objects.isNull(toolKey) || !toolMaintainService.exists(toolKey)) {
                throw new ToolNotExistsException(toolKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSurePermissionLevelValid(int permissionLevel) throws HandlerException {
        if (permissionLevel == Constants.PERMISSION_LEVEL_GUEST) {
            return;
        }
        throw new InvalidPermissionLevelException(permissionLevel);
    }

    @SuppressWarnings("DuplicatedCode")
    public void makeSureUserInspectPermittedForCabinet(StringIdKey userKey, LongIdKey cabinetKey)
            throws HandlerException {
        try {
            // 1. 构造 Poca 主键。
            PocaKey pocaKey = new PocaKey(cabinetKey.getLongId(), userKey.getStringId());

            // 2. 查看 Poca 实体是否存在，如果不存在，则没有权限。
            if (!pocaMaintainService.exists(pocaKey)) {
                throw new UserNotPermittedForCabinetException(userKey, cabinetKey);
            }

            // 3. 查看 Poca.permissionLevel 是否为 Poca.PERMISSION_LEVEL_OWNER 或 Poca.PERMISSION_LEVEL_GUEST，
            // 如果不是，则没有权限。
            Poca poca = pocaMaintainService.get(pocaKey);
            if (Objects.equals(poca.getPermissionLevel(), Constants.PERMISSION_LEVEL_OWNER)) {
                return;
            }
            if (Objects.equals(poca.getPermissionLevel(), Constants.PERMISSION_LEVEL_GUEST)) {
                return;
            }
            throw new UserNotPermittedForCabinetException(userKey, cabinetKey);
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    public void makeSureUserModifyPermittedForCabinet(StringIdKey userKey, LongIdKey cabinetKey)
            throws HandlerException {
        try {
            // 1. 构造 Poca 主键。
            PocaKey pocaKey = new PocaKey(cabinetKey.getLongId(), userKey.getStringId());

            // 2. 查看 Poca 实体是否存在，如果不存在，则没有权限。
            if (!pocaMaintainService.exists(pocaKey)) {
                throw new UserNotPermittedForCabinetException(userKey, cabinetKey);
            }

            // 3. 查看 Poca.permissionLevel 是否为 Poca.PERMISSION_LEVEL_OWNER，如果不是，则没有权限。
            Poca poca = pocaMaintainService.get(pocaKey);
            if (Objects.equals(poca.getPermissionLevel(), Constants.PERMISSION_LEVEL_OWNER)) {
                return;
            }
            throw new UserNotPermittedForCabinetException(userKey, cabinetKey);
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureCabinetIdenticalForCabinet(LongIdKey folderKey, LongIdKey cabinetKey) throws HandlerException {
        try {
            // 如果 leftFolderKey 为 null，则表示该项目是根项目，不需要进行任何判断。
            if (Objects.isNull(folderKey)) {
                return;
            }

            Folder parentFolder = folderMaintainService.get(folderKey);
            LongIdKey parentSetKey = parentFolder.getCabinetKey();
            if (Objects.isNull(parentSetKey)) {
                throw new IllegalFolderStateException(folderKey);
            }
            if (!Objects.equals(parentSetKey, cabinetKey)) {
                throw new CabinetNotIdenticalException(parentSetKey, cabinetKey);
            }
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserInspectPermittedForFolder(StringIdKey userKey, LongIdKey folderKey)
            throws HandlerException {
        try {
            // 1. 查找指定的个人最佳节点是否绑定个人最佳集合，如果不绑定个人最佳集合，则抛出个人最佳节点状态异常。
            Folder folder = folderMaintainService.get(folderKey);
            if (Objects.isNull(folder.getCabinetKey())) {
                throw new IllegalFolderStateException(folderKey);
            }

            // 2. 取出个人最佳节点的个人最佳集合外键，判断用户是否拥有该个人最佳集合的权限。
            makeSureUserInspectPermittedForCabinet(userKey, folder.getCabinetKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserModifyPermittedForFolder(StringIdKey userKey, LongIdKey folderKey) throws HandlerException {
        try {
            // 1. 查找指定的个人最佳节点是否绑定个人最佳集合，如果不绑定个人最佳集合，则抛出个人最佳节点状态异常。
            Folder folder = folderMaintainService.get(folderKey);
            if (Objects.isNull(folder.getCabinetKey())) {
                throw new IllegalFolderStateException(folderKey);
            }

            // 2. 取出个人最佳节点的个人最佳集合外键，判断用户是否拥有该个人最佳集合的权限。
            makeSureUserModifyPermittedForCabinet(userKey, folder.getCabinetKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserInspectPermittedForTool(StringIdKey userKey, LongIdKey toolKey)
            throws HandlerException {
        try {
            // 1. 查找指定的个人最佳项目是否绑定个人最佳集合，如果不绑定个人最佳集合，则抛出个人最佳项目状态异常。
            Tool tool = toolMaintainService.get(toolKey);
            if (Objects.isNull(tool.getCabinetKey())) {
                throw new IllegalToolStateException(toolKey);
            }

            // 2. 取出个人最佳项目的个人最佳集合外键，判断用户是否拥有该个人最佳节点的权限。
            makeSureUserInspectPermittedForCabinet(userKey, tool.getCabinetKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureUserModifyPermittedForTool(StringIdKey userKey, LongIdKey toolKey)
            throws HandlerException {
        try {
            // 1. 查找指定的个人最佳项目是否绑定个人最佳集合，如果不绑定个人最佳集合，则抛出个人最佳项目状态异常。
            Tool tool = toolMaintainService.get(toolKey);
            if (Objects.isNull(tool.getCabinetKey())) {
                throw new IllegalToolStateException(toolKey);
            }

            // 2. 取出个人最佳项目的个人最佳集合外键，判断用户是否拥有该个人最佳节点的权限。
            makeSureUserModifyPermittedForCabinet(userKey, tool.getCabinetKey());
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }

    public void makeSureCabinetIdenticalForFolder(LongIdKey leftFolderKey, LongIdKey rightFolderKey)
            throws HandlerException {
        try {
            Folder childFolder = folderMaintainService.get(rightFolderKey);
            LongIdKey childSetKey = childFolder.getCabinetKey();
            if (Objects.isNull(childSetKey)) {
                throw new IllegalFolderStateException(leftFolderKey);
            }
            makeSureCabinetIdenticalForCabinet(leftFolderKey, childSetKey);
        } catch (ServiceException e) {
            throw new HandlerException(e);
        }
    }
}
