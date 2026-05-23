package com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0404-02_1
 * 画面名：認証用メールアドレス更新
 *
 * @author SCSK
 *
 */
@Data
public class IfaAuthMailAddressUpdateA004ApiRequest {
    
    /** ログインID. */
    @NotEmpty(message = "ログインID")
    @Size(max = 16, message = "ログインID")
    private String loginId;

    /** 認証用メールアドレス. */
    @NotEmpty(message = "認証用メールアドレス")
    private String mailAddr;

}
