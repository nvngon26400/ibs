package com.sbisec.helios.ap.common.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbibits.earth.model.DataList;
import com.sbisec.helios.ap.common.constants.AppConstants;
import com.sbisec.helios.ap.common.enums.ErrorLevel;
import com.sbisec.helios.ap.common.exception.SystemErrorException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.ldap.userdetails.LdapUserDetails;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Filterで利用するレスポンスを生成する。
 *
 * @author SCSK
 *
 */
public class ServletUtil {
    
    private ErrorLevel errorLevel = ErrorLevel.INFO;
    
    private String returnCode;

    private String message = "";
    
    private DataList<Object> dataList = new DataList<Object>();
    
    private HttpStatus httpStatus;
    
    private HttpServletResponse response;
    
    private HttpServletRequest request;
    
    private Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    
    /**
     * Construction method.
     */
    private ServletUtil() {
        
    }
    
    /**
     * Construction method.
     *
     * @param response response
     */
    private ServletUtil(HttpServletRequest request) {
        
        this.request = request;
    }
    
    /**
     * Construction method.
     *
     * @param response response
     */
    private ServletUtil(HttpServletResponse response) {
        
        this.response = response;
    }
    
    /**
     * Construction method.
     *
     * @param response response
     * @param rtn      response return code
     */
    private ServletUtil(HttpServletResponse response, String returnCode) {
        
        this.response = response;
        this.returnCode = returnCode;
    }
    
    /**
     * instance ServletUtil.
     *
     * @return ServletUtilのインスタンス
     */
    public static ServletUtil instance() {
        
        return new ServletUtil();
    }
    
    /**
     * instance ServletUtil.
     *
     * @param request response
     * @return ServletUtilのインスタンス
     */
    public static ServletUtil instance(HttpServletRequest request) {
        
        return new ServletUtil(request);
    }
    
    /**
     * instance ServletUtil.
     *
     * @param response response
     * @return ServletUtilのインスタンス
     */
    public static ServletUtil instance(HttpServletResponse response) {
        
        return new ServletUtil(response);
    }
    
    /**
     * Set the errorLevel.
     *
     * @param errorLevel errorLevel
     * @return ServletUtilのインスタンス
     */
    public ServletUtil errorLevel(ErrorLevel errorLevel) {
        
        this.errorLevel = errorLevel;
        return this;
    }
    
    /**
     * Set the return value.
     *
     * @param returnCode response code status
     * @return ServletUtilのインスタンス
     */
    public ServletUtil returnCd(String returnCode) {
        
        this.returnCode = returnCode;
        return this;
    }
    
    /**
     * Set the response message.
     *
     * @param message response message
     * @return ServletUtilのインスタンス
     */
    public ServletUtil message(String message) {
        
        this.message = message;
        return this;
    }
    
    /**
     * Set response content.
     *
     * @param object response content
     * @return ServletUtilのインスタンス
     */
    @SuppressWarnings("unchecked")
    public ServletUtil object(Object object) {
        
        if (object instanceof DataList) {
            
            this.dataList = (DataList<Object>) object;
            
        } else {
            this.dataList.getDataList().add(object);
        }
        return this;
    }
    
    /**
     * Set the HTTP status of the response.
     *
     * @param status HTTP status
     * @return ServletUtilのインスタンス
     */
    public ServletUtil httpStatus(HttpStatus status) {
        
        this.httpStatus = status;
        return this;
    }
    
    /**
     * Print the return value in the response.
     */
    public void print() {
        
        dataList.setErrorLevel(errorLevel.getId());
        dataList.setReturnCode(returnCode);
        dataList.setMaxRownum(dataList.size());
        dataList.setTotalSize(dataList.size());
        dataList.setRequestedTime(IfaCommonUtil.getFormattedRequestedTime());
        
        if (StringUtils.isEmpty(message)) {
            
            if (StringUtils.isNotEmpty(returnCode)) {
                
                // リターンコードからメッセージを取得して設定する
                dataList.setMessage(IfaCommonUtil.retrieveMessage(returnCode));
            }
            
        } else {
            
            dataList.setMessage(message);
        }
        
        response.setContentType(AppConstants.RESPONSE_ENCODE);
        response.setStatus(this.httpStatus == null ? HttpStatus.OK.value() : this.httpStatus.value());
        
        try (PrintWriter out = response.getWriter();) {
            out.write(new ObjectMapper().writeValueAsString(dataList));
            out.flush();
        } catch (IOException e) {
            throw new SystemErrorException(e);
        }
    }
    
    /**
     * Read request context.
     *
     * @return リクエストボディ
     */
    public String readRequest() {
        
        StringBuilder sb = new StringBuilder();
        try (InputStream inputStream = this.request.getInputStream();
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(inputStream, Charset.forName(AppConstants.ENCODE)));) {
            String line = StringUtils.EMPTY;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (IOException e) {
            throw new SystemErrorException(e);
        }
        return sb.toString();
    }
    
    /**
     * Get the user id from the session in request.
     *
     * @return UserId
     */
    public String getUserId() {
        
        String userId = StringUtils.EMPTY;
        if (authentication == null) {
            return userId;
        }
        Object u = authentication.getPrincipal();
        if (u instanceof LdapUserDetails) {
            userId = ((LdapUserDetails) u).getUsername();
        }
        return userId;
    }
    
    /**
     * Get the permissions of the currently login user.
     *
     * @return LoginUserAuths
     */
    public List<String> getLoginUserAuths() {
        
        return ((LdapUserDetails) authentication.getPrincipal()).getAuthorities().stream().map(a -> a.getAuthority())
                .collect(Collectors.toList());
    }
    
    /**
     * Get servlet request attributes.
     *
     * @return ServletRequestAttributes
     */
    public ServletRequestAttributes getRequestAttributes() {
        
        RequestAttributes attributes = RequestContextHolder.getRequestAttributes();
        return (ServletRequestAttributes) attributes;
    }
}
