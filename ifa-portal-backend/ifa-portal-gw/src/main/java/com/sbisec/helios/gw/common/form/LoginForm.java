package com.sbisec.helios.gw.common.form;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * ログイン入力項目
 *
 * @author SCSK
 *
 */
@Data
public class LoginForm {
    
    @NotNull
    @Size(min = 6, max = 16)
    @Pattern(regexp = "[a-zA-Z0-9\\+\\-_\\./@\\*#%]*")
    private String userId;
    
    @NotNull
    @Size(min = 6, max = 32)
    @Pattern(regexp = "[a-zA-Z0-9\\+\\-_\\./@\\*#%!\"\\$&\\(\\)=~\\^\\?>\\|`\\[{\\]}:;<´]*")
    private String password;
    
    @Size(min = 4, max = 4)
    @Pattern(regexp = "^[a-zA-Z0-9]{4}$")
    private String verifyCode;
    
}
