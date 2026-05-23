package com.sbisec.helios.gw.common.logger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.util.ContentCachingRequestWrapper;

import com.ibm.icu.text.MessageFormat;
import com.sbibits.earth.mdc.enums.MdcLogParameter;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.enums.HttpHeaderEnum;
import com.sbisec.helios.ap.common.enums.LogKeyEnum;
import com.sbisec.helios.ap.common.enums.LogTypeEnum;
import com.sbisec.helios.ap.common.model.UserAccount;
import com.sbisec.helios.ap.common.util.HttpRequestUtil;
import com.sbisec.helios.ap.common.util.IfaCommonUtil;
import com.sbisec.helios.ap.common.util.ServletUtil;
import com.sbisec.helios.gw.common.filter.wrapper.BodyRequestWrapper;
import com.sbisec.helios.gw.common.util.StringUtil;

/**
 * Log file content settings.
 * <p>
 * It includes the type of request, the IP currently accessing the service, the user Id or batch Id.
 * </p>
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class LogFilter implements Filter {

    private static final Logger LOGGER = LoggerFactory.getLogger(LogFilter.class);
    
    private RequestMatcher loginMatcher = new AntPathRequestMatcher(AppConstants.URL_EXT_LOGIN);
    private RequestMatcher logoutMatcher = new AntPathRequestMatcher(AppConstants.URL_EXT_LOGOUT);
    private RequestMatcher apiMatcher = new AntPathRequestMatcher(AppConstants.URL_INT_API_ALL);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        if (request instanceof HttpServletRequest) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpServletResponse resp = ((HttpServletResponse) response);

            // put ip address
            MDC.put(LogKeyEnum.ip.getKey(), HttpRequestUtil.getRemoteAddress(req));

            if (apiMatcher.matches(req)) {
                String body = ServletUtil.instance(req).readRequest();
                // Get the ID of the request API.
                String batchId = StringUtil.jsonValueByKey(body, AppConstants.API_BATCHID);
                // Set the kind of request to API.
                MDC.put(LogKeyEnum.kind.getKey(), LogTypeEnum.API.getType());
                // Set the id of request.
                MDC.put(LogKeyEnum.batch_id.getKey(), batchId);
                // Rewrite data to response.
                chain.doFilter(new BodyRequestWrapper(req, body), resp);
                return;
            }

            if (loginMatcher.matches(req) || logoutMatcher.matches(req)) {
                // Get the user id that initiated the request from the web page.
                String userId = request.getParameter(AppConstants.LOGIN_USERID);
                // Set the kind of request to security.
                MDC.put(LogKeyEnum.kind.getKey(), LogTypeEnum.Security.getType());
                // Set the user id of request.
                MDC.put(LogKeyEnum.user_id.getKey(), userId);
            } else {
                String tid = MessageFormat.format(AppConstants.MDC_FORMAT_TID, Thread.currentThread().getId());
                String fid = MessageFormat.format(AppConstants.MDC_FORMAT_FID, req.getRequestURI(), req.getMethod());
                
                // Set LogParameter.
                MDC.put(LogKeyEnum.kind.getKey(), LogTypeEnum.WEB.getType());
                MDC.put(MdcLogParameter.MDC_TID.getId(), tid);
                MDC.put(MdcLogParameter.MDC_FID.getId(), fid);
                MDC.put(MdcLogParameter.MDC_INH_ID.getId(), MessageFormat.format(AppConstants.MDC_FORMAT_INH_ID, fid, tid));
                
                // Headerになければ空文字とする
                String frameworkSessionId = req.getHeader(HttpHeaderEnum.SESSION_ID.getName());
                MDC.put(LogKeyEnum.session_id.getKey(), frameworkSessionId != null ? frameworkSessionId : "");
                // ユーザIDを取得する。取得できない場合は空文字とする
                UserAccount ua = IfaCommonUtil.getUserAccount();
                String userId = "";
                if (ua != null) {
                    userId = ua.getUserId();
                }
                MDC.put("user_id", userId);
                
                // ログレベルがDEBUGに設定されている場合にのみ実行
                if (LOGGER.isDebugEnabled()) {
                    // フィルタチェーンを続行する前に、リクエストをラップしてキャッシュを有効化
                    // これにより、後続のフィルターやサーブレットでリクエストボディを読み出しても、ログ出力のために再度読み出すことができるようになる
                    ContentCachingRequestWrapper wrappedRequest = new ContentCachingRequestWrapper(req);
                    chain.doFilter(wrappedRequest, resp);
                    return;
                }
                                
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
