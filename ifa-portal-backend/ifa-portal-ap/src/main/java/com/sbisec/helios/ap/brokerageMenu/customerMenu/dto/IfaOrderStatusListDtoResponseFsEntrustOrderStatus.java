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
public class IfaOrderStatusListDtoResponseFsEntrustOrderStatus {
    
    /** 注文番号（数字）. */
    private String orderNumber;
    
    /** 注文Sub番号（数字）. */
    private String orderSubNumber;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場略名. */
    private String market;
    
    /** 株取引区分. */
    private String stockTradeType;
    
    /** 注文数量. */
    private String domesticQuantityInput;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 決済方法（全角半角）. */
    private String kessaiHoho;
    
    /** 平均約定単価（数値(小数)）. */
    private String averageTradePrice;
    
    /** 約定数量（数値(整数)）. */
    private String tradeQuantity;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 約定状況. */
    private String tradeStatus;
    
    /** 注文日時. */
    private String orderDate;
    
    /** 手数料適用区分. */
    private String commissionApplicationType;
    
    /** 価格条件. */
    private String orderStatusListOrderClass;
    
    /** 取引種別（算出）. */
    private String buySellTypeName;
    
    /** 価格（算出）. */
    private String price;
    
    /** 期間（算出）. */
    private String orderPeriod;
    
    /** 条件詳細（算出）. */
    private String conditions;
    
    /** 取消ボタン表示判定（算出）. */
    private String mutualFundCancelButtonDisplayJudgment;
    
}
