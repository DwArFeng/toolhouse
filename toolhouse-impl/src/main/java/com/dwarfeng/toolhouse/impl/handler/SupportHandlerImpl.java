package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.sdk.exception.HandlerExceptionHelper;
import com.dwarfeng.subgrade.sdk.interceptor.analyse.BehaviorAnalyse;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.sdk.handler.ExecutorSupporter;
import com.dwarfeng.toolhouse.sdk.handler.VisualizerSupporter;
import com.dwarfeng.toolhouse.stack.bean.entity.ExecutorSupport;
import com.dwarfeng.toolhouse.stack.bean.entity.VisualizerSupport;
import com.dwarfeng.toolhouse.stack.handler.SupportHandler;
import com.dwarfeng.toolhouse.stack.service.ExecutorSupportMaintainService;
import com.dwarfeng.toolhouse.stack.service.VisualizerSupportMaintainService;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 支持处理器实现。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Component
public class SupportHandlerImpl implements SupportHandler {

    private final ExecutorSupportMaintainService executorSupportMaintainService;
    private final VisualizerSupportMaintainService visualizerSupportMaintainService;

    private final List<ExecutorSupporter> executorSupporters;
    private final List<VisualizerSupporter> visualizerSupporters;

    public SupportHandlerImpl(
            ExecutorSupportMaintainService executorSupportMaintainService,
            VisualizerSupportMaintainService visualizerSupportMaintainService,
            List<ExecutorSupporter> executorSupporters,
            List<VisualizerSupporter> visualizerSupporters
    ) {
        this.executorSupportMaintainService = executorSupportMaintainService;
        this.visualizerSupportMaintainService = visualizerSupportMaintainService;
        this.executorSupporters = Optional.ofNullable(executorSupporters).orElse(Collections.emptyList());
        this.visualizerSupporters = Optional.ofNullable(visualizerSupporters).orElse(Collections.emptyList());
    }

    @Override
    @BehaviorAnalyse
    public void resetExecutor() throws HandlerException {
        try {
            doResetExecutor();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetExecutor() throws Exception {
        List<StringIdKey> executorKeys = executorSupportMaintainService.lookupAsList().stream()
                .map(ExecutorSupport::getKey).collect(Collectors.toList());
        executorSupportMaintainService.batchDelete(executorKeys);
        List<ExecutorSupport> executorSupports = executorSupporters.stream().map(
                supporter -> new ExecutorSupport(
                        new StringIdKey(supporter.provideType()),
                        supporter.provideLabel(),
                        supporter.provideDescription(),
                        supporter.provideExampleParam()
                )
        ).collect(Collectors.toList());
        executorSupportMaintainService.batchInsert(executorSupports);
    }

    @Override
    @BehaviorAnalyse
    public void resetVisualizer() throws HandlerException {
        try {
            doResetVisualizer();
        } catch (Exception e) {
            throw HandlerExceptionHelper.parse(e);
        }
    }

    private void doResetVisualizer() throws Exception {
        List<StringIdKey> visualizerKeys = visualizerSupportMaintainService.lookupAsList().stream()
                .map(VisualizerSupport::getKey).collect(Collectors.toList());
        visualizerSupportMaintainService.batchDelete(visualizerKeys);
        List<VisualizerSupport> visualizerSupports = visualizerSupporters.stream().map(
                supporter -> new VisualizerSupport(
                        new StringIdKey(supporter.provideType()),
                        supporter.provideLabel(),
                        supporter.provideDescription(),
                        supporter.provideExampleParam()
                )
        ).collect(Collectors.toList());
        visualizerSupportMaintainService.batchInsert(visualizerSupports);
    }
}
