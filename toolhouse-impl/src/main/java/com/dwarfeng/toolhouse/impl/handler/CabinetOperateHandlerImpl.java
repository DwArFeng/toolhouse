package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;
import com.dwarfeng.toolhouse.stack.bean.entity.Favorite;
import com.dwarfeng.toolhouse.stack.bean.entity.Poca;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
import com.dwarfeng.toolhouse.stack.handler.CabinetOperateHandler;
import com.dwarfeng.toolhouse.stack.service.CabinetMaintainService;
import com.dwarfeng.toolhouse.stack.service.FavoriteMaintainService;
import com.dwarfeng.toolhouse.stack.service.PocaMaintainService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class CabinetOperateHandlerImpl implements CabinetOperateHandler {

    private final CabinetMaintainService cabinetMaintainService;
    private final PocaMaintainService pocaMaintainService;
    private final FavoriteMaintainService favoriteMaintainService;

    private final OperateHandlerValidator operateHandlerValidator;

    public CabinetOperateHandlerImpl(
            CabinetMaintainService cabinetMaintainService,
            PocaMaintainService pocaMaintainService,
            FavoriteMaintainService favoriteMaintainService,
            OperateHandlerValidator operateHandlerValidator
    ) {
        this.cabinetMaintainService = cabinetMaintainService;
        this.pocaMaintainService = pocaMaintainService;
        this.favoriteMaintainService = favoriteMaintainService;
        this.operateHandlerValidator = operateHandlerValidator;
    }

    @Override
    public LongIdKey createCabinet(StringIdKey userKey, CabinetCreateInfo cabinetCreateInfo)
            throws HandlerException {
        try {
            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 根据 cabinetCreateInfo 以及创建的规则组合 工具柜 实体。
            Date currentDate = new Date();
            Cabinet cabinet = new Cabinet(
                    null, cabinetCreateInfo.getName(), cabinetCreateInfo.getRemark(), currentDate, 0
            );

            // 插入工具柜实体，并获取生成的主键。
            LongIdKey cabinetKey = cabinetMaintainService.insert(cabinet);

            // 由工具柜实体生成的主键和用户主键组合权限信息，并插入。
            Poca poca = new Poca(
                    new PocaKey(cabinetKey.getLongId(), userKey.getStringId()),
                    Constants.PERMISSION_LEVEL_OWNER,
                    "创建工具柜时自动插入，赋予创建人所有者权限"
            );
            pocaMaintainService.insert(poca);

            // 如果是收藏，则插入收藏实体。
            if (cabinetCreateInfo.isFavorite()) {
                Favorite favorite = new Favorite(
                        new FavoriteKey(cabinetKey.getLongId(), userKey.getStringId()),
                        "通过 toolhouse 服务上传/更新留言附件"
                );
                favoriteMaintainService.insertOrUpdate(favorite);
            }

            // 返回生成的主键。
            return cabinetKey;
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateCabinet(StringIdKey userKey, CabinetUpdateInfo cabinetUpdateInfo)
            throws HandlerException {
        try {
            LongIdKey cabinetKey = cabinetUpdateInfo.getCabinetKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 确认工具柜存在。
            operateHandlerValidator.makeSureCabinetExists(cabinetKey);

            // 确认用户有权限操作指定的工具柜。
            operateHandlerValidator.makeSureUserModifyPermittedForCabinet(userKey, cabinetKey);

            // 根据 cabinetUpdateInfo 以及更新的规则设置 工具柜 实体。
            Cabinet cabinet = cabinetMaintainService.get(cabinetKey);
            cabinet.setName(cabinetUpdateInfo.getName());
            cabinet.setRemark(cabinetUpdateInfo.getRemark());

            // 更新工具柜实体。
            cabinetMaintainService.update(cabinet);

            // 如果是收藏，则插入收藏实体。
            FavoriteKey favoriteKey = new FavoriteKey(cabinetKey.getLongId(), userKey.getStringId());
            if (cabinetUpdateInfo.isFavorite()) {
                Favorite favorite = new Favorite(favoriteKey, "通过 toolhouse 服务创建/更新");
                favoriteMaintainService.insertOrUpdate(favorite);
            }
            // 否则删除收藏实体。
            else {
                favoriteMaintainService.deleteIfExists(favoriteKey);
            }
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeCabinet(StringIdKey userKey, LongIdKey cabinetKey) throws HandlerException {
        try {
            // 1. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(userKey);

            // 2. 确认工具柜存在。
            operateHandlerValidator.makeSureCabinetExists(cabinetKey);

            // 3. 确认用户有权限操作指定的工具柜。
            operateHandlerValidator.makeSureUserModifyPermittedForCabinet(userKey, cabinetKey);

            // 4. 删除指定主键的工具柜。
            cabinetMaintainService.delete(cabinetKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了程序的可读性，此处不做代码提取。
    @SuppressWarnings("ExtractMethodRecommender")
    @Override
    public void upsertPermission(
            StringIdKey ownerUserKey, CabinetPermissionUpsertInfo cabinetPermissionUpsertInfo
    ) throws HandlerException {
        try {
            LongIdKey cabinetKey = cabinetPermissionUpsertInfo.getCabinetKey();
            StringIdKey targetUserKey = cabinetPermissionUpsertInfo.getUserKey();
            int permissionLevel = cabinetPermissionUpsertInfo.getPermissionLevel();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认 permissionLevel 有效。
            operateHandlerValidator.makeSurePermissionLevelValid(permissionLevel);

            // 3. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(ownerUserKey);
            operateHandlerValidator.makeSureUserExists(targetUserKey);

            // 4. 确认工具柜存在。
            operateHandlerValidator.makeSureCabinetExists(cabinetKey);

            // 5. 确认用户有权限操作指定的工具柜。
            operateHandlerValidator.makeSureUserModifyPermittedForCabinet(ownerUserKey, cabinetKey);

            // 6. 通过入口信息组合权限实体，并进行插入或更新操作。
            String permissionLabel;
            switch (permissionLevel) {
                case Constants.PERMISSION_LEVEL_GUEST:
                    permissionLabel = "目标";
                    break;
                case Constants.PERMISSION_LEVEL_OWNER:
                    permissionLabel = "所有者";
                    break;
                default:
                    permissionLabel = "（未知）";
            }
            Poca poca = new Poca(
                    new PocaKey(cabinetKey.getLongId(), targetUserKey.getStringId()),
                    permissionLevel,
                    "赋予用户 " + targetUserKey.getStringId() + " " + permissionLabel + "权限"
            );
            pocaMaintainService.insertOrUpdate(poca);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removePermission(
            StringIdKey ownerUserKey, CabinetPermissionRemoveInfo cabinetPermissionRemoveInfo
    ) throws HandlerException {
        try {
            LongIdKey cabinetKey = cabinetPermissionRemoveInfo.getCabinetKey();
            StringIdKey targetUserKey = cabinetPermissionRemoveInfo.getUserKey();

            // 1. 如果用户主键与目标主键一致，则什么也不做。
            if (Objects.equals(ownerUserKey, targetUserKey)) {
                return;
            }

            // 2. 确认用户存在。
            operateHandlerValidator.makeSureUserExists(ownerUserKey);
            operateHandlerValidator.makeSureUserExists(targetUserKey);

            // 3. 确认工具柜存在。
            operateHandlerValidator.makeSureCabinetExists(cabinetKey);

            // 4. 确认用户有权限操作指定的工具柜。
            operateHandlerValidator.makeSureUserModifyPermittedForCabinet(ownerUserKey, cabinetKey);

            // 5. 通过入口信息组合权限实体主键，并进行存在删除操作。
            PocaKey pocaKey = new PocaKey(cabinetKey.getLongId(), targetUserKey.getStringId());
            pocaMaintainService.deleteIfExists(pocaKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void changeFavored(StringIdKey operateUserKey, CabinetFavoredChangeInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey cabinetKey = info.getCabinetKey();

            // 确认用户存在。
            operateHandlerValidator.makeSureUserExists(operateUserKey);
            // 确认工具柜存在。
            operateHandlerValidator.makeSureCabinetExists(cabinetKey);
            // 确认用户有权限查看指定的工具柜。
            operateHandlerValidator.makeSureUserInspectPermittedForCabinet(operateUserKey, cabinetKey);

            // 构造收藏实体主键。
            FavoriteKey favoriteKey = new FavoriteKey(cabinetKey.getLongId(), operateUserKey.getStringId());

            // 如果主键对应的实体存在，则删除实体。
            if (favoriteMaintainService.exists(favoriteKey)) {
                favoriteMaintainService.delete(favoriteKey);
            }
            // 否则，插入实体。
            else {
                Favorite favorite = new Favorite(favoriteKey, "通过 toolhouse 服务创建/更新");
                favoriteMaintainService.insertOrUpdate(favorite);
            }
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
