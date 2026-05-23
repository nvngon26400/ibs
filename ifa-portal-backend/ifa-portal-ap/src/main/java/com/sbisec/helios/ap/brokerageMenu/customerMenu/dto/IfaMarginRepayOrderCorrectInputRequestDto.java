package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-06_1
 * 画面名：信用返済注文訂正入力
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaMarginRepayOrderCorrectInputRequestDto {
    
    /** EC受注番号 */
    private String ecOrderNo;
}
