package com.dwarfeng.toolhouse.sdk.bean;

import com.dwarfeng.subgrade.sdk.bean.key.FastJsonLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.FastJsonStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.entity.*;
import com.dwarfeng.toolhouse.sdk.bean.key.*;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.*;

/**
 * FastJson Bean 映射器。
 *
 * @author DwArFeng
 * @see BeanMapper
 * @since beta-1.0.0
 * @deprecated 使用 {@link BeanMapper} 代替。
 */
@Deprecated
public interface FastJsonMapper {

    FastJsonLongIdKey longIdKeyToFastJson(LongIdKey longIdKey);

    LongIdKey longIdKeyFromFastJson(FastJsonLongIdKey fastJsonLongIdKey);

    FastJsonStringIdKey stringIdKeyToFastJson(StringIdKey stringIdKey);

    StringIdKey stringIdKeyFromFastJson(FastJsonStringIdKey fastJsonStringIdKey);

    FastJsonPocaKey pocaKeyToFastJson(PocaKey pocaKey);

    PocaKey pocaKeyFromFastJson(FastJsonPocaKey fastJsonPocaKey);

    FastJsonFavoriteKey favoriteKeyToFastJson(FavoriteKey favoriteKey);

    FavoriteKey favoriteKeyFromFastJson(FastJsonFavoriteKey fastJsonFavoriteKey);

    FastJsonVisualizerKey visualizerKeyToFastJson(VisualizerKey visualizerKey);

    VisualizerKey visualizerKeyFromFastJson(FastJsonVisualizerKey fastJsonVisualizerKey);

    FastJsonExecutorKey executorKeyToFastJson(ExecutorKey executorKey);

    ExecutorKey executorKeyFromFastJson(FastJsonExecutorKey fastJsonExecutorKey);

    FastJsonVariableKey variableKeyToFastJson(VariableKey variableKey);

    VariableKey variableKeyFromFastJson(FastJsonVariableKey fastJsonVariableKey);

    FastJsonTaskItemKey taskItemKeyToFastJson(TaskItemKey taskItemKey);

    TaskItemKey taskItemKeyFromFastJson(FastJsonTaskItemKey fastJsonTaskItemKey);

    FastJsonPoca pocaToFastJson(Poca poca);

    Poca pocaFromFastJson(FastJsonPoca fastJsonPoca);

    FastJsonCabinet cabinetToFastJson(Cabinet cabinet);

    Cabinet cabinetFromFastJson(FastJsonCabinet fastJsonCabinet);

    FastJsonFolder folderToFastJson(Folder folder);

    Folder folderFromFastJson(FastJsonFolder fastJsonFolder);

    FastJsonTool toolToFastJson(Tool tool);

    Tool toolFromFastJson(FastJsonTool fastJsonTool);

    FastJsonUser userToFastJson(User user);

    User userFromFastJson(FastJsonUser fastJsonUser);

    FastJsonFavorite favoriteToFastJson(Favorite favorite);

    Favorite favoriteFromFastJson(FastJsonFavorite fastJsonFavorite);

    FastJsonVisualizerInfo visualizerInfoToFastJson(VisualizerInfo visualizerInfo);

    VisualizerInfo visualizerInfoFromFastJson(FastJsonVisualizerInfo fastJsonVisualizerInfo);

    FastJsonVisualizerSupport visualizerSupportToFastJson(VisualizerSupport visualizerSupport);

    VisualizerSupport visualizerSupportFromFastJson(FastJsonVisualizerSupport fastJsonVisualizerSupport);

    FastJsonExecutorInfo executorInfoToFastJson(ExecutorInfo executorInfo);

    ExecutorInfo executorInfoFromFastJson(FastJsonExecutorInfo fastJsonExecutorInfo);

    FastJsonExecutorSupport executorSupportToFastJson(ExecutorSupport executorSupport);

    ExecutorSupport executorSupportFromFastJson(FastJsonExecutorSupport fastJsonExecutorSupport);

    FastJsonSession sessionToFastJson(Session session);

    Session sessionFromFastJson(FastJsonSession fastJsonSession);

    FastJsonVariable variableToFastJson(Variable variable);

    Variable variableFromFastJson(FastJsonVariable fastJsonVariable);

    FastJsonTask taskToFastJson(Task task);

    Task taskFromFastJson(FastJsonTask fastJsonTask);

    FastJsonInputItem inputItemToFastJson(InputItem inputItem);

    InputItem inputItemFromFastJson(FastJsonInputItem fastJsonInputItem);

    FastJsonOutputItem outputItemToFastJson(OutputItem outputItem);

    OutputItem outputItemFromFastJson(FastJsonOutputItem fastJsonOutputItem);

    FastJsonFileInfo fileInfoToFastJson(FileInfo fileInfo);

    FileInfo fileInfoFromFastJson(FastJsonFileInfo fastJsonFileInfo);
}
