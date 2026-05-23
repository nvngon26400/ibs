package com.sbisec.helios.gw.internalAdminMenu.monthCustomerNum.form;

import lombok.Data;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@Data
public class IfaMonthCustomerNumA004ApiRequest {
    
    /** 対象年月From */
    private String targetDateYmFrom;
    
    /** 対象年月To */
    private String targetDateYmTo;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者除外 */
    private String chkBrokerCodeExclude;
}
