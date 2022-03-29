package cn.ridup.tool.yuquehooks.config;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import com.fasterxml.jackson.databind.ObjectMapper;

import cn.ridup.tool.yuquehooks.cache.AbstractStringCacheStore;
import cn.ridup.tool.yuquehooks.cache.InMemoryCacheStore;
import cn.ridup.tool.yuquehooks.cache.LevelCacheStore;
import cn.ridup.tool.yuquehooks.cache.RedisCacheStore;
import cn.ridup.tool.yuquehooks.config.properties.YuqueProperties;
import cn.ridup.tool.yuquehooks.utils.HttpClientUtils;
import lombok.extern.slf4j.Slf4j;
/**
 * Yuque configuration.
 *
 * @author johnniang
 */
@Slf4j
@EnableAsync
@EnableCaching
@EnableScheduling
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(YuqueProperties.class)
public class YuqueHooksConfiguration {

    private final YuqueProperties yuqueProperties;

    private final StringRedisTemplate stringRedisTemplate;

    public YuqueHooksConfiguration(YuqueProperties yuqueProperties,
        StringRedisTemplate stringRedisTemplate) {
        this.yuqueProperties = yuqueProperties;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @Bean
    ObjectMapper objectMapper(Jackson2ObjectMapperBuilder builder) {
        builder.failOnEmptyBeans(false);
        return builder.build();
    }

    @Bean
    RestTemplate httpsRestTemplate(RestTemplateBuilder builder)
        throws NoSuchAlgorithmException, KeyStoreException, KeyManagementException {
        RestTemplate httpsRestTemplate = builder.build();
        httpsRestTemplate.setRequestFactory(
            new HttpComponentsClientHttpRequestFactory(HttpClientUtils.createHttpsClient(
                (int) yuqueProperties.getDownloadTimeout().toMillis())));
        return httpsRestTemplate;
    }

    @Bean
    @ConditionalOnMissingBean
    AbstractStringCacheStore stringCacheStore() {
        AbstractStringCacheStore stringCacheStore;
        switch (yuqueProperties.getCache()) {
            case "level":
                stringCacheStore = new LevelCacheStore(this.yuqueProperties);
                break;
            case "redis":
                stringCacheStore = new RedisCacheStore(stringRedisTemplate);
                break;
            case "memory":
            default:
                stringCacheStore = new InMemoryCacheStore();
                break;
        }
        log.info("Yuque cache store load impl : [{}]", stringCacheStore.getClass());
        return stringCacheStore;
    }
}
