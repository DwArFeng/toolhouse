package com.dwarfeng.toolhouse.sdk.bean;

import com.dwarfeng.subgrade.sdk.bean.key.*;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.dto.*;
import com.dwarfeng.toolhouse.sdk.bean.entity.*;
import com.dwarfeng.toolhouse.sdk.bean.key.*;
import com.dwarfeng.toolhouse.stack.bean.dto.*;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>sdk</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Mapper
public interface BeanMapper {

    // region Subgrade Key

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    JSFixedFastJsonLongIdKey longIdKeyToJSFixedFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromJSFixedFastJson(JSFixedFastJsonLongIdKey jSFixedFastJsonLongIdKey);

    WebInputLongIdKey longIdKeyToWebInput(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromWebInput(WebInputLongIdKey webInputLongIdKey);

    WebInputStringIdKey stringIdKeyToWebInput(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromWebInput(WebInputStringIdKey webInputStringIdKey);

    // endregion

    // region Toolhouse Key

    FastJsonExecutorKey executorKeyToFastJson(ExecutorKey executorKey);

    @InheritInverseConfiguration
    ExecutorKey executorKeyFromFastJson(FastJsonExecutorKey fastJsonExecutorKey);

    FastJsonFavoriteKey favoriteKeyToFastJson(FavoriteKey favoriteKey);

    @InheritInverseConfiguration
    FavoriteKey favoriteKeyFromFastJson(FastJsonFavoriteKey fastJsonFavoriteKey);

    FastJsonPocaKey pocaKeyToFastJson(PocaKey pocaKey);

    @InheritInverseConfiguration
    PocaKey pocaKeyFromFastJson(FastJsonPocaKey fastJsonPocaKey);

    FastJsonTaskItemKey taskItemKeyToFastJson(TaskItemKey taskItemKey);

    @InheritInverseConfiguration
    TaskItemKey taskItemKeyFromFastJson(FastJsonTaskItemKey fastJsonTaskItemKey);

    FastJsonVariableKey variableKeyToFastJson(VariableKey variableKey);

    @InheritInverseConfiguration
    VariableKey variableKeyFromFastJson(FastJsonVariableKey fastJsonVariableKey);

    FastJsonVisualizerKey visualizerKeyToFastJson(VisualizerKey visualizerKey);

    @InheritInverseConfiguration
    VisualizerKey visualizerKeyFromFastJson(FastJsonVisualizerKey fastJsonVisualizerKey);

    JSFixedFastJsonExecutorKey executorKeyToJSFixedFastJson(ExecutorKey executorKey);

    @InheritInverseConfiguration
    ExecutorKey executorKeyFromJSFixedFastJson(JSFixedFastJsonExecutorKey jSFixedFastJsonExecutorKey);

    JSFixedFastJsonFavoriteKey favoriteKeyToJSFixedFastJson(FavoriteKey favoriteKey);

    @InheritInverseConfiguration
    FavoriteKey favoriteKeyFromJSFixedFastJson(JSFixedFastJsonFavoriteKey jSFixedFastJsonFavoriteKey);

    JSFixedFastJsonPocaKey pocaKeyToJSFixedFastJson(PocaKey pocaKey);

    @InheritInverseConfiguration
    PocaKey pocaKeyFromJSFixedFastJson(JSFixedFastJsonPocaKey jSFixedFastJsonPocaKey);

    JSFixedFastJsonTaskItemKey taskItemKeyToJSFixedFastJson(TaskItemKey taskItemKey);

    @InheritInverseConfiguration
    TaskItemKey taskItemKeyFromJSFixedFastJson(JSFixedFastJsonTaskItemKey jSFixedFastJsonTaskItemKey);

    JSFixedFastJsonVariableKey variableKeyToJSFixedFastJson(VariableKey variableKey);

    @InheritInverseConfiguration
    VariableKey variableKeyFromJSFixedFastJson(JSFixedFastJsonVariableKey jSFixedFastJsonVariableKey);

    JSFixedFastJsonVisualizerKey visualizerKeyToJSFixedFastJson(VisualizerKey visualizerKey);

    @InheritInverseConfiguration
    VisualizerKey visualizerKeyFromJSFixedFastJson(JSFixedFastJsonVisualizerKey jSFixedFastJsonVisualizerKey);

    WebInputExecutorKey executorKeyToWebInput(ExecutorKey executorKey);

    @InheritInverseConfiguration
    ExecutorKey executorKeyFromWebInput(WebInputExecutorKey webInputExecutorKey);

    WebInputTaskItemKey taskItemKeyToWebInput(TaskItemKey taskItemKey);

    @InheritInverseConfiguration
    TaskItemKey taskItemKeyFromWebInput(WebInputTaskItemKey webInputTaskItemKey);

    WebInputVariableKey variableKeyToWebInput(VariableKey variableKey);

    @InheritInverseConfiguration
    VariableKey variableKeyFromWebInput(WebInputVariableKey webInputVariableKey);

    WebInputVisualizerKey visualizerKeyToWebInput(VisualizerKey visualizerKey);

    @InheritInverseConfiguration
    VisualizerKey visualizerKeyFromWebInput(WebInputVisualizerKey webInputVisualizerKey);

    // endregion

    // region Toolhouse Entity

    FastJsonCabinet cabinetToFastJson(Cabinet cabinet);

    @InheritInverseConfiguration
    Cabinet cabinetFromFastJson(FastJsonCabinet fastJsonCabinet);

    FastJsonExecutorInfo executorInfoToFastJson(ExecutorInfo executorInfo);

    @InheritInverseConfiguration
    ExecutorInfo executorInfoFromFastJson(FastJsonExecutorInfo fastJsonExecutorInfo);

    FastJsonExecutorSupport executorSupportToFastJson(ExecutorSupport executorSupport);

    @InheritInverseConfiguration
    ExecutorSupport executorSupportFromFastJson(FastJsonExecutorSupport fastJsonExecutorSupport);

    FastJsonFavorite favoriteToFastJson(Favorite favorite);

    @InheritInverseConfiguration
    Favorite favoriteFromFastJson(FastJsonFavorite fastJsonFavorite);

    FastJsonFileInfo fileInfoToFastJson(FileInfo fileInfo);

    @InheritInverseConfiguration
    FileInfo fileInfoFromFastJson(FastJsonFileInfo fastJsonFileInfo);

    FastJsonFolder folderToFastJson(Folder folder);

    @InheritInverseConfiguration
    Folder folderFromFastJson(FastJsonFolder fastJsonFolder);

    FastJsonInputItem inputItemToFastJson(InputItem inputItem);

    @InheritInverseConfiguration
    InputItem inputItemFromFastJson(FastJsonInputItem fastJsonInputItem);

    FastJsonOutputItem outputItemToFastJson(OutputItem outputItem);

    @InheritInverseConfiguration
    OutputItem outputItemFromFastJson(FastJsonOutputItem fastJsonOutputItem);

    FastJsonPoca pocaToFastJson(Poca poca);

    @InheritInverseConfiguration
    Poca pocaFromFastJson(FastJsonPoca fastJsonPoca);

    FastJsonSession sessionToFastJson(Session session);

    @InheritInverseConfiguration
    Session sessionFromFastJson(FastJsonSession fastJsonSession);

    FastJsonTask taskToFastJson(Task task);

    @InheritInverseConfiguration
    Task taskFromFastJson(FastJsonTask fastJsonTask);

    FastJsonTool toolToFastJson(Tool tool);

    @InheritInverseConfiguration
    Tool toolFromFastJson(FastJsonTool fastJsonTool);

    FastJsonUser userToFastJson(User user);

    @InheritInverseConfiguration
    User userFromFastJson(FastJsonUser fastJsonUser);

    FastJsonVariable variableToFastJson(Variable variable);

    @InheritInverseConfiguration
    Variable variableFromFastJson(FastJsonVariable fastJsonVariable);

    FastJsonVisualizerInfo visualizerInfoToFastJson(VisualizerInfo visualizerInfo);

    @InheritInverseConfiguration
    VisualizerInfo visualizerInfoFromFastJson(FastJsonVisualizerInfo fastJsonVisualizerInfo);

    FastJsonVisualizerSupport visualizerSupportToFastJson(VisualizerSupport visualizerSupport);

    @InheritInverseConfiguration
    VisualizerSupport visualizerSupportFromFastJson(FastJsonVisualizerSupport fastJsonVisualizerSupport);

    JSFixedFastJsonCabinet cabinetToJSFixedFastJson(Cabinet cabinet);

    @InheritInverseConfiguration
    Cabinet cabinetFromJSFixedFastJson(JSFixedFastJsonCabinet jSFixedFastJsonCabinet);

    JSFixedFastJsonExecutorInfo executorInfoToJSFixedFastJson(ExecutorInfo executorInfo);

    @InheritInverseConfiguration
    ExecutorInfo executorInfoFromJSFixedFastJson(JSFixedFastJsonExecutorInfo jSFixedFastJsonExecutorInfo);

    JSFixedFastJsonFavorite favoriteToJSFixedFastJson(Favorite favorite);

    @InheritInverseConfiguration
    Favorite favoriteFromJSFixedFastJson(JSFixedFastJsonFavorite jSFixedFastJsonFavorite);

    JSFixedFastJsonFileInfo fileInfoToJSFixedFastJson(FileInfo fileInfo);

    @InheritInverseConfiguration
    FileInfo fileInfoFromJSFixedFastJson(JSFixedFastJsonFileInfo jSFixedFastJsonFileInfo);

    JSFixedFastJsonFolder folderToJSFixedFastJson(Folder folder);

    @InheritInverseConfiguration
    Folder folderFromJSFixedFastJson(JSFixedFastJsonFolder jSFixedFastJsonFolder);

    JSFixedFastJsonInputItem inputItemToJSFixedFastJson(InputItem inputItem);

    @InheritInverseConfiguration
    InputItem inputItemFromJSFixedFastJson(JSFixedFastJsonInputItem jSFixedFastJsonInputItem);

    JSFixedFastJsonOutputItem outputItemToJSFixedFastJson(OutputItem outputItem);

    @InheritInverseConfiguration
    OutputItem outputItemFromJSFixedFastJson(JSFixedFastJsonOutputItem jSFixedFastJsonOutputItem);

    JSFixedFastJsonPoca pocaToJSFixedFastJson(Poca poca);

    @InheritInverseConfiguration
    Poca pocaFromJSFixedFastJson(JSFixedFastJsonPoca jSFixedFastJsonPoca);

    JSFixedFastJsonSession sessionToJSFixedFastJson(Session session);

    @InheritInverseConfiguration
    Session sessionFromJSFixedFastJson(JSFixedFastJsonSession jSFixedFastJsonSession);

    JSFixedFastJsonTask taskToJSFixedFastJson(Task task);

    @InheritInverseConfiguration
    Task taskFromJSFixedFastJson(JSFixedFastJsonTask jSFixedFastJsonTask);

    JSFixedFastJsonTool toolToJSFixedFastJson(Tool tool);

    @InheritInverseConfiguration
    Tool toolFromJSFixedFastJson(JSFixedFastJsonTool jSFixedFastJsonTool);

    JSFixedFastJsonVariable variableToJSFixedFastJson(Variable variable);

    @InheritInverseConfiguration
    Variable variableFromJSFixedFastJson(JSFixedFastJsonVariable jSFixedFastJsonVariable);

    JSFixedFastJsonVisualizerInfo visualizerInfoToJSFixedFastJson(VisualizerInfo visualizerInfo);

    @InheritInverseConfiguration
    VisualizerInfo visualizerInfoFromJSFixedFastJson(JSFixedFastJsonVisualizerInfo jSFixedFastJsonVisualizerInfo);

    // endregion

    // region Toolhouse DTO

    FastJsonFileStreamUploadResult fileStreamUploadResultToFastJson(FileStreamUploadResult fileStreamUploadResult);

    @InheritInverseConfiguration
    FileStreamUploadResult fileStreamUploadResultFromFastJson(FastJsonFileStreamUploadResult fastJsonFileStreamUploadResult);

    FastJsonFileUploadResult fileUploadResultToFastJson(FileUploadResult fileUploadResult);

    @InheritInverseConfiguration
    FileUploadResult fileUploadResultFromFastJson(FastJsonFileUploadResult fastJsonFileUploadResult);

    FastJsonSessionCreateResult sessionCreateResultToFastJson(SessionCreateResult sessionCreateResult);

    @InheritInverseConfiguration
    SessionCreateResult sessionCreateResultFromFastJson(FastJsonSessionCreateResult fastJsonSessionCreateResult);

    FastJsonTaskCreateResult taskCreateResultToFastJson(TaskCreateResult taskCreateResult);

    @InheritInverseConfiguration
    TaskCreateResult taskCreateResultFromFastJson(FastJsonTaskCreateResult fastJsonTaskCreateResult);

    FastJsonVisualizerCallResult visualizerCallResultToFastJson(VisualizerCallResult visualizerCallResult);

    @InheritInverseConfiguration
    VisualizerCallResult visualizerCallResultFromFastJson(FastJsonVisualizerCallResult fastJsonVisualizerCallResult);

    JSFixedFastJsonFileStreamUploadResult fileStreamUploadResultToJSFixedFastJson(FileStreamUploadResult fileStreamUploadResult);

    @InheritInverseConfiguration
    FileStreamUploadResult fileStreamUploadResultFromJSFixedFastJson(JSFixedFastJsonFileStreamUploadResult jSFixedFastJsonFileStreamUploadResult);

    JSFixedFastJsonFileUploadResult fileUploadResultToJSFixedFastJson(FileUploadResult fileUploadResult);

    @InheritInverseConfiguration
    FileUploadResult fileUploadResultFromJSFixedFastJson(JSFixedFastJsonFileUploadResult jSFixedFastJsonFileUploadResult);

    JSFixedFastJsonSessionCreateResult sessionCreateResultToJSFixedFastJson(SessionCreateResult sessionCreateResult);

    @InheritInverseConfiguration
    SessionCreateResult sessionCreateResultFromJSFixedFastJson(JSFixedFastJsonSessionCreateResult jSFixedFastJsonSessionCreateResult);

    JSFixedFastJsonTaskCreateResult taskCreateResultToJSFixedFastJson(TaskCreateResult taskCreateResult);

    @InheritInverseConfiguration
    TaskCreateResult taskCreateResultFromJSFixedFastJson(JSFixedFastJsonTaskCreateResult jSFixedFastJsonTaskCreateResult);

    WebInputCabinetCreateInfo cabinetCreateInfoToWebInput(CabinetCreateInfo cabinetCreateInfo);

    @InheritInverseConfiguration
    CabinetCreateInfo cabinetCreateInfoFromWebInput(WebInputCabinetCreateInfo webInputCabinetCreateInfo);

    WebInputCabinetFavoredChangeInfo cabinetFavoredChangeInfoToWebInput(CabinetFavoredChangeInfo cabinetFavoredChangeInfo);

    @InheritInverseConfiguration
    CabinetFavoredChangeInfo cabinetFavoredChangeInfoFromWebInput(WebInputCabinetFavoredChangeInfo webInputCabinetFavoredChangeInfo);

    WebInputCabinetPermissionRemoveInfo cabinetPermissionRemoveInfoToWebInput(CabinetPermissionRemoveInfo cabinetPermissionRemoveInfo);

    @InheritInverseConfiguration
    CabinetPermissionRemoveInfo cabinetPermissionRemoveInfoFromWebInput(WebInputCabinetPermissionRemoveInfo webInputCabinetPermissionRemoveInfo);

    WebInputCabinetPermissionUpsertInfo cabinetPermissionUpsertInfoToWebInput(CabinetPermissionUpsertInfo cabinetPermissionUpsertInfo);

    @InheritInverseConfiguration
    CabinetPermissionUpsertInfo cabinetPermissionUpsertInfoFromWebInput(WebInputCabinetPermissionUpsertInfo webInputCabinetPermissionUpsertInfo);

    WebInputCabinetUpdateInfo cabinetUpdateInfoToWebInput(CabinetUpdateInfo cabinetUpdateInfo);

    @InheritInverseConfiguration
    CabinetUpdateInfo cabinetUpdateInfoFromWebInput(WebInputCabinetUpdateInfo webInputCabinetUpdateInfo);

    WebInputExecutorInfoCreateInfo executorInfoCreateInfoToWebInput(ExecutorInfoCreateInfo executorInfoCreateInfo);

    @InheritInverseConfiguration
    ExecutorInfoCreateInfo executorInfoCreateInfoFromWebInput(WebInputExecutorInfoCreateInfo webInputExecutorInfoCreateInfo);

    WebInputExecutorInfoRemoveInfo executorInfoRemoveInfoToWebInput(ExecutorInfoRemoveInfo executorInfoRemoveInfo);

    @InheritInverseConfiguration
    ExecutorInfoRemoveInfo executorInfoRemoveInfoFromWebInput(WebInputExecutorInfoRemoveInfo webInputExecutorInfoRemoveInfo);

    WebInputExecutorInfoUpdateInfo executorInfoUpdateInfoToWebInput(ExecutorInfoUpdateInfo executorInfoUpdateInfo);

    @InheritInverseConfiguration
    ExecutorInfoUpdateInfo executorInfoUpdateInfoFromWebInput(WebInputExecutorInfoUpdateInfo webInputExecutorInfoUpdateInfo);

    WebInputFileManualDownloadInfo fileManualDownloadInfoToWebInput(FileManualDownloadInfo fileManualDownloadInfo);

    @InheritInverseConfiguration
    FileManualDownloadInfo fileManualDownloadInfoFromWebInput(WebInputFileManualDownloadInfo webInputFileManualDownloadInfo);

    WebInputFileManualRemoveInfo fileManualRemoveInfoToWebInput(FileManualRemoveInfo fileManualRemoveInfo);

    @InheritInverseConfiguration
    FileManualRemoveInfo fileManualRemoveInfoFromWebInput(WebInputFileManualRemoveInfo webInputFileManualRemoveInfo);

    WebInputFileOverrideDownloadInfo fileOverrideDownloadInfoToWebInput(FileOverrideDownloadInfo fileOverrideDownloadInfo);

    @InheritInverseConfiguration
    FileOverrideDownloadInfo fileOverrideDownloadInfoFromWebInput(WebInputFileOverrideDownloadInfo webInputFileOverrideDownloadInfo);

    WebInputFileOverrideRemoveInfo fileOverrideRemoveInfoToWebInput(FileOverrideRemoveInfo fileOverrideRemoveInfo);

    @InheritInverseConfiguration
    FileOverrideRemoveInfo fileOverrideRemoveInfoFromWebInput(WebInputFileOverrideRemoveInfo webInputFileOverrideRemoveInfo);

    WebInputFileSystemDownloadInfo fileSystemDownloadInfoToWebInput(FileSystemDownloadInfo fileSystemDownloadInfo);

    @InheritInverseConfiguration
    FileSystemDownloadInfo fileSystemDownloadInfoFromWebInput(WebInputFileSystemDownloadInfo webInputFileSystemDownloadInfo);

    WebInputFileSystemRemoveInfo fileSystemRemoveInfoToWebInput(FileSystemRemoveInfo fileSystemRemoveInfo);

    @InheritInverseConfiguration
    FileSystemRemoveInfo fileSystemRemoveInfoFromWebInput(WebInputFileSystemRemoveInfo webInputFileSystemRemoveInfo);

    WebInputFolderCreateInfo folderCreateInfoToWebInput(FolderCreateInfo folderCreateInfo);

    @InheritInverseConfiguration
    FolderCreateInfo folderCreateInfoFromWebInput(WebInputFolderCreateInfo webInputFolderCreateInfo);

    WebInputFolderUpdateInfo folderUpdateInfoToWebInput(FolderUpdateInfo folderUpdateInfo);

    @InheritInverseConfiguration
    FolderUpdateInfo folderUpdateInfoFromWebInput(WebInputFolderUpdateInfo webInputFolderUpdateInfo);

    WebInputInputItemManualRemoveInfo inputItemManualRemoveInfoToWebInput(InputItemManualRemoveInfo inputItemManualRemoveInfo);

    @InheritInverseConfiguration
    InputItemManualRemoveInfo inputItemManualRemoveInfoFromWebInput(WebInputInputItemManualRemoveInfo webInputInputItemManualRemoveInfo);

    WebInputInputItemManualUpsertInfo inputItemManualUpsertInfoToWebInput(InputItemManualUpsertInfo inputItemManualUpsertInfo);

    @InheritInverseConfiguration
    InputItemManualUpsertInfo inputItemManualUpsertInfoFromWebInput(WebInputInputItemManualUpsertInfo webInputInputItemManualUpsertInfo);

    WebInputInputItemOverrideRemoveInfo inputItemOverrideRemoveInfoToWebInput(InputItemOverrideRemoveInfo inputItemOverrideRemoveInfo);

    @InheritInverseConfiguration
    InputItemOverrideRemoveInfo inputItemOverrideRemoveInfoFromWebInput(WebInputInputItemOverrideRemoveInfo webInputInputItemOverrideRemoveInfo);

    WebInputInputItemOverrideUpsertInfo inputItemOverrideUpsertInfoToWebInput(InputItemOverrideUpsertInfo inputItemOverrideUpsertInfo);

    @InheritInverseConfiguration
    InputItemOverrideUpsertInfo inputItemOverrideUpsertInfoFromWebInput(WebInputInputItemOverrideUpsertInfo webInputInputItemOverrideUpsertInfo);

    WebInputInputItemSystemInspectInfo inputItemSystemInspectInfoToWebInput(InputItemSystemInspectInfo inputItemSystemInspectInfo);

    @InheritInverseConfiguration
    InputItemSystemInspectInfo inputItemSystemInspectInfoFromWebInput(WebInputInputItemSystemInspectInfo webInputInputItemSystemInspectInfo);

    WebInputInputItemSystemRemoveInfo inputItemSystemRemoveInfoToWebInput(InputItemSystemRemoveInfo inputItemSystemRemoveInfo);

    @InheritInverseConfiguration
    InputItemSystemRemoveInfo inputItemSystemRemoveInfoFromWebInput(WebInputInputItemSystemRemoveInfo webInputInputItemSystemRemoveInfo);

    WebInputInputItemSystemUpsertInfo inputItemSystemUpsertInfoToWebInput(InputItemSystemUpsertInfo inputItemSystemUpsertInfo);

    @InheritInverseConfiguration
    InputItemSystemUpsertInfo inputItemSystemUpsertInfoFromWebInput(WebInputInputItemSystemUpsertInfo webInputInputItemSystemUpsertInfo);

    WebInputOutputItemManualRemoveInfo outputItemManualRemoveInfoToWebInput(OutputItemManualRemoveInfo outputItemManualRemoveInfo);

    @InheritInverseConfiguration
    OutputItemManualRemoveInfo outputItemManualRemoveInfoFromWebInput(WebInputOutputItemManualRemoveInfo webInputOutputItemManualRemoveInfo);

    WebInputOutputItemManualUpsertInfo outputItemManualUpsertInfoToWebInput(OutputItemManualUpsertInfo outputItemManualUpsertInfo);

    @InheritInverseConfiguration
    OutputItemManualUpsertInfo outputItemManualUpsertInfoFromWebInput(WebInputOutputItemManualUpsertInfo webInputOutputItemManualUpsertInfo);

    WebInputOutputItemOverrideRemoveInfo outputItemOverrideRemoveInfoToWebInput(OutputItemOverrideRemoveInfo outputItemOverrideRemoveInfo);

    @InheritInverseConfiguration
    OutputItemOverrideRemoveInfo outputItemOverrideRemoveInfoFromWebInput(WebInputOutputItemOverrideRemoveInfo webInputOutputItemOverrideRemoveInfo);

    WebInputOutputItemOverrideUpsertInfo outputItemOverrideUpsertInfoToWebInput(OutputItemOverrideUpsertInfo outputItemOverrideUpsertInfo);

    @InheritInverseConfiguration
    OutputItemOverrideUpsertInfo outputItemOverrideUpsertInfoFromWebInput(WebInputOutputItemOverrideUpsertInfo webInputOutputItemOverrideUpsertInfo);

    WebInputOutputItemSystemInspectInfo outputItemSystemInspectInfoToWebInput(OutputItemSystemInspectInfo outputItemSystemInspectInfo);

    @InheritInverseConfiguration
    OutputItemSystemInspectInfo outputItemSystemInspectInfoFromWebInput(WebInputOutputItemSystemInspectInfo webInputOutputItemSystemInspectInfo);

    WebInputOutputItemSystemRemoveInfo outputItemSystemRemoveInfoToWebInput(OutputItemSystemRemoveInfo outputItemSystemRemoveInfo);

    @InheritInverseConfiguration
    OutputItemSystemRemoveInfo outputItemSystemRemoveInfoFromWebInput(WebInputOutputItemSystemRemoveInfo webInputOutputItemSystemRemoveInfo);

    WebInputOutputItemSystemUpsertInfo outputItemSystemUpsertInfoToWebInput(OutputItemSystemUpsertInfo outputItemSystemUpsertInfo);

    @InheritInverseConfiguration
    OutputItemSystemUpsertInfo outputItemSystemUpsertInfoFromWebInput(WebInputOutputItemSystemUpsertInfo webInputOutputItemSystemUpsertInfo);

    WebInputSessionManualCreateInfo sessionManualCreateInfoToWebInput(SessionManualCreateInfo sessionManualCreateInfo);

    @InheritInverseConfiguration
    SessionManualCreateInfo sessionManualCreateInfoFromWebInput(WebInputSessionManualCreateInfo webInputSessionManualCreateInfo);

    WebInputSessionManualRemoveInfo sessionManualRemoveInfoToWebInput(SessionManualRemoveInfo sessionManualRemoveInfo);

    @InheritInverseConfiguration
    SessionManualRemoveInfo sessionManualRemoveInfoFromWebInput(WebInputSessionManualRemoveInfo webInputSessionManualRemoveInfo);

    WebInputSessionOverrideCreateInfo sessionOverrideCreateInfoToWebInput(SessionOverrideCreateInfo sessionOverrideCreateInfo);

    @InheritInverseConfiguration
    SessionOverrideCreateInfo sessionOverrideCreateInfoFromWebInput(WebInputSessionOverrideCreateInfo webInputSessionOverrideCreateInfo);

    WebInputSessionOverrideRemoveInfo sessionOverrideRemoveInfoToWebInput(SessionOverrideRemoveInfo sessionOverrideRemoveInfo);

    @InheritInverseConfiguration
    SessionOverrideRemoveInfo sessionOverrideRemoveInfoFromWebInput(WebInputSessionOverrideRemoveInfo webInputSessionOverrideRemoveInfo);

    WebInputSessionSystemCreateInfo sessionSystemCreateInfoToWebInput(SessionSystemCreateInfo sessionSystemCreateInfo);

    @InheritInverseConfiguration
    SessionSystemCreateInfo sessionSystemCreateInfoFromWebInput(WebInputSessionSystemCreateInfo webInputSessionSystemCreateInfo);

    WebInputSessionSystemRemoveInfo sessionSystemRemoveInfoToWebInput(SessionSystemRemoveInfo sessionSystemRemoveInfo);

    @InheritInverseConfiguration
    SessionSystemRemoveInfo sessionSystemRemoveInfoFromWebInput(WebInputSessionSystemRemoveInfo webInputSessionSystemRemoveInfo);

    WebInputTaskManualCreateInfo taskManualCreateInfoToWebInput(TaskManualCreateInfo taskManualCreateInfo);

    @InheritInverseConfiguration
    TaskManualCreateInfo taskManualCreateInfoFromWebInput(WebInputTaskManualCreateInfo webInputTaskManualCreateInfo);

    WebInputTaskManualExecuteInfo taskManualExecuteInfoToWebInput(TaskManualExecuteInfo taskManualExecuteInfo);

    @InheritInverseConfiguration
    TaskManualExecuteInfo taskManualExecuteInfoFromWebInput(WebInputTaskManualExecuteInfo webInputTaskManualExecuteInfo);

    WebInputTaskOverrideCreateInfo taskOverrideCreateInfoToWebInput(TaskOverrideCreateInfo taskOverrideCreateInfo);

    @InheritInverseConfiguration
    TaskOverrideCreateInfo taskOverrideCreateInfoFromWebInput(WebInputTaskOverrideCreateInfo webInputTaskOverrideCreateInfo);

    WebInputTaskOverrideExecuteInfo taskOverrideExecuteInfoToWebInput(TaskOverrideExecuteInfo taskOverrideExecuteInfo);

    @InheritInverseConfiguration
    TaskOverrideExecuteInfo taskOverrideExecuteInfoFromWebInput(WebInputTaskOverrideExecuteInfo webInputTaskOverrideExecuteInfo);

    WebInputTaskSystemBeatInfo taskSystemBeatInfoToWebInput(TaskSystemBeatInfo taskSystemBeatInfo);

    @InheritInverseConfiguration
    TaskSystemBeatInfo taskSystemBeatInfoFromWebInput(WebInputTaskSystemBeatInfo webInputTaskSystemBeatInfo);

    WebInputTaskSystemCreateInfo taskSystemCreateInfoToWebInput(TaskSystemCreateInfo taskSystemCreateInfo);

    @InheritInverseConfiguration
    TaskSystemCreateInfo taskSystemCreateInfoFromWebInput(WebInputTaskSystemCreateInfo webInputTaskSystemCreateInfo);

    WebInputTaskSystemDieInfo taskSystemDieInfoToWebInput(TaskSystemDieInfo taskSystemDieInfo);

    @InheritInverseConfiguration
    TaskSystemDieInfo taskSystemDieInfoFromWebInput(WebInputTaskSystemDieInfo webInputTaskSystemDieInfo);

    WebInputTaskSystemExecuteInfo taskSystemExecuteInfoToWebInput(TaskSystemExecuteInfo taskSystemExecuteInfo);

    @InheritInverseConfiguration
    TaskSystemExecuteInfo taskSystemExecuteInfoFromWebInput(WebInputTaskSystemExecuteInfo webInputTaskSystemExecuteInfo);

    WebInputTaskSystemExpireInfo taskSystemExpireInfoToWebInput(TaskSystemExpireInfo taskSystemExpireInfo);

    @InheritInverseConfiguration
    TaskSystemExpireInfo taskSystemExpireInfoFromWebInput(WebInputTaskSystemExpireInfo webInputTaskSystemExpireInfo);

    WebInputTaskSystemFailInfo taskSystemFailInfoToWebInput(TaskSystemFailInfo taskSystemFailInfo);

    @InheritInverseConfiguration
    TaskSystemFailInfo taskSystemFailInfoFromWebInput(WebInputTaskSystemFailInfo webInputTaskSystemFailInfo);

    WebInputTaskSystemFinishInfo taskSystemFinishInfoToWebInput(TaskSystemFinishInfo taskSystemFinishInfo);

    @InheritInverseConfiguration
    TaskSystemFinishInfo taskSystemFinishInfoFromWebInput(WebInputTaskSystemFinishInfo webInputTaskSystemFinishInfo);

    WebInputTaskSystemStartInfo taskSystemStartInfoToWebInput(TaskSystemStartInfo taskSystemStartInfo);

    @InheritInverseConfiguration
    TaskSystemStartInfo taskSystemStartInfoFromWebInput(WebInputTaskSystemStartInfo webInputTaskSystemStartInfo);

    WebInputTaskSystemUpdateModalInfo taskSystemUpdateModalInfoToWebInput(TaskSystemUpdateModalInfo taskSystemUpdateModalInfo);

    @InheritInverseConfiguration
    TaskSystemUpdateModalInfo taskSystemUpdateModalInfoFromWebInput(WebInputTaskSystemUpdateModalInfo webInputTaskSystemUpdateModalInfo);

    WebInputToolCreateInfo toolCreateInfoToWebInput(ToolCreateInfo toolCreateInfo);

    @InheritInverseConfiguration
    ToolCreateInfo toolCreateInfoFromWebInput(WebInputToolCreateInfo webInputToolCreateInfo);

    WebInputToolUpdateInfo toolUpdateInfoToWebInput(ToolUpdateInfo toolUpdateInfo);

    @InheritInverseConfiguration
    ToolUpdateInfo toolUpdateInfoFromWebInput(WebInputToolUpdateInfo webInputToolUpdateInfo);

    WebInputVariableManualRemoveInfo variableManualRemoveInfoToWebInput(VariableManualRemoveInfo variableManualRemoveInfo);

    @InheritInverseConfiguration
    VariableManualRemoveInfo variableManualRemoveInfoFromWebInput(WebInputVariableManualRemoveInfo webInputVariableManualRemoveInfo);

    WebInputVariableManualUpsertInfo variableManualUpsertInfoToWebInput(VariableManualUpsertInfo variableManualUpsertInfo);

    @InheritInverseConfiguration
    VariableManualUpsertInfo variableManualUpsertInfoFromWebInput(WebInputVariableManualUpsertInfo webInputVariableManualUpsertInfo);

    WebInputVariableOverrideRemoveInfo variableOverrideRemoveInfoToWebInput(VariableOverrideRemoveInfo variableOverrideRemoveInfo);

    @InheritInverseConfiguration
    VariableOverrideRemoveInfo variableOverrideRemoveInfoFromWebInput(WebInputVariableOverrideRemoveInfo webInputVariableOverrideRemoveInfo);

    WebInputVariableOverrideUpsertInfo variableOverrideUpsertInfoToWebInput(VariableOverrideUpsertInfo variableOverrideUpsertInfo);

    @InheritInverseConfiguration
    VariableOverrideUpsertInfo variableOverrideUpsertInfoFromWebInput(WebInputVariableOverrideUpsertInfo webInputVariableOverrideUpsertInfo);

    WebInputVariableSystemInspectInfo variableSystemInspectInfoToWebInput(VariableSystemInspectInfo variableSystemInspectInfo);

    @InheritInverseConfiguration
    VariableSystemInspectInfo variableSystemInspectInfoFromWebInput(WebInputVariableSystemInspectInfo webInputVariableSystemInspectInfo);

    WebInputVariableSystemRemoveInfo variableSystemRemoveInfoToWebInput(VariableSystemRemoveInfo variableSystemRemoveInfo);

    @InheritInverseConfiguration
    VariableSystemRemoveInfo variableSystemRemoveInfoFromWebInput(WebInputVariableSystemRemoveInfo webInputVariableSystemRemoveInfo);

    WebInputVariableSystemUpsertInfo variableSystemUpsertInfoToWebInput(VariableSystemUpsertInfo variableSystemUpsertInfo);

    @InheritInverseConfiguration
    VariableSystemUpsertInfo variableSystemUpsertInfoFromWebInput(WebInputVariableSystemUpsertInfo webInputVariableSystemUpsertInfo);

    WebInputVisualizerInfoCreateInfo visualizerInfoCreateInfoToWebInput(VisualizerInfoCreateInfo visualizerInfoCreateInfo);

    @InheritInverseConfiguration
    VisualizerInfoCreateInfo visualizerInfoCreateInfoFromWebInput(WebInputVisualizerInfoCreateInfo webInputVisualizerInfoCreateInfo);

    WebInputVisualizerInfoRemoveInfo visualizerInfoRemoveInfoToWebInput(VisualizerInfoRemoveInfo visualizerInfoRemoveInfo);

    @InheritInverseConfiguration
    VisualizerInfoRemoveInfo visualizerInfoRemoveInfoFromWebInput(WebInputVisualizerInfoRemoveInfo webInputVisualizerInfoRemoveInfo);

    WebInputVisualizerInfoUpdateInfo visualizerInfoUpdateInfoToWebInput(VisualizerInfoUpdateInfo visualizerInfoUpdateInfo);

    @InheritInverseConfiguration
    VisualizerInfoUpdateInfo visualizerInfoUpdateInfoFromWebInput(WebInputVisualizerInfoUpdateInfo webInputVisualizerInfoUpdateInfo);

    WebInputVisualizerManualCallInfo visualizerManualCallInfoToWebInput(VisualizerManualCallInfo visualizerManualCallInfo);

    @InheritInverseConfiguration
    VisualizerManualCallInfo visualizerManualCallInfoFromWebInput(WebInputVisualizerManualCallInfo webInputVisualizerManualCallInfo);

    WebInputVisualizerOverrideCallInfo visualizerOverrideCallInfoToWebInput(VisualizerOverrideCallInfo visualizerOverrideCallInfo);

    @InheritInverseConfiguration
    VisualizerOverrideCallInfo visualizerOverrideCallInfoFromWebInput(WebInputVisualizerOverrideCallInfo webInputVisualizerOverrideCallInfo);

    WebInputVisualizerSystemCallInfo visualizerSystemCallInfoToWebInput(VisualizerSystemCallInfo visualizerSystemCallInfo);

    @InheritInverseConfiguration
    VisualizerSystemCallInfo visualizerSystemCallInfoFromWebInput(WebInputVisualizerSystemCallInfo webInputVisualizerSystemCallInfo);

    // endregion
}
