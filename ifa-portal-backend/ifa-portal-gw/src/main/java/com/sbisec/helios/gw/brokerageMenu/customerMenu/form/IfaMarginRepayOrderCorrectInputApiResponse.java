package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0212-06_1
 * 画面名：信用返済注文訂正入力
 * 2024/05/24 新規作成
 *
 * @author SCSK 笹倉秀行
 */
@Data
public class IfaMarginRepayOrderCorrectInputApiResponse {
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 銘柄名 */
    private String brandName;
    
    /** 発注市場 */
    private String orderMarket;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 注文数量 */
    private String quantity;
    
    /** 未約定数量 */
    private String unTradeQuantity;
    
    /** 期間 */
    private String period;
    
    /** 注文種別 */
    private String orderKind;
    
    /** 通常/逆指値.執行方法 */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件 */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値） */
    private String triggerPrice;
    
    /** 通常/逆指値.発火条件価格（逆指値）ゾーン */
    private String triggerPriceText;
    
    /** 通常/逆指値.執行方法（逆指値） */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価 */
    private String price;
    
    /** OCO1.執行方法 */
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件 */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価 */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値） */
    private String oco2TriggerPrice;
    
    /** OCO2.発火条件価格（逆指値）ゾーン */
    private String oco2TriggerPriceText;
    
    /** OCO2.執行方法（逆指値） */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値） */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価 */
    private String oco2Price;
    
    /** 発火状態 */
    private String workingStatus;
    
    /** 弁済期限 */
    private String paymentDeadline;
    
    /** 信用取引区分 */
    private String marginTradeTypeText;
    
    /** RBE注文ステータス */
    private String rbeOrderStatus;
    
    /** 新規単価 */
    private String newPrice;
    
    /** 新規建日（新規約定日） */
    private String constructionDate;
    
    /** 建玉No */
    private String positionNo;
    
    /** 手数料区分 */
    private String tesuuryouKbn;
    
    /** 受注日 */
    private String orderDate;

    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;

    /** 約定ステータス */
    private String tradeStatus;

    /** 直近市場 */
    private String latestMarketId;

    /** SOR注文区分 */
    private String sorOrderClassification;
    
    /** SOR取扱区分 */
    private String sorServiceKbn;
}
