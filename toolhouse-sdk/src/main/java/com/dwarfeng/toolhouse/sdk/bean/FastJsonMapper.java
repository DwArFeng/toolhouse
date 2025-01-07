package com.dwarfeng.toolhouse.sdk.bean;

import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.entity.*;
import com.dwarfeng.toolhouse.sdk.bean.key.*;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @since beta-1.0.0
 */
@Mapper
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonPocaKey pocaKeyToFastJson(PocaKey pocaKey);

    @InheritInverseConfiguration
    PocaKey pocaKeyFromFastJson(FastJsonPocaKey fastJsonPocaKey);

    FastJsonFavoriteKey favoriteKeyToFastJson(FavoriteKey favoriteKey);

    @InheritInverseConfiguration
    FavoriteKey favoriteKeyFromFastJson(FastJsonFavoriteKey fastJsonFavoriteKey);

    FastJsonVisualizerKey visualizerKeyToFastJson(VisualizerKey visualizerKey);

    @InheritInverseConfiguration
    VisualizerKey visualizerKeyFromFastJson(FastJsonVisualizerKey fastJsonVisualizerKey);

    FastJsonExecutorKey executorKeyToFastJson(ExecutorKey executorKey);

    @InheritInverseConfiguration
    ExecutorKey executorKeyFromFastJson(FastJsonExecutorKey fastJsonExecutorKey);

    FastJsonVariableKey variableKeyToFastJson(VariableKey variableKey);

    @InheritInverseConfiguration
    VariableKey variableKeyFromFastJson(FastJsonVariableKey fastJsonVariableKey);

    FastJsonTaskItemKey taskItemKeyToFastJson(TaskItemKey taskItemKey);

    @InheritInverseConfiguration
    TaskItemKey taskItemKeyFromFastJson(FastJsonTaskItemKey fastJsonTaskItemKey);

    FastJsonPoca pocaToFastJson(Poca poca);

    @InheritInverseConfiguration
    Poca pocaFromFastJson(FastJsonPoca fastJsonPoca);

    FastJsonCabinet cabinetToFastJson(Cabinet cabinet);

    @InheritInverseConfiguration
    Cabinet cabinetFromFastJson(FastJsonCabinet fastJsonCabinet);

    FastJsonFolder folderToFastJson(Folder folder);

    @InheritInverseConfiguration
    Folder folderFromFastJson(FastJsonFolder fastJsonFolder);

    FastJsonTool toolToFastJson(Tool tool);

    @InheritInverseConfiguration
    Tool toolFromFastJson(FastJsonTool fastJsonTool);

    FastJsonUser userToFastJson(User user);

    @InheritInverseConfiguration
    User userFromFastJson(FastJsonUser fastJsonUser);

    FastJsonFavorite favoriteToFastJson(Favorite favorite);

    @InheritInverseConfiguration
    Favorite favoriteFromFastJson(FastJsonFavorite fastJsonFavorite);

    FastJsonVisualizerInfo visualizerInfoToFastJson(VisualizerInfo visualizerInfo);

    @InheritInverseConfiguration
    VisualizerInfo visualizerInfoFromFastJson(FastJsonVisualizerInfo fastJsonVisualizerInfo);

    FastJsonVisualizerSupport visualizerSupportToFastJson(VisualizerSupport visualizerSupport);

    @InheritInverseConfiguration
    VisualizerSupport visualizerSupportFromFastJson(FastJsonVisualizerSupport fastJsonVisualizerSupport);

    FastJsonExecutorInfo executorInfoToFastJson(ExecutorInfo executorInfo);

    @InheritInverseConfiguration
    ExecutorInfo executorInfoFromFastJson(FastJsonExecutorInfo fastJsonExecutorInfo);

    FastJsonExecutorSupport executorSupportToFastJson(ExecutorSupport executorSupport);

    @InheritInverseConfiguration
    ExecutorSupport executorSupportFromFastJson(FastJsonExecutorSupport fastJsonExecutorSupport);

    FastJsonSession sessionToFastJson(Session session);

    @InheritInverseConfiguration
    Session sessionFromFastJson(FastJsonSession fastJsonSession);

    FastJsonVariable variableToFastJson(Variable variable);

    @InheritInverseConfiguration
    Variable variableFromFastJson(FastJsonVariable fastJsonVariable);

    FastJsonTask taskToFastJson(Task task);

    @InheritInverseConfiguration
    Task taskFromFastJson(FastJsonTask fastJsonTask);

    FastJsonInputItem inputItemToFastJson(InputItem inputItem);

    @InheritInverseConfiguration
    InputItem inputItemFromFastJson(FastJsonInputItem fastJsonInputItem);

    FastJsonOutputItem outputItemToFastJson(OutputItem outputItem);

    @InheritInverseConfiguration
    OutputItem outputItemFromFastJson(FastJsonOutputItem fastJsonOutputItem);

    FastJsonFileInfo fileInfoToFastJson(FileInfo fileInfo);

    @InheritInverseConfiguration
    FileInfo fileInfoFromFastJson(FastJsonFileInfo fastJsonFileInfo);
}
