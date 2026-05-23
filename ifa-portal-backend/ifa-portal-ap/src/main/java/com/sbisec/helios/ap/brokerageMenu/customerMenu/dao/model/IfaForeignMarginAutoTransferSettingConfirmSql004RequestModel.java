package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaForeignMarginAutoTransferSettingConfirmSql004RequestModel {
    
    /** APIエラーコード（全角半角）. */
    private String apiErrorCode;
    
    /** APIステータスコード（数字）. */
    private String apiStatusCode;
    
    /** APIメッセージ（全角半角）. */
    private String apiMsg;
    
    /** 更新者. */
    private String updateUser;
    
    /** SQL003.IFA自動振替設定指示番号. */
    private String autoTransferSettingNo;
    
}
