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
import com.dwarfeng.toolhouse.impl.bean.key.HibernateFavoriteKey;
import com.dwarfeng.toolhouse.impl.bean.key.HibernatePocaKey;
import com.dwarfeng.toolhouse.impl.dao.preset.*;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
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

    @Value("${hibernate.jdbc.batch_size}")
    private int batchSize;

    public DaoConfiguration(
            HibernateTemplate template,
            PocaPresetCriteriaMaker pocaPresetCriteriaMaker,
            CabinetPresetCriteriaMaker cabinetPresetCriteriaMaker,
            FolderPresetCriteriaMaker folderPresetCriteriaMaker,
            ToolPresetCriteriaMaker toolPresetCriteriaMaker,
            FavoritePresetCriteriaMaker favoritePresetCriteriaMaker
    ) {
        this.template = template;
        this.pocaPresetCriteriaMaker = pocaPresetCriteriaMaker;
        this.cabinetPresetCriteriaMaker = cabinetPresetCriteriaMaker;
        this.folderPresetCriteriaMaker = folderPresetCriteriaMaker;
        this.toolPresetCriteriaMaker = toolPresetCriteriaMaker;
        this.favoritePresetCriteriaMaker = favoritePresetCriteriaMaker;
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
}
