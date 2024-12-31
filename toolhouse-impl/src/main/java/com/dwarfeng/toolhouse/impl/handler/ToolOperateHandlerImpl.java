package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import com.dwarfeng.toolhouse.stack.bean.dto.ToolCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ToolUpdateInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.Cabinet;
import com.dwarfeng.toolhouse.stack.bean.entity.Tool;
import com.dwarfeng.toolhouse.stack.handler.ToolOperateHandler;
import com.dwarfeng.toolhouse.stack.service.CabinetMaintainService;
import com.dwarfeng.toolhouse.stack.service.ToolMaintainService;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Objects;

@Component
public class ToolOperateHandlerImpl implements ToolOperateHandler {

    private final ToolMaintainService toolMaintainService;
    private final CabinetMaintainService cabinetMaintainService;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final HandlerValidator handlerValidator;

    public ToolOperateHandlerImpl(
            ToolMaintainService toolMaintainService,
            CabinetMaintainService cabinetMaintainService,
            KeyGenerator<LongIdKey> keyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.toolMaintainService = toolMaintainService;
        this.cabinetMaintainService = cabinetMaintainService;
        this.keyGenerator = keyGenerator;
        this.handlerValidator = handlerValidator;
    }

    @Override
    public LongIdKey createTool(StringIdKey userKey, ToolCreateInfo toolCreateInfo) throws HandlerException {
        try {
            LongIdKey cabinetKey = toolCreateInfo.getCabinetKey();
            LongIdKey folderKey = toolCreateInfo.getFolderKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 确认工具柜存在。
            handlerValidator.makeSureCabinetExists(cabinetKey);

            // 确认文件夹存在。
            if (Objects.nonNull(folderKey)) {
                handlerValidator.makeSureFolderExists(folderKey);
            }

            // 确认用户有权限操作指定的文件夹。
            handlerValidator.makeSureUserModifyPermittedForCabinet(userKey, cabinetKey);

            // 确认文件夹与父文件夹的工具柜存在。
            handlerValidator.makeSureCabinetIdenticalForCabinet(folderKey, cabinetKey);

            // 分配主键。
            LongIdKey toolKey = keyGenerator.generate();

            // 根据 toolCreateInfo 以及创建的规则组合 工具 实体，并调用维护服务插入。
            Date currentDate = new Date();
            Tool tool = new Tool(
                    toolKey, folderKey, cabinetKey, toolCreateInfo.getName(), toolCreateInfo.getRemark(), currentDate
            );
            toolMaintainService.insert(tool);

            // 更新工具柜字段值，并调用维护服务进行更新。
            Cabinet cabinet = cabinetMaintainService.get(cabinetKey);
            cabinet.setToolCount(cabinet.getToolCount() + 1);

            cabinetMaintainService.update(cabinet);

            // 返回主键。
            return toolKey;
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void updateTool(StringIdKey userKey, ToolUpdateInfo toolUpdateInfo) throws HandlerException {
        try {
            LongIdKey toolKey = toolUpdateInfo.getKey();
            LongIdKey folderKey = toolUpdateInfo.getFolderKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 确认工具存在。
            handlerValidator.makeSureToolExists(toolKey);
            Tool tool = toolMaintainService.get(toolKey);
            LongIdKey oldFolderKey = tool.getFolderKey();

            // 确认工具柜存在。
            LongIdKey cabinetKey = tool.getCabinetKey();
            handlerValidator.makeSureCabinetExists(cabinetKey);

            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(userKey, toolKey);

            // 确认文件夹与父文件夹的工具柜存在。
            if (!Objects.equals(folderKey, oldFolderKey)) {
                handlerValidator.makeSureCabinetIdenticalForFolder(oldFolderKey, folderKey);
            }

            // 更新工具柜字段值，并调用维护服务进行更新。
            Cabinet cabinet = cabinetMaintainService.get(cabinetKey);
            cabinet.setToolCount(cabinet.getToolCount() + 1);
            cabinetMaintainService.update(cabinet);

            // 根据 toolUpdateInfo 以及更新的规则设置 工具 实体，并调用维护服务进行更新。
            tool.setName(toolUpdateInfo.getName());
            tool.setRemark(toolUpdateInfo.getRemark());
            if (!Objects.equals(folderKey, oldFolderKey)) {
                tool.setFolderKey(folderKey);
            }
            toolMaintainService.update(tool);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void removeTool(StringIdKey userKey, LongIdKey toolKey) throws HandlerException {
        try {
            // 确认用户存在。
            handlerValidator.makeSureUserExists(userKey);

            // 确认工具存在。
            handlerValidator.makeSureToolExists(toolKey);
            Tool tool = toolMaintainService.get(toolKey);
            LongIdKey cabinetKey = tool.getCabinetKey();

            // 确认工具柜存在。
            handlerValidator.makeSureCabinetExists(cabinetKey);

            // 确认用户有权限操作指定的银行卡。
            handlerValidator.makeSureUserModifyPermittedForTool(userKey, toolKey);

            // 存在删除指定的工具。
            toolMaintainService.delete(toolKey);

            // 更新工具柜字段值，并调用维护服务进行更新。
            Cabinet cabinet = cabinetMaintainService.get(cabinetKey);
            cabinet.setToolCount(Math.max(cabinet.getToolCount() - 1, 0));
            cabinetMaintainService.update(cabinet);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
