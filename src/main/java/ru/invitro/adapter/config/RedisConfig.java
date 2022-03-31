package ru.invitro.adapter.config;

import io.lettuce.core.ReadFrom;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;

import java.time.Duration;

/**
 * Конфигурация кластера Redis.
 */
@Configuration
public class RedisConfig {

    /**
     *
     * @param properties {@link RedisProperties}
     * @return {@link RedisClusterConfiguration}
     */
    @Bean
    public RedisClusterConfiguration redisClusterConfiguration(RedisProperties properties) {
        RedisClusterConfiguration clusterConfiguration = new RedisClusterConfiguration(properties.getCluster().getNodes());
        clusterConfiguration.setMaxRedirects(properties.getCluster().getMaxRedirects());

        return clusterConfiguration;
    }

    /**
     *
     * @param redisConfiguration {@link RedisClusterConfiguration}
     * @return {@link LettuceConnectionFactory}
     */
    @Bean
    public LettuceConnectionFactory redisConnectionFactory(RedisClusterConfiguration redisConfiguration) {
        LettuceClientConfiguration clientConfig = LettuceClientConfiguration.builder()
                .readFrom(ReadFrom.REPLICA_PREFERRED).build();

        LettuceConnectionFactory cf = new LettuceConnectionFactory(redisConfiguration, clientConfig);
        cf.setShareNativeConnection(true);

        return cf;
    }

    /**
     *
     * @param connectionFactory {@link RedisConnectionFactory}
     * @return {@link RedisCacheManager}
     */
    @Bean
    public RedisCacheManager redisCacheManager(RedisConnectionFactory connectionFactory) {
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .disableCachingNullValues()
                .entryTtl(Duration.ofMinutes(1))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(RedisSerializer.byteArray()));
        redisCacheConfiguration.usePrefix();

        return RedisCacheManager.RedisCacheManagerBuilder.fromConnectionFactory(connectionFactory)
                .cacheDefaults(redisCacheConfiguration).build();
    }
}