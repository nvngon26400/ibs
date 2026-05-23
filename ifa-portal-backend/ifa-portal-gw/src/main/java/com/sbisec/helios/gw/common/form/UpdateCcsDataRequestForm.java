package com.sbisec.helios.gw.common.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * CCSログイン情報登録
 *
 * @author SCSK 矢口
 */
@Data
public class UpdateCcsDataRequestForm {
    
    /** CCSパスワード */
    @NotNull(message = "CCSパスワード")
    @Size(max = 16, message = "CCSパスワード")
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "CCSパスワード")
    private String ccsUserPw;
    
}
