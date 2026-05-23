package com.sbisec.helios.gw.common.aop;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.common.util.ObjectDumpUtil;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import lombok.extern.slf4j.Slf4j;

/**
 * Log出力
 */
@Aspect
@Component
@Slf4j
public class LogToFileAspect {
    
    private ObjectMapper objectMapper = new ObjectMapper();
    
    /**
     * Log for controller method start.
     *
     * @param joinPoint point
     */
    @Before("within(com.sbisec.**.gw.**.controller..*)")
    public void addBeforeLogger(JoinPoint joinPoint) {
        
        log.info("Start: {}.{}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());
    }
    
    /**
     * RestControllerアノテーションが付与されている場合にリクエストの内容をログ出力する<br />
     * LoggerのログレベルがDEBUGの場合のみ出力
     */
    @Before("within(@org.springframework.web.bind.annotation.RestController *)")
    public void addBeforeControllerInputLogger(JoinPoint joinPoint) {
        
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
                .getRequest();
        
        if (request instanceof ContentCachingRequestWrapper && log.isDebugEnabled()) {
            ContentCachingRequestWrapper wrapper = (ContentCachingRequestWrapper) request;
            String requestBody = getRequestBody(wrapper);
            log.debug("Request: {}.{}, Params: {}", joinPoint.getTarget().getClass().getSimpleName(),
                    joinPoint.getSignature().getName(), requestBody);
        }
    }
    
    private String getRequestBody(ContentCachingRequestWrapper requestWrapper) {
        
        String requestBody = "";
        if (requestWrapper.getContentAsByteArray().length > 0) {
            requestBody = new String(requestWrapper.getContentAsByteArray(), StandardCharsets.UTF_8);
        }
        return requestBody;
    }
    
    /**
     * log for controller method end.
     */
    @After("within(com.sbisec.**.gw.**.controller..*)")
    public void addAfterLogger(JoinPoint joinPoint) {
        
        log.info("End: {}.{}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());
    }
    
    /**
     * RestControllerアノテーションが付与されている場合にレスポンスの内容をログ出力する<br />
     * LoggerのログレベルがDEBUGの場合のみ出力
     */
    @AfterReturning(pointcut = "within(@org.springframework.web.bind.annotation.RestController *)", returning = "returnValue")
    public void addAfterControllerOutputLogger(JoinPoint joinPoint, Object returnValue) {
        
        if (log.isDebugEnabled()) {
            try {
                // JSON 文字列を Java オブジェクトに変換
                Object jsonAsObject = objectMapper.readValue((String) returnValue, Object.class);
                // Java オブジェクトを JSON 文字列に変換して Unicode エスケープをデコード
                String jsonResponse = objectMapper.writeValueAsString(jsonAsObject);
                log.debug("Response: {}.{}, Params: {}", joinPoint.getTarget().getClass().getSimpleName(),
                        joinPoint.getSignature().getName(), ObjectDumpUtil.reflectionToString(jsonResponse));
            } catch (JsonProcessingException e) {
                // JSON変換失敗時は変換前の文字列でログ出力
                log.debug("Response: {}.{}, Params: {}", joinPoint.getTarget().getClass().getSimpleName(),
                        joinPoint.getSignature().getName(), ObjectDumpUtil.reflectionToString(returnValue));
            } catch (Exception e) {
                log.debug("Response: {}.{}, Params: parameter parse error.",
                        joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());
            }
        }
    }
    
    /**
     * log for dao method start.
     */
    @Before("execution(* com.sbisec.**.ap.**.dao..*(..))")
    public void addBeforeDaoLogger(JoinPoint joinPoint) {
        
        log.info("Start: {}.{}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());
    }
    
    /**
     * log for dao method end.
     */
    @After("execution(* com.sbisec.**.ap.**.dao..*(..))")
    public void addAfterDaoLogger(JoinPoint joinPoint) {
        
        log.info("End: {}.{}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());
    }
    
    /**
     * log for service method start.
     */
    @Before("execution(* com.sbisec.**.ap.**.service..*(..))")
    public void addBeforeServiceLogger(JoinPoint joinPoint) {
        
        log.info("Start: {}.{}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());
    }
    
    /**
     * log for service method end.
     */
    @After("execution(* com.sbisec.**.ap.**.service..*(..))")
    public void addAfterServiceLogger(JoinPoint joinPoint) {
        
        log.info("End: {}.{}", joinPoint.getTarget().getClass().getSimpleName(), joinPoint.getSignature().getName());
    }
    
    /**
     * log for throw exception.
     *
     * @param joinPoint point
     * @param ex        exception
     */
    @AfterThrowing(pointcut = "within(com.sbisec.**.gw.**.controller..*)", throwing = "ex")
    public void addAfterThrowingLogger(JoinPoint joinPoint, Exception ex) {
        
        log.warn("Exception Throwing Logger: {}", ex.getMessage(), ex);
    }
}
