package com.sbisec.helios.ap.common.aop.dao;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.sbisec.helios.ap.common.util.IfaCommonUtil;

/**
 * Transactionalアノテーション妥当性チェックAOPクラス。
 * 
 * @author SCSK宮坂
 */
@Aspect
@Component
public class TransactionalValidationAspect {
	/** 利用禁止アノテーションメッセージID */
	private static final String FORBIDDEN_ANNOTATION_MESSAGE_ID = "errors.common.forbiddenAnnotation";

	/**
	 * Transactionalアノテーションが付くクラスの全メソッドか、Transactionalアノテーションが付く個別メソッドをハンドルする。
	 * 
	 * @param joinPoint     結合点。
	 * @param transactional Transactionalインスタンス。
	 */
	@Before("@within(transactional) or @annotation(transactional)")
	public void handleBefore(JoinPoint joinPoint, Transactional transactional) {
		// トランザクションマネージャーが明示指定されている場合はNG
		if (!StringUtils.isEmpty(transactional.transactionManager())) {
			// ログ埋め込み情報を取得
			String className = joinPoint.getTarget().getClass().toString();
			String methodName = ((MethodSignature) joinPoint.getSignature()).getMethod().getName();
			String annotationName = Transactional.class.getName();

			throw new UnsupportedOperationException(IfaCommonUtil.getMessage(FORBIDDEN_ANNOTATION_MESSAGE_ID, new Object[] { className, methodName, annotationName }));
		}
	}
}