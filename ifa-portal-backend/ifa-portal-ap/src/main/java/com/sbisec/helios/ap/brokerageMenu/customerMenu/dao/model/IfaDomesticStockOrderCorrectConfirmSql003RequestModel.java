package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * @author SCSK
 *
 */
@Data
public class IfaDomesticStockOrderCorrectConfirmSql003RequestModel {

    /** EC受注番号 */
    private String orderNum;

    /** 部店コード */
    private String butenCode;

    /** 受注日 */
    private String acceptDate;
    
    /** 口座番号 */
    private String accountNumber;
    
    /** 顧客ID */
    private String customerId;
    
    /** 特定口座区分 */
    private String specificKbn;

    /** 確認項目.SOR確認 */
    private String checkSor;
}
