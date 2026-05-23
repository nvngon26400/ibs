package com.sbisec.helios.gw.internalAdminMenu.authMailAddressChange.form;

import java.util.List;

import lombok.Data;

/**
 * 画面ID：SUB0404-01
 * 画面名：認証用メールアドレス一覧
 *
 * @author SCSK
 */
@Data
public class IfaAuthMailAddressChangeListA002ApiResponse {
    
    /** 認証用メールアドレス一覧リスト */
    List<IfaAuthMailAddressChangeListA002AuthMailAddressListApiResponse> authMailAddressList;

}
