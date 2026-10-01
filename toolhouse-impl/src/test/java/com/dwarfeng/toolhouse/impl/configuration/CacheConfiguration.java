package com.dwarfeng.toolhouse.impl.configuration;

import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.BeanMapper;
import com.dwarfeng.toolhouse.sdk.bean.entity.*;
import com.dwarfeng.toolhouse.sdk.bean.key.formatter.*;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;

@Configuration
public class CacheConfiguration {

    private final RedisTemplate<String, ?> template;

    @Value("${cache.prefix.entity.user}")
    private String userPrefix;
    @Value("${cache.prefix.entity.poca}")
    private String pocaPrefix;
    @Value("${cache.prefix.entity.cabinet}")
    private String cabinetPrefix;
    @Value("${cache.prefix.entity.folder}")
    private String folderPrefix;
    @Value("${cache.prefix.entity.tool}")
    private String toolPrefix;
    @Value("${cache.prefix.entity.favorite}")
    private String favoritePrefix;
    @Value("${cache.prefix.entity.visualizer_info}")
    private String visualizerInfoPrefix;
    @Value("${cache.prefix.entity.visualizer_support}")
    private String visualizerSupportPrefix;
    @Value("${cache.prefix.entity.executor_info}")
    private String executorInfoPrefix;
    @Value("${cache.prefix.entity.executor_support}")
    private String executorSupportPrefix;
    @Value("${cache.prefix.entity.session}")
    private String sessionPrefix;
    @Value("${cache.prefix.entity.variable}")
    private String variablePrefix;
    @Value("${cache.prefix.entity.task}")
    private String taskPrefix;
    @Value("${cache.prefix.entity.input_item}")
    private String inputItemPrefix;
    @Value("${cache.prefix.entity.output_item}")
    private String outputItemPrefix;
    @Value("${cache.prefix.entity.file_info}")
    private String fileInfoPrefix;

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new MapStructBeanTransformer<>(User.class, FastJsonUser.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<PocaKey, Poca, FastJsonPoca> pocaRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPoca>) template,
                new PocaStringKeyFormatter(pocaPrefix),
                new MapStructBeanTransformer<>(Poca.class, FastJsonPoca.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Cabinet, FastJsonCabinet> cabinetRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonCabinet>) template,
                new LongIdStringKeyFormatter(cabinetPrefix),
                new MapStructBeanTransformer<>(Cabinet.class, FastJsonCabinet.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Folder, FastJsonFolder> folderRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFolder>) template,
                new LongIdStringKeyFormatter(folderPrefix),
                new MapStructBeanTransformer<>(Folder.class, FastJsonFolder.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Tool, FastJsonTool> toolRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTool>) template,
                new LongIdStringKeyFormatter(toolPrefix),
                new MapStructBeanTransformer<>(Tool.class, FastJsonTool.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<FavoriteKey, Favorite, FastJsonFavorite> favoriteRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFavorite>) template,
                new FavoriteStringKeyFormatter(favoritePrefix),
                new MapStructBeanTransformer<>(Favorite.class, FastJsonFavorite.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<VisualizerKey, VisualizerInfo, FastJsonVisualizerInfo>
    visualizerInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVisualizerInfo>) template,
                new VisualizerStringKeyFormatter(visualizerInfoPrefix),
                new MapStructBeanTransformer<>(VisualizerInfo.class, FastJsonVisualizerInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, VisualizerSupport, FastJsonVisualizerSupport>
    visualizerSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVisualizerSupport>) template,
                new StringIdStringKeyFormatter(visualizerSupportPrefix),
                new MapStructBeanTransformer<>(
                        VisualizerSupport.class, FastJsonVisualizerSupport.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<ExecutorKey, ExecutorInfo, FastJsonExecutorInfo> executorInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonExecutorInfo>) template,
                new ExecutorStringKeyFormatter(executorInfoPrefix),
                new MapStructBeanTransformer<>(ExecutorInfo.class, FastJsonExecutorInfo.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, ExecutorSupport, FastJsonExecutorSupport>
    executorSupportRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonExecutorSupport>) template,
                new StringIdStringKeyFormatter(executorSupportPrefix),
                new MapStructBeanTransformer<>(
                        ExecutorSupport.class, FastJsonExecutorSupport.class, BeanMapper.class
                )
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Session, FastJsonSession> sessionRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonSession>) template,
                new LongIdStringKeyFormatter(sessionPrefix),
                new MapStructBeanTransformer<>(Session.class, FastJsonSession.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<VariableKey, Variable, FastJsonVariable> variableRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonVariable>) template,
                new VariableStringKeyFormatter(variablePrefix),
                new MapStructBeanTransformer<>(Variable.class, FastJsonVariable.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Task, FastJsonTask> taskRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTask>) template,
                new LongIdStringKeyFormatter(taskPrefix),
                new MapStructBeanTransformer<>(Task.class, FastJsonTask.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<TaskItemKey, InputItem, FastJsonInputItem> inputItemRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonInputItem>) template,
                new TaskItemStringKeyFormatter(inputItemPrefix),
                new MapStructBeanTransformer<>(InputItem.class, FastJsonInputItem.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<TaskItemKey, OutputItem, FastJsonOutputItem> outputItemRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonOutputItem>) template,
                new TaskItemStringKeyFormatter(outputItemPrefix),
                new MapStructBeanTransformer<>(OutputItem.class, FastJsonOutputItem.class, BeanMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, FileInfo, FastJsonFileInfo> fileInfoRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFileInfo>) template,
                new LongIdStringKeyFormatter(fileInfoPrefix),
                new MapStructBeanTransformer<>(FileInfo.class, FastJsonFileInfo.class, BeanMapper.class)
        );
    }
}
