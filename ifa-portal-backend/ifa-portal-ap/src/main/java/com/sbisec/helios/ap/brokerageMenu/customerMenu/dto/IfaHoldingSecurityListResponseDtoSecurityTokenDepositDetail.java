package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 保有商品一覧 ST（セキュリティ・トークン） 預り明細情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListResponseDtoSecurityTokenDepositDetail {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 約定基準残高（数値(整数)）. */
    private String contractStandardDeposit;
    
    /** 取得単価（数値(小数)）. */
    private String openPrice;
    
    /** 時価（数値(小数)）. */
    private String price;
    
    /** 評価額（円貨）（数値(整数)）. */
    private String valuation;
    
    /** 評価損益（数値(整数)）. */
    private String yenProfitLoss;
    
}
