package com.sbisec.helios.gw.common.filter;

import java.io.IOException;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.CacheManager;

import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.ServletUtil;
import com.sbisec.helios.gw.common.util.ExtApiHelper;
import com.sbisec.helios.gw.common.util.ExtApiHelper.ExtApiAuthConfig.ExtApiAuthConfigInfo;
import com.sbisec.helios.gw.common.util.ExtApiHelper.ExtApiAuthHttpHeader;

/**
 * 外部API向けログインフィルタクラス。
 *
 * @author SCSK宮坂
 */
public class ExtApiLoginFilter implements Filter {
    
    /** 認証NGエラーコード */
    private static final String AUTH_NG_RETURN_CD = "E91";
    
    /** 認証NGメッセージID */
    private static final String AUTH_NG_MESSAGE_ID = "errors.extApi.connection.failed";
    
    /** リクエストスコープ属性名（FID） */
    private static final String REQUEST_ATTR_NM_FID = "fid";
    
    /** リクエストスコープ属性名（TID） */
    private static final String REQUEST_ATTR_NM_TID = "tid";
    
    /** 外部API向けヘルパー */
    private ExtApiHelper extApiHelper = null;
    
    /**
     * コンストラクタ。
     *
     * @param cacheManager キャッシュマネージャー。
     */
    public ExtApiLoginFilter(CacheManager cacheManager) {
        
        this.extApiHelper = new ExtApiHelper(cacheManager);
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        
        // 外部API向け認証HTTPヘッダを取得
        ExtApiAuthHttpHeader extApiAuthHttpHeader = extApiHelper.getAuthHttpHeader(httpServletRequest);
        
        // リクエストが外部APIか判定
        if (extApiHelper.isRequestedExtApi(extApiAuthHttpHeader)) {
            // エラーレスポンス送信用にリクエスト時刻を設定
            httpServletRequest.setAttribute(IfaCommonUtil.ATTR_KEY_RTIME, new Date());
            
            try {
                // 外部API向け認証を行う
                if (authenticate(httpServletResponse, extApiAuthHttpHeader) == false) {
                    return;
                }
                
                // フレームワークセッションIDと認証トークンのリクエスト属性を設定
                httpServletRequest.setAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID,
                        StringUtils.defaultString(extApiAuthHttpHeader.getSessionId()));
                httpServletRequest.setAttribute(IfaCommonUtil.ATTR_AUTH_TOKEN,
                        StringUtils.defaultString(extApiAuthHttpHeader.getAuthToken()));
                
                // APサーバへの連携用にFIDとTIDのリクエスト属性を設定
                httpServletRequest.setAttribute(REQUEST_ATTR_NM_FID,
                        httpServletRequest.getRequestURI() + ":" + httpServletRequest.getMethod());
                httpServletRequest.setAttribute(REQUEST_ATTR_NM_TID, String.valueOf(Thread.currentThread().getId()));
            } catch (Exception e) {
                // 全ての想定外エラーはシステムエラー
                ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR)
                        .returnCd("errors.systemError").print();
                
                return;
            }
        }
        
        // 次のフィルタ処理へ連携
        chain.doFilter(request, response);
    }
    
    /**
     * 認証HTTPヘッダ情報で外部API向け認証を行う。認証NGの場合はエラーHTTPレスポンスを送信する。
     *
     * @param httpServletResponse  HTTPサーブレットレスポンス。
     * @param extApiAuthHttpHeader 外部API向け認証HTTPヘッダ。
     * @param extApiAuthConfigInfo 外部API向け認証設定情報。
     * @return 認証済み判定フラグ（true：認証済みである、false：認証済みではない）
     */
    private boolean authenticate(HttpServletResponse httpServletResponse, ExtApiAuthHttpHeader extApiAuthHttpHeader) {
        
        boolean isAuthenticated = true;
        
        // 該当システムIDの認証設定情報を取得
        ExtApiAuthConfigInfo extApiAuthConfigInfo = extApiHelper.getAuthConfigInfo(extApiAuthHttpHeader);
        
        if (extApiAuthConfigInfo == null) {
            // システムIDが未設定か、該当システムIDの認証設定情報が存在しない場合
            ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR).returnCd(AUTH_NG_RETURN_CD)
                    .message(IfaCommonUtil.getMessage(AUTH_NG_MESSAGE_ID,
                            new Object[] { ExtApiHelper.HTTP_HEADER_NM_SYSTEM_ID, extApiAuthHttpHeader.getSystemId() }))
                    .print();
            
            isAuthenticated = false;
        } else if (!StringUtils.equals(extApiAuthHttpHeader.getUserId(), extApiAuthConfigInfo.getUserId())) {
            // ユーザID不一致の場合
            ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR).returnCd(AUTH_NG_RETURN_CD)
                    .message(IfaCommonUtil.getMessage(AUTH_NG_MESSAGE_ID,
                            new Object[] { ExtApiHelper.HTTP_HEADER_NM_USER_ID, extApiAuthHttpHeader.getUserId() }))
                    .print();
            
            isAuthenticated = false;
        } else if (!StringUtils.equals(extApiAuthHttpHeader.getSessionId(), extApiAuthConfigInfo.getSessionId())) {
            // セッションID不一致の場合
            ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR).returnCd(AUTH_NG_RETURN_CD)
                    .message(IfaCommonUtil.getMessage(AUTH_NG_MESSAGE_ID, new Object[] {
                            ExtApiHelper.HTTP_HEADER_NM_SESSION_ID, extApiAuthHttpHeader.getSessionId() }))
                    .print();
            
            isAuthenticated = false;
        } else if (!StringUtils.equals(extApiAuthHttpHeader.getAuthToken(), extApiAuthConfigInfo.getAuthToken())) {
            // 認証トークン不一致の場合
            ServletUtil.instance(httpServletResponse).errorLevel(ErrorLevel.SYSTEM_ERROR).returnCd(AUTH_NG_RETURN_CD)
                    .message(IfaCommonUtil.getMessage(AUTH_NG_MESSAGE_ID, new Object[] {
                            ExtApiHelper.HTTP_HEADER_NM_AUTH_TOKEN, extApiAuthHttpHeader.getAuthToken() }))
                    .print();
            
            isAuthenticated = false;
        }
        
        return isAuthenticated;
    }
}
