package com.sbisec.helios.gw.extapi.servicenow.validator;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 権限コード Validator
 *
 * @author SCSK
 */
@Constraint(validatedBy = {})
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Size(min = 1, max = 2, message = "権限コード")
@Pattern(regexp = "[1-9]|1[12]", message = "権限コード")
public @interface PrivId {
    
    /**
     * メッセージ
     */
    String message() default "";
    
    /**
     * グループ
     */
    Class<?>[] groups() default {};
    
    /**
     * ペイロード 
     */
    Class<? extends Payload>[] payload() default {};
    
}
