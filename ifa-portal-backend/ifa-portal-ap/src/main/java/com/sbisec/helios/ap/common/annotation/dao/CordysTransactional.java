package com.sbisec.helios.ap.common.annotation.dao;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.core.annotation.AliasFor;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.sbisec.helios.ap.common.config.MybatisConfig.Cordys;

/**
 * cordysトランザクション指定アノテーション。
 * 
 * @author SCSK宮坂
 */
@Target(value = { ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Transactional(Cordys.TRANSACTION_MANAGER_BEAN_NAME)
public @interface CordysTransactional {
	/**
	 * トランザクション伝搬レベルを指定する。 デフォルト値はTransactionalと同様。
	 * 
	 * @see org.springframework.transaction.annotation.Transactional#propagation()
	 */
	@AliasFor(annotation = Transactional.class)
	Propagation propagation() default Propagation.REQUIRED;

	/**
	 * トランザクション分離レベルを指定する。 デフォルト値はTransactionalと同様。
	 * 
	 * @see org.springframework.transaction.annotation.Transactional#isolation()
	 */
	@AliasFor(annotation = Transactional.class)
	Isolation isolation() default Isolation.DEFAULT;

	/**
	 * トランザクションタイムアウト値を指定する。 デフォルト値はTransactionalと同様。
	 * 
	 * @see org.springframework.transaction.annotation.Transactional#timeout()
	 */
	@AliasFor(annotation = Transactional.class)
	int timeout() default TransactionDefinition.TIMEOUT_DEFAULT;

	/**
	 * トランザクション読み取り専用フラグを指定する。 デフォルト値はTransactionalと同様。
	 * 
	 * @see org.springframework.transaction.annotation.Transactional#readOnly()
	 */
	@AliasFor(annotation = Transactional.class)
	boolean readOnly() default false;

	/**
	 * ロールバック対象例外クラスを指定する。 デフォルト値は全ての例外クラス。
	 * 
	 * @see org.springframework.transaction.annotation.Transactional#rollbackFor()
	 */
	@AliasFor(annotation = Transactional.class)
	Class<? extends Throwable>[] rollbackFor() default { Throwable.class };

	/**
	 * ロールバック対象外例外クラスを指定する。 デフォルト値はTransactionalと同様。
	 * 
	 * @see org.springframework.transaction.annotation.Transactional#noRollbackFor()
	 */
	@AliasFor(annotation = Transactional.class)
	Class<? extends Throwable>[] noRollbackFor() default {};
}