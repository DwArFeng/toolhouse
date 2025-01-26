package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.subgrade.stack.generation.KeyGenerator;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;
import com.dwarfeng.toolhouse.stack.handler.TaskOperateHandler;
import com.dwarfeng.toolhouse.stack.service.SessionMaintainService;
import com.dwarfeng.toolhouse.stack.service.TaskMaintainService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
public class TaskOperateHandlerImpl implements TaskOperateHandler {

    private static final Set<Integer> VALID_TASK_STATUS_SET_START;
    private static final Set<Integer> VALID_TASK_STATUS_SET_FINISH;
    private static final Set<Integer> VALID_TASK_STATUS_SET_FAIL;
    private static final Set<Integer> VALID_TASK_STATUS_SET_EXPIRE;
    private static final Set<Integer> VALID_TASK_STATUS_SET_DEAD;
    private static final Set<Integer> VALID_TASK_STATUS_SET_UPDATE_MODAL;
    private static final Set<Integer> VALID_TASK_STATUS_SET_BEAT;

    static {
        Set<Integer> VALID_TASK_STATUS_SET_START_DEJA_VU = new HashSet<>();
        VALID_TASK_STATUS_SET_START_DEJA_VU.add(Constants.TASK_STATUS_CREATED);
        VALID_TASK_STATUS_SET_START = Collections.unmodifiableSet(VALID_TASK_STATUS_SET_START_DEJA_VU);

        Set<Integer> VALID_TASK_STATUS_SET_FINISH_DEJA_VU = new HashSet<>();
        VALID_TASK_STATUS_SET_FINISH_DEJA_VU.add(Constants.TASK_STATUS_CREATED);
        VALID_TASK_STATUS_SET_FINISH_DEJA_VU.add(Constants.TASK_STATUS_PROCESSING);
        VALID_TASK_STATUS_SET_FINISH = Collections.unmodifiableSet(VALID_TASK_STATUS_SET_FINISH_DEJA_VU);

        Set<Integer> VALID_TASK_STATUS_SET_FAIL_DEJA_VU = new HashSet<>();
        VALID_TASK_STATUS_SET_FAIL_DEJA_VU.add(Constants.TASK_STATUS_CREATED);
        VALID_TASK_STATUS_SET_FAIL_DEJA_VU.add(Constants.TASK_STATUS_PROCESSING);
        VALID_TASK_STATUS_SET_FAIL = Collections.unmodifiableSet(VALID_TASK_STATUS_SET_FAIL_DEJA_VU);

        Set<Integer> VALID_TASK_STATUS_SET_EXPIRE_DEJA_VU = new HashSet<>();
        VALID_TASK_STATUS_SET_EXPIRE_DEJA_VU.add(Constants.TASK_STATUS_CREATED);
        VALID_TASK_STATUS_SET_EXPIRE_DEJA_VU.add(Constants.TASK_STATUS_PROCESSING);
        VALID_TASK_STATUS_SET_EXPIRE = Collections.unmodifiableSet(VALID_TASK_STATUS_SET_EXPIRE_DEJA_VU);

        Set<Integer> VALID_TASK_STATUS_SET_DEAD_DEJA_VU = new HashSet<>();
        VALID_TASK_STATUS_SET_DEAD_DEJA_VU.add(Constants.TASK_STATUS_PROCESSING);
        VALID_TASK_STATUS_SET_DEAD = Collections.unmodifiableSet(VALID_TASK_STATUS_SET_DEAD_DEJA_VU);

        Set<Integer> VALID_TASK_STATUS_SET_UPDATE_MODAL_DEJA_VU = new HashSet<>();
        VALID_TASK_STATUS_SET_UPDATE_MODAL_DEJA_VU.add(Constants.TASK_STATUS_PROCESSING);
        VALID_TASK_STATUS_SET_UPDATE_MODAL = Collections.unmodifiableSet(VALID_TASK_STATUS_SET_UPDATE_MODAL_DEJA_VU);

        Set<Integer> VALID_TASK_STATUS_SET_BEAT_DEJA_VU = new HashSet<>();
        VALID_TASK_STATUS_SET_BEAT_DEJA_VU.add(Constants.TASK_STATUS_PROCESSING);
        VALID_TASK_STATUS_SET_BEAT = Collections.unmodifiableSet(VALID_TASK_STATUS_SET_BEAT_DEJA_VU);
    }

    private final TaskMaintainService taskMaintainService;
    private final SessionMaintainService sessionMaintainService;

    private final KeyGenerator<LongIdKey> keyGenerator;

    private final HandlerValidator handlerValidator;

    @Value("${task.expire_timeout}")
    private long expireTimeout;
    @Value("${task.die_timeout}")
    private long dieTimeout;

    public TaskOperateHandlerImpl(
            TaskMaintainService taskMaintainService,
            SessionMaintainService sessionMaintainService,
            KeyGenerator<LongIdKey> keyGenerator,
            HandlerValidator handlerValidator
    ) {
        this.taskMaintainService = taskMaintainService;
        this.sessionMaintainService = sessionMaintainService;
        this.keyGenerator = keyGenerator;
        this.handlerValidator = handlerValidator;
    }

    // 为了代码的可扩展性，此处代码不做简化处理。
    @SuppressWarnings({"DuplicatedCode", "ExtractMethodRecommender"})
    @Override
    public TaskCreateResult manualCreate(StringIdKey operateUserKey, TaskManualCreateInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户一致。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserConsists(session.getUserKey(), operateUserKey);

            // 根据 info 以及创建的规则组合 任务 实体。
            LongIdKey taskKey = keyGenerator.generate();
            Date currentDate = new Date();
            Task task = new Task(
                    taskKey,
                    sessionKey,
                    session.getToolKey(),
                    session.getUserKey(),
                    Constants.TASK_STATUS_CREATED,
                    currentDate,
                    null,
                    new Date(currentDate.getTime() + expireTimeout),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    "通过 toolhouse 操作处理器创建"
            );

            // 插入任务实体。
            taskMaintainService.insert(task);

            // 返回结果。
            return new TaskCreateResult(taskKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可扩展性，此处代码不做简化处理。
    @SuppressWarnings({"DuplicatedCode", "ExtractMethodRecommender"})
    @Override
    public TaskCreateResult overrideCreate(StringIdKey operateUserKey, TaskOverrideCreateInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认用户有权限查看指定的工具。
            Session session = sessionMaintainService.get(sessionKey);
            handlerValidator.makeSureUserInspectPermittedForTool(operateUserKey, session.getToolKey());

            // 根据 info 以及创建的规则组合 任务 实体。
            LongIdKey taskKey = keyGenerator.generate();
            Date currentDate = new Date();
            Task task = new Task(
                    taskKey,
                    sessionKey,
                    session.getToolKey(),
                    session.getUserKey(),
                    Constants.TASK_STATUS_CREATED,
                    currentDate,
                    null,
                    new Date(currentDate.getTime() + expireTimeout),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    "通过 toolhouse 操作处理器创建"
            );

            // 插入任务实体。
            taskMaintainService.insert(task);

            // 返回结果。
            return new TaskCreateResult(taskKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    // 为了代码的可扩展性，此处代码不做简化处理。
    @SuppressWarnings({"DuplicatedCode", "ExtractMethodRecommender"})
    @Override
    public TaskCreateResult systemCreate(TaskSystemCreateInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);

            // 获取会话实体。
            Session session = sessionMaintainService.get(sessionKey);

            // 根据 info 以及创建的规则组合 任务 实体。
            LongIdKey taskKey = keyGenerator.generate();
            Date currentDate = new Date();
            Task task = new Task(
                    taskKey,
                    sessionKey,
                    session.getToolKey(),
                    session.getUserKey(),
                    Constants.TASK_STATUS_CREATED,
                    currentDate,
                    null,
                    new Date(currentDate.getTime() + expireTimeout),
                    null,
                    null,
                    null,
                    null,
                    null,
                    null,
                    "通过 toolhouse 操作处理器创建"
            );

            // 插入任务实体。
            taskMaintainService.insert(task);

            // 返回结果。
            return new TaskCreateResult(taskKey);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemStart(TaskSystemStartInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认任务状态合法。
            handlerValidator.makeSureTaskStatusValid(taskKey, VALID_TASK_STATUS_SET_START);

            // 获取任务。
            Task task = taskMaintainService.get(taskKey);

            // 更新任务字段。
            Date currentDate = new Date();
            task.setStatus(Constants.TASK_STATUS_PROCESSING);
            task.setStartDate(currentDate);
            task.setShouldDieDate(new Date(currentDate.getTime() + dieTimeout));

            // 更新任务实体。
            taskMaintainService.update(task);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemFinish(TaskSystemFinishInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认任务状态合法。
            handlerValidator.makeSureTaskStatusValid(taskKey, VALID_TASK_STATUS_SET_FINISH);

            // 获取任务。
            Task task = taskMaintainService.get(taskKey);

            // 更新任务字段。
            Date currentDate = new Date();
            task.setStatus(Constants.TASK_STATUS_FINISHED);
            task.setEndDate(currentDate);
            task.setDuration(currentDate.getTime() - task.getStartDate().getTime());

            // 更新任务实体。
            taskMaintainService.update(task);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemFail(TaskSystemFailInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认任务状态合法。
            handlerValidator.makeSureTaskStatusValid(taskKey, VALID_TASK_STATUS_SET_FAIL);

            // 获取任务。
            Task task = taskMaintainService.get(taskKey);

            // 更新任务字段。
            Date currentDate = new Date();
            task.setStatus(Constants.TASK_STATUS_FAILED);
            task.setEndDate(currentDate);
            task.setDuration(currentDate.getTime() - task.getStartDate().getTime());

            // 更新任务实体。
            taskMaintainService.update(task);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemExpire(TaskSystemExpireInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认任务状态合法。
            handlerValidator.makeSureTaskStatusValid(taskKey, VALID_TASK_STATUS_SET_EXPIRE);

            // 获取任务。
            Task task = taskMaintainService.get(taskKey);

            // 更新任务字段。
            Date currentDate = new Date();
            task.setStatus(Constants.TASK_STATUS_EXPIRED);
            task.setEndDate(currentDate);
            task.setDuration(currentDate.getTime() - task.getStartDate().getTime());
            task.setExpireDate(currentDate);

            // 更新任务实体。
            taskMaintainService.update(task);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemUpdateModal(TaskSystemUpdateModalInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();
            String frontMessage = info.getFrontMessage();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认任务状态合法。
            handlerValidator.makeSureTaskStatusValid(taskKey, VALID_TASK_STATUS_SET_UPDATE_MODAL);

            // 获取任务。
            Task task = taskMaintainService.get(taskKey);

            // 更新任务字段。
            task.setFrontMessage(frontMessage);

            // 更新任务实体。
            taskMaintainService.update(task);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemDie(TaskSystemDieInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认任务状态合法。
            handlerValidator.makeSureTaskStatusValid(taskKey, VALID_TASK_STATUS_SET_DEAD);

            // 获取任务。
            Task task = taskMaintainService.get(taskKey);

            // 更新任务字段。
            Date currentDate = new Date();
            task.setStatus(Constants.TASK_STATUS_DIED);
            task.setEndDate(currentDate);
            task.setDuration(currentDate.getTime() - task.getStartDate().getTime());
            task.setDieDate(currentDate);

            // 更新任务实体。
            taskMaintainService.update(task);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public void systemBeat(TaskSystemBeatInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey taskKey = info.getTaskKey();

            // 确认任务存在。
            handlerValidator.makeSureTaskExists(taskKey);
            // 确认任务状态合法。
            handlerValidator.makeSureTaskStatusValid(taskKey, VALID_TASK_STATUS_SET_BEAT);

            // 获取任务。
            Task task = taskMaintainService.get(taskKey);

            // 更新任务字段。
            Date currentDate = new Date();
            task.setShouldDieDate(new Date(currentDate.getTime() + dieTimeout));

            // 更新任务实体。
            taskMaintainService.update(task);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }
}
