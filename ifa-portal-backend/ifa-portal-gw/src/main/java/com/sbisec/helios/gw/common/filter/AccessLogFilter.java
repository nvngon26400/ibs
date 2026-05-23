package com.sbisec.helios.gw.common.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.cache.CacheManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibm.icu.text.MessageFormat;
import com.sbibits.earth.mdc.enums.MdcLogParameter;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.enums.HttpHeaderEnum;
import com.sbisec.helios.ap.common.enums.LogKeyEnum;
import com.sbisec.helios.ap.common.enums.LogTypeEnum;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.HttpRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.RedisCacheHelper;
import com.sbisec.helios.ap.common.util.RedisCacheHelper.RedisCachedJsonConfig;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * アクセスログフィルタークラス。<br>
 * リクエストを受信した時とそのリクエストに対するレスポンスを送信する時のログを出力する。
 *
 * @author SCSK宮坂
 */
@Slf4j
public class AccessLogFilter implements Filter {
    
    /** アクセスログフィルタ設定クラス */
    @Data
    private static class AccessLogFilterConfig implements RedisCachedJsonConfig {
        
        /** ログ出力除外パスリスト */
        @JsonProperty(value = "ignorePathList")
        private List<String> ignorePathList = null;
    }
    
    /** Redisキャッシュキー（パラメータログ設定） */
    private static final String REDIS_CACHE_KEY_FILTER_CONFIG = "accessLogFilter";
    
    /** ログフォーマット（アクセス開始） */
    private static final String LOG_FORMAT_BEGIN_ACCESS = "{}\t{}\t start\t{}";
    
    /** ログフォーマット（アクセス終了） */
    private static final String LOG_FORMAT_END_ACCESS = "{}\t{}|{}\t{} ms\t{}";
    
    /** ログフォーマット（ログ出力除外パスリスト設定あり） */
    private static final String LOG_FORMAT_CONFIG_FOUND_IGNORE_PATH = "AccessLogFilter ignore url setting:[{}]";
    
    /** セッションID不明時代替文字列 */
    private static final String UNKNOWN_SESSION_ID_TEXT = "Invalidate session";
    
    /** ユーザID不明時代替文字列 */
    private static final String UNKNOWN_USER_ID_TEXT = "*****";
    
    /** 開始終了セッションID同一時代替文字列 */
    private static final String SAME_SESSION_ID_TEXT = "-";
    
    /** アクセスログフィルタ設定 */
    private AccessLogFilterConfig accessLogFilterConfig = null;
    
    /**
     * コンストラクタ。
     *
     * @param cacheManager キャッシュマネージャー。
     */
    public AccessLogFilter(CacheManager cacheManager) {
        
        this.accessLogFilterConfig = new RedisCacheHelper(cacheManager).getJsonConfig(REDIS_CACHE_KEY_FILTER_CONFIG,
                AccessLogFilterConfig.class);
        
        // 設定内容をログ出力
        printConfigLog();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        if (request instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) request;
            
            // スキップ対象のリクエストではない場合、自フィルタ連携の前後でログ出力
            if (!isSkippedPrintLog(httpServletRequest)) {
                // アクセス開始時刻を取得
                long beginTimeMillis = System.currentTimeMillis();
                
                // 開始ログ出力時のフレームワークセッションIDを取得
                String beginFrameworkSessionId = getFrameworkSessionId();
                
                // 開始ログを出力
                printBeginLog(httpServletRequest, beginFrameworkSessionId);
                
                // 次のフィルタ処理へ連携
                chain.doFilter(request, response);
                
                // 終了ログを出力
                printEndLog(httpServletRequest, beginTimeMillis, beginFrameworkSessionId);
            } else {
                // 次のフィルタ処理へ連携
                chain.doFilter(request, response);
            }
        } else {
            // 次のフィルタ処理へ連携
            chain.doFilter(request, response);
        }
    }
    
    /**
     * 設定内容をログ出力する。
     */
    private void printConfigLog() {
        
        if (accessLogFilterConfig.getIgnorePathList() != null) {
            for (String ignorePath : accessLogFilterConfig.getIgnorePathList()) {
                log.info(LOG_FORMAT_CONFIG_FOUND_IGNORE_PATH, ignorePath);
            }
        }
    }
    
    /**
     * ログ出力をスキップするかどうかを判定する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @return ログ出力スキップフラグ（true：スキップする、false：スキップしない）
     */
    private boolean isSkippedPrintLog(HttpServletRequest httpServletRequest) {
        
        boolean isSkippedPrintLog = false;
        
        // ログ出力除外パスリストが存在する場合
        if (accessLogFilterConfig.getIgnorePathList() != null) {
            for (String ignorePath : accessLogFilterConfig.getIgnorePathList()) {
                RequestMatcher requestMatcher = new AntPathRequestMatcher(ignorePath);
                
                // パスパターンで判定
                if (requestMatcher.matches(httpServletRequest)) {
                    isSkippedPrintLog = true;
                    
                    break;
                }
            }
        }
        
        return isSkippedPrintLog;
    }
    
    /**
     * フレームワークセッションIDを取得する。
     *
     * @return フレームワークセッションID。
     */
    private String getFrameworkSessionId() {
        
        String frameworkSessionId = null;
        
        try {
            // フレームワークセッションIDを取得
            frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                    String.class);
            
            if (StringUtils.isEmpty(frameworkSessionId)) {
                // 取得できない場合は代替文字列を採用
                frameworkSessionId = UNKNOWN_SESSION_ID_TEXT;
            }
        } catch (Throwable t) {
            // すべての発生例外を無視
            log.debug(t.getMessage(), t);
            
            // 代替文字列を採用
            frameworkSessionId = UNKNOWN_SESSION_ID_TEXT;
        }
        
        return frameworkSessionId;
    }
    
    /**
     * 開始ログを出力する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     */
    private void printBeginLog(HttpServletRequest httpServletRequest, String beginFrameworkSessionId) {
        
        try {
            // ログコンテキストを登録
            registerLogContext(httpServletRequest);
            
            // パラメータログを出力
            log.info(LOG_FORMAT_BEGIN_ACCESS, beginFrameworkSessionId, httpServletRequest.getServletPath(),
                    HttpRequestUtil.getRemoteAddress(httpServletRequest));
        } catch (Throwable t) {
            // すべての発生例外を無視
            log.debug(t.getMessage(), t);
        } finally {
            // ログコンテキストを解除
            removeLogContext();
        }
    }
    
    /**
     * 終了ログを出力する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     */
    private void printEndLog(HttpServletRequest httpServletRequest, long beginTimeMillis,
            String beginFrameworkSessionId) {
        
        try {
            // ログコンテキストを登録
            registerLogContext(httpServletRequest);
            
            // ユーザ共通情報をリクエストスコープから取得
            UserAccount userAccount = IfaCommonUtil.getUserAccount();
            
            // ユーザIDを取得
            String userId = null;
            if (userAccount != null) {
                userId = userAccount.getUserId();
            } else {
                // 取得できない場合は代替文字列を採用
                userId = UNKNOWN_USER_ID_TEXT;
            }
            
            // 終了ログ出力時のフレームワークセッションIDを取得
            String endSrameworkSessionId = getFrameworkSessionId();
            if (endSrameworkSessionId.equals(beginFrameworkSessionId)) {
                // 開始ログ出力時と終了ログ出力時で同一のフレームワークセッションIDの場合は、終了ログ出力時のフレームワークセッションIDを省略
                endSrameworkSessionId = SAME_SESSION_ID_TEXT;
            }
            
            // アクセス開始から終了までの経過時間を取得
            long elapsedTimeMillis = System.currentTimeMillis() - beginTimeMillis;
            
            // パラメータログを出力
            log.info(LOG_FORMAT_END_ACCESS, userId, beginFrameworkSessionId, endSrameworkSessionId, elapsedTimeMillis,
                    HttpRequestUtil.getRemoteAddress(httpServletRequest));
        } catch (Throwable t) {
            // すべての発生例外を無視
            log.debug(t.getMessage(), t);
        } finally {
            // ログコンテキストを解除
            removeLogContext();
        }
    }
    
    /**
     * ログコンテキストを登録する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     */
    private void registerLogContext(HttpServletRequest httpServletRequest) {
        
        // ログコンテキストを登録
        MDC.put(LogKeyEnum.kind.getKey(), LogTypeEnum.ACCESS.getType());
        MDC.put(MdcLogParameter.MDC_TID.getId(),
                MessageFormat.format(AppConstants.MDC_FORMAT_TID, Thread.currentThread().getId()));
        MDC.put(MdcLogParameter.MDC_FID.getId(), MessageFormat.format(AppConstants.MDC_FORMAT_FID,
                httpServletRequest.getRequestURI(), httpServletRequest.getMethod()));
        MDC.put(MdcLogParameter.MDC_INH_ID.getId(), MessageFormat.format(AppConstants.MDC_FORMAT_INH_ID,
                MDC.get(MdcLogParameter.MDC_FID.getId()), MDC.get(MdcLogParameter.MDC_TID.getId())));
        MDC.put(LogKeyEnum.session_id.getKey(), httpServletRequest.getHeader(HttpHeaderEnum.SESSION_ID.getName()));
    }
    
    /**
     * ログコンテキストを解除する。
     */
    private void removeLogContext() {
        
        // ログコンテキストを解除
        MDC.remove(LogKeyEnum.kind.getKey());
        MDC.remove(MdcLogParameter.MDC_TID.getId());
        MDC.remove(MdcLogParameter.MDC_FID.getId());
        MDC.remove(MdcLogParameter.MDC_INH_ID.getId());
        MDC.remove(LogKeyEnum.session_id.getKey());
    }
}
