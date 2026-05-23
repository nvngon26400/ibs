package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 米株信用代用振替確認 SQL003 リクエストモデル
 *
 * @author SCSK川崎
 */
@Data
public class IfaForeignMarginCollateralTransferConfirmSql003RequestModel {
    
    /** APIエラーコード（全角半角）. */
    private String apiErrorCode;
    
    /** APIステータスコード（数字）. */
    private String apiStatusCode;
    
    /** APIメッセージ（全角半角）. */
    private String apiMessage;
    
    /** 更新者. */
    private String updateUser;
    
    /** IFA代用振替指示番号. */
    private String stockTransferNo;
    
}
