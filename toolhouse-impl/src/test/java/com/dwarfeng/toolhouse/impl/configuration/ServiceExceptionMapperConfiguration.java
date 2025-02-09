package com.dwarfeng.toolhouse.impl.configuration;

import com.dwarfeng.subgrade.impl.exception.MapServiceExceptionMapper;
import com.dwarfeng.subgrade.sdk.exception.ServiceExceptionHelper;
import com.dwarfeng.subgrade.stack.exception.ServiceException;
import com.dwarfeng.toolhouse.sdk.util.ServiceExceptionCodes;
import com.dwarfeng.toolhouse.stack.exception.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

@Configuration
public class ServiceExceptionMapperConfiguration {

    @Bean
    public MapServiceExceptionMapper mapServiceExceptionMapper() {
        Map<Class<? extends Exception>, ServiceException.Code> destination = ServiceExceptionHelper.putDefaultDestination(null);
        destination.put(UserNotExistsException.class, ServiceExceptionCodes.USER_NOT_EXISTS);
        destination.put(UserNotPermittedForCabinetException.class, ServiceExceptionCodes.USER_NOT_PERMITTED_FOR_CABINET);
        destination.put(CabinetNotExistsException.class, ServiceExceptionCodes.CABINET_NOT_EXISTS);
        destination.put(InvalidPermissionLevelException.class, ServiceExceptionCodes.INVALID_PERMISSION_LEVEL);
        destination.put(FolderNotExistsException.class, ServiceExceptionCodes.FOLDER_NOT_EXISTS);
        destination.put(IllegalFolderStateException.class, ServiceExceptionCodes.ILLEGAL_FOLDER_STATE);
        destination.put(CabinetNotIdenticalException.class, ServiceExceptionCodes.CABINET_NOT_IDENTICAL);
        destination.put(ToolNotExistsException.class, ServiceExceptionCodes.TOOL_NOT_EXISTS);
        destination.put(IllegalToolStateException.class, ServiceExceptionCodes.ILLEGAL_TOOL_STATE);
        destination.put(VisualizerInfoNotExistsException.class, ServiceExceptionCodes.VISUALIZER_INFO_NOT_EXISTS);
        destination.put(ExecutorInfoNotExistsException.class, ServiceExceptionCodes.EXECUTOR_INFO_NOT_EXISTS);
        destination.put(SessionNotExistsException.class, ServiceExceptionCodes.SESSION_NOT_EXISTS);
        destination.put(UserNotConsistsException.class, ServiceExceptionCodes.USER_NOT_CONSISTS);
        destination.put(FileInfoNotExistsException.class, ServiceExceptionCodes.FILE_INFO_NOT_EXISTS);
        destination.put(InvalidVariableTypeException.class, ServiceExceptionCodes.INVALID_VARIABLE_TYPE);
        destination.put(TaskNotExistsException.class, ServiceExceptionCodes.TASK_NOT_EXISTS);
        destination.put(TaskStatusMismatchException.class, ServiceExceptionCodes.TASK_STATUS_MISMATCH);
        destination.put(InvalidTaskItemTypeException.class, ServiceExceptionCodes.INVALID_TASK_ITEM_TYPE);
        destination.put(ExecutorException.class, ServiceExceptionCodes.EXECUTOR_FAILED);
        destination.put(ExecutorMakeException.class, ServiceExceptionCodes.EXECUTOR_MAKE_FAILED);
        destination.put(ExecutorExecutionException.class, ServiceExceptionCodes.EXECUTOR_EXECUTION_FAILED);
        destination.put(UnsupportedExecutorTypeException.class, ServiceExceptionCodes.EXECUTOR_TYPE_UNSUPPORTED);
        destination.put(VisualizerException.class, ServiceExceptionCodes.VISUALIZER_FAILED);
        destination.put(VisualizerExecutionException.class, ServiceExceptionCodes.VISUALIZER_EXECUTION_FAILED);
        destination.put(UnsupportedVisualizerTypeException.class, ServiceExceptionCodes.VISUALIZER_TYPE_UNSUPPORTED);
        return new MapServiceExceptionMapper(destination, com.dwarfeng.subgrade.sdk.exception.ServiceExceptionCodes.UNDEFINED);
    }
}
