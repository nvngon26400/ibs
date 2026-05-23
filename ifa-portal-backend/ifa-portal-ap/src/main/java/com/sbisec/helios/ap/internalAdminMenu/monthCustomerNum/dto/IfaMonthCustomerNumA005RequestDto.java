package com.sbisec.helios.ap.internalAdminMenu.monthCustomerNum.dto;

import lombok.Data;

/**
 * 画面ID：SUB0407_01
 * 画面名：月末口座数
 *
 * @author SBI大連 チョウ
   2025/05/22 新規作成
 */
@Data
public class IfaMonthCustomerNumA005RequestDto {

    /** 対象年月 */
    private String dateYm;
    
    /** 仲介業者コード */
    private String brokerCode;
}
