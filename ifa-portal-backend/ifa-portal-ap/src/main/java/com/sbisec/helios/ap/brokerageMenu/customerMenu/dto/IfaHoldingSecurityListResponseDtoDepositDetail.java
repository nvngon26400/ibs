package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 保有商品一覧 その他商品 預り明細情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListResponseDtoDepositDetail {
    
    /** 商品分類（全角半角）. */
    private String securityClass;
    
    /** 銘柄名/ファンド名（全角半角）. */
    private String brandNameFundName;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
}
