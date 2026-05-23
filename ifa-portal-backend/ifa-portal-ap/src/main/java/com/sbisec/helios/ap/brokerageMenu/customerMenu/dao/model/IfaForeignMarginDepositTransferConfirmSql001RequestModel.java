package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 振替指示前の指示登録リクエストモデル
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignMarginDepositTransferConfirmSql001RequestModel {
    
    /** IFA保証金振替指示番号. */
    private String depositTransferNo;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 委託保証金振替区分. */
    private String entrustDepositTransferClassification;
    
    /** 指示金額（数値(小数)）. */
    private String destinationAmount;
    
    /** ユーザーID（全角半角）. */
    private String userId;
    
}
