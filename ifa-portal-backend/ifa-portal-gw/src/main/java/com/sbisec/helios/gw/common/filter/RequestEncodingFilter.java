package com.sbisec.helios.gw.common.filter;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.cache.CacheManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbisec.helios.ap.common.util.RedisCacheHelper;
import com.sbisec.helios.ap.common.util.RedisCacheHelper.RedisCachedJsonConfig;

import lombok.Data;

/**
 * リクエストエンコーディングフィルタークラス。<br>
 * 文字化け防止のためにリクエストボディの文字コードを指定する。<br>
 * HeliosではJSON形式のリクエストがメインであり、またJSONではUTF-8が標準となるが<br>
 * FAQでform形式のリクエストが使用される可能性があり、必ずしも文字コードが固定されるとは限らないため当フィルタを適用する。<br>
 *
 * @author SCSK宮坂
 */
public class RequestEncodingFilter implements Filter {
    
    /** リクエストエンコーディングフィルタ設定クラス */
    @Data
    private static class RequestEncodingFilterConfig implements RedisCachedJsonConfig {
        
        /** エンコーディング */
        @JsonProperty(value = "encoding")
        private String encoding = StandardCharsets.UTF_8.name();
    }
    
    /** Redisキャッシュキー（パラメータログ設定） */
    private static final String REDIS_CACHE_KEY_FILTER_CONFIG = "requestBodyEncodingFilter";
    
    /** リクエストエンコーディングフィルタ設定 */
    private RequestEncodingFilterConfig requestEncodingFilterConfig = null;
    
    /**
     * コンストラクタ。
     *
     * @param cacheManager キャッシュマネージャー。
     */
    public RequestEncodingFilter(CacheManager cacheManager) {
        
        this.requestEncodingFilterConfig = new RedisCacheHelper(cacheManager)
                .getJsonConfig(REDIS_CACHE_KEY_FILTER_CONFIG, RequestEncodingFilterConfig.class);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        // エンコーディングを設定
        request.setCharacterEncoding(requestEncodingFilterConfig.getEncoding());
        
        // 次のフィルタ処理へ連携
        chain.doFilter(request, response);
    }
}
