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
public class IfaMarginRepayOrderCorrectInputA010ApiRequest {
    
    /** EC受注番号 */
    private String ecOrderNo;
    
    /** 市場 */
    private String orderMarket;
    
    /** 銘柄コード */
    private String brandCode;
    
    /** 訂正後市場 */
    private String afterCorrecMarket;

    /** 数量 */
    private String quantity;
    
    /** 未約定数量 */
    private String unTradeQuantity;
    
    /** 注文種別 */
    private String orderKind;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 期間 */
    private String period;
    
    /** 信用取引区分 */
    private String marginTradeTypeText;
    
    /** 発火状態 */
    private String workingStatus;
    
    /** 弁済期限 */
    private String paymentDeadline;
    
    /** RBE注文ステータス */
    private String rbeOrderStatus;
    
    /** 通常/逆指値.執行方法 */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件 */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値） */
    private String triggerPrice;
    
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
    
    /** OCO2.執行方法（逆指値） */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値） */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価 */
    private String oco2Price;
    
    /** 訂正前価格.通常/逆指値.執行方法 */
    private String correctBeforePriceSasinariHouhou;
    
    /** 訂正前価格.通常/逆指値.執行条件 */
    private String correctBeforePriceSasinariJyouken;
    
    /** 訂正前価格.通常/逆指値.発火条件価格（逆指値） */
    private String correctBeforePriceTriggerPrice;
    
    /** 訂正前価格.通常/逆指値.発火条件価格（逆指値）ゾーン */
    private String correctBeforePriceStopOrderPriceText2;
    
    /** 訂正前価格.通常/逆指値.執行方法（逆指値） */
    private String correctBeforePriceGyakusasiHouhou;
    
    /** 訂正前価格.通常/逆指値.注文単価 */
    private String correctBeforePricePrice;
    
    /** 訂正前価格.OCO1.執行方法 */
    private String correctBeforePriceOco1SasinariHouhou;
    
    /** 訂正前価格.OCO1.執行条件 */
    private String correctBeforePriceOco1SasinariJyouken;
    
    /** 訂正前価格.OCO1.注文単価 */
    private String correctBeforePriceOco1Price;
    
    /** 訂正前価格.OCO2.発火条件価格（逆指値） */
    private String correctBeforePriceOco2TriggerPrice;
    
    /** 訂正前価格.OCO2.発火条件価格（逆指値）ゾーン */
    private String correctBeforePriceOco2StopOrderPriceText2;
    
    /** 訂正前価格.OCO2.執行方法（逆指値） */
    private String correctBeforePriceOco2GyakusasiHouhou;
    
    /** 訂正前価格.OCO2.執行条件（逆指値） */
    private String correctBeforePriceOco2GyakusasiJyouken;
    
    /** 訂正前価格.OCO2.注文単価 */
    private String correctBeforePriceOco2Price;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** 受注方法 */
    private String orderMethod;
    
    /** 確認項目.契約締結前交付書面確認 */
    private String checkInsider;

    /** 確認項目.SOR確認 */
    private String checkSor;
    
    /** 手数料区分 */
    private String tesuuryouKbn;
    
    /** 受注日 */
    private String orderDate;

    /** 訂正SOR注文区分 */
    private String correctSorOrderClassification;
    
    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;
}
