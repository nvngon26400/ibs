package com.sbisec.helios.gw.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Custom operation log record annotation.
 * 
 * @Organization SBIBITS DaLian CB Group
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    String menu() default "";

    String content() default "";

    boolean isBatch() default false;
}
