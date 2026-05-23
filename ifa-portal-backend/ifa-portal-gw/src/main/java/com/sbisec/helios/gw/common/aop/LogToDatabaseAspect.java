package com.sbisec.helios.gw.common.aop;

import java.lang.reflect.Method;

import com.sbisec.helios.ap.common.enums.LogKeyEnum;
import com.sbisec.helios.ap.common.exception.LogRecordingException;
import com.sbisec.helios.ap.common.util.ServletUtil;
import com.sbisec.helios.gw.common.annotation.Log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.MDC;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * You can use Log annotation to record the log to the database.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Aspect
@Component
@Slf4j
public class LogToDatabaseAspect {

    @Pointcut("@annotation(com.sbisec.helios.gw.common.annotation.Log)")
    public void logPointCut() {
    }

    @AfterReturning(pointcut = "logPointCut()")
    public void doBefore(JoinPoint joinPoint) {
        handleLog(joinPoint, null);
    }

    @AfterThrowing(value = "logPointCut()", throwing = "e")
    public void doAfter(JoinPoint joinPoint, Exception e) {
        handleLog(joinPoint, e);
    }

    @Async
    protected void handleLog(final JoinPoint joinPoint, final Exception e) {
        try {
            Log logCut = annotationLog(joinPoint);
            if (logCut == null) {
                return;
            }

            // get remote address
            String ip = ServletUtil.instance().getRequestAttributes().getRequest().getRemoteAddr();

            log.info(logCut.menu());
            log.info(logCut.content());
            log.info(ip);

            if (logCut.isBatch()) {
                // get batch id
                String batchId = MDC.get(LogKeyEnum.batch_id.getKey());
                log.info("batch id is: {}", batchId);
            } else {
                // get user id
                String userId = ServletUtil.instance().getUserId();
                log.info("user id is: {}", userId);
            }
        } catch (Exception e1) {
            throw new LogRecordingException(e);
        }
    }

    private Log annotationLog(JoinPoint joinPoint) throws Exception {
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method method = methodSignature.getMethod();
        if (method != null) {
            return method.getAnnotation(Log.class);
        }
        return null;
    }
}
