package com.sbisec.helios.gw.common.filter;

import java.io.IOException;
import java.text.MessageFormat;
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
import com.sbibits.earth.mdc.enums.MdcLogParameter;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.enums.HttpHeaderEnum;
import com.sbisec.helios.ap.common.enums.LogKeyEnum;
import com.sbisec.helios.ap.common.enums.LogTypeEnum;
import com.sbisec.helios.ap.common.filter.wrapper.RepeatableReadHttpServletRequestWrapper;
import com.sbisec.helios.ap.common.util.HttpRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.RedisCacheHelper;
import com.sbisec.helios.ap.common.util.RedisCacheHelper.RedisCachedJsonConfig;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * パラメータログフィルタークラス。<br>
 * URLパスとリクエストパラメータをログファイル出力する。
 *
 * @author SCSK宮坂
 */
@Slf4j
public class ParameterLogFilter implements Filter {
    
    /** パラメータログフィルタ設定クラス */
    @Data
    private static class ParameterLogFilterConfig implements RedisCachedJsonConfig {
        
        /** ログ出力除外パスリスト */
        @JsonProperty(value = "ignorePathList")
        private List<String> ignorePathList = null;
        
        /** マスキング対象パラメータキーリスト */
        @JsonProperty(value = "maskingParamKeyList")
        private List<String> maskingParamKeyList = null;
    }
    
    /** Redisキャッシュキー（パラメータログ設定） */
    private static final String REDIS_CACHE_KEY_FILTER_CONFIG = "parameterLogFilter";
    
    /** セッションID不明時代替文字列 */
    private static final String UNKNOWN_SESSION_ID_TEXT = "Invalidate session";
    
    /** マスキング文字列 */
    private static final String MASKING_TEXT = "******";
    
    /** パラメータデリミター */
    private static final String PARAM_DELIMITER = ", ";
    
    /** ログ項目フォーマット（パラメータキー・値） */
    private static final String LOG_ITEM_FORMAT_PARAM_KEY_VALUE = "{0}=''{1}''" + PARAM_DELIMITER;
    
    /** ログフォーマット（ログ出力除外パスリスト設定あり） */
    private static final String LOG_FORMAT_CONFIG_FOUND_IGNORE_PATH = "ParameterLogFilter ignore url setting:[{}]";
    
    /** ログフォーマット（マスキング対象パラメータキー設定あり） */
    private static final String LOG_FORMAT_CONFIG_FOUND_MASKING_PARAM_KEY = "ParameterLogFilter masking params setting:[{}]";
    
    /** ログフォーマット（パラメータ出力） */
    private static final String LOG_FORMAT_PRINT_PARAM = "{}, {}:{}, request parameters:[{}]";
    
    /** パラメータログフィルタ設定 */
    private ParameterLogFilterConfig parameterLogFilterConfig = null;
    
    /**
     * コンストラクタ。
     *
     * @param cacheManager キャッシュマネージャー。
     */
    public ParameterLogFilter(CacheManager cacheManager) {
        
        this.parameterLogFilterConfig = new RedisCacheHelper(cacheManager).getJsonConfig(REDIS_CACHE_KEY_FILTER_CONFIG,
                ParameterLogFilterConfig.class);
        
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
            RepeatableReadHttpServletRequestWrapper bodyCachingHttpServletRequestWrapper = new RepeatableReadHttpServletRequestWrapper(
                    (HttpServletRequest) request);
            
            try {
                // スキップ対象のリクエストではない場合
                if (!isSkippedPrintLog(bodyCachingHttpServletRequestWrapper)) {
                    // ログヘッダを設定
                    MDC.put(LogKeyEnum.kind.getKey(), LogTypeEnum.REQUEST_PARAM.getType());
                    MDC.put(MdcLogParameter.MDC_TID.getId(),
                            MessageFormat.format(AppConstants.MDC_FORMAT_TID, Thread.currentThread().getId()));
                    MDC.put(MdcLogParameter.MDC_FID.getId(),
                            MessageFormat.format(AppConstants.MDC_FORMAT_FID,
                                    bodyCachingHttpServletRequestWrapper.getRequestURI(),
                                    bodyCachingHttpServletRequestWrapper.getMethod()));
                    MDC.put(MdcLogParameter.MDC_INH_ID.getId(), MessageFormat.format(AppConstants.MDC_FORMAT_INH_ID,
                            MDC.get(MdcLogParameter.MDC_FID.getId()), MDC.get(MdcLogParameter.MDC_TID.getId())));
                    MDC.put(LogKeyEnum.session_id.getKey(),
                            bodyCachingHttpServletRequestWrapper.getHeader(HttpHeaderEnum.SESSION_ID.getName()));
                    
                    // パラメータログを保存
                    printParameterLog(bodyCachingHttpServletRequestWrapper);
                }
            } catch (Throwable t) {
                // ログコンテキストの操作が失敗することは通常ありえないため、全ての発生エラーを無視しデバッグログのみ出力
                log.debug(t.getMessage(), t);
            } finally {
                // このフィルタで設定したロガー用キーを削除
                MDC.remove(LogKeyEnum.kind.getKey());
                MDC.remove(MdcLogParameter.MDC_TID.getId());
                MDC.remove(MdcLogParameter.MDC_FID.getId());
                MDC.remove(MdcLogParameter.MDC_INH_ID.getId());
                MDC.remove(LogKeyEnum.session_id.getKey());
            }
            
            // 次のフィルタ処理へ連携
            chain.doFilter(bodyCachingHttpServletRequestWrapper, response);
        } else {
            // 次のフィルタ処理へ連携
            chain.doFilter(request, response);
        }
    }
    
    /**
     * 設定内容をログ出力する。
     */
    private void printConfigLog() {
        
        if (parameterLogFilterConfig.getIgnorePathList() != null) {
            for (String ignorePath : parameterLogFilterConfig.getIgnorePathList()) {
                log.info(LOG_FORMAT_CONFIG_FOUND_IGNORE_PATH, ignorePath);
            }
        }
        
        if (parameterLogFilterConfig.getMaskingParamKeyList() != null) {
            for (String maskingParamKey : parameterLogFilterConfig.getMaskingParamKeyList()) {
                log.info(LOG_FORMAT_CONFIG_FOUND_MASKING_PARAM_KEY, maskingParamKey);
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
        if (parameterLogFilterConfig.getIgnorePathList() != null) {
            for (String ignorePath : parameterLogFilterConfig.getIgnorePathList()) {
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
     * パラメータログを出力する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     */
    private void printParameterLog(HttpServletRequest httpServletRequest) {
        
        // フォームパラメータを文字列として取得
        String paramText = HttpRequestUtil.getFormParamText(httpServletRequest, LOG_ITEM_FORMAT_PARAM_KEY_VALUE,
                PARAM_DELIMITER, parameterLogFilterConfig.getMaskingParamKeyList(), MASKING_TEXT);
        
        // デバッグモードが有効の場合
        if (log.isDebugEnabled()) {
            if (StringUtils.isEmpty(paramText)) {
                // フォームパラメータが取れなければ、ボディパラメータを文字列として取得
                paramText = HttpRequestUtil.getBodyParamText(httpServletRequest,
                        parameterLogFilterConfig.getMaskingParamKeyList(), MASKING_TEXT);
            }
        }
        
        if (StringUtils.isEmpty(paramText)) {
            // パラメータなしの場合は空文字をセット
            paramText = StringUtils.EMPTY;
        }
        
        // フレームワークセッションIDを取得
        String frameworkSessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                String.class);
        if (StringUtils.isEmpty(frameworkSessionId)) {
            // 不明な場合は代替文字列を採用
            frameworkSessionId = UNKNOWN_SESSION_ID_TEXT;
        }
        
        // パラメータログを出力
        log.info(LOG_FORMAT_PRINT_PARAM, frameworkSessionId, httpServletRequest.getServletPath(),
                httpServletRequest.getMethod(), paramText);
    }
}
