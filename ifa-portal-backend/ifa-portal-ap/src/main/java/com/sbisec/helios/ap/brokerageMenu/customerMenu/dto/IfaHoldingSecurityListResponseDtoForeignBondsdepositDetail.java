package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 保有商品一覧 外国債券 預り明細情報
 *
 * @author SCSK
 */
@Data
public class IfaHoldingSecurityListResponseDtoForeignBondsdepositDetail {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 保有額面. */
    private String volumeForeign;
    
    /** 買付単価（数値(小数)）. */
    private String unitPrice;
    
    /** 外貨建評価額（数値(整数)）. */
    private String foreignValuation17;
    
    /** 参考為替（数値(小数)）. */
    private String exchangeRate;
    
    /** 円換算評価額（数値(整数)）. */
    private String yenConversionValuation;
    
    /** 通貨コード（全角半角）. */
    private String currencyCode;
    
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

    /** 預り区分. */
    private String depositType;

    /** 国コード. */
    private String countryCode;

    /** 商品コード. */
    private String securityClass;
    
}
