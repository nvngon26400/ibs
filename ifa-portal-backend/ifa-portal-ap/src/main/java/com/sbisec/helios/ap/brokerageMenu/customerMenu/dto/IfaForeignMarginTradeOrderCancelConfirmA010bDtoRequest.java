package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

@Data
public class IfaForeignMarginTradeOrderCancelConfirmA010bDtoRequest {
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 決済方法（全角半角）. */
    private String currencyTypeRadio;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 銘柄情報.銘柄コード. */
    private String brandCode;
    
    /** 銘柄情報.銘柄名. */
    private String brandName;
    
    /** 取引通貨. */
    private String currencyCode;
    
    /** 市場情報.市場名. */
    private String marketName;
    
    /** 市場情報.市場コード. */
    private String marketCode;
    
    /** 市場情報.国コード（全角半角）. */
    private String countryCode;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 注文数量. */
    private String foreignQuantity;
    
    /** 価格条件. */
    private String orderPriceKindList;
    
    /** 注文単価（数値(小数)）. */
    private String hiddenOrderPrice;
    
    /** 発火条件価格. */
    private String stopOrderPrice2;
    
    /** 期間条件. */
    private String periodRadio;
    
    /** 期間. */
    private String period;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 国内約定日. */
    private String domesticTradeDate;
    
    /** 現地約定日. */
    private String foreignTradeDate;
    
    /** 返済建玉指定方法（全角半角）. */
    private String repaymentMethodRadio;
    
    /** 返済選択順序（全角半角）. */
    private String sortOrderRadio;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号(数字）. */
    private String ifaOrderSubNo;
    
}
