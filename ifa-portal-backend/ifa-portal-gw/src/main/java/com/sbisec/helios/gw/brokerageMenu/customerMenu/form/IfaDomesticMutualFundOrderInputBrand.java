package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.math.BigDecimal;

import lombok.Data;

/**
 * 銘柄情報
 *
 * @author SCSK
 */
@Data
public class IfaDomesticMutualFundOrderInputBrand {
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 基準価額単位（数値(整数)）. */
    private BigDecimal priceUnit;
    
    /** 販売最低口数. */
    private BigDecimal minSalesLot;
    
    /** 販売最小単位金額(2回目以降). */
    private BigDecimal minSalesUnitAmount;
    
    /** 販売単位口数. */
    private BigDecimal salesUnitLot;
    
    /** 販売売買単位金額(2回目以降). */
    private BigDecimal salesTradeUnitAmount;
    
    /** 解約売買単位口数. */
    private BigDecimal cancelTradeUnitLot;
    
    /** 解約売買単位金額(2回目以降). */
    private BigDecimal cancelTradeUnitAmount;
    
    /** 注文締切時間. */
    private String deadlines;
    
    /** 売却方法. */
    private String buyMethodSelect;
    
    /** ファンドタイプ（半角英数字）. */
    private String fundType;
    
    /** 再投資区分. */
    private String reInvestmentClassification;
    
    /** 特殊区分. */
    private String brandSpecialClassification;
    
    /** 基準価格（数値(整数)）. */
    private BigDecimal basePrice;
    
    /** 基準価額日付. */
    private String priceDate;
    
}
