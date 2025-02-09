package com.dwarfeng.toolhouse.impl.handler;

import com.dwarfeng.subgrade.stack.exception.HandlerException;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.exception.UnsupportedVisualizerTypeException;
import com.dwarfeng.toolhouse.stack.handler.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Nullable;
import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class VisualizerHandlerImpl implements VisualizerHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(VisualizerHandlerImpl.class);

    private final FileOperateHandler fileOperateHandler;
    private final VariableOperateHandler variableOperateHandler;
    private final TaskOperateHandler taskOperateHandler;
    private final ExecuteHandler executeHandler;
    private final InputItemOperateHandler inputItemOperateHandler;
    private final OutputItemOperateHandler outputItemOperateHandler;

    private final List<VisualizerProvider> visualizerProviders;

    private final InternalVisualizerContext visualizerContext = new InternalVisualizerContext();

    public VisualizerHandlerImpl(
            FileOperateHandler fileOperateHandler,
            VariableOperateHandler variableOperateHandler,
            TaskOperateHandler taskOperateHandler,
            ExecuteHandler executeHandler,
            InputItemOperateHandler inputItemOperateHandler,
            OutputItemOperateHandler outputItemOperateHandler,
            List<VisualizerProvider> visualizerProviders
    ) {
        this.fileOperateHandler = fileOperateHandler;
        this.variableOperateHandler = variableOperateHandler;
        this.taskOperateHandler = taskOperateHandler;
        this.executeHandler = executeHandler;
        this.inputItemOperateHandler = inputItemOperateHandler;
        this.outputItemOperateHandler = outputItemOperateHandler;
        this.visualizerProviders = Optional.ofNullable(visualizerProviders).orElse(Collections.emptyList());
    }

    @PostConstruct
    public void init() {
        LOGGER.info("初始化可视化器...");
        visualizerProviders.stream().map(VisualizerProvider::provide)
                .forEach(visualizer -> visualizer.init(visualizerContext));
    }

    @Override
    public List<Visualizer> all() {
        return visualizerProviders.stream().map(VisualizerProvider::provide).collect(Collectors.toList());
    }

    @Override
    public Visualizer find(String type) throws HandlerException {
        return visualizerProviders.stream().filter(dp -> dp.supportType(type)).map(VisualizerProvider::provide)
                .findAny().orElseThrow(() -> new UnsupportedVisualizerTypeException(type));
    }

    private class InternalVisualizerContext implements Visualizer.Context {

        @Override
        public File downloadFile(FileSystemDownloadInfo info) throws Exception {
            return fileOperateHandler.systemDownloadFile(info);
        }

        @Override
        public FileStream downloadFileStream(FileStreamSystemDownloadInfo info) throws Exception {
            return fileOperateHandler.systemDownloadFileStream(info);
        }

        @Override
        public FileUploadResult uploadFile(FileSystemUploadInfo info) throws Exception {
            return fileOperateHandler.systemUploadFile(info);
        }

        @Override
        public FileStreamUploadResult uploadFileStream(FileStreamSystemUploadInfo info) throws Exception {
            return fileOperateHandler.systemUploadFileStream(info);
        }

        @Override
        public void updateFile(FileSystemUpdateInfo info) throws Exception {
            fileOperateHandler.systemUpdateFile(info);
        }

        @Override
        public void updateFileStream(FileStreamSystemUpdateInfo info) throws Exception {
            fileOperateHandler.systemUpdateFileStream(info);
        }

        @Override
        public void removeFile(FileSystemRemoveInfo info) throws Exception {
            fileOperateHandler.systemRemoveFile(info);
        }

        @Override
        public VariableInspectResult inspectVariable(VariableSystemInspectInfo info) throws Exception {
            return variableOperateHandler.systemInspect(info);
        }

        @Override
        public void upsertVariable(VariableSystemUpsertInfo info) throws Exception {
            variableOperateHandler.systemUpsert(info);
        }

        @Override
        public void removeVariable(VariableSystemRemoveInfo info) throws Exception {
            variableOperateHandler.systemRemove(info);
        }

        @Override
        public TaskCreateResult createTask(TaskSystemCreateInfo info) throws Exception {
            return taskOperateHandler.systemCreate(info);
        }

        @Override
        public void executeTask(TaskSystemExecuteInfo info) throws Exception {
            executeHandler.systemExecuteTask(info);
        }

        @Override
        public CompletableFuture<Void> executeTaskAsync(TaskSystemExecuteInfo info) throws Exception {
            return executeHandler.systemExecuteTaskAsync(info);
        }

        @Nullable
        @Override
        public InputItemInspectResult inspectInputItem(InputItemSystemInspectInfo info) throws Exception {
            return inputItemOperateHandler.systemInspect(info);
        }

        @Override
        public void upsertInputItem(InputItemSystemUpsertInfo info) throws Exception {
            inputItemOperateHandler.systemUpsert(info);
        }

        @Override
        public void removeInputItem(InputItemSystemRemoveInfo info) throws Exception {
            inputItemOperateHandler.systemRemove(info);
        }

        @Nullable
        @Override
        public OutputItemInspectResult inspectOutputItem(OutputItemSystemInspectInfo info) throws Exception {
            return outputItemOperateHandler.systemInspect(info);
        }

        @Override
        public void upsertOutputItem(OutputItemSystemUpsertInfo info) throws Exception {
            outputItemOperateHandler.systemUpsert(info);
        }

        @Override
        public void removeOutputItem(OutputItemSystemRemoveInfo info) throws Exception {
            outputItemOperateHandler.systemRemove(info);
        }
    }
}
