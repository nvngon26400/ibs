package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
@Data
public class IfaDomesticStockOrderCancelConfirmA002aDtoResponse {
    
    /** IFA注文番号（数字） */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字） */
    private String ifaOrderSubNo;
    
}
