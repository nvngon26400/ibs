package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0104-01
 * 画面名：注文状況一覧
 *
 * @author 齋藤
 *
 *          2023/10/16 新規作成
 */

@Data
public class IfaOrderStatusListApiResponseDomesticMutualFundOrderStatus {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 売買区分（全角半角）. */
    private String tradeKbn;
    
    /** ファンドコード（回数）（半角英数字）. */
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    private String fundCodeIssues;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 非特定預り売買区分（全角半角）. */
    private String notSpecificDepositTradeType;
    
    /** 発注日. */
    private String orderDate;
    
    /** 注文受付日時. */
    private String orderDayTime;
    
    /** 再投資停止指定. */
    private String distributionReceiveMethod;
    
    /** 受付経路区分. */
    private String callcenterKbn;
    
    /** ポイント種別（全角半角）. */
    private String pointType;
    
    /** 利用ポイント（数値(整数)）. */
    private String point;
    
    /** 余力チェック区分（全角半角）. */
    private String yoryokuCheckKbn;
    
    /** 注文状況（算出）. */
    private String orderStatus;
    
    /** 取引種別（算出）. */
    private String buySellTypeName;
    
    /** 数量（算出）. */
    private String unitAmount;
    
    /** 数量単位（算出）. */
    private String unit;
    
    /** 取消ボタン表示判定（算出）. */
    private String mutualFundCancelButtonDisplayJudgment;
    
}
