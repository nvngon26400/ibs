package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 国内投信注文取消確認 A001 レスポンスパラメータ
 *
 * @author SCSK
 *
 *     2023/11/24 新規作成
 */
@Data
public class IfaDomesticMutualFundOrderCancelConfirmA001ApiResponse {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 購入解約方法. */
    private String buyCancelMethod;
    
    /** 金額（数値(整数)）. */
    private String amount;
    
    /** 口数（数値(整数)）. */
    private String unit;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 利用ポイント（数値(整数)）. */
    private String point;
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** ファンドタイプ（全角半角）. */
    private String fundType;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** 乗換優遇区分（全角半角）. */
    private String norikaeYuguKbn;
    
    /** 見積単価（数値(小数)）. */
    private String quoteUnitPrice;
    
    /** 約定金額（数値(整数)）. */
    private String contractAmount;
    
    /** 手数料/諸費用（数値(整数)）. */
    private String charge;
    
    /** 消費税（数値(整数)）. */
    private String consumptionTax;
    
    /** 讓渡益税（数値(整数)）. */
    private String yieldTax;
    
    /** 精算金額（数値(整数)）. */
    private String settlementAmount;
    
    /** 約定予定日. */
    private String contractDate;
    
    /** 受渡予定日. */
    private String deliveryDate;
    
    /** 受注日. */
    private String orderDate;
    
    /** 受注時刻. */
    private String orderTime;
    
    /** 受注数量（数値(整数)）. */
    private String orderQuantity;
    
    /** ポイント種別（全角半角）. */
    private String pointType;
    
    /** 受付経路区分. */
    private String callcenterClassification;
    
    /** 分配金受取方法. */
    private String distributionReceiveMethod;
    
}
