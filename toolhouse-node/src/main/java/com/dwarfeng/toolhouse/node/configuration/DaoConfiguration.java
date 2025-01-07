package com.dwarfeng.toolhouse.node.configuration;

import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.dao.HibernateBatchBaseDao;
import com.dwarfeng.subgrade.impl.dao.HibernateEntireLookupDao;
import com.dwarfeng.subgrade.impl.dao.HibernatePresetLookupDao;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateLongIdKey;
import com.dwarfeng.subgrade.sdk.bean.key.HibernateStringIdKey;
import com.dwarfeng.subgrade.sdk.hibernate.modification.DefaultDeletionMod;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.impl.bean.HibernateMapper;
import com.dwarfeng.toolhouse.impl.bean.entity.*;
import com.dwarfeng.toolhouse.impl.bean.key.*;
import com.dwarfeng.toolhouse.impl.dao.preset.*;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate5.HibernateTemplate;

@Configuration
public class DaoConfiguration {

    private final HibernateTemplate template;

    private final PocaPresetCriteriaMaker pocaPresetCriteriaMaker;
    private final CabinetPresetCriteriaMaker cabinetPresetCriteriaMaker;
    private final FolderPresetCriteriaMaker folderPresetCriteriaMaker;
    private final ToolPresetCriteriaMaker toolPresetCriteriaMaker;
    private final FavoritePresetCriteriaMaker favoritePresetCriteriaMaker;
    private final VisualizerInfoPresetCriteriaMaker visualizerInfoPresetCriteriaMaker;
    private final VisualizerSupportPresetCriteriaMaker visualizerSupportPresetCriteriaMaker;
    private final ExecutorInfoPresetCriteriaMaker executorInfoPresetCriteriaMaker;
    private final ExecutorSupportPresetCriteriaMaker executorSupportPresetCriteriaMaker;
    private final SessionPresetCriteriaMaker sessionPresetCriteriaMaker;
    private final VariablePresetCriteriaMaker variablePresetCriteriaMaker;
    private final TaskPresetCriteriaMaker taskPresetCriteriaMaker;
    private final InputItemPresetCriteriaMaker inputItemPresetCriteriaMaker;
    private final OutputItemPresetCriteriaMaker outputItemPresetCriteriaMaker;
    private final FileInfoPresetCriteriaMaker fileInfoPresetCriteriaMaker;

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template,
            PocaPresetCriteriaMaker pocaPresetCriteriaMaker,
            CabinetPresetCriteriaMaker cabinetPresetCriteriaMaker,
            FolderPresetCriteriaMaker folderPresetCriteriaMaker,
            ToolPresetCriteriaMaker toolPresetCriteriaMaker,
            FavoritePresetCriteriaMaker favoritePresetCriteriaMaker,
            VisualizerInfoPresetCriteriaMaker visualizerInfoPresetCriteriaMaker,
            VisualizerSupportPresetCriteriaMaker visualizerSupportPresetCriteriaMaker,
            ExecutorInfoPresetCriteriaMaker executorInfoPresetCriteriaMaker,
            ExecutorSupportPresetCriteriaMaker executorSupportPresetCriteriaMaker,
            SessionPresetCriteriaMaker sessionPresetCriteriaMaker,
            VariablePresetCriteriaMaker variablePresetCriteriaMaker,
            TaskPresetCriteriaMaker taskPresetCriteriaMaker,
            InputItemPresetCriteriaMaker inputItemPresetCriteriaMaker,
            OutputItemPresetCriteriaMaker outputItemPresetCriteriaMaker,
            FileInfoPresetCriteriaMaker fileInfoPresetCriteriaMaker
    ) {
        this.template = template;
        this.pocaPresetCriteriaMaker = pocaPresetCriteriaMaker;
        this.cabinetPresetCriteriaMaker = cabinetPresetCriteriaMaker;
        this.folderPresetCriteriaMaker = folderPresetCriteriaMaker;
        this.toolPresetCriteriaMaker = toolPresetCriteriaMaker;
        this.favoritePresetCriteriaMaker = favoritePresetCriteriaMaker;
        this.visualizerInfoPresetCriteriaMaker = visualizerInfoPresetCriteriaMaker;
        this.visualizerSupportPresetCriteriaMaker = visualizerSupportPresetCriteriaMaker;
        this.executorInfoPresetCriteriaMaker = executorInfoPresetCriteriaMaker;
        this.executorSupportPresetCriteriaMaker = executorSupportPresetCriteriaMaker;
        this.sessionPresetCriteriaMaker = sessionPresetCriteriaMaker;
        this.variablePresetCriteriaMaker = variablePresetCriteriaMaker;
        this.taskPresetCriteriaMaker = taskPresetCriteriaMaker;
        this.inputItemPresetCriteriaMaker = inputItemPresetCriteriaMaker;
        this.outputItemPresetCriteriaMaker = outputItemPresetCriteriaMaker;
        this.fileInfoPresetCriteriaMaker = fileInfoPresetCriteriaMaker;
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, User, HibernateUser> userHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(User.class, HibernateUser.class, HibernateMapper.class),
                HibernateUser.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateBatchBaseDao<PocaKey, HibernatePocaKey, Poca, HibernatePoca> pocaHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(PocaKey.class, HibernatePocaKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Poca.class, HibernatePoca.class, HibernateMapper.class),
                HibernatePoca.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Poca, HibernatePoca> pocaHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Poca.class, HibernatePoca.class, HibernateMapper.class),
                HibernatePoca.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Poca, HibernatePoca> pocaHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Poca.class, HibernatePoca.class, HibernateMapper.class),
                HibernatePoca.class,
                pocaPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Cabinet, HibernateCabinet>
    cabinetHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Cabinet.class, HibernateCabinet.class, HibernateMapper.class),
                HibernateCabinet.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Cabinet, HibernateCabinet> cabinetHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Cabinet.class, HibernateCabinet.class, HibernateMapper.class),
                HibernateCabinet.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Cabinet, HibernateCabinet> cabinetHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Cabinet.class, HibernateCabinet.class, HibernateMapper.class),
                HibernateCabinet.class,
                cabinetPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Folder, HibernateFolder>
    folderHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Folder.class, HibernateFolder.class, HibernateMapper.class),
                HibernateFolder.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Folder, HibernateFolder> folderHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Folder.class, HibernateFolder.class, HibernateMapper.class),
                HibernateFolder.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Folder, HibernateFolder> folderHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Folder.class, HibernateFolder.class, HibernateMapper.class),
                HibernateFolder.class,
                folderPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Tool, HibernateTool>
    toolHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Tool.class, HibernateTool.class, HibernateMapper.class),
                HibernateTool.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Tool, HibernateTool> toolHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Tool.class, HibernateTool.class, HibernateMapper.class),
                HibernateTool.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Tool, HibernateTool> toolHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Tool.class, HibernateTool.class, HibernateMapper.class),
                HibernateTool.class,
                toolPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<FavoriteKey, HibernateFavoriteKey, Favorite, HibernateFavorite>
    favoriteHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(FavoriteKey.class, HibernateFavoriteKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Favorite.class, HibernateFavorite.class, HibernateMapper.class),
                HibernateFavorite.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Favorite, HibernateFavorite> favoriteHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Favorite.class, HibernateFavorite.class, HibernateMapper.class),
                HibernateFavorite.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Favorite, HibernateFavorite> favoriteHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Favorite.class, HibernateFavorite.class, HibernateMapper.class),
                HibernateFavorite.class,
                favoritePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<VisualizerKey, HibernateVisualizerKey, VisualizerInfo, HibernateVisualizerInfo>
    visualizerInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(
                        VisualizerKey.class, HibernateVisualizerKey.class, HibernateMapper.class
                ),
                new MapStructBeanTransformer<>(
                        VisualizerInfo.class, HibernateVisualizerInfo.class, HibernateMapper.class
                ),
                HibernateVisualizerInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<VisualizerInfo, HibernateVisualizerInfo> visualizerInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        VisualizerInfo.class, HibernateVisualizerInfo.class, HibernateMapper.class
                ),
                HibernateVisualizerInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<VisualizerInfo, HibernateVisualizerInfo> visualizerInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        VisualizerInfo.class, HibernateVisualizerInfo.class, HibernateMapper.class
                ),
                HibernateVisualizerInfo.class,
                visualizerInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, VisualizerSupport, HibernateVisualizerSupport>
    visualizerSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        VisualizerSupport.class, HibernateVisualizerSupport.class, HibernateMapper.class
                ),
                HibernateVisualizerSupport.class
        );
    }

    @Bean
    public HibernateEntireLookupDao<VisualizerSupport, HibernateVisualizerSupport>
    visualizerSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        VisualizerSupport.class, HibernateVisualizerSupport.class, HibernateMapper.class
                ),
                HibernateVisualizerSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<VisualizerSupport, HibernateVisualizerSupport>
    visualizerSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        VisualizerSupport.class, HibernateVisualizerSupport.class, HibernateMapper.class
                ),
                HibernateVisualizerSupport.class,
                visualizerSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<ExecutorKey, HibernateExecutorKey, ExecutorInfo, HibernateExecutorInfo>
    executorInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ExecutorKey.class, HibernateExecutorKey.class, HibernateMapper.class
                ),
                new MapStructBeanTransformer<>(
                        ExecutorInfo.class, HibernateExecutorInfo.class, HibernateMapper.class
                ),
                HibernateExecutorInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<ExecutorInfo, HibernateExecutorInfo> executorInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ExecutorInfo.class, HibernateExecutorInfo.class, HibernateMapper.class
                ),
                HibernateExecutorInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ExecutorInfo, HibernateExecutorInfo> executorInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ExecutorInfo.class, HibernateExecutorInfo.class, HibernateMapper.class
                ),
                HibernateExecutorInfo.class,
                executorInfoPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<StringIdKey, HibernateStringIdKey, ExecutorSupport, HibernateExecutorSupport>
    executorSupportHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(StringIdKey.class, HibernateStringIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(
                        ExecutorSupport.class, HibernateExecutorSupport.class, HibernateMapper.class
                ),
                HibernateExecutorSupport.class
        );
    }

    @Bean
    public HibernateEntireLookupDao<ExecutorSupport, HibernateExecutorSupport>
    executorSupportHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ExecutorSupport.class, HibernateExecutorSupport.class, HibernateMapper.class
                ),
                HibernateExecutorSupport.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<ExecutorSupport, HibernateExecutorSupport>
    executorSupportHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(
                        ExecutorSupport.class, HibernateExecutorSupport.class, HibernateMapper.class
                ),
                HibernateExecutorSupport.class,
                executorSupportPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Session, HibernateSession>
    sessionHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Session.class, HibernateSession.class, HibernateMapper.class),
                HibernateSession.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Session, HibernateSession> sessionHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Session.class, HibernateSession.class, HibernateMapper.class),
                HibernateSession.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Session, HibernateSession> sessionHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Session.class, HibernateSession.class, HibernateMapper.class),
                HibernateSession.class,
                sessionPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<VariableKey, HibernateVariableKey, Variable, HibernateVariable>
    variableHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(VariableKey.class, HibernateVariableKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Variable.class, HibernateVariable.class, HibernateMapper.class),
                HibernateVariable.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Variable, HibernateVariable> variableHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Variable.class, HibernateVariable.class, HibernateMapper.class),
                HibernateVariable.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Variable, HibernateVariable> variableHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Variable.class, HibernateVariable.class, HibernateMapper.class),
                HibernateVariable.class,
                variablePresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, Task, HibernateTask> taskHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(Task.class, HibernateTask.class, HibernateMapper.class),
                HibernateTask.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<Task, HibernateTask> taskHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Task.class, HibernateTask.class, HibernateMapper.class),
                HibernateTask.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<Task, HibernateTask> taskHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(Task.class, HibernateTask.class, HibernateMapper.class),
                HibernateTask.class,
                taskPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<TaskItemKey, HibernateTaskItemKey, InputItem, HibernateInputItem>
    inputItemHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(TaskItemKey.class, HibernateTaskItemKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(InputItem.class, HibernateInputItem.class, HibernateMapper.class),
                HibernateInputItem.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<InputItem, HibernateInputItem> inputItemHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(InputItem.class, HibernateInputItem.class, HibernateMapper.class),
                HibernateInputItem.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<InputItem, HibernateInputItem> inputItemHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(InputItem.class, HibernateInputItem.class, HibernateMapper.class),
                HibernateInputItem.class,
                inputItemPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<TaskItemKey, HibernateTaskItemKey, OutputItem, HibernateOutputItem>
    outputItemHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(TaskItemKey.class, HibernateTaskItemKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(OutputItem.class, HibernateOutputItem.class, HibernateMapper.class),
                HibernateOutputItem.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<OutputItem, HibernateOutputItem> outputItemHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(OutputItem.class, HibernateOutputItem.class, HibernateMapper.class),
                HibernateOutputItem.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<OutputItem, HibernateOutputItem> outputItemHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(OutputItem.class, HibernateOutputItem.class, HibernateMapper.class),
                HibernateOutputItem.class,
                outputItemPresetCriteriaMaker
        );
    }

    @Bean
    public HibernateBatchBaseDao<LongIdKey, HibernateLongIdKey, FileInfo, HibernateFileInfo>
    fileInfoHibernateBatchBaseDao() {
        return new HibernateBatchBaseDao<>(
                template,
                new MapStructBeanTransformer<>(LongIdKey.class, HibernateLongIdKey.class, HibernateMapper.class),
                new MapStructBeanTransformer<>(FileInfo.class, HibernateFileInfo.class, HibernateMapper.class),
                HibernateFileInfo.class,
                new DefaultDeletionMod<>(),
                batchSize
        );
    }

    @Bean
    public HibernateEntireLookupDao<FileInfo, HibernateFileInfo> fileInfoHibernateEntireLookupDao() {
        return new HibernateEntireLookupDao<>(
                template,
                new MapStructBeanTransformer<>(FileInfo.class, HibernateFileInfo.class, HibernateMapper.class),
                HibernateFileInfo.class
        );
    }

    @Bean
    public HibernatePresetLookupDao<FileInfo, HibernateFileInfo> fileInfoHibernatePresetLookupDao() {
        return new HibernatePresetLookupDao<>(
                template,
                new MapStructBeanTransformer<>(FileInfo.class, HibernateFileInfo.class, HibernateMapper.class),
                HibernateFileInfo.class,
                fileInfoPresetCriteriaMaker
        );
    }
}
