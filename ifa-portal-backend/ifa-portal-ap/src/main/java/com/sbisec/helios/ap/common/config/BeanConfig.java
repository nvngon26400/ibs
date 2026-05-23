package com.sbisec.helios.ap.common.config;

import java.time.Duration;

import com.sbisec.helios.ap.admin.dao.AdminDao;
import com.sbisec.helios.ap.admin.dao.impl.AdminDaoImpl;
import com.sbisec.helios.ap.admin.service.AdminService;
import com.sbisec.helios.ap.admin.service.impl.AdminServiceImplL;
import com.sbisec.helios.ap.common.dao.AccControlDao;
import com.sbisec.helios.ap.common.dao.BranchDao;
import com.sbisec.helios.ap.common.dao.MCodeDao;
import com.sbisec.helios.ap.common.dao.MedGovMenuDao;
import com.sbisec.helios.ap.common.dao.MedUsersDao;
import com.sbisec.helios.ap.common.dao.MediateBranchDao;
import com.sbisec.helios.ap.common.dao.MediateChargeInfoDao;
import com.sbisec.helios.ap.common.dao.MediateUserAttributeInfoDao;
import com.sbisec.helios.ap.common.dao.OrderCheckDao;
import com.sbisec.helios.ap.common.dao.ServiceStatusDao;
import com.sbisec.helios.ap.common.dao.impl.AccControlDaoImpl;
import com.sbisec.helios.ap.common.dao.impl.BranchDaoImpl;
import com.sbisec.helios.ap.common.dao.impl.MCodeDaoImpl;
import com.sbisec.helios.ap.common.dao.impl.MedGovMenuDaoImpl;
import com.sbisec.helios.ap.common.dao.impl.MedUsersDaoImpl;
import com.sbisec.helios.ap.common.dao.impl.MediateBranchDaoImpl;
import com.sbisec.helios.ap.common.dao.impl.MediateChargeInfoDaoImpl;
import com.sbisec.helios.ap.common.dao.impl.MediateUserAttributeInfoDaoImpl;
import com.sbisec.helios.ap.common.dao.impl.OrderCheckDaoImpl;
import com.sbisec.helios.ap.common.dao.impl.ServiceStatusDaoImpl;
import com.sbisec.helios.ap.common.service.MCodeService;
import com.sbisec.helios.ap.common.service.ReflectionConfigService;
import com.sbisec.helios.ap.common.service.ServiceStatusService;
import com.sbisec.helios.ap.common.service.UserAdministrationService;
import com.sbisec.helios.ap.common.service.impl.MCodeServiceImpl;
import com.sbisec.helios.ap.common.service.impl.ReflectionConfigServiceImpl;
import com.sbisec.helios.ap.common.service.impl.ServiceStatusServiceImpl;
import com.sbisec.helios.ap.common.service.impl.UserAdministrationServiceImplLEx;

import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

@Configuration
public class BeanConfig {
    
    /**
     * ログ出力用のフィルタ
     *
     * @return ログ出力用のフィルタのインスタンス
     */
    @Bean
    public CommonsRequestLoggingFilter requestLoggingFilter() {
        
        CommonsRequestLoggingFilter filter = new CommonsRequestLoggingFilter();
        filter.setIncludeClientInfo(true);
        filter.setIncludeQueryString(true);
        filter.setIncludeHeaders(true);
        filter.setIncludePayload(true);
        filter.setMaxPayloadLength(1024);
        return filter;
    }
    
    /**
     * RedisCacheManager
     *
     * @param redisConnectionFactory RedisCacheManagerを生成するためのFactoryオブジェクト
     * @return RedisCacheManagerのインスタンス 
     */
    @Bean
    public CacheManager cacheManager(RedisConnectionFactory redisConnectionFactory) {
        
        // RedisCacheConfigurationの定義
        RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofDays(1));
        
        RedisCacheManager.RedisCacheManagerBuilder builder = RedisCacheManager.builder(redisConnectionFactory)
                .cacheDefaults(redisCacheConfiguration);
        
        return builder.build();
    }
}
