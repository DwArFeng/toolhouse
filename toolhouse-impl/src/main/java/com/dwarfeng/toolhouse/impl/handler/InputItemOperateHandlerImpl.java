package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.InputItem;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;
import com.dwarfeng.toolhouse.stack.bean.key.TaskItemKey;
import com.dwarfeng.toolhouse.stack.handler.InputItemOperateHandler;
import com.dwarfeng.toolhouse.stack.service.InputItemMaintainService;
import com.dwarfeng.toolhouse.stack.service.TaskMaintainService;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.Date;

@Component
public class InputItemOperateHandlerImpl implements InputItemOperateHandler {

    private final InputItemMaintainService inputItemMaintainService;
    private final TaskMaintainService taskMaintainService;

    private final HandlerValidator handlerValidator;

    public InputItemOperateHandlerImpl(
            InputItemMaintainService inputItemMaintainService,
            TaskMaintainService taskMaintainService,
            HandlerValidator handlerValidator
    ) {
        this.inputItemMaintainService = inputItemMaintainService;
        this.taskMaintainService = taskMaintainService;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void manualUpsert(StringIdKey operateUserKey, InputItemManualUpsertInfo info) throws HandlerException {
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

            // 根据 info 创建 InputItem。
            InputItem inputItem = new InputItem();
            inputItem.setKey(new TaskItemKey(taskKey.getLongId(), itemStringId));
            inputItem.setToolKey(task.getToolKey());
            inputItem.setUserKey(task.getUserKey());
            inputItem.setType(type);
            inputItem.setRemark(remark);
            setValue(inputItem, type, stringValue, longValue, doubleValue, booleanValue, dateValue, fileRef);

            // 调用维护服务插入或更新输入项。
            inputItemMaintainService.insertOrUpdate(inputItem);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void manualRemove(StringIdKey operateUserKey, InputItemManualRemoveInfo info) throws HandlerException {
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

            // 调用维护服务删除输入项（如果存在）。
            inputItemMaintainService.deleteIfExists(taskItemKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void overrideUpsert(StringIdKey operateUserKey, InputItemOverrideUpsertInfo info) throws HandlerException {
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

            // 根据 info 创建 InputItem。
            InputItem inputItem = new InputItem();
            inputItem.setKey(new TaskItemKey(taskKey.getLongId(), itemStringId));
            inputItem.setToolKey(task.getToolKey());
            inputItem.setUserKey(task.getUserKey());
            inputItem.setType(type);
            inputItem.setRemark(remark);
            setValue(inputItem, type, stringValue, longValue, doubleValue, booleanValue, dateValue, fileRef);

            // 调用维护服务插入或更新输入项。
            inputItemMaintainService.insertOrUpdate(inputItem);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void overrideRemove(StringIdKey operateUserKey, InputItemOverrideRemoveInfo info) throws HandlerException {
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

            // 调用维护服务删除输入项（如果存在）。
            inputItemMaintainService.deleteIfExists(taskItemKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Nullable
    @Override
    public InputItemInspectResult systemInspect(InputItemSystemInspectInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String itemStringId = info.getItemStringId();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);

            // 根据 info 构造 TaskItemKey。
            TaskItemKey taskItemKey = new TaskItemKey(taskKey.getLongId(), itemStringId);

            // 调用维护服务获取输入项。
            InputItem inputItem = inputItemMaintainService.getIfExists(taskItemKey);

            // 如果输入项不存在，则返回 null。
            if (inputItem == null) {
                return null;
            }

            // 构造 InputItemInspectResult 并返回。
            return new InputItemInspectResult(
                    taskKey, itemStringId, inputItem.getType(), inputItem.getStringValue(), inputItem.getLongValue(),
                    inputItem.getDoubleValue(), inputItem.getBooleanValue(), inputItem.getDateValue(),
                    inputItem.getFileRef(), inputItem.getRemark()
            );
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void systemUpsert(InputItemSystemUpsertInfo info) throws HandlerException {
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

            // 根据 info 创建 InputItem。
            InputItem inputItem = new InputItem();
            inputItem.setKey(new TaskItemKey(taskKey.getLongId(), itemStringId));
            inputItem.setToolKey(taskMaintainService.get(taskKey).getToolKey());
            inputItem.setUserKey(taskMaintainService.get(taskKey).getUserKey());
            inputItem.setType(type);
            inputItem.setRemark(remark);
            setValue(inputItem, type, stringValue, longValue, doubleValue, booleanValue, dateValue, fileRef);

            // 调用维护服务插入或更新输入项。
            inputItemMaintainService.insertOrUpdate(inputItem);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public void systemRemove(InputItemSystemRemoveInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String itemStringId = info.getItemStringId();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);

            // 根据 info 构造 TaskItemKey。
            TaskItemKey taskItemKey = new TaskItemKey(taskKey.getLongId(), itemStringId);

            // 调用维护服务删除输入项（如果存在）。
            inputItemMaintainService.deleteIfExists(taskItemKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private void setValue(
            InputItem inputItem, int type, String stringValue, Long longValue, Double doubleValue, Boolean booleanValue,
            Date dateValue, Long fileRef
    ) {
        switch (type) {
            case Constants.TASK_ITEM_TYPE_STRING:
                inputItem.setStringValue(stringValue);
                break;
            case Constants.TASK_ITEM_TYPE_LONG:
                inputItem.setLongValue(longValue);
                break;
            case Constants.TASK_ITEM_TYPE_DOUBLE:
                inputItem.setDoubleValue(doubleValue);
                break;
            case Constants.TASK_ITEM_TYPE_BOOLEAN:
                inputItem.setBooleanValue(booleanValue);
                break;
            case Constants.TASK_ITEM_TYPE_DATE:
                inputItem.setDateValue(dateValue);
                break;
            case Constants.TASK_ITEM_TYPE_FILE:
                inputItem.setFileRef(fileRef);
                break;
            default:
                throw new IllegalStateException("不应该执行到此处, 请联系开发人员");
        }
    }
}
