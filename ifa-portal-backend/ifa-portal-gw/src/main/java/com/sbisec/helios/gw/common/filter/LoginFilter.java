package com.sbisec.helios.gw.common.filter;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.enums.HttpHeaderEnum;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.RedisCacheHelper;
import com.sbisec.helios.ap.common.util.RedisCacheHelper.RedisCachedJsonConfig;
import com.sbisec.helios.ap.common.util.ServletUtil;
import com.sbisec.helios.common.dto.GwApSharedInfo;
import com.sbisec.helios.gw.common.service.TokenService;
import com.sbisec.helios.gw.common.util.ExtApiHelper;
import com.sbisec.helios.gw.common.util.IfaGwCommonUtil;
import com.sbisec.helios.gw.common.util.ExtApiHelper.ExtApiAuthHttpHeader;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * When login to the system,<br>
 * you need to verify whether the front and back version numbers are consistent.
 *
 * @Organization SBIBITS DaLian CB Group
 */
@Slf4j
public class LoginFilter implements Filter {
    
    /** ログインフィルタ設定クラス */
    @Data
    private static class LoginFilterConfig implements RedisCachedJsonConfig {
        
        /** リクエストパスホワイトリスト */
        @JsonProperty(value = "whiteList")
        private List<String> whiteList = null;
    }
    
    /** Redisキャッシュキー（ログインフィルタ設定） */
    private static final String REDIS_CACHE_KEY_FILTER_CONFIG = "loginFilter";
    
    /** ログフォーマット（想定外エラー） */
    private static final String LOG_FORMAT_UNEXPECTED_ERROR = "LoginFilter unexpected error has occurred.";
    
    /** ログインフィルタ設定 */
    private LoginFilterConfig loginFilterConfig = null;
    
    /** 外部API向けヘルパー */
    private ExtApiHelper extApiHelper = null;
    
    /** トークンチェックサービス */
    private TokenService tokenService = null;
    
    /**
     * コンストラクタ。
     *
     * @param cacheManager キャッシュマネージャー。
     * @param tokenService トークチェックサービス。
     */
    public LoginFilter(CacheManager cacheManager, TokenService tokenService) {
        
        this.loginFilterConfig = new RedisCacheHelper(cacheManager).getJsonConfig(REDIS_CACHE_KEY_FILTER_CONFIG,
                LoginFilterConfig.class);
        this.extApiHelper = new ExtApiHelper(cacheManager);
        this.tokenService = tokenService;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        // 外部API向け認証HTTPヘッダを取得
        ExtApiAuthHttpHeader extApiAuthHttpHeader = extApiHelper.getAuthHttpHeader(httpRequest);
        
        // リクエストが外部APIの場合は通常ログインフィルタをスキップ
        if (extApiHelper.isRequestedExtApi(extApiAuthHttpHeader)) {
            chain.doFilter(request, response);
            
            return;
        }
        
        // エラーレスポンス送信用にリクエスト時刻を設定
        httpRequest.setAttribute("RequestTime", new Date());
        
        try {
            // リクエスト内のフレームワークセッションID取得。
            String frameworkSessionId = httpRequest.getHeader(HttpHeaderEnum.SESSION_ID.getName());
            // リクエスト内の認証トークン取得。
            String authToken = httpRequest.getHeader("authToken");
            
            // フレームワークセッションIDと認証トークンをリクエストの属性値として設定。
            httpRequest.setAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                    StringUtils.defaultString(frameworkSessionId));
            httpRequest.setAttribute(IfaCommonUtil.ATTR_AUTH_TOKEN, StringUtils.defaultString(authToken));
            // APサーバへの連携用にFID、TIDを設定。
            httpRequest.setAttribute("fid", httpRequest.getRequestURI() + ":" + httpRequest.getMethod());
            httpRequest.setAttribute("tid", String.valueOf(Thread.currentThread().getId()));
            
            // GW・APサーバ共有情報をリクエストスコープに設定。
            setGwApSharedInfoToRequestScope(httpRequest, frameworkSessionId);
            
            if (isSkippedAuthTokenVerification(httpRequest)) {
                // 認証不要なリクエストの場合は何もせず処理を継続する。
                chain.doFilter(request, response);
            } else if (IfaCommonUtil.hasDebugEnvironmentVariable()) {
                // デバッグフラグがONの場合は処理を継続する。
                
                // frameworkSessionId が設定されている場合、Redisからユーザ情報を復元
                if (StringUtils.isNotEmpty(frameworkSessionId)) {
                    request.setAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT,
                            tokenService.getUserAccountObjectFromJwt(tokenService.getSessionInfo(frameworkSessionId)));
                    // Redisに顧客共通情報が存在した場合、リクエストスコープに埋め込む。
                    IfaGwCommonUtil.setCustomerCommonToRequestScope();
                }
                
                chain.doFilter(request, response);
            } else {
                // ログイン処理エンドポイントでないかつ、デバッグフラグONでない場合は認証トークンチェック処理を実行する。
                if (StringUtils.isEmpty(frameworkSessionId) || StringUtils.isEmpty(authToken)
                        || !tokenService.verifyAuthenticationToken(frameworkSessionId, authToken)) {
                    // フレームワークセッションID未設定
                    // またはトークン未設定
                    // または不正なトークンが設定されていた場合はエラー。
                    ServletUtil.instance(httpResponse).errorLevel(ErrorLevel.SYSTEM_ERROR)
                            .returnCd("errors.authTokenCheckError").print();
                } else {
                    // 認証トークンチェックに問題なければ処理継続。
                    // ログイン中ユーザのユーザ情報をリクエスト内に埋め込む。
                    request.setAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT,
                            tokenService.getUserAccountObjectFromJwt(tokenService.getSessionInfo(frameworkSessionId)));
                    // Redisに顧客共通情報が存在した場合、リクエストスコープに埋め込む。
                    IfaGwCommonUtil.setCustomerCommonToRequestScope();
                    
                    chain.doFilter(request, response);
                }
            }
        } catch (Exception e) {
            log.error(LOG_FORMAT_UNEXPECTED_ERROR, e);
            
            List<String> dataList = IfaCommonUtil.createErrorDataList(e);
            
            ServletUtil.instance(httpResponse).errorLevel(ErrorLevel.SYSTEM_ERROR).returnCd("errors.systemError")
                    .object(dataList).print();
        }
    }
    
    /**
     * GW・APサーバ共有情報をリクエストスコープに設定します。
     *
     * @param httpRequest HttpServletRequest
     * @param frameworkSessionId FWセッションID
     * @throws Exception 任意の例外
     */
    private void setGwApSharedInfoToRequestScope(HttpServletRequest httpRequest, String frameworkSessionId)
            throws Exception {
        
        GwApSharedInfo gwApSharedInfo = new GwApSharedInfo();
        
        // APIテスト対象をRedisから取得しリクエストスコープに設定
        String apiStubTargetTestCase = IfaGwCommonUtil.getDataFromRedis("apiStub", true, "testCase", String.class);
        if (StringUtils.isEmpty(apiStubTargetTestCase)) {
            apiStubTargetTestCase = IfaGwCommonUtil.getDataFromRedis("apiStub", false, "testCase", String.class);
        }
        
        gwApSharedInfo.setApiStubTargetTestCase(apiStubTargetTestCase);
        
        // APIスタブテストデータ一覧をRedisから取得しリクエストスコープに設定
        List<?> apiStubTestDataList = IfaGwCommonUtil.getDataFromRedis("apiStub", true, "testDataList", List.class);
        if (apiStubTestDataList == null || apiStubTestDataList.isEmpty()) {
            apiStubTestDataList = IfaGwCommonUtil.getDataFromRedis("apiStub", false, "testDataList", List.class);
        }
        
        gwApSharedInfo.setApiStubTestDataList(IfaCommonUtil.<Map<String, String>>castList(apiStubTestDataList));
        
        httpRequest.setAttribute(IfaCommonUtil.ATTR_KEY_GW_AP_SHARED_INFO, gwApSharedInfo);
    }
    
    /**
     * 認証トークンチェックをスキップするかどうかを判定する。
     *
     * @param httpServletRequest HTTPサーブレットリクエスト。
     * @return 認証トークンチェックスキップフラグ（true：スキップする、false：スキップしない）
     */
    private boolean isSkippedAuthTokenVerification(HttpServletRequest httpServletRequest) {
        
        boolean isSkippedVerifyAuthToken = false;
        
        // ホワイトリスト設定が存在する場合
        if (loginFilterConfig.getWhiteList() != null) {
            for (String path : loginFilterConfig.getWhiteList()) {
                RequestMatcher requestMatcher = new AntPathRequestMatcher(path);
                
                // パスパターンで判定
                if (requestMatcher.matches(httpServletRequest)) {
                    isSkippedVerifyAuthToken = true;
                    
                    break;
                }
            }
        }
        
        return isSkippedVerifyAuthToken;
    }
}
