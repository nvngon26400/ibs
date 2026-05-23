package com.sbisec.helios.gw.common.interceptor;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.ibm.icu.text.MessageFormat;
import com.sbibits.earth.servlet.annotation.ScreenId;
import com.sbisec.helios.ap.common.dto.SaveParameterLogRequestDto;
import com.sbisec.helios.ap.common.enums.HttpHeaderEnum;
import com.sbisec.helios.ap.common.model.FrameworkSessionInfo;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.ApiRequestUtil;
import com.sbisec.helios.ap.common.util.HttpRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.RedisCacheHelper;
import com.sbisec.helios.ap.common.util.RedisCacheHelper.RedisCachedJsonConfig;
import com.sbisec.helios.gw.common.service.TokenService;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * パラメータログインターセプタークラス。<br>
 * ユーザ情報、アクセス画面、リクエストパラメータをテーブル出力する。
 *
 * @author SCSK宮坂
 */
@Slf4j
public class ParameterLogInterceptor implements HandlerInterceptor {
    
    /** パラメータログインターセプター設定クラス */
    @Data
    private static class ParameterLogInterceptorConfig implements RedisCachedJsonConfig {
        
        /** システムID */
        @JsonProperty(value = "systemId")
        private String systemId = null;
        
        /** ログ出力除外パスリスト */
        @JsonProperty(value = "ignorePathList")
        private List<String> ignorePathList = null;
        
        /** マスキング対象パラメーターキーリスト */
        @JsonProperty(value = "maskingParamKeyList")
        private List<String> maskingParamKeyList = null;
    }
    
    /** Redisキャッシュキー（パラメータログインターセプター設定） */
    private static final String REDIS_CACHE_KEY_INTERCEPTOR_CONFIG = "parameterLogInterceptor";
    
    /** ログフォーマット（パラメータ切り捨て発生） */
    private static final String LOG_FORMAT_PARAMETER_TRUNCATED = "Parameter size is over {}({}).";
    
    /** パラメータデリミター */
    private static final String PARAM_DELIMITER = ",";
    
    /** ログ項目値（ポジション：0固定） */
    private static final String LOG_ITEM_VALUE_POSITION = "0";
    
    /** ログ項目フォーマット（コントローラークラス名） */
    private static final String LOG_ITEM_FORMAT_CONTROLLER_CLASS_NM = "{0}.{1}";
    
    /** ログ項目フォーマット（コントローラークラス名・システムIDなし） */
    private static final String LOG_ITEM_FORMAT_CONTROLLER_CLASS_NM_WITHOUT_SYSTEM_ID = "{0}";
    
    /** ログ項目フォーマット（パラメータキー・値） */
    private static final String LOG_ITEM_FORMAT_PARAM_KEY_VALUE = "{0}:{1}" + PARAM_DELIMITER;
    
    /** メニューID不明時代替文字列 */
    private static final String UNKNOWN_MENU_ID_TEXT = "undefined";
    
    /** マスキング文字列 */
    private static final String MASKING_TEXT = "******";
    
    /** 最大書き込み可能パラメータサイズ（＝ログテーブルのカラムサイズ） */
    private static final int PARAMETER_MAX_BYTES = 4000;
    
    // RT3163: TB_ACCESS_LOG insert時に「ORA-01461: LONG値はLONG列にのみバインドできます。」が発生
    /** リクエストボディエンコーディング */
    private static final String BYTES_ENCODE = "UTF-8";
    
    /** パラメータログインターセプター設定 */
    private ParameterLogInterceptorConfig parameterLogInterceptorConfig = null;
    
    /** トークンチェックサービス */
    private TokenService tokenService = null;
    
    /** ユーザ共通情報キャッシュ */
    private ThreadLocal<UserAccount> userAccountCache = null;
    
    /**
     * コンストラクタ。
     *
     * @param cacheManager キャッシュマネージャー。
     * @param tokenService トークチェックサービス。
     */
    public ParameterLogInterceptor(CacheManager cacheManager, TokenService tokenService) {
        
        this.parameterLogInterceptorConfig = new RedisCacheHelper(cacheManager)
                .getJsonConfig(REDIS_CACHE_KEY_INTERCEPTOR_CONFIG, ParameterLogInterceptorConfig.class);
        this.tokenService = tokenService;
        this.userAccountCache = new ThreadLocal<UserAccount>();
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        try {
            // ヘッダからフレームワークセッションIDを取得
            String frameworkSessionId = request.getHeader(HttpHeaderEnum.SESSION_ID.getName());
            
            if (!StringUtils.isEmpty(frameworkSessionId)) {
                // フレームワークセッションIDが取得できたらセッション情報を取得
                FrameworkSessionInfo frameworkSessionInfo = tokenService.getSessionInfo(frameworkSessionId);
                
                if (frameworkSessionInfo != null) {
                    // セッション情報が取得できたらユーザ共通情報を取得
                    UserAccount userAccount = tokenService.getUserAccountObjectFromJwt(frameworkSessionInfo);
                    
                    if (userAccount != null) {
                        // 取得できたらローカルキャッシュに保管
                        userAccountCache.set(userAccount);
                    }
                }
            }
        } catch (Throwable t) {
            // すべての発生例外を無視
            log.debug(t.getMessage(), t);
        }
        
        return true;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler,
            Exception ex) {
        
        try {
            if (!isSkippedSaveParameterLog(request)) {
                // ログアウトなどリクエスト属性に設定されない場合やControllerで削除される場合を考慮して、まずはローカルキャッシュから取得
                UserAccount userAccount = userAccountCache.get();
                
                // 取得できない場合はリクエスト属性から取得を試みる
                if (userAccount == null) {
                    // リクエスト属性からユーザ共通情報を取得
                    userAccount = IfaCommonUtil.getUserAccount();
                    
                    // それでも取得できない場合はログ出力を行わない
                    if (userAccount == null) {
                        return;
                    }
                }
                
                // パラメータログを保管
                saveParameterLog(request, (HandlerMethod) handler, userAccount);
            }
        } catch (Throwable t) {
            // すべての発生例外を無視
            log.debug(t.getMessage(), t);
        } finally {
            // 無条件でローカルキャッシュを削除
            userAccountCache.remove();
        }
    }
    
    /**
     * テーブル保管をスキップするかどうかを判定する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @return ログ出力スキップフラグ（true：スキップする、false：スキップしない）
     */
    private boolean isSkippedSaveParameterLog(HttpServletRequest httpServletRequest) {
        
        boolean isSkippedSaveParameterLog = false;
        
        if (parameterLogInterceptorConfig.getIgnorePathList() != null) {
            for (String ignorePath : parameterLogInterceptorConfig.getIgnorePathList()) {
                RequestMatcher requestMatcher = new AntPathRequestMatcher(ignorePath);
                
                // パスパターンで判定
                if (requestMatcher.matches(httpServletRequest)) {
                    isSkippedSaveParameterLog = true;
                    
                    break;
                }
            }
        }
        
        return isSkippedSaveParameterLog;
    }
    
    /**
     * パラメータログを保管する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @param handlerMethod      ハンドラーメソッド。
     * @param userAccount        ユーザ共通情報。
     * @throws Exception リクエストパラメータの取得に失敗した場合、Service呼び出しに失敗した場合。
     */
    private void saveParameterLog(HttpServletRequest httpServletRequest, HandlerMethod handlerMethod,
            UserAccount userAccount) throws Exception {
        
        // パラメータログ保管リクエストDTOを構築
        SaveParameterLogRequestDto saveParameterLogRequestDto = new SaveParameterLogRequestDto();
        saveParameterLogRequestDto.setUserId(userAccount.getUserId());
        saveParameterLogRequestDto.setUserNm(userAccount.getUserNm());
        ScreenId screenId = (ScreenId) handlerMethod.getBeanType().getAnnotation(ScreenId.class);
        if (screenId != null) {
            saveParameterLogRequestDto.setMenuId(screenId.id());
        } else {
            // 不明な場合は代替文字列を採用
            saveParameterLogRequestDto.setMenuId(UNKNOWN_MENU_ID_TEXT);
        }
        saveParameterLogRequestDto.setPrivId(userAccount.getPrivId());
        if (!StringUtils.isEmpty(parameterLogInterceptorConfig.getSystemId())) {
            saveParameterLogRequestDto.setControllerClassNm(MessageFormat.format(LOG_ITEM_FORMAT_CONTROLLER_CLASS_NM,
                    parameterLogInterceptorConfig.getSystemId(), handlerMethod.getBeanType().getSimpleName()));
        } else {
            saveParameterLogRequestDto
                    .setControllerClassNm(MessageFormat.format(LOG_ITEM_FORMAT_CONTROLLER_CLASS_NM_WITHOUT_SYSTEM_ID,
                            handlerMethod.getBeanType().getSimpleName()));
        }
        saveParameterLogRequestDto.setHttpMethodNm(httpServletRequest.getMethod());
        saveParameterLogRequestDto.setControllerMethodNm(handlerMethod.getMethod().getName());
        saveParameterLogRequestDto.setPosition(LOG_ITEM_VALUE_POSITION);
        saveParameterLogRequestDto.setParameters(getRequestParam(httpServletRequest));
        
        // 戻り値型を構築
        TypeReference<Boolean> resultType = new TypeReference<Boolean>() {
        };
        
        // パラメータログ保管を呼び出し
        ApiRequestUtil.invoke("parameterLogService", "saveParameterLog", resultType, saveParameterLogRequestDto);
    }
    
    /**
     * リクエストパラメータを取得する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @return リクエストパラメータ文字列。
     * @throws UnsupportedEncodingException パラメータの文字列表現の構築に失敗した場合（エンコーディング固定のため通常発生しえない）。
     */
    private String getRequestParam(HttpServletRequest httpServletRequest) throws UnsupportedEncodingException {
        
        String paramText = null;
        
        // フォームパラメータを文字列として取得
        paramText = HttpRequestUtil.getFormParamText(httpServletRequest, LOG_ITEM_FORMAT_PARAM_KEY_VALUE,
                PARAM_DELIMITER, parameterLogInterceptorConfig.getMaskingParamKeyList(), MASKING_TEXT);
        
        if (StringUtils.isEmpty(paramText)) {
            // フォームパラメータが取れなければ、ボディパラメータを文字列として取得
            paramText = HttpRequestUtil.getBodyParamText(httpServletRequest,
                    parameterLogInterceptorConfig.getMaskingParamKeyList(), MASKING_TEXT);
        }
        
        // テーブルカラムサイズの考慮
        if (!StringUtils.isEmpty(paramText)) {
            byte[] paramBytes = paramText.getBytes(BYTES_ENCODE);
            if (paramBytes.length > PARAMETER_MAX_BYTES) {
                // 切り捨てる場合はログ出力
                log.warn(LOG_FORMAT_PARAMETER_TRUNCATED, PARAMETER_MAX_BYTES, paramBytes.length);
                
                // 切り捨てた文字列を再設定
                paramText = new String(Arrays.copyOfRange(paramBytes, 0, PARAMETER_MAX_BYTES), BYTES_ENCODE);
            }
        }
        
        return paramText;
    }
}
