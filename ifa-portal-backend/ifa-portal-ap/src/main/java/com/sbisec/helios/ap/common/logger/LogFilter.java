package com.sbisec.helios.ap.common.logger;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

import com.ibm.icu.text.MessageFormat;
import com.sbibits.earth.mdc.enums.MdcLogParameter;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.enums.HttpHeaderEnum;
import com.sbisec.helios.ap.common.enums.LogKeyEnum;
import com.sbisec.helios.ap.common.enums.LogTypeEnum;
import com.sbisec.helios.ap.common.filter.wrapper.BodyRequestWrapper;
import com.sbisec.helios.ap.common.util.HttpRequestUtil;
import com.sbisec.helios.ap.common.util.ServletUtil;
import com.sbisec.helios.ap.common.util.StringUtil;

/**
 * Log file content settings.
 * <p>
 * It includes the type of request, the IP currently accessing the service, the user Id or batch Id.
 * </p>
 * 
 * @Organization SBIBITS DaLian CB Group
 */
public class LogFilter implements Filter {

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
                String tid = "TID" + req.getHeader("tid");
                String fid = req.getHeader("fid");
                String body = ServletUtil.instance(req).readRequest();
                String serviceName = StringUtil.jsonValueByKey(body, "serviceName");
                String methodName = StringUtil.jsonValueByKey(body, "methodName");

                // Set LogParameter.
                MDC.put(LogKeyEnum.kind.getKey(), LogTypeEnum.WEB.getType());
                MDC.put(MdcLogParameter.MDC_TID.getId(), MessageFormat.format(AppConstants.MDC_FORMAT_TID, Thread.currentThread().getId()));
                MDC.put(MdcLogParameter.MDC_FID.getId(), MessageFormat.format(AppConstants.MDC_FORMAT_FID, req.getRequestURI(), req.getMethod()));
                MDC.put(MdcLogParameter.MDC_FSID.getId(),   serviceName + "." + methodName);
                MDC.put(MdcLogParameter.MDC_SYS_ID.getId(), "helios-gw");
                MDC.put(MdcLogParameter.MDC_INH_ID.getId(), MessageFormat.format(AppConstants.MDC_FORMAT_INH_ID, fid, tid));
                
                // headerにない場合は空文字とする
                String frameworkSessionId = req.getHeader(HttpHeaderEnum.SESSION_ID.getName());
                MDC.put(LogKeyEnum.session_id.getKey(), frameworkSessionId != null ? frameworkSessionId : "");
                String userId = req.getHeader("userId");
                MDC.put("user_id", userId != null ? userId : "");
                
                chain.doFilter(new BodyRequestWrapper(req, body), resp);
                return;
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }

}
