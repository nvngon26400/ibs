package com.sbisec.helios.gw.common.interceptor;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbibits.earth.servlet.annotation.Popup;
import com.sbibits.earth.servlet.annotation.ResponseFile;
import com.sbibits.earth.servlet.annotation.ResponseJson;
import com.sbibits.earth.servlet.annotation.SessionCheckTarget;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class HttpSessionCheckInterceptor implements HandlerInterceptor {
    
    private static final Logger LOGGER = LoggerFactory.getLogger(HttpSessionCheckInterceptor.class);
    
    private static final String LOG_HEADER_PRE_HANDLE = "HttpSessionCheckInterceptor.preHandle";
    
    private static final String DEFAULT_USER_ID = "undefined";
    
    private static final String DEFAULT_SESSION_ID = "Invalidate session";
    
    private static final String CHECK_TYPE = "httpSession";
    
    private static final String FORWARD_NAME_PREFIX = "/error?page=timeout";
    
    private static final String FORWARD_NAME_JSON = FORWARD_NAME_PREFIX + "j";
    
    // 認証確認をスキップするフラグ。環境変数より設定する。
    private Boolean debugFlag = Boolean.valueOf(System.getenv().get("DEBUG"));
    
    // When touching, we are reset UserAccount into the session.
    // Ignore visitor.
    private boolean touchSession = false;
    
    /**
     * Setting touchSession.<br>
     * DI from spring.
     *
     * @param touchSession touchSession
     */
    public void setTouchSession(boolean touchSession) {
        
        LOGGER.info("DI: setTouchSession:[{}] of HttpSessionCheckInterceptor:[{}]", touchSession, this);
    }
    
    /**
     * セッションチェック
     *
     * @param request HttpRequest
     * @param response HttpResponse
     * @param handler Handler
     * @return チェック結果
     * @throws Exception 例外
     * @see org.springframework.web.servlet.HandlerInterceptor#preHandle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object)
     */
    @SuppressWarnings("unchecked")
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        
        // 静的コンテンツに対するアクセスの場合、チェックを行わない
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }
        
        // 外部API認可チェックOKの場合はチェックを行わない
        if (Boolean.valueOf((String) request.getAttribute(IfaCommonUtil.ATTR_EXT_API_PERMISSION_FLAG))) {
            return true;
        }
        
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        
        @SuppressWarnings("rawtypes")
        Class controller = handlerMethod.getBeanType();
        
        // Get annotaion of the SessionCheckTarget from the controller.
        // And that controller marked check, we have to check at this session is login.
        SessionCheckTarget checkMark = (SessionCheckTarget) controller.getAnnotation(SessionCheckTarget.class);
        
        String userId = DEFAULT_USER_ID;
        String sessionId = DEFAULT_SESSION_ID;
        
        // セッションチェック対象判定。
        if (!debugFlag && (checkMark != null && CHECK_TYPE.equals(checkMark.type()))) {
            
            try {
                // ユーザ情報を取得。
                sessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
                UserAccount userAccount = IfaCommonUtil.getUserAccount();
                
                if (userAccount == null) {
                    // ユーザ情報が取得できないケース。
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("{}: This session has been logouted or new one.", LOG_HEADER_PRE_HANDLE);
                    }
                    
                    // TODO:[PROTO1] エラーハンドリング処理の実装。
                    setForwardToRequestDispatcher(handlerMethod, request, response);
                    
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("user id:[{}], framwork session Id:[{}]", userId, sessionId);
                    }
                    
                    // Subsequent processing is not performed
                    return false;
                }
                // Reset UserAccount (This is for JDBC stored session system.)
                // TODO: Redisへの再登録
                // if (touchSession) session.setAttribute(sessionId, userAccount);
                
                userId = userAccount.getUserId();
                
            } catch (IllegalStateException e) {
                
                if (LOGGER.isDebugEnabled()) {
                    LOGGER.debug("{}: This session has already invalidate.", LOG_HEADER_PRE_HANDLE);
                }
                
            } catch (UnsupportedClassVersionError e) {
                
                LOGGER.warn("{}: Caught UnsupportedClassVersionError at get UserAccount. {}", LOG_HEADER_PRE_HANDLE, e);
            }
            
            //        } else {
            //            try {
            //                // フレームワークセッションIDをキーにJWTを取得・デコードし、ユーザ情報を取得。
            //                sessionId = IfaCommonUtil.getRequestAttribute(IfaCommonUtil.ATTR_FRAMEWORK_SESSION_ID, String.class);
            //                UserAccount userAccount = IfaCommonUtil.getUserAccount();
            //                
            //                if (userAccount != null) {
            //                    
            //                    if (touchSession) {
            //                        request.setAttribute(IfaCommonUtil.ATTR_KEY_USER_ACCOUNT, userAccount);
            //                    }
            //                    
            //                    userId = userAccount.getUserId();
            //                }
            //                
            //            } catch (IllegalStateException e) {
            //                
            //                if (LOGGER.isDebugEnabled()) {
            //                    LOGGER.debug("{}: This session has already invalidate.", LOG_HEADER_PRE_HANDLE);
            //                }
            //                
            //            } catch (UnsupportedClassVersionError e) {
            //                
            //                LOGGER.warn("{}: Caught UnsupportedClassVersionError at get UserAccount. {}", LOG_HEADER_PRE_HANDLE, e);
            //            }
        }
        
        if (LOGGER.isDebugEnabled()) {
            LOGGER.debug("user id:[{}], framwrork session Id:[{}]", userId, sessionId);
        }
        
        return true;
    }
    
    private void setForwardToRequestDispatcher(HandlerMethod handlerMethod, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        
        // Forward to the login with parameters for handle error.
        RequestDispatcher dispatcher = request.getRequestDispatcher(FORWARD_NAME_JSON);
        
        dispatcher.forward(request, response);
    }
    
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
            ModelAndView modelAndView) throws Exception {
        
        // if (logger.isDebugEnabled()) logger.debug("HttpSessionCheckInterceptor.postHandle");
        
        // Nothing to do.
    }
    
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception {
        
        // if (logger.isDebugEnabled()) logger.debug("HttpSessionCheckInterceptor.afterCompletion");
        
        // Nothing to do.
    }
}
