package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 振替指示後の指示更新リクエストモデル
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignMarginDepositTransferConfirmSql002RequestModel {
    
    /** IFA保証金振替指示番号. */
    private String depositTransferNo;
    
    /** ユーザーID（全角半角）. */
    private String userId;
    
    /** エラーコード（半角英数字）. */
    private String code;
    
    /** HTTPのレスポンスコード. */
    private String responseCode;
    
    /** エラーメッセージ（全角半角）. */
    private String errMessage;
    
}
