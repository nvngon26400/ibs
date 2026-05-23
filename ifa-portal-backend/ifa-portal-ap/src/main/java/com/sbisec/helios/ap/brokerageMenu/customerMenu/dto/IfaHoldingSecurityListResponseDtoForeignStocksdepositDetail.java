package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 保有商品一覧 外国株式 預り明細情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListResponseDtoForeignStocksdepositDetail {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 保有数量（数値(整数)）. */
    private String holdingQuantity;
    
    /** 注文中（数値(整数)）. */
    private String unactualQuantity;
    
    /** 保護区分（全角半角）. */
    private String protectType;
    
    /** 取得単価（数値(整数)）. */
    private String openPrice;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 現在値（数値(小数)）. */
    private String currentPrice;
    
    /** 通貨コード1（全角半角）. */
    private String currencyCode1;
    
    /** 外貨建評価損益（数値(小数)）. */
    private String foreignProfitAndLoss;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode2;
    
    /** 評価為替レート（数値(小数)）. */
    private String fxValuationRate;
    
    /** 評価額（円貨）（数値(整数)）. */
    private String valuation;
    
    /** 評価損益（円貨）（数値(整数)）. */
    private String yenProfitLoss;
    
    /** 買付表示区分. */
    private String buyDisplayClassification;
    
    /** 売却表示区分. */
    private String saleDisplayClassification;
    
    /** 商品コード（全角半角）. */
    private String securityClass;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 通貨コード3（全角半角）. */
    private String currencyCode3;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 銘柄上場ステータス（半角英数字）. */
    private String brandListedStatus;
    
}
