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
 * 本支店種別 Validator
 *
 * @author SCSK
 */
@Constraint(validatedBy = {})
@Target({ FIELD, PARAMETER })
@Retention(RUNTIME)
@Size(min = 2, max = 2, message = "本支店種別")
@Pattern(regexp = "(00|01)", message = "本支店種別")
public @interface BranchKind {
    
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
