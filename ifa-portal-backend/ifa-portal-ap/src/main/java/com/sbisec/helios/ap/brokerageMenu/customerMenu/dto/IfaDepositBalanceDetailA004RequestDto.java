package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaDepositBalanceDetailA004RequestDto {

    /** 商品名. */
    private String productName;

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 銘柄名（全角半角）. */
    private String brandName;

    /** 商品区分（全角半角）. */
    private String securityType;

    /** 国内外国区分（半角英数字）. */
    private String kokunaiGaiKbn;

    /** 商品種別１（半角英数字）. */
    private String securityClass1;

    /** 商品種別2（半角英数字）. */
    private String securityClass2;

    /** 会社ｺｰﾄﾞ（数字）. */
    private String companyCode;

    /** 権利区分（半角英数字）. */
    private String rightType;

    /** 新旧区分（半角英数字）. */
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
    private String nonSpecificDepositCategory;

    /** 取得口座区分. */
    private String getAccountCategory;

    /** 商品コード（全角半角）. */
    private String securityClass;

    /** 国コード（全角半角）. */
    private String countryCode;

    /** 通貨コード（全角半角）. */
    private String currencyCode;

    /** 預り区分（全角半角）. */
    private String depositType;

}
