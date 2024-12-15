package com.dwarfeng.toolhouse.impl.configuration;

import com.dwarfeng.subgrade.impl.bean.MapStructBeanTransformer;
import com.dwarfeng.subgrade.impl.cache.RedisBatchBaseCache;
import com.dwarfeng.subgrade.sdk.redis.formatter.LongIdStringKeyFormatter;
import com.dwarfeng.subgrade.sdk.redis.formatter.StringIdStringKeyFormatter;
import com.dwarfeng.subgrade.stack.bean.key.LongIdKey;
import com.dwarfeng.subgrade.stack.bean.key.StringIdKey;
import com.dwarfeng.toolhouse.sdk.bean.FastJsonMapper;
import com.dwarfeng.toolhouse.sdk.bean.entity.*;
import com.dwarfeng.toolhouse.sdk.bean.key.formatter.FavoriteStringKeyFormatter;
import com.dwarfeng.toolhouse.sdk.bean.key.formatter.PocaStringKeyFormatter;
import com.dwarfeng.toolhouse.stack.bean.entity.*;
import com.dwarfeng.toolhouse.stack.bean.key.FavoriteKey;
import com.dwarfeng.toolhouse.stack.bean.key.PocaKey;
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

    public CacheConfiguration(RedisTemplate<String, ?> template) {
        this.template = template;
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<StringIdKey, User, FastJsonUser> userRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonUser>) template,
                new StringIdStringKeyFormatter(userPrefix),
                new MapStructBeanTransformer<>(User.class, FastJsonUser.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<PocaKey, Poca, FastJsonPoca> pocaRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonPoca>) template,
                new PocaStringKeyFormatter(pocaPrefix),
                new MapStructBeanTransformer<>(Poca.class, FastJsonPoca.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Cabinet, FastJsonCabinet> cabinetRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonCabinet>) template,
                new LongIdStringKeyFormatter(cabinetPrefix),
                new MapStructBeanTransformer<>(Cabinet.class, FastJsonCabinet.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Folder, FastJsonFolder> folderRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFolder>) template,
                new LongIdStringKeyFormatter(folderPrefix),
                new MapStructBeanTransformer<>(Folder.class, FastJsonFolder.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<LongIdKey, Tool, FastJsonTool> toolRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonTool>) template,
                new LongIdStringKeyFormatter(toolPrefix),
                new MapStructBeanTransformer<>(Tool.class, FastJsonTool.class, FastJsonMapper.class)
        );
    }

    @Bean
    @SuppressWarnings("unchecked")
    public RedisBatchBaseCache<FavoriteKey, Favorite, FastJsonFavorite> favoriteRedisBatchBaseCache() {
        return new RedisBatchBaseCache<>(
                (RedisTemplate<String, FastJsonFavorite>) template,
                new FavoriteStringKeyFormatter(favoritePrefix),
                new MapStructBeanTransformer<>(Favorite.class, FastJsonFavorite.class, FastJsonMapper.class)
        );
    }
}
