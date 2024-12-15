package com.dwarfeng.toolhouse.impl.configuration;

import com.dwarfeng.subgrade.impl.generation.ExceptionKeyGenerator;
import com.dwarfeng.subgrade.impl.service.CustomBatchCrudService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyEntireLookupService;
import com.dwarfeng.subgrade.impl.service.DaoOnlyPresetLookupService;
import com.dwarfeng.subgrade.impl.service.GeneralBatchCrudService;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.subgrade.stack.log.LogLevel;
import com.dwarfeng.toolhouse.impl.service.operation.CabinetCrudOperation;
import com.dwarfeng.toolhouse.impl.service.operation.FolderCrudOperation;
import com.dwarfeng.toolhouse.impl.service.operation.ToolCrudOperation;
import com.dwarfeng.toolhouse.impl.service.operation.UserCrudOperation;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
import com.dwarfeng.toolhouse.stack.cache.FavoriteCache;
import com.dwarfeng.toolhouse.stack.cache.PocaCache;
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

    @Value("${cache.timeout.entity.poca}")
    private long pocaTimeout;
    @Value("${cache.timeout.entity.favorite}")
    private long favoriteTimeout;

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
            FavoriteCache favoriteCache
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
}
