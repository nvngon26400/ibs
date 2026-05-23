package com.sbisec.helios.common.unittest.form;

import java.time.LocalDate;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * バリデーションチェック用フォーム
 *
 * @author SCSK
 *
 */
@Data
public class ValidationCheckForm {
    
    @Size(min = 3, max = 6, message = "サイズチェックMaxMin")
    private String sizeCheckMaxMin;
    
    @Size(max = 6, message = "サイズチェックMax")
    private String sizeCheckMax;
    
    @Size(min = 3, message = "サイズチェックMin")
    private String sizeCheckMin;
    
    @Size(min = 3, max = 3, message = "固定長チェック")
    private String sizeCheckFixedLength;
    
    @Pattern(regexp = "[a-zA-Z0-9]*", message = "パターンチェック")
    private String patternCheckRegexp;
    
    @NotEmpty(message = "空チェック")
    private String notEmptyCheck;
    
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate dateTimeFormatCheck;
    
    @Digits(integer = 3, fraction = 2, message = "数値チェック")
    private String digitsCheckIntegerFraction;
}
