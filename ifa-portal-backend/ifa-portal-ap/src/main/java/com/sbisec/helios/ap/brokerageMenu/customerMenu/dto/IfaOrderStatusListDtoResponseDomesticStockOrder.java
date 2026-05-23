package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

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
public class IfaOrderStatusListDtoResponseDomesticStockOrder {
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 商品区分（全角半角）. */
    private String securityType;
    
    /** 有効期限（全角半角）. */
    private String orderPeriod;
    
    /** マーケット発注日. */
    private String marketOrderDate;
    
    /** 強制区分（全角半角）. */
    private String coercionType;
    
    /** 直近市場. */
    private String latestMarketId;
    
    /** SOR連携区分（全角半角）. */
    private String sorLinkKbn;
    
    /** 国内株式銘柄コード. */
    private String brandCode;
    
    /** 漢字銘柄名. */
    private String brandName;
    
    /** 非特定預り売買区分（全角半角）. */
    private String notSpecificDepositTradeType;
    
    /** 採用手数料区分. */
    private String tradingCoursText;
    
    /** 利用Ｔポイント. */
    private String point;
    
    /** 注文受注日時. */
    private String orderDayTime;
    
    /** 受注数量（数値(整数)）. */
    private String orderQuantity;
    
    /** 注文状況（算出）. */
    private String orderStatus;
    
    /** 注文状況補足（算出）. */
    private String orderStatusSupplement;
    
    /** 取引種別（算出）. */
    private String buySellTypeName;
    
    /** 注文種別（算出）. */
    private String orderStatusListOrderClass;
    
    /** 市場（算出）. */
    private String market;
    
    /** 未出来数量（算出）. */
    private String unMatchedTradeQuantity;
    
    /** 執行方法（算出）. */
    private String orderExecuteMethodList;
    
    /** 信用取引区分（算出）. */
    private String marginTradeTypeText;
    
    /** 価格（算出）. */
    private String price;
    
    /** 条件（算出）. */
    private String conditions;
    
    /** 訂正ボタン表示判定（算出）. */
    private String domesticStockCorrectButtonDisplayJudgment;
    
    /** 取消ボタン表示判定（算出）. */
    private String domesticStockCancelButtonDisplayJudgment;
    
}
