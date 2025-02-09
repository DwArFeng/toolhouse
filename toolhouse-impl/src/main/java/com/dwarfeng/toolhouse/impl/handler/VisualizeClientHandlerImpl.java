package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.stack.bean.dto.PagingInfo;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.sdk.util.Constants;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.Session;
import com.dwarfeng.toolhouse.stack.bean.entity.Task;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerInfo;
import com.dwarfeng.toolhouse.stack.bean.key.VisualizerKey;
import com.dwarfeng.toolhouse.stack.handler.VisualizeClientHandler;
import com.dwarfeng.toolhouse.stack.handler.Visualizer;
import com.dwarfeng.toolhouse.stack.handler.VisualizerHandler;
import com.dwarfeng.toolhouse.stack.service.SessionMaintainService;
import com.dwarfeng.toolhouse.stack.service.TaskMaintainService;
import com.dwarfeng.toolhouse.stack.service.VisualizerInfoMaintainService;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class VisualizeClientHandlerImpl implements VisualizeClientHandler {

    private final ApplicationContext ctx;

    private final SessionMaintainService sessionMaintainService;
    private final VisualizerInfoMaintainService visualizerInfoMaintainService;
    private final TaskMaintainService taskMaintainService;

    private final VisualizerHandler visualizerHandler;

    private final HandlerValidator handlerValidator;

    public VisualizeClientHandlerImpl(
            ApplicationContext ctx,
            SessionMaintainService sessionMaintainService,
            VisualizerInfoMaintainService visualizerInfoMaintainService,
            TaskMaintainService taskMaintainService,
            VisualizerHandler visualizerHandler,
            HandlerValidator handlerValidator
    ) {
        this.ctx = ctx;
        this.sessionMaintainService = sessionMaintainService;
        this.visualizerInfoMaintainService = visualizerInfoMaintainService;
        this.taskMaintainService = taskMaintainService;
        this.visualizerHandler = visualizerHandler;
        this.handlerValidator = handlerValidator;
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public VisualizerCallResult manualVisualizerCall(StringIdKey operateUserKey, VisualizerManualCallInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认工具存在。
            Session session = sessionMaintainService.get(sessionKey);
            LongIdKey toolKey = session.getToolKey();
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户一致。
            handlerValidator.makeSureUserConsists(session.getUserKey(), operateUserKey);

            // 根据 VisualizerManualCallInfo 构造 VisualizerSystemCallInfo。
            VisualizerSystemCallInfo visualizerSystemCallInfo = new VisualizerSystemCallInfo(
                    sessionKey, info.getVisualizerStringId(),
                    info.getFunctionName(), info.getFunctionDescription(),
                    info.getRequestText(), info.getRequestTextDescription()
            );

            // 调用可视化器。
            return visualizerCall0(toolKey, visualizerSystemCallInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public VisualizerStreamCallResult manualVisualizerStreamCall(
            StringIdKey operateUserKey, VisualizerManualStreamCallInfo info
    ) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认工具存在。
            Session session = sessionMaintainService.get(sessionKey);
            LongIdKey toolKey = session.getToolKey();
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户一致。
            handlerValidator.makeSureUserConsists(session.getUserKey(), operateUserKey);

            // 根据 VisualizerManualStreamCallInfo 构造 VisualizerSystemStreamCallInfo。
            VisualizerSystemStreamCallInfo visualizerSystemStreamCallInfo = new VisualizerSystemStreamCallInfo(
                    sessionKey, info.getVisualizerStringId(),
                    info.getFunctionName(), info.getFunctionDescription(),
                    info.getRequestText(), info.getRequestTextDescription(),
                    info.getRequestStream(), info.getRequestStreamDescription()
            );

            // 调用可视化器。
            return visualizerStreamCall0(toolKey, visualizerSystemStreamCallInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public VisualizerCallResult overrideVisualizerCall(StringIdKey operateUserKey, VisualizerOverrideCallInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认工具存在。
            Session session = sessionMaintainService.get(sessionKey);
            LongIdKey toolKey = session.getToolKey();
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, toolKey);

            // 根据 VisualizerOverrideCallInfo 构造 VisualizerSystemCallInfo。
            VisualizerSystemCallInfo visualizerSystemCallInfo = new VisualizerSystemCallInfo(
                    sessionKey, info.getVisualizerStringId(),
                    info.getFunctionName(), info.getFunctionDescription(),
                    info.getRequestText(), info.getRequestTextDescription()
            );

            // 调用可视化器。
            return visualizerCall0(toolKey, visualizerSystemCallInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    @Override
    public VisualizerStreamCallResult overrideVisualizerStreamCall(
            StringIdKey operateUserKey, VisualizerOverrideStreamCallInfo info
    ) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认用户存在。
            handlerValidator.makeSureUserExists(operateUserKey);
            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认工具存在。
            Session session = sessionMaintainService.get(sessionKey);
            LongIdKey toolKey = session.getToolKey();
            handlerValidator.makeSureToolExists(toolKey);
            // 确认用户有权限操作指定的工具。
            handlerValidator.makeSureUserModifyPermittedForTool(operateUserKey, toolKey);

            // 根据 VisualizerOverrideStreamCallInfo 构造 VisualizerSystemStreamCallInfo。
            VisualizerSystemStreamCallInfo visualizerSystemStreamCallInfo = new VisualizerSystemStreamCallInfo(
                    sessionKey, info.getVisualizerStringId(),
                    info.getFunctionName(), info.getFunctionDescription(),
                    info.getRequestText(), info.getRequestTextDescription(),
                    info.getRequestStream(), info.getRequestStreamDescription()
            );

            // 调用可视化器。
            return visualizerStreamCall0(toolKey, visualizerSystemStreamCallInfo);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public VisualizerCallResult systemVisualizerCall(VisualizerSystemCallInfo info) throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认工具存在。
            Session session = sessionMaintainService.get(sessionKey);
            LongIdKey toolKey = session.getToolKey();
            handlerValidator.makeSureToolExists(toolKey);

            // 调用可视化器。
            return visualizerCall0(toolKey, info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @Override
    public VisualizerStreamCallResult systemVisualizerStreamCall(VisualizerSystemStreamCallInfo info)
            throws HandlerException {
        try {
            // 展开参数。
            LongIdKey sessionKey = info.getSessionKey();

            // 确认会话存在。
            handlerValidator.makeSureSessionExists(sessionKey);
            // 确认工具存在。
            Session session = sessionMaintainService.get(sessionKey);
            LongIdKey toolKey = session.getToolKey();
            handlerValidator.makeSureToolExists(toolKey);

            // 调用可视化器。
            return visualizerStreamCall0(toolKey, info);
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    @SuppressWarnings("DuplicatedCode")
    private VisualizerCallResult visualizerCall0(LongIdKey toolKey, VisualizerSystemCallInfo info) throws Exception {
        // 根据 toolKey 以及 info 获取相应的 VisualizerInfo。
        VisualizerKey visualizerKey = new VisualizerKey(toolKey.getLongId(), info.getVisualizerStringId());
        handlerValidator.makeSureVisualizerInfoExists(visualizerKey);
        VisualizerInfo visualizerInfo = visualizerInfoMaintainService.get(visualizerKey);

        // 根据 visualizerInfo 获取相应的 Visualizer。
        Visualizer visualizer = visualizerHandler.find(visualizerInfo.getType());

        // 构造 Visualizer.CallerContext。
        Visualizer.CallerContext callerContext = ctx.getBean(
                InternalVisualizerCallContext.class,
                visualizerInfo, toolKey, info.getSessionKey(),
                taskMaintainService
        );

        // 调用 Visualizer。
        return visualizer.handleCall(callerContext, info);
    }

    @SuppressWarnings("DuplicatedCode")
    private VisualizerStreamCallResult visualizerStreamCall0(LongIdKey toolKey, VisualizerSystemStreamCallInfo info)
            throws Exception {
        // 根据 toolKey 以及 info 获取相应的 VisualizerInfo。
        VisualizerKey visualizerKey = new VisualizerKey(toolKey.getLongId(), info.getVisualizerStringId());
        handlerValidator.makeSureVisualizerInfoExists(visualizerKey);
        VisualizerInfo visualizerInfo = visualizerInfoMaintainService.get(visualizerKey);

        // 根据 visualizerInfo 获取相应的 Visualizer。
        Visualizer visualizer = visualizerHandler.find(visualizerInfo.getType());

        // 构造 Visualizer.CallerContext。
        Visualizer.CallerContext callerContext = ctx.getBean(
                InternalVisualizerCallContext.class,
                visualizerInfo, toolKey, info.getSessionKey(),
                taskMaintainService
        );

        // 调用 Visualizer。
        return visualizer.handleStreamCall(callerContext, info);
    }

    /**
     * 可视化器调用者上下文的内部实现。
     *
     * @author DwArFeng
     * @since beta-1.0.0
     */
    @Component
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public static class InternalVisualizerCallContext implements Visualizer.CallerContext {

        private final VisualizerInfo visualizerInfo;
        private final LongIdKey toolKey;
        private final LongIdKey sessionKey;

        private final TaskMaintainService taskMaintainService;

        public InternalVisualizerCallContext(
                VisualizerInfo visualizerInfo,
                LongIdKey toolKey,
                LongIdKey sessionKey,
                TaskMaintainService taskMaintainService
        ) {
            this.visualizerInfo = visualizerInfo;
            this.toolKey = toolKey;
            this.sessionKey = sessionKey;
            this.taskMaintainService = taskMaintainService;
        }

        @Override
        public VisualizerInfo getVisualizerInfo() {
            return visualizerInfo;
        }

        @Override
        public LongIdKey getToolKey() {
            return toolKey;
        }

        @Override
        public LongIdKey getSessionKey() {
            return sessionKey;
        }

        @Nullable
        @Override
        public Task latestTask() throws Exception {
            return taskMaintainService.lookupFirst(
                    TaskMaintainService.CHILD_FOR_SESSION_CREATE_DATE_DESC, new Object[]{sessionKey}
            );
        }

        @Nullable
        @Override
        public Task latestCreatedTask() throws Exception {
            Set<Integer> statuses = new HashSet<>();
            statuses.add(Constants.TASK_STATUS_CREATED);
            return taskMaintainService.lookupFirst(
                    TaskMaintainService.CHILD_FOR_SESSION_STATUS_IN_CREATE_DATE_DESC,
                    new Object[]{sessionKey, statuses}
            );
        }

        @Override
        public List<Task> latestTasks() throws Exception {
            return taskMaintainService.lookupAsList(
                    TaskMaintainService.CHILD_FOR_SESSION_CREATE_DATE_DESC, new Object[]{sessionKey}
            );
        }

        @Override
        public List<Task> latestTasks(PagingInfo pagingInfo) throws Exception {
            return taskMaintainService.lookupAsList(
                    TaskMaintainService.CHILD_FOR_SESSION_CREATE_DATE_DESC, new Object[]{sessionKey}, pagingInfo
            );
        }
    }
}
