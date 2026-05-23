package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 米株建玉一覧 A001 レスポンスパラメータ（建玉一覧情報）
 *
 * @author SCSK
 * 
 */
@Data
public class IfaForeignPositionListA001ApiResponsePositionList {
    
    /** 国コード（全角半角）. */
    private String countryCode;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場略名. */
    private String marketAbbreviatedName;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 信用期日. */
    private String marginDueDate;
    
    /** 建日. */
    private String openTradeDate;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 返済期限. */
    private String lastTradeDate;
    
    /** 建玉必要保証金率. */
    private String positionNecessaryDepositRate;
    
    /** 増担保規制建玉フラグ. */
    private String additionalSecurityRegulationPositionFlag;
    
    /** 建玉残数量. */
    private String positionRemainingQuantity;
    
    /** 注文中（数値(整数)）. */
    private String unactualQuantity;
    
    /** 新規建単価（外貨）. */
    private String newPositionPriceForeign;
    
    /** 現在値or前日終値. */
    private String currentPriceOrPreviousDayEndPrice;
    
    /** 新規建代金（外貨）（数値(小数)）. */
    private String foreignNewPositionAmount;
    
    /** 諸経費合計額（外貨）. */
    private String expensesTotalAmountForeign;
    
    /** 評価損益（外貨）. */
    private String customerListProfitAndLossForeign;
    
    /** 評価割合（数値(小数)）. */
    private String valuationRate;
    
    /** 返済売ボタン. */
    private String repaySellButton;
    
    /** 返済買ボタン. */
    private String repayBuyButton;
    
    /** 取引ボタン非活性（全角半角）. */
    private String tradeButtonDeactivation;
    
}
