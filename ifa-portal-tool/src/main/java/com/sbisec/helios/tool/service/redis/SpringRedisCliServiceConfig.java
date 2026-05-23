package com.sbisec.helios.tool.service.redis;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * spring Redis CLIサービス設定クラス。
 * 
 * @author SCSK宮坂
 */
@Configuration
public class SpringRedisCliServiceConfig {
	/** Bean名（SpringRedisCliProperties） */
	public static final String BEAN_NAME_PROPERTIES = "SpringRedisCliService_springRedisCliProperties";
	/** Bean名（JedisConnectionFactory） */
	public static final String BEAN_NAME_CONNECTION_FACTORY = "SpringRedisCliService_jedisConnectionFactory";
	/** Bean名（CacheManager） */
	public static final String BEAN_NAME_CACHE_MANAGER = "SpringRedisCliService_cacheManager";

	/**
	 * Beanを登録する（SpringRedisCliProperties）。
	 * 
	 * @return SpringRedisCliPropertiesのBeanインスタンス。
	 */
	@Bean(name = BEAN_NAME_PROPERTIES)
	@Primary
	public SpringRedisCliProperties springRedisCliProperties() {
		return new SpringRedisCliProperties();
	}

	/**
	 * Beanを登録する（JedisConnectionFactory）。
	 * 
	 * @return JedisConnectionFactoryのBeanインスタンス。
	 */
	@Bean(name = BEAN_NAME_CONNECTION_FACTORY)
	@Primary
	public JedisConnectionFactory jedisConnectionFactory(@Qualifier(BEAN_NAME_PROPERTIES) SpringRedisCliProperties springRedisCliProperties) {
		// Jedis接続ファクトリを生成
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();

		// 接続関連のシステムプロパティを反映
		jedisConnectionFactory.getStandaloneConfiguration().setHostName(springRedisCliProperties.getHostName());
		jedisConnectionFactory.getStandaloneConfiguration().setPort(springRedisCliProperties.getPortNo());
		jedisConnectionFactory.getStandaloneConfiguration().setPassword(springRedisCliProperties.getPassword());
		jedisConnectionFactory.getStandaloneConfiguration().setDatabase(springRedisCliProperties.getDatabaseIndexNo());

		return jedisConnectionFactory;
	}

	/**
	 * Beanを登録する（CacheManager）。
	 * 
	 * @return CacheManagerのBeanインスタンス。
	 */
	@Bean(name = BEAN_NAME_CACHE_MANAGER)
	@Primary
	public CacheManager cacheManager(@Qualifier(BEAN_NAME_PROPERTIES) SpringRedisCliProperties springRedisCliProperties,
			@Qualifier(BEAN_NAME_CONNECTION_FACTORY) JedisConnectionFactory jedisConnectionFactory) {
		// デフォルトのRedisキャッシュ設定にシステムプロパティを反映
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
				.entryTtl(Duration.ofSeconds(springRedisCliProperties.getCacheTtlSec()));

		// Redisキャッシュマネージャーを構築
		return RedisCacheManager.builder(jedisConnectionFactory).cacheDefaults(redisCacheConfiguration).build();
	}
}