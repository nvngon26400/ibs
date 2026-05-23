package com.sbisec.helios.gw.common.util;

import java.io.Serializable;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.RedisCacheHelper;
import com.sbisec.helios.ap.common.util.RedisCacheHelper.RedisCachedDataObject;
import com.sbisec.helios.ap.common.util.RedisCacheHelper.RedisCachedJsonConfig;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * リクエスト制限ヘルパークラス。<br>
 * Filterクラス等のコンストラクタでの利用を考慮しBean登録は行わない。
 *
 * @author SCSK宮坂
 */
@Slf4j
public class RequestRestrictionHelper {
    
    /** リクエスト制限フィルタ設定クラス */
    @Data
    public static class RequestRestrictionFilterConfig implements RedisCachedJsonConfig {
        
        /** リクエストパスホワイトリスト */
        @JsonProperty(value = "whiteList")
        private List<String> whiteList = null;
        
        /** 最大リクエスト可能回数 */
        @JsonProperty(value = "maxRequestableCount")
        private int maxRequestableCount = 0;
        
        /** チェック間隔（ミリ秒） */
        @JsonProperty(value = "checkIntervalMillis")
        private long checkIntervalMillis = 0;
        
        /** リクエスト制限時待機時間（ミリ秒） */
        @JsonProperty(value = "restrictionWaitTimeMillis")
        private long restrictionWaitTimeMillis = 0;
        
        /** 現在リクエスト状態ロックリトライ間隔（ミリ秒） */
        @JsonProperty(value = "lockRetryIntervalMillis")
        private long lockRetryIntervalMillis = 1;
        
        /** 現在リクエスト状態最大ロック待機時間（ミリ秒） */
        @JsonProperty(value = "maxLockWaitTimeMillis")
        private long maxLockWaitTimeMillis = 0;
    }
    
    /** 現在リクエスト状態クラス */
    @Data
    public static class CurrentRequestState implements RedisCachedDataObject, Serializable {
        
        /** シリアルバージョンUID */
        private static final long serialVersionUID = 1L;
        
        /** フレームワークセッションID */
        private String frameworkSessionId = null;
        
        /** リクエスト回数 */
        private int requestCount = 0;
        
        /** 初回リクエスト時刻 */
        private long firstRequestTimeMillis = 0;
        
        /** 最新リクエスト時刻 */
        private long latestRequestTimeMillis = 0;
        
        /** リクエスト制限済みフラグ */
        private boolean isRestrictedRequest = false;
        
        /** 更新ロック済みフラグ */
        private boolean isLockedForUpdate = false;
    }
    
    /** Redisキャッシュキー（リクエスト制限設定） */
    private static final String REDIS_CACHE_KEY_FILTER_CONFIG = "requestRestrictionFilter";
    
    /** Redisキャッシュキー（現在リクエスト状態） */
    private static final String REDIS_CACHE_KEY_REQUEST_STATE = "currentRequestState";
    
    /** ログフォーマット（リクエスト制限除外パスリスト設定あり） */
    private static final String LOG_FORMAT_CONFIG_FOUND_IGNORE_PATH = "RequestRestrictionFilter() ignore url setting:[{}]";
    
    /** ログフォーマット（最大リクエスト可能回数設定あり） */
    private static final String LOG_FORMAT_CONFIG_FOUND_MAX_REQUESTABLE_COUNT = "RequestRestrictionFilter() maxRequestableCount setting:[{}]";
    
    /** ログフォーマット（最大リクエスト可能回数設定デフォルト値） */
    private static final String LOG_FORMAT_CONFIG_FOUND_MAX_REQUESTABLE_COUNT_DEFAULT = "RequestRestrictionFilter() maxRequestableCount setting:[{}(default)]";
    
    /** キャッシュマネージャー */
    private RedisCacheHelper redisCacheHelper = null;
    
    /** リクエスト制限フィルタ設定 */
    private RequestRestrictionFilterConfig requestRestrictionFilterConfig = null;
    
    /**
     * コンストラクタ。<br>
     *
     * @param cacheManager キャッシュマネージャー。
     */
    public RequestRestrictionHelper(CacheManager cacheManager) {
        
        this.redisCacheHelper = new RedisCacheHelper(cacheManager);
        this.requestRestrictionFilterConfig = redisCacheHelper.getJsonConfig(REDIS_CACHE_KEY_FILTER_CONFIG,
                RequestRestrictionFilterConfig.class);
        
        // 設定内容をログ出力
        printConfigLog();
    }
    
    /**
     * リクエスト制限をスキップするかどうかを判定する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @return リクエスト制限スキップフラグ（true：スキップする、false：スキップしない）
     */
    public boolean isSkippedRestriction(HttpServletRequest httpServletRequest) {
        
        boolean isSkippedRestriction = false;
        
        if (!IfaCommonUtil.hasDebugEnvironmentVariable()) {
            // フレームワークセッションIDを取得
            String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                    String.class);
            
            if (!StringUtils.isEmpty(frameworkSessionId)) {
                if (requestRestrictionFilterConfig.getMaxRequestableCount() < 1) {
                    // 最大リクエスト可能回数が無効値の場合はスキップ
                    isSkippedRestriction = true;
                } else {
                    // リクエスト制限除外パスリストが存在する場合
                    if (requestRestrictionFilterConfig.getWhiteList() != null) {
                        for (String path : requestRestrictionFilterConfig.getWhiteList()) {
                            RequestMatcher requestMatcher = new AntPathRequestMatcher(path);
                            
                            // パスパターンで判定
                            if (requestMatcher.matches(httpServletRequest)) {
                                isSkippedRestriction = true;
                                
                                break;
                            }
                        }
                    }
                }
            } else {
                // フレームワークセッションIDが取得できず判別不能な場合はスキップ
                isSkippedRestriction = true;
            }
        } else {
            // デバッグフラグがONの場合は常にスキップ
            isSkippedRestriction = true;
        }
        
        return isSkippedRestriction;
    }
    
    /**
     * 該当セッションのリクエスト回数をインクリメントする。<br>
     * なお、別途判定メソッドで判断するので、有効なフレームワークセッションIDが取得できる前提とする。
     */
    public void incrementRequestCount() {
        
        // 現在時刻を取得
        long currentTimeMillis = System.currentTimeMillis();
        
        // フレームワークセッションIDを取得
        String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                String.class);
        
        // 更新ロックモードで現在リクエスト状態を取得
        CurrentRequestState currentRequestState = getCurrentRequestState(frameworkSessionId, true);
        
        if (currentRequestState != null) {
            if (!currentRequestState.isRestrictedRequest()) {
                // リクエスト制限済み出ない場合は、現在リクエスト状態を更新
                currentRequestState.setRequestCount(currentRequestState.getRequestCount() + 1);
                currentRequestState.setLatestRequestTimeMillis(currentTimeMillis);
            } else {
                if (currentTimeMillis - currentRequestState
                        .getLatestRequestTimeMillis() > requestRestrictionFilterConfig.getRestrictionWaitTimeMillis()) {
                    // リクエスト制限済みの場合でさらに待機時間を超えている場合はリクエスト制限を解除して初期状態に戻す
                    currentRequestState.setRequestCount(1);
                    currentRequestState.setFirstRequestTimeMillis(currentTimeMillis);
                    currentRequestState.setLatestRequestTimeMillis(currentTimeMillis);
                    currentRequestState.setRestrictedRequest(false);
                }
            }
        } else {
            // 空の場合は初回リクエスト時として現在リクエスト状態を設定
            currentRequestState = new CurrentRequestState();
            currentRequestState.setFrameworkSessionId(frameworkSessionId);
            currentRequestState.setRequestCount(1);
            currentRequestState.setFirstRequestTimeMillis(currentTimeMillis);
            currentRequestState.setLatestRequestTimeMillis(currentTimeMillis);
            currentRequestState.setRestrictedRequest(false);
        }
        
        // いずれの場合でもロック解除は必須
        currentRequestState.setLockedForUpdate(false);
        
        // 現在リクエスト状態を設定
        redisCacheHelper.putCacheData(REDIS_CACHE_KEY_REQUEST_STATE, currentRequestState.getFrameworkSessionId(),
                currentRequestState);
    }
    
    /**
     * リクエスト制限に抵触したかを判定する。<br>
     * なお、別途判定メソッドで判断するので、有効なフレームワークセッションIDが取得できる前提とする。<br>
     * また、フィルタ処理でフレームワークセッションIDに対する現在リクエスト状態が必ず設定されるため、<br>
     * 現在リクエスト状態が取得できない場合は何も考慮せずスキップする。
     *
     * @return リクエスト制限抵触フラグ（true：抵触済み、false：未抵触）。
     */
    public boolean isRestrictedRequest() {
        
        boolean isRestrictedRequest = false;
        
        // フレームワークセッションIDを取得
        String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                String.class);
        
        // 現在リクエスト状態を取得
        CurrentRequestState currentRequestState = getCurrentRequestState(frameworkSessionId, false);
        
        if (currentRequestState != null) {
            // 現在のリクエスト回数が閾値を超えている場合
            if (currentRequestState.getRequestCount() > requestRestrictionFilterConfig.getMaxRequestableCount()) {
                // 初回リクエストから最新リクエストまでの経過時間を取得
                long elapsedTimeMillis = currentRequestState.getLatestRequestTimeMillis()
                        - currentRequestState.getFirstRequestTimeMillis();
                
                // 経過時間が閾値を超えている場合
                if (elapsedTimeMillis < requestRestrictionFilterConfig.getCheckIntervalMillis()) {
                    isRestrictedRequest = true;
                } else {
                    // 更新ロックモードで現在リクエスト状態を再取得
                    currentRequestState = getCurrentRequestState(frameworkSessionId, true);
                    
                    // リクエスト制限済み状態にしてロック解除
                    currentRequestState.setRestrictedRequest(true);
                    currentRequestState.setLockedForUpdate(false);
                    
                    // 現在リクエスト状態を上書き設定
                    redisCacheHelper.putCacheData(REDIS_CACHE_KEY_REQUEST_STATE,
                            currentRequestState.getFrameworkSessionId(), currentRequestState);
                }
            }
        }
        
        return isRestrictedRequest;
    }
    
    /**
     * 該当セッションの現在リクエスト状態を取得する。<br>
     * なお、別途判定メソッドで判断するので、フレームワークセッションIDに有効な値が設定される前提とする。
     *
     * @param frameworkSessionId フレームワークセッションID。
     * @param lockForUpdate      更新ロック指示フラグ（true：ロックする、false：ロックしない）。
     * @return 現在リクエスト状態。
     */
    private CurrentRequestState getCurrentRequestState(String frameworkSessionId, boolean lockForUpdate) {
        
        CurrentRequestState currentRequestState = null;
        
        // キャッシュから現在リクエスト状態を取得
        currentRequestState = redisCacheHelper.getDataObject(REDIS_CACHE_KEY_REQUEST_STATE, frameworkSessionId,
                CurrentRequestState.class);
        
        if (currentRequestState != null) {
            long lockWaitTimeMillis = 0;
            
            while (true) {
                if (lockForUpdate) {
                    if (currentRequestState.isLockedForUpdate()
                            && lockWaitTimeMillis < requestRestrictionFilterConfig.getMaxLockWaitTimeMillis()) {
                        try {
                            // ロック待機時間を加算
                            lockWaitTimeMillis += requestRestrictionFilterConfig.getLockRetryIntervalMillis();
                            
                            // 設定時間で待機
                            Thread.sleep(requestRestrictionFilterConfig.getLockRetryIntervalMillis());
                        } catch (InterruptedException e) {
                            // デバッグログ出力
                            log.debug(e.getMessage(), e);
                            
                            // 待機に失敗した場合は強制ロック
                            currentRequestState.setLockedForUpdate(true);
                            
                            // 現在リクエスト状態を上書き設定
                            redisCacheHelper.putCacheData(REDIS_CACHE_KEY_REQUEST_STATE,
                                    currentRequestState.getFrameworkSessionId(), currentRequestState);
                            
                            break;
                        }
                    } else {
                        // 未ロック状態、または最大ロック待機時間を超えていた場合は強制ロック
                        currentRequestState.setLockedForUpdate(true);
                        
                        // 現在リクエスト状態を上書き設定
                        redisCacheHelper.putCacheData(REDIS_CACHE_KEY_REQUEST_STATE,
                                currentRequestState.getFrameworkSessionId(), currentRequestState);
                        
                        break;
                    }
                } else {
                    // 更新ロックモードではない場合は待機しない
                    break;
                }
            }
        }
        
        return currentRequestState;
    }
    
    /**
     * 設定内容をログ出力する。
     */
    private void printConfigLog() {
        
        if (requestRestrictionFilterConfig.getWhiteList() != null) {
            for (String path : requestRestrictionFilterConfig.getWhiteList()) {
                log.info(LOG_FORMAT_CONFIG_FOUND_IGNORE_PATH, path);
            }
        }
        
        if (requestRestrictionFilterConfig.getMaxRequestableCount() != new RequestRestrictionFilterConfig()
                .getMaxRequestableCount()) {
            log.info(LOG_FORMAT_CONFIG_FOUND_MAX_REQUESTABLE_COUNT,
                    requestRestrictionFilterConfig.getMaxRequestableCount());
        } else {
            log.info(LOG_FORMAT_CONFIG_FOUND_MAX_REQUESTABLE_COUNT_DEFAULT,
                    requestRestrictionFilterConfig.getMaxRequestableCount());
        }
    }
}
