package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用新規注文取消確認発注前の国内株式注文登録レスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginNewOrderCancelConfirmA002aResponseDto {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
}
