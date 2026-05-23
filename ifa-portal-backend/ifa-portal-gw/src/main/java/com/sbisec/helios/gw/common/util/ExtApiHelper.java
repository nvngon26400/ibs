package com.sbisec.helios.gw.common.util;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbisec.helios.ap.common.util.RedisCacheHelper;
import com.sbisec.helios.ap.common.util.RedisCacheHelper.RedisCachedJsonConfig;
import com.sbisec.helios.gw.common.util.ExtApiHelper.ExtApiAuthConfig.ExtApiAuthConfigInfo;

import lombok.Data;

/**
 * 外部API向けヘルパークラス。
 *
 * @author SCSK宮坂
 */
public class ExtApiHelper {
    
    /** 外部API向け認証HTTPヘッダクラス */
    @Data
    public static class ExtApiAuthHttpHeader {
        
        /** システムID */
        String systemId = null;
        
        /** ユーザID */
        String userId = null;
        
        /** セッションID */
        String sessionId = null;
        
        /** 認証トークン */
        String authToken = null;
    }
    
    /** 外部API向け認証設定クラス */
    @Data
    public static class ExtApiAuthConfig implements RedisCachedJsonConfig {
        
        /** 認証設定情報クラス */
        @Data
        public static class ExtApiAuthConfigInfo {
            
            /** ユーザID */
            @JsonProperty(value = "userId")
            String userId = null;
            
            /** セッションID */
            @JsonProperty(value = "sessionId")
            String sessionId = null;
            
            /** 認証トークン */
            @JsonProperty(value = "authToken")
            String authToken = null;
        }
        
        /** 認証情報設定情報マップ */
        @JsonProperty(value = "systemIds")
        private Map<String, ExtApiAuthConfigInfo> authConfigInfoMap = null;
    }
    
    /** 外部API向けアクセス制御設定クラス */
    @Data
    public static class ExtApiAccessCtrlConfig implements RedisCachedJsonConfig {
        
        /** ホワイトリストマップ */
        @JsonProperty(value = "systemIds")
        private Map<String, List<String>> whiteListMap = null;
    }
    
    /** HTTPヘッダ名（外部API向けシステムID） */
    public static final String HTTP_HEADER_NM_SYSTEM_ID = "Ifap-Ext-Api-System-Id";
    
    /** HTTPヘッダ名（外部API向けユーザID） */
    public static final String HTTP_HEADER_NM_USER_ID = "Ifap-Ext-Api-User-Id";
    
    /** HTTPヘッダ名（外部API向けセッションID） */
    public static final String HTTP_HEADER_NM_SESSION_ID = "Ifap-Ext-Api-Session-Id";
    
    /** HTTPヘッダ名（外部API向け認証トークン） */
    public static final String HTTP_HEADER_NM_AUTH_TOKEN = "Ifap-Ext-Api-Auth-Token";
    
    /** Redisキャッシュキー（外部API向け認証設定） */
    public static final String REDIS_CACHE_KEY_EXT_API_AUTH_CONFIG = "extApi:authentication";
    
    /** Redisキャッシュキー（外部API向けアクセス制御設定） */
    public static final String REDIS_CACHE_KEY_EXT_API_ACCESS_CTRL_CONFIG = "extApi:accessControl";
    
    /** 外部API向け認証設定 */
    private ExtApiAuthConfig extApiAuthConfig = null;
    
    /** 外部API向けアクセス制御設定 */
    private ExtApiAccessCtrlConfig extApiAccessCtrlConfig = null;
    
    /**
     * コンストラクタ。
     * 
     * @param cacheManager キャッシュマネージャー。
     */
    public ExtApiHelper(CacheManager cacheManager) {
        
        this.extApiAuthConfig = new RedisCacheHelper(cacheManager).getJsonConfig(REDIS_CACHE_KEY_EXT_API_AUTH_CONFIG,
                ExtApiAuthConfig.class);
        this.extApiAccessCtrlConfig = new RedisCacheHelper(cacheManager)
                .getJsonConfig(REDIS_CACHE_KEY_EXT_API_ACCESS_CTRL_CONFIG, ExtApiAccessCtrlConfig.class);
    }
    
    /**
     * 外部API向け認証HTTPヘッダを取得する。
     * 
     * @return 外部API向け認証HTTPヘッダ。対応するヘッダが存在しないフィールドはnullとなる。
     */
    public ExtApiAuthHttpHeader getAuthHttpHeader(HttpServletRequest httpServletRequest) {
        
        ExtApiAuthHttpHeader extApiAuthHttpHeader = new ExtApiAuthHttpHeader();
        
        // ヘッダ情報から外部API向けシステムIDを設定
        extApiAuthHttpHeader.setSystemId(httpServletRequest.getHeader(HTTP_HEADER_NM_SYSTEM_ID));
        extApiAuthHttpHeader.setUserId(httpServletRequest.getHeader(HTTP_HEADER_NM_USER_ID));
        extApiAuthHttpHeader.setSessionId(httpServletRequest.getHeader(HTTP_HEADER_NM_SESSION_ID));
        extApiAuthHttpHeader.setAuthToken(httpServletRequest.getHeader(HTTP_HEADER_NM_AUTH_TOKEN));
        
        return extApiAuthHttpHeader;
    }
    
    /**
     * リクエストが外部APIかどうかを判定する。
     * 
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @return 外部APIリクエスト判定フラグ（true：外部APIリクエストである、false：外部APIリクエストではない）
     */
    public boolean isRequestedExtApi(ExtApiAuthHttpHeader extApiAuthHttpHeader) {
        
        boolean isRequestedExtApi = false;
        
        // 外部API向けシステムIDヘッダが指定されている場合は外部API向けAPIとみなす
        if (extApiAuthHttpHeader.getSystemId() != null) {
            isRequestedExtApi = true;
        }
        
        return isRequestedExtApi;
    }
    
    /**
     * Redisキャッシュから該当システムIDの外部API向け認証設定情報を取得する。
     * 
     * @return 外部API向け認証設定情報。存在しない場合はnullとなる。
     */
    public ExtApiAuthConfigInfo getAuthConfigInfo(ExtApiAuthHttpHeader extApiAuthHttpHeader) {
        
        ExtApiAuthConfigInfo extApiAuthConfigInfo = null;
        
        // 外部API向けシステムIDがヘッダに指定されている場合
        if (!StringUtils.isEmpty(extApiAuthHttpHeader.getSystemId())) {
            // 認証設定が存在する場合
            if (extApiAuthConfig.getAuthConfigInfoMap() != null) {
                // 該当システムIDの認証設定を取得
                extApiAuthConfigInfo = extApiAuthConfig.getAuthConfigInfoMap().get(extApiAuthHttpHeader.getSystemId());
            }
        }
        
        return extApiAuthConfigInfo;
    }
    
    /**
     * Redisキャッシュから該当システムIDの外部API向けアクセス制御設定にあるホワイトリストを取得する。
     * 
     * @return 外部API向けホワイトリスト。存在しない場合はnullとなる。
     */
    public List<String> getWhiteList(ExtApiAuthHttpHeader extApiAuthHttpHeader) {
        
        List<String> whiteList = null;
        
        // 外部API向けシステムIDがヘッダに指定されている場合
        if (!StringUtils.isEmpty(extApiAuthHttpHeader.getSystemId())) {
            // ホワイトリスト設定が存在する場合
            if (extApiAccessCtrlConfig.getWhiteListMap() != null) {
                // 該当システムIDのホワイトリスト設定を取得
                whiteList = extApiAccessCtrlConfig.getWhiteListMap().get(extApiAuthHttpHeader.getSystemId());
            }
        }
        
        return whiteList;
    }
}
