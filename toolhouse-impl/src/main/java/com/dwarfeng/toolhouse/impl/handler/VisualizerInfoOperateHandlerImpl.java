package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.VisualizerInfoUpdateInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;
import com.dwarfeng.toolhouse.stack.handler.VisualizerInfoOperateHandler;
import com.dwarfeng.toolhouse.stack.service.VisualizerInfoMaintainService;
import org.springframework.stereotype.Component;

@Component
public class VisualizerInfoOperateHandlerImpl implements VisualizerInfoOperateHandler {

    private final VisualizerInfoMaintainService visualizerInfoMaintainService;

    private final HandlerValidator handlerValidator;

    public VisualizerInfoOperateHandlerImpl(
            VisualizerInfoMaintainService visualizerInfoMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.visualizerInfoMaintainService = visualizerInfoMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void create(StringIdKey operateUserKey, VisualizerInfoCreateInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey toolKey = info.getToolKey();
            String visualizerStringId = info.getVisualizerStringId();
            boolean enabled = info.isEnabled();
            String type = info.getType();
            String param = info.getParam();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认工具存在。
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, toolKey);

            // 根据 info 以及创建的规则组合 可视化器信息 实体。
            VisualizerInfo visualizerInfo = new VisualizerInfo(
                    new VisualizerKey(toolKey.getLongId(), visualizerStringId), enabled, type, param, remark
            );

            // 插入可视化器信息实体。
            visualizerInfoMaintainService.insert(visualizerInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void update(StringIdKey operateUserKey, VisualizerInfoUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            VisualizerKey visualizerKey = info.getVisualizerKey();
            boolean enabled = info.isEnabled();
            String type = info.getType();
            String param = info.getParam();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认可视化器信息存在。
            handlerValidator.makeSureVisualizerInfoExists(visualizerKey);
            // 确认工具存在。
            LongIdKey toolKey = new LongIdKey(visualizerKey.getToolLongId());
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, toolKey);

            // 根据 info 以及更新的规则设置 可视化器信息 实体。
            VisualizerInfo visualizerInfo = visualizerInfoMaintainService.get(visualizerKey);
            visualizerInfo.setEnabled(enabled);
            visualizerInfo.setType(type);
            visualizerInfo.setParam(param);
            visualizerInfo.setRemark(remark);

            // 更新可视化器信息实体。
            visualizerInfoMaintainService.update(visualizerInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void remove(StringIdKey operateUserKey, VisualizerInfoRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            VisualizerKey visualizerKey = info.getVisualizerKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认可视化器信息存在。
            handlerValidator.makeSureVisualizerInfoExists(visualizerKey);
            // 确认工具存在。
            LongIdKey toolKey = new LongIdKey(visualizerKey.getToolLongId());
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, toolKey);

            // 删除可视化器信息实体。
            visualizerInfoMaintainService.delete(visualizerKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
