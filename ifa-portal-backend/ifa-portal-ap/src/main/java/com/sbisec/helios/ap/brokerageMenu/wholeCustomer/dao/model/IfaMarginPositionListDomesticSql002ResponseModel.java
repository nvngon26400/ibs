package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

@Data
public class IfaMarginPositionListDomesticSql002ResponseModel {
    
    /** 証券コード. */
    private String securityCode;
    
    /** ＩＳＩＮ. */
    private String ISINCode;
    
    /** 銘柄略称. */
    private String shortBrand;
    
    /** 英語銘柄略称. */
    private String shortBrandEnglish;
    
    /** 銘柄名称 */
    private String brandName;
    
    /** 英語銘柄名称 */
    private String brandNameEnglish;
    
    /** 読みカナ銘柄名称. */
    private String brandNameKana;
    
    /** 種類株区分. */
    private String stockType;
    
    /** 投資信託区分. */
    private String mutualFund;
    
    /** 証券コード協議会業種コード. */
    private String councilInustryCode;
    
    /** 主要取引所コード. */
    private String mainExchangeCode;
    
    /** SBI主要取引所コード. */
    private String SBIMainExchangeCode;
    
    /** 単元株数. */
    private String unitStock;
    
}
