package com.dwarfeng.toolhouse.impl.configuration;

import com.dwarfeng.subgrade.impl.generation.ExceptionKeyGenerator;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.impl.service.operation.*;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.*;
import com.dwarfeng.toolhouse.stack.cache.*;
import com.dwarfeng.toolhouse.stack.dao.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfiguration {

    private final ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration;
    private final GenerateConfiguration generateConfiguration;

    private final UserCrudOperation userCrudOperation;
    private final PocaDao pocaDao;
    private final PocaCache pocaCache;
    private final CabinetCrudOperation cabinetCrudOperation;
    private final CabinetDao cabinetDao;
    private final FolderCrudOperation folderCrudOperation;
    private final FolderDao folderDao;
    private final ToolCrudOperation toolCrudOperation;
    private final ToolDao toolDao;
    private final FavoriteDao favoriteDao;
    private final FavoriteCache favoriteCache;
    private final VisualizerInfoDao visualizerInfoDao;
    private final VisualizerInfoCache visualizerInfoCache;
    private final VisualizerSupportCache visualizerSupportCache;
    private final VisualizerSupportDao visualizerSupportDao;
    private final ExecutorInfoDao executorInfoDao;
    private final ExecutorInfoCache executorInfoCache;
    private final ExecutorSupportCache executorSupportCache;
    private final ExecutorSupportDao executorSupportDao;
    private final SessionCrudOperation sessionCrudOperation;
    private final SessionDao sessionDao;
    private final VariableCrudOperation variableCrudOperation;
    private final VariableDao variableDao;
    private final TaskCrudOperation taskCrudOperation;
    private final TaskDao taskDao;
    private final InputItemCrudOperation inputItemCrudOperation;
    private final InputItemDao inputItemDao;
    private final OutputItemCrudOperation outputItemCrudOperation;
    private final OutputItemDao outputItemDao;
    private final FileInfoCrudOperation fileInfoCrudOperation;
    private final FileInfoDao fileInfoDao;

    @Value("${cache.timeout.entity.poca}")
    private long pocaTimeout;
    @Value("${cache.timeout.entity.favorite}")
    private long favoriteTimeout;
    @Value("${cache.timeout.entity.visualizer_info}")
    private long visualizerInfoTimeout;
    @Value("${cache.timeout.entity.visualizer_support}")
    private long visualizerSupportTimeout;
    @Value("${cache.timeout.entity.executor_info}")
    private long executorInfoTimeout;
    @Value("${cache.timeout.entity.executor_support}")
    private long executorSupportTimeout;

    public ServiceConfiguration(
            ServiceExceptionMapperConfiguration serviceExceptionMapperConfiguration,
            GenerateConfiguration generateConfiguration,
            UserCrudOperation userCrudOperation,
            PocaDao pocaDao,
            PocaCache pocaCache,
            CabinetCrudOperation cabinetCrudOperation,
            CabinetDao cabinetDao,
            FolderCrudOperation folderCrudOperation,
            FolderDao folderDao,
            ToolCrudOperation toolCrudOperation,
            ToolDao toolDao,
            FavoriteDao favoriteDao,
            FavoriteCache favoriteCache,
            VisualizerInfoDao visualizerInfoDao,
            VisualizerInfoCache visualizerInfoCache,
            VisualizerSupportCache visualizerSupportCache,
            VisualizerSupportDao visualizerSupportDao,
            ExecutorInfoDao executorInfoDao,
            ExecutorInfoCache executorInfoCache,
            ExecutorSupportCache executorSupportCache,
            ExecutorSupportDao executorSupportDao,
            SessionCrudOperation sessionCrudOperation,
            SessionDao sessionDao,
            VariableCrudOperation variableCrudOperation,
            VariableDao variableDao,
            TaskCrudOperation taskCrudOperation,
            TaskDao taskDao,
            InputItemCrudOperation inputItemCrudOperation,
            InputItemDao inputItemDao,
            OutputItemCrudOperation outputItemCrudOperation,
            OutputItemDao outputItemDao,
            FileInfoCrudOperation fileInfoCrudOperation,
            FileInfoDao fileInfoDao
    ) {
        this.serviceExceptionMapperConfiguration = serviceExceptionMapperConfiguration;
        this.generateConfiguration = generateConfiguration;
        this.userCrudOperation = userCrudOperation;
        this.pocaDao = pocaDao;
        this.pocaCache = pocaCache;
        this.cabinetCrudOperation = cabinetCrudOperation;
        this.cabinetDao = cabinetDao;
        this.folderCrudOperation = folderCrudOperation;
        this.folderDao = folderDao;
        this.toolCrudOperation = toolCrudOperation;
        this.toolDao = toolDao;
        this.favoriteDao = favoriteDao;
        this.favoriteCache = favoriteCache;
        this.visualizerInfoDao = visualizerInfoDao;
        this.visualizerInfoCache = visualizerInfoCache;
        this.visualizerSupportCache = visualizerSupportCache;
        this.visualizerSupportDao = visualizerSupportDao;
        this.executorInfoDao = executorInfoDao;
        this.executorInfoCache = executorInfoCache;
        this.executorSupportCache = executorSupportCache;
        this.executorSupportDao = executorSupportDao;
        this.sessionCrudOperation = sessionCrudOperation;
        this.sessionDao = sessionDao;
        this.variableCrudOperation = variableCrudOperation;
        this.variableDao = variableDao;
        this.taskCrudOperation = taskCrudOperation;
        this.taskDao = taskDao;
        this.inputItemCrudOperation = inputItemCrudOperation;
        this.inputItemDao = inputItemDao;
        this.outputItemCrudOperation = outputItemCrudOperation;
        this.outputItemDao = outputItemDao;
        this.fileInfoCrudOperation = fileInfoCrudOperation;
        this.fileInfoDao = fileInfoDao;
    }

    @Bean
    public CustomBatchCrudService<StringIdKey, User> userBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                userCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public GeneralBatchCrudService<PocaKey, Poca> pocaGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                pocaDao,
                pocaCache,
                new ExceptionKeyGenerator<>(),
                pocaTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Poca> pocaDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                pocaDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Poca> pocaDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                pocaDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Cabinet> cabinetBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                cabinetCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Cabinet> cabinetDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                cabinetDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Cabinet> cabinetDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                cabinetDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Folder> folderBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                folderCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Folder> folderDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                folderDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Folder> folderDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                folderDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Tool> toolBatchCustomCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                toolCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Tool> toolDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                toolDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Tool> toolDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                toolDao
        );
    }

    @Bean
    public GeneralBatchCrudService<FavoriteKey, Favorite> favoriteGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                favoriteDao,
                favoriteCache,
                new ExceptionKeyGenerator<>(),
                favoriteTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Favorite> favoriteDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                favoriteDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Favorite> favoriteDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                favoriteDao
        );
    }

    @Bean
    public GeneralBatchCrudService<VisualizerKey, VisualizerInfo> visualizerInfoBatchGeneralCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerInfoDao,
                visualizerInfoCache,
                new ExceptionKeyGenerator<>(),
                visualizerInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<VisualizerInfo> visualizerInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<VisualizerInfo> visualizerInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, VisualizerSupport> visualizerSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerSupportDao,
                visualizerSupportCache,
                new ExceptionKeyGenerator<>(),
                visualizerSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<VisualizerSupport> visualizerSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<VisualizerSupport> visualizerSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                visualizerSupportDao
        );
    }

    @Bean
    public GeneralBatchCrudService<ExecutorKey, ExecutorInfo> executorInfoBatchGeneralCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                executorInfoDao,
                executorInfoCache,
                new ExceptionKeyGenerator<>(),
                executorInfoTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ExecutorInfo> executorInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                executorInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ExecutorInfo> executorInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                executorInfoDao
        );
    }

    @Bean
    public GeneralBatchCrudService<StringIdKey, ExecutorSupport> executorSupportGeneralBatchCrudService() {
        return new GeneralBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                executorSupportDao,
                executorSupportCache,
                new ExceptionKeyGenerator<>(),
                executorSupportTimeout
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<ExecutorSupport> executorSupportDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                executorSupportDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<ExecutorSupport> executorSupportDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                executorSupportDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Session> sessionCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sessionCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Session> sessionDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sessionDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Session> sessionDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                sessionDao
        );
    }

    @Bean
    public CustomBatchCrudService<VariableKey, Variable> variableCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                variableCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Variable> variableDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                variableDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Variable> variableDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                variableDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, Task> taskCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<Task> taskDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<Task> taskDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                taskDao
        );
    }

    @Bean
    public CustomBatchCrudService<TaskItemKey, InputItem> inputItemCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                inputItemCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<InputItem> inputItemDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                inputItemDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<InputItem> inputItemDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                inputItemDao
        );
    }

    @Bean
    public CustomBatchCrudService<TaskItemKey, OutputItem> outputItemCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                outputItemCrudOperation,
                new ExceptionKeyGenerator<>()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<OutputItem> outputItemDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                outputItemDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<OutputItem> outputItemDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                outputItemDao
        );
    }

    @Bean
    public CustomBatchCrudService<LongIdKey, FileInfo> fileInfoCustomBatchCrudService() {
        return new CustomBatchCrudService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fileInfoCrudOperation,
                generateConfiguration.snowflakeLongIdKeyGenerator()
        );
    }

    @Bean
    public DaoOnlyEntireLookupService<FileInfo> fileInfoDaoOnlyEntireLookupService() {
        return new DaoOnlyEntireLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fileInfoDao
        );
    }

    @Bean
    public DaoOnlyPresetLookupService<FileInfo> fileInfoDaoOnlyPresetLookupService() {
        return new DaoOnlyPresetLookupService<>(
                serviceExceptionMapperConfiguration.mapServiceExceptionMapper(),
                LogLevel.WARN,
                fileInfoDao
        );
    }
}
