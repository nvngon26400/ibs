package com.sbisec.helios.ap.internalAdminMenu.byYearAccountQuantityFeeAmountLookup.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0406-01
 * 画面名：年度別口座数・報酬額照会
 *
 * @author SBI大連 夏
 * @date   2025/06/11
 */

@Data
public class IfaByYearAccountQuantityFeeAmountLookupSql004ResponseModel {

    /** 決算年月. */
    private String closingMonth;
    
    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者名. */
    private String brokerName;
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** 顧客名(漢字). */
    private String nameKanji;
    
    /** 顧客名(カナ). */
    private String nameKana;
    
    /** 扱者コード. */
    private String dealerNumber;
    
    /** コース名. */
    private String courseName;
    
    /** 口座開設日. */
    private String openAcctDate;
    
    /** 取引の有無. */
    private String tradeKbn;
    
}
