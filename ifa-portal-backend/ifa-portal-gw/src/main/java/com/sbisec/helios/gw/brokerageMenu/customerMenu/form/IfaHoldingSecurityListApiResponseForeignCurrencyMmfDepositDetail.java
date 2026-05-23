package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 保有商品一覧 外貨建MMF 預り明細情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListApiResponseForeignCurrencyMmfDepositDetail {
    
    /** ファンド名（全角半角）. */
    private String fundName;
    
    /** 保有口数（数値(整数)）. */
    private String unitVolume;
    
    /** 注文中（数値(整数)）. */
    private String unactualQuantity;
    
    /** 評価額（外貨）（数値(小数)）. */
    private String foreignValuation;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
    /** 評価為替レート（数値(小数)）. */
    private String fxValuationRate;
    
    /** 評価額（円貨）（数値(整数)）. */
    private String valuation;
    
    /** 評価損益（円貨）（数値(整数)）. */
    private String yenProfitLoss;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 商品コード（全角半角）. */
    private String securityClass;
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
}
