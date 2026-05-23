package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-06_2
 * 画面名：信用返済注文訂正確認
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉 秀行
 */
@Data
public class IfaMarginRepayOrderCorrectConfirmA001aResponseDto {
    
    /** IFA注文番号. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号. */
    private String ifaOrderSubNo;
}
