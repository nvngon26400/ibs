package com.sbisec.helios.ap.common.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.util.ContentCachingRequestWrapper;

import java.nio.charset.StandardCharsets;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sbisec.helios.ap.common.exception.AbstractDataListPreservingException;
import com.sbisec.helios.ap.common.util.ObjectDumpUtil;

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
        log.info("Start: {}.{}",
                 joinPoint.getTarget().getClass().getSimpleName(),
                 joinPoint.getSignature().getName());
    }

    /**
     * log for controller method end.
     */
    @After("within(com.sbisec.**.gw.**.controller..*)")
    public void addAfterLogger(JoinPoint joinPoint) {
        log.info("End: {}.{}",
                 joinPoint.getTarget().getClass().getSimpleName(),
                 joinPoint.getSignature().getName());
    }
    
    /**
     * Controllerのリクエストの内容をログ出力する<br />
     * LoggerのログレベルがDEBUGの場合のみ出力
     */
    @Before("within(com.sbisec.**.ap.**.controller..*)")
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
     * Controllerのレスポンスの内容をログ出力する<br />
     * LoggerのログレベルがDEBUGの場合のみ出力
     */
    @AfterReturning(pointcut = "within(com.sbisec.**.ap.**.controller..*)", returning = "returnValue")
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
        log.info("Start: {}.{}",
                 joinPoint.getTarget().getClass().getSimpleName(),
                 joinPoint.getSignature().getName());
    }

    /**
     * log for dao method end.
     */
    @After("execution(* com.sbisec.**.ap.**.dao..*(..))")
    public void addAfterDaoLogger(JoinPoint joinPoint) {
        log.info("End: {}.{}",
                 joinPoint.getTarget().getClass().getSimpleName(),
                 joinPoint.getSignature().getName());
    }
    
    /**
     * log for dao throw exception.
     */
    @AfterThrowing(pointcut = "within(com.sbisec.**.ap..*.dao..*)", throwing = "ex")
    public void addAfterThrowingDaoLogger(JoinPoint joinPoint, Exception ex) {
        log.error("SQL Exception Throwing Logger: {}", ex.getMessage(), ex);
    }

    /**
     * log for service method start.
     */
    @Before("execution(* com.sbisec.**.ap.**.service..*(..))")
    public void addBeforeServiceLogger(JoinPoint joinPoint) {
        log.info("Start: {}.{}",
                 joinPoint.getTarget().getClass().getSimpleName(),
                 joinPoint.getSignature().getName());
        
    }

    /**
     * log for service method end.
     */
    @After("execution(* com.sbisec.**.ap.**.service..*(..))")
    public void addAfterServiceLogger(JoinPoint joinPoint) {
        log.info("End: {}.{}",
                 joinPoint.getTarget().getClass().getSimpleName(),
                 joinPoint.getSignature().getName());
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
    
    /**
     * 共通関数デバッグ用途として、入力引数のログを出力する。
     *
     * @param joinPoint point
     */
    @Before("within(com.sbisec.**.ap.bizcommon.component..*)")
    public void addBeforeFctLogger(JoinPoint joinPoint) {
        
        if (log.isDebugEnabled()) {
            String[] argNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
            Object[] argValues = joinPoint.getArgs();
            
            for (int i = 0; i < argNames.length; ++i) {
                log.debug("{}#{} 入力引数 {}={}", joinPoint.getSignature().getDeclaringType().getName(),
                        joinPoint.getSignature().getName(), argNames[i],
                        ObjectDumpUtil.reflectionToString(argValues[i]));
            }
        }
    }
    
    /**
     * 共通関数デバッグ用途として、正常終了時のログを出力する。
     *
     * @param joinPoint point
     * @param returnValue returnValue
     */
    @AfterReturning(pointcut = "within(com.sbisec.**.ap.bizcommon.component..*)", returning = "returnValue")
    public void addAfterFctLogger(JoinPoint joinPoint, Object returnValue) {
        
        if (log.isDebugEnabled()) {
            log.debug("{}#{} 正常終了 {}", joinPoint.getSignature().getDeclaringType().getName(),
                    joinPoint.getSignature().getName(), ObjectDumpUtil.reflectionToString(returnValue));
        }
    }
    
    /**
     * 共通関数デバッグ用途として、例外発生時のログを出力する。
     *
     * @param joinPoint point
     * @param throwable 例外
     */
    @AfterThrowing(pointcut = "within(com.sbisec.**.ap.bizcommon.component..*)", throwing = "throwable")
    public void addAfterFctLogger(JoinPoint joinPoint, Throwable throwable) {
        
        if (log.isDebugEnabled()) {
            if (throwable instanceof AbstractDataListPreservingException) {
                AbstractDataListPreservingException dataListPreservingException = (AbstractDataListPreservingException) throwable;
                
                log.debug("{}#{} 例外発生 {}={} DataList={}", joinPoint.getSignature().getDeclaringType().getName(),
                        joinPoint.getSignature().getName(), throwable.getClass().getName(), throwable.getMessage(),
                        ObjectDumpUtil.reflectionToString(dataListPreservingException.getDataList()));
            } else {
                log.debug("{}#{} 例外発生 {}={}", joinPoint.getSignature().getDeclaringType().getName(),
                        joinPoint.getSignature().getName(), throwable.getClass().getName(), throwable.getMessage());
            }
        }
    }
}
