package com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form;

import javax.validation.constraints.Size;

import lombok.Data;

/**
 * 画面ID：SUB0404-01
 * 画面名：認証用メールアドレス一覧
 *
 * @author SCSK
 */
@Data
public class IfaAuthMailAddressChangeListA002ApiRequest {
    
    /** ログインID */
    @Size(max = 16, message = "ログインID")
    private String loginId;
    
    /** 支店名/仲介業者名 */
    @Size(max = 80, message = "支店名/仲介業者名")
    private String brokerOrBranchName;
    
    /** 社員名/担当者名 */
    @Size(max = 80, message = "社員名/担当者名")
    private String employeeName;
}
