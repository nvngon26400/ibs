package com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form;

import lombok.Data;

/**
 * 画面ID：SUB0404-01
 * 画面名：認証用メールアドレス一覧
 *
 * @author SCSK
 */
@Data
public class IfaAuthMailAddressChangeListA002AuthMailAddressListApiResponse {
    
    /** 認証用メールアドレス一覧リスト.ユーザーID */
    private String userId;
    
    /** 認証用メールアドレス一覧リスト.本支店名 */
    private String branchName;    
    
    /** 認証用メールアドレス一覧リスト.仲介業者支店名 */
    private String brokerName;
    
    /** 認証用メールアドレス一覧リスト.ユーザ名 */
    private String userName;
    
    /** 認証用メールアドレス一覧リスト.メールアドレス */
    private String mailAddress;
}
