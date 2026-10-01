package com.dwarfeng.toolhouse.impl.bean;

import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.impl.bean.entity.*;
import com.dwarfeng.toolhouse.impl.bean.key.*;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.*;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Bean 映射器。
 *
 * <p>
 * 该映射器中包含了 <code>impl</code> 模块中所有实体与 <code>stack</code> 模块中对应实体的映射方法。
 *
 * @author DwArFeng
 * @since 1.1.0
 */
@Mapper
public interface BeanMapper {

    // region Subgrade Key

    HibernateLongIdKey longIdKeyToHibernate(LongIdKey longIdKey);

    @InheritInverseConfiguration
    LongIdKey longIdKeyFromHibernate(HibernateLongIdKey hibernateLongIdKey);

    HibernateStringIdKey stringIdKeyToHibernate(StringIdKey stringIdKey);

    @InheritInverseConfiguration
    StringIdKey stringIdKeyFromHibernate(HibernateStringIdKey hibernateStringIdKey);

    // endregion

    // region Toolhouse Key

    HibernatePocaKey pocaKeyToHibernate(PocaKey pocaKey);

    @InheritInverseConfiguration
    PocaKey pocaKeyFromHibernate(HibernatePocaKey hibernatePocaKey);

    HibernateFavoriteKey favoriteKeyToHibernate(FavoriteKey favoriteKey);

    @InheritInverseConfiguration
    FavoriteKey favoriteKeyFromHibernate(HibernateFavoriteKey hibernateFavoriteKey);

    HibernateVisualizerKey visualizerKeyToHibernate(VisualizerKey visualizerKey);

    @InheritInverseConfiguration
    VisualizerKey visualizerKeyFromHibernate(HibernateVisualizerKey hibernateVisualizerKey);

    HibernateExecutorKey executorKeyToHibernate(ExecutorKey executorKey);

    @InheritInverseConfiguration
    ExecutorKey executorKeyFromHibernate(HibernateExecutorKey hibernateExecutorKey);

    HibernateVariableKey variableKeyToHibernate(VariableKey variableKey);

    @InheritInverseConfiguration
    VariableKey variableKeyFromHibernate(HibernateVariableKey hibernateVariableKey);

    HibernateTaskItemKey taskItemKeyToHibernate(TaskItemKey taskItemKey);

    @InheritInverseConfiguration
    TaskItemKey taskItemKeyFromHibernate(HibernateTaskItemKey hibernateTaskItemKey);

    // endregion

    // region Toolhouse Entity

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cabinetLongId", ignore = true)
    @Mapping(target = "cabinet", ignore = true)
    HibernatePoca pocaToHibernate(Poca poca);

    @InheritInverseConfiguration
    Poca pocaFromHibernate(HibernatePoca hibernatePoca);

    @Mapping(target = "tools", ignore = true)
    @Mapping(target = "pocas", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "folders", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    HibernateCabinet cabinetToHibernate(Cabinet cabinet);

    @InheritInverseConfiguration
    Cabinet cabinetFromHibernate(HibernateCabinet hibernateCabinet);

    @Mapping(target = "tools", ignore = true)
    @Mapping(target = "parentLongId", ignore = true)
    @Mapping(target = "parent", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "cabinetLongId", ignore = true)
    @Mapping(target = "cabinet", ignore = true)
    HibernateFolder folderToHibernate(Folder folder);

    @InheritInverseConfiguration
    Folder folderFromHibernate(HibernateFolder hibernateFolder);

    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "executorInfos", ignore = true)
    @Mapping(target = "visualizerInfos", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "folderLongId", ignore = true)
    @Mapping(target = "folder", ignore = true)
    @Mapping(target = "cabinetLongId", ignore = true)
    @Mapping(target = "cabinet", ignore = true)
    HibernateTool toolToHibernate(Tool tool);

    @InheritInverseConfiguration
    Tool toolFromHibernate(HibernateTool hibernateTool);

    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "sessions", ignore = true)
    @Mapping(target = "stringId", ignore = true)
    @Mapping(target = "pocas", ignore = true)
    @Mapping(target = "favorites", ignore = true)
    HibernateUser userToHibernate(User user);

    @InheritInverseConfiguration
    User userFromHibernate(HibernateUser hibernateUser);

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "cabinetLongId", ignore = true)
    @Mapping(target = "cabinet", ignore = true)
    HibernateFavorite favoriteToHibernate(Favorite favorite);

    @InheritInverseConfiguration
    Favorite favoriteFromHibernate(HibernateFavorite hibernateFavorite);

    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "visualizerStringId", ignore = true)
    @Mapping(target = "toolLongId", ignore = true)
    @Mapping(target = "tool", ignore = true)
    HibernateVisualizerInfo visualizerInfoToHibernate(VisualizerInfo visualizerInfo);

    @InheritInverseConfiguration
    VisualizerInfo visualizerInfoFromHibernate(HibernateVisualizerInfo hibernateVisualizerInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateVisualizerSupport visualizerSupportToHibernate(VisualizerSupport visualizerSupport);

    @InheritInverseConfiguration
    VisualizerSupport visualizerSupportFromHibernate(HibernateVisualizerSupport hibernateVisualizerSupport);

    @Mapping(target = "modifiedDatamark", ignore = true)
    @Mapping(target = "createdDatamark", ignore = true)
    @Mapping(target = "executorStringId", ignore = true)
    @Mapping(target = "toolLongId", ignore = true)
    @Mapping(target = "tool", ignore = true)
    HibernateExecutorInfo executorInfoToHibernate(ExecutorInfo executorInfo);

    @InheritInverseConfiguration
    ExecutorInfo executorInfoFromHibernate(HibernateExecutorInfo hibernateExecutorInfo);

    @Mapping(target = "stringId", ignore = true)
    HibernateExecutorSupport executorSupportToHibernate(ExecutorSupport executorSupport);

    @InheritInverseConfiguration
    ExecutorSupport executorSupportFromHibernate(HibernateExecutorSupport hibernateExecutorSupport);

    @Mapping(target = "variables", ignore = true)
    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "toolLongId", ignore = true)
    @Mapping(target = "tool", ignore = true)
    @Mapping(target = "tasks", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "fileInfos", ignore = true)
    HibernateSession sessionToHibernate(Session session);

    @InheritInverseConfiguration
    Session sessionFromHibernate(HibernateSession hibernateSession);

    @Mapping(target = "variableStringId", ignore = true)
    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "toolLongId", ignore = true)
    @Mapping(target = "sessionLongId", ignore = true)
    @Mapping(target = "session", ignore = true)
    HibernateVariable variableToHibernate(Variable variable);

    @InheritInverseConfiguration
    Variable variableFromHibernate(HibernateVariable hibernateVariable);

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "toolLongId", ignore = true)
    @Mapping(target = "sessionLongId", ignore = true)
    @Mapping(target = "session", ignore = true)
    @Mapping(target = "longId", ignore = true)
    @Mapping(target = "inputItems", ignore = true)
    HibernateTask taskToHibernate(Task task);

    @InheritInverseConfiguration
    Task taskFromHibernate(HibernateTask hibernateTask);

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "toolLongId", ignore = true)
    @Mapping(target = "taskLongId", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "sessionLongId", ignore = true)
    @Mapping(target = "itemStringId", ignore = true)
    HibernateInputItem inputItemToHibernate(InputItem inputItem);

    @InheritInverseConfiguration
    InputItem inputItemFromHibernate(HibernateInputItem hibernateInputItem);

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "toolLongId", ignore = true)
    @Mapping(target = "taskLongId", ignore = true)
    @Mapping(target = "task", ignore = true)
    @Mapping(target = "sessionLongId", ignore = true)
    @Mapping(target = "itemStringId", ignore = true)
    HibernateOutputItem outputItemToHibernate(OutputItem outputItem);

    @InheritInverseConfiguration
    OutputItem outputItemFromHibernate(HibernateOutputItem hibernateOutputItem);

    @Mapping(target = "userStringId", ignore = true)
    @Mapping(target = "toolLongId", ignore = true)
    @Mapping(target = "sessionLongId", ignore = true)
    @Mapping(target = "session", ignore = true)
    @Mapping(target = "longId", ignore = true)
    HibernateFileInfo fileInfoToHibernate(FileInfo fileInfo);

    @InheritInverseConfiguration
    FileInfo fileInfoFromHibernate(HibernateFileInfo hibernateFileInfo);

    // endregion
}
