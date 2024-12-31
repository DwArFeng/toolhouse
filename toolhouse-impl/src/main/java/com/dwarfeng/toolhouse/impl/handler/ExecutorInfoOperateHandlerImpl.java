package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoCreateInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoRemoveInfo;
import com.dwarfeng.toolhouse.stack.bean.dto.ExecutorInfoUpdateInfo;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorInfo;
import com.dwarfeng.toolhouse.stack.bean.key.ExecutorKey;
import com.dwarfeng.toolhouse.stack.handler.ExecutorInfoOperateHandler;
import com.dwarfeng.toolhouse.stack.service.ExecutorInfoMaintainService;
import org.springframework.stereotype.Component;

@Component
public class ExecutorInfoOperateHandlerImpl implements ExecutorInfoOperateHandler {

    private final ExecutorInfoMaintainService executorInfoMaintainService;

    private final HandlerValidator handlerValidator;

    public ExecutorInfoOperateHandlerImpl(
            ExecutorInfoMaintainService executorInfoMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.executorInfoMaintainService = executorInfoMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void create(StringIdKey operateUserKey, ExecutorInfoCreateInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey toolKey = info.getToolKey();
            String executorStringId = info.getExecutorStringId();
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

            // 根据 info 以及创建的规则组合 执行器信息 实体。
            ExecutorInfo executorInfo = new ExecutorInfo(
                    new ExecutorKey(toolKey.getLongId(), executorStringId), enabled, type, param, remark
            );

            // 插入执行器信息实体。
            executorInfoMaintainService.insert(executorInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void update(StringIdKey operateUserKey, ExecutorInfoUpdateInfo info) throws HandlerException {
        try {
            // 展开参数。
            ExecutorKey executorKey = info.getExecutorKey();
            boolean enabled = info.isEnabled();
            String type = info.getType();
            String param = info.getParam();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认执行器信息存在。
            handlerValidator.makeSureExecutorInfoExists(executorKey);
            // 确认工具存在。
            LongIdKey toolKey = new LongIdKey(executorKey.getToolLongId());
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, toolKey);

            // 根据 info 以及更新的规则设置 执行器信息 实体。
            ExecutorInfo executorInfo = executorInfoMaintainService.get(executorKey);
            executorInfo.setEnabled(enabled);
            executorInfo.setType(type);
            executorInfo.setParam(param);
            executorInfo.setRemark(remark);

            // 更新执行器信息实体。
            executorInfoMaintainService.update(executorInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void remove(StringIdKey operateUserKey, ExecutorInfoRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            ExecutorKey executorKey = info.getExecutorKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认执行器信息存在。
            handlerValidator.makeSureExecutorInfoExists(executorKey);
            // 确认工具存在。
            LongIdKey toolKey = new LongIdKey(executorKey.getToolLongId());
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, toolKey);

            // 删除执行器信息实体。
            executorInfoMaintainService.delete(executorKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
