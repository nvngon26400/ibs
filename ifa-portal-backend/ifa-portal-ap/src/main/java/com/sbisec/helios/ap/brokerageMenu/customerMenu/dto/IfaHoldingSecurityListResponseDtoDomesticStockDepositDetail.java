package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 保有商品一覧 国内株式 預り明細情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListResponseDtoDomesticStockDepositDetail {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 保有株数（数値(整数)）. */
    private String holdingStock;
    
    /** 売却注文中（数値(整数)）. */
    private String sellingVolume;
    
    /** 取得単価/参考単価. */
    private String acquirePriceReferencePrice;
    
    /** 現在値（数値(小数)）. */
    private String currentPrice;
    
    /** 取得金額/参考金額（数値(小数)）. */
    private String acquireAmountReferenceAmount;
    
    /** 評価額（数値(小数)）. */
    private String valuation;
    
    /** 評価損益. */
    private String domesticStockListValuation;
    
    /** 現買表示区分. */
    private String buyDisplayClassification;
    
    /** 現売表示区分. */
    private String saleDisplayClassification;
    
    /** 商品区分（全角半角）. */
    private String securityType;
    
    /** 国内外国区分（全角半角）. */
    private String kokunaiGaiKbn;
    
    /** 商品種別１（全角半角）. */
    private String securityClass1;
    
    /** 商品種別2（全角半角）. */
    private String securityClass2;
    
    /** 会社ｺｰﾄﾞ（数字）. */
    private String companyCode;
    
    /** 権利区分（全角半角）. */
    private String rightType;
    
    /** 新旧区分（全角半角）. */
    private String newOldType;
    
    /** 回数（数値(整数)）. */
    private String times;
    
    /** 号1（全角半角）. */
    private String issue1;
    
    /** 号2（全角半角）. */
    private String issue2;
    
    /** 上場国ｺｰﾄﾞ（全角半角）. */
    private String listedCountryCode;
    
    /** 非特定預り区分. */
    private String depositBalanceListSpecificDepositType;
    
}
