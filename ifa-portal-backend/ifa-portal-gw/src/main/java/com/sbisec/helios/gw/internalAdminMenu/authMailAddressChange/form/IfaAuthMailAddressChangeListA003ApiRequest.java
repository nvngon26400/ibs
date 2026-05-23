package com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0404-01
 * 画面名：認証用メールアドレス一覧
 *
 * @author SCSK
 */
@Data
public class IfaAuthMailAddressChangeListA003ApiRequest {
    
    /** ログインID. */
    @NotEmpty(message = "ログインID")
    @Size(max = 16, message = "ログインID")
    private String loginId;

    /** 社員名/担当者名. */
    private String employeeName;

    /** 認証用メールアドレス. */
    private String mailAddr;


}
