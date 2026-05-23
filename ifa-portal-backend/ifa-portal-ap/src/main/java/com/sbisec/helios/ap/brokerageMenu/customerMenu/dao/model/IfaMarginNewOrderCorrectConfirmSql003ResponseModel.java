package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaMarginNewOrderCorrectConfirmSql003ResponseModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;

    /** 部店コード（半角英数字）. */
    private String butenCode;

    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客ID（数字）. */
    private String kokyakuId;
    
    /** 特定口座区分（半角英数字）. */
    private String tokuteiKouzaKbn;
    
    /** 確認項目.SOR確認 */
    private String checkSor;
    
    /** 譲渡益税区分. */
    private String joutoekizeiKbn;

}
