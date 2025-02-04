package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.OutputItem;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import com.dwarfeng.toolhouse.stack.handler.OutputItemOperateHandler;
import com.dwarfeng.toolhouse.stack.service.OutputItemMaintainService;
import com.dwarfeng.toolhouse.stack.service.TaskMaintainService;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Date;

@Component
public class OutputItemOperateHandlerImpl implements OutputItemOperateHandler {

    private final OutputItemMaintainService outputItemMaintainService;
    private final TaskMaintainService taskMaintainService;

    private final HandlerValidator handlerValidator;

    public OutputItemOperateHandlerImpl(
            OutputItemMaintainService outputItemMaintainService,
            TaskMaintainService taskMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.outputItemMaintainService = outputItemMaintainService;
        this.taskMaintainService = taskMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void manualUpsert(StringIdKey operateUserKey, OutputItemManualUpsertInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String itemStringId = info.getItemStringId();
            int type = info.getType();
            String stringValue = info.getStringValue();
            Long longValue = info.getLongValue();
            Double doubleValue = info.getDoubleValue();
            Boolean booleanValue = info.getBooleanValue();
            Date dateValue = info.getDateValue();
            Long fileRef = info.getFileRef();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认用户一致。
            Task task = taskMaintainService.get(taskKey);
            handlerValidator.makeSureUserConsists(task.getUserKey(), operateUserKey);
            // 确认类型合法。
            handlerValidator.makeSureTaskItemTypeValid(type);

            // 根据 info 创建 OutputItem。
            OutputItem outputItem = new OutputItem();
            outputItem.setKey(new TaskItemKey(taskKey.getLongId(), itemStringId));
            outputItem.setToolKey(task.getToolKey());
            outputItem.setUserKey(task.getUserKey());
            outputItem.setType(type);
            outputItem.setRemark(remark);
            setValue(outputItem, type, stringValue, longValue, doubleValue, booleanValue, dateValue, fileRef);

            // 调用维护服务插入或更新输出项。
            outputItemMaintainService.insertOrUpdate(outputItem);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void manualRemove(StringIdKey operateUserKey, OutputItemManualRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String itemStringId = info.getItemStringId();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认用户一致。
            Task task = taskMaintainService.get(taskKey);
            handlerValidator.makeSureUserConsists(task.getUserKey(), operateUserKey);

            // 根据 info 构造 TaskItemKey。
            TaskItemKey taskItemKey = new TaskItemKey(taskKey.getLongId(), itemStringId);

            // 调用维护服务删除输出项（如果存在）。
            outputItemMaintainService.deleteIfExists(taskItemKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void overrideUpsert(StringIdKey operateUserKey, OutputItemOverrideUpsertInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String itemStringId = info.getItemStringId();
            int type = info.getType();
            String stringValue = info.getStringValue();
            Long longValue = info.getLongValue();
            Double doubleValue = info.getDoubleValue();
            Boolean booleanValue = info.getBooleanValue();
            Date dateValue = info.getDateValue();
            Long fileRef = info.getFileRef();
            String remark = info.getRemark();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认用户有权限操作指定的工具。
            Task task = taskMaintainService.get(taskKey);
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, task.getToolKey());

            // 根据 info 创建 OutputItem。
            OutputItem outputItem = new OutputItem();
            outputItem.setKey(new TaskItemKey(taskKey.getLongId(), itemStringId));
            outputItem.setToolKey(task.getToolKey());
            outputItem.setUserKey(task.getUserKey());
            outputItem.setType(type);
            outputItem.setRemark(remark);
            setValue(outputItem, type, stringValue, longValue, doubleValue, booleanValue, dateValue, fileRef);

            // 调用维护服务插入或更新输出项。
            outputItemMaintainService.insertOrUpdate(outputItem);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void overrideRemove(StringIdKey operateUserKey, OutputItemOverrideRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String itemStringId = info.getItemStringId();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认用户有权限操作指定的工具。
            Task task = taskMaintainService.get(taskKey);
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, task.getToolKey());

            // 根据 info 构造 TaskItemKey。
            TaskItemKey taskItemKey = new TaskItemKey(taskKey.getLongId(), itemStringId);

            // 调用维护服务删除输出项（如果存在）。
            outputItemMaintainService.deleteIfExists(taskItemKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Nullable
    @Override
    public OutputItemInspectResult systemInspect(OutputItemSystemInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String itemStringId = info.getItemStringId();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);

            // 根据 info 构造 TaskItemKey。
            TaskItemKey taskItemKey = new TaskItemKey(taskKey.getLongId(), itemStringId);

            // 调用维护服务获取输出项。
            OutputItem outputItem = outputItemMaintainService.getIfExists(taskItemKey);

            // 如果输出项不存在，则返回 null。
            if (outputItem == null) {
                return null;
            }

            // 构造 OutputItemInspectResult 并返回。
            return new OutputItemInspectResult(
                    taskKey, itemStringId, outputItem.getType(), outputItem.getStringValue(), outputItem.getLongValue(),
                    outputItem.getDoubleValue(), outputItem.getBooleanValue(), outputItem.getDateValue(),
                    outputItem.getFileRef(), outputItem.getRemark()
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void systemUpsert(OutputItemSystemUpsertInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String itemStringId = info.getItemStringId();
            int type = info.getType();
            String stringValue = info.getStringValue();
            Long longValue = info.getLongValue();
            Double doubleValue = info.getDoubleValue();
            Boolean booleanValue = info.getBooleanValue();
            Date dateValue = info.getDateValue();
            Long fileRef = info.getFileRef();
            String remark = info.getRemark();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);

            // 根据 info 创建 OutputItem。
            OutputItem outputItem = new OutputItem();
            outputItem.setKey(new TaskItemKey(taskKey.getLongId(), itemStringId));
            outputItem.setToolKey(taskMaintainService.get(taskKey).getToolKey());
            outputItem.setUserKey(taskMaintainService.get(taskKey).getUserKey());
            outputItem.setType(type);
            outputItem.setRemark(remark);
            setValue(outputItem, type, stringValue, longValue, doubleValue, booleanValue, dateValue, fileRef);

            // 调用维护服务插入或更新输出项。
            outputItemMaintainService.insertOrUpdate(outputItem);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void systemRemove(OutputItemSystemRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String itemStringId = info.getItemStringId();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);

            // 根据 info 构造 TaskItemKey。
            TaskItemKey taskItemKey = new TaskItemKey(taskKey.getLongId(), itemStringId);

            // 调用维护服务删除输出项（如果存在）。
            outputItemMaintainService.deleteIfExists(taskItemKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void setValue(
            OutputItem outputItem, int type, String stringValue, Long longValue, Double doubleValue, Boolean booleanValue,
            Date dateValue, Long fileRef
    ) {
        switch (type) {
            case Constants.TASK_ITEM_TYPE_STRING:
                outputItem.setStringValue(stringValue);
                break;
            case Constants.TASK_ITEM_TYPE_LONG:
                outputItem.setLongValue(longValue);
                break;
            case Constants.TASK_ITEM_TYPE_DOUBLE:
                outputItem.setDoubleValue(doubleValue);
                break;
            case Constants.TASK_ITEM_TYPE_BOOLEAN:
                outputItem.setBooleanValue(booleanValue);
                break;
            case Constants.TASK_ITEM_TYPE_DATE:
                outputItem.setDateValue(dateValue);
                break;
            case Constants.TASK_ITEM_TYPE_FILE:
                outputItem.setFileRef(fileRef);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }
}
