package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaMarginNewOrderCorrectConfirmSql003RequestModel {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 受注日 */
    private String inputDate;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客ID（数字）. */
    private String kokyakuId;
    
    /** 特定口座区分（半角英数字）. */
    private String tokuteiKouzaKbn;
    
    /** 確認項目.SOR確認  */
    private String checkSor;

}
