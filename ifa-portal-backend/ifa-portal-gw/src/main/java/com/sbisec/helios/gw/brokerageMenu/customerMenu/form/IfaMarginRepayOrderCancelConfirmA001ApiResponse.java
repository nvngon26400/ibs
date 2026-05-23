package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 信用返済注文取消確認初期化APIレスポンス.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderCancelConfirmA001ApiResponse {
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場（全角）. */
    private String market;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 注文数量. */
    private String orderQuantity;
    
    /** 未約定数量（数値(整数)）. */
    private String unTradeQuantity;
    
    /** 期間. */
    private String period;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 注文種別（半角英数字）. */
    private String orderKind;
    
    /** 受注日時. */
    private String orderDayTime;
    
    /** 通常/逆指値.執行方法. */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件. */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数値(小数)）. */
    private String triggerPrice;
    
    /** 通常/逆指値.発火条件価格（逆指値）ゾーン. */
    private String triggerPriceZone;
    
    /** 通常/逆指値.執行方法（逆指値）. */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数値(小数)）. */
    private String price;
    
    /** OCO1.執行方法. */
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件. */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数値(小数)）. */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数値(小数)）. */
    private String oco2TriggerPrice;
    
    /** OCO2.発火条件価格（逆指値）ゾーン. */
    private String oco2TriggerPriceZone;
    
    /** OCO2.執行方法（逆指値）. */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値）. */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数値(小数)）. */
    private String oco2Price;
    
    /** IFD1.執行方法. */
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件. */
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数値(小数)）. */
    private String ifd1TriggerPrice;
    
    /** IFD1.発火条件価格（逆指値）ゾーン. */
    private String ifd1TriggerPriceZone;
    
    /** IFD1.執行方法（逆指値）. */
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数値(小数)）. */
    private String ifd1Price;
    
    /** IFD2.期間.日付. */
    private String ifd2Limit;
    
    /** IFD2.執行方法. */
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件. */
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数値(小数)）. */
    private String ifd2TriggerPrice;
    
    /** IFD2.発火条件価格（逆指値）ゾーン. */
    private String ifd2TriggerPriceZone;
    
    /** IFD2.執行方法（逆指値）. */
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価（数値(小数)）. */
    private String ifd2Price;
    
    /** 発火状態（半角英数字）. */
    private String workingStatus;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 信用取引区分（算出）. */
    private String marginTradeTypeTextCalculation;
    
    /** RBE注文種別（全角半角）. */
    private String rbeChumonShubetsu;
    
    /** 指成区分（半角英数字）. */
    private String sashinariKbn;
    
    /** 指値（数値(小数)）. */
    private String sashine;
    
    /** トリガ発動ゾーン（半角英数字）. */
    private String triggerZone;
    
    /** トリガ値段（数値(小数)）. */
    private String triggerNedan;
    
    /** OCO指成区分（半角英数字）. */
    private String ocoSasinariKbn;
    
    /** OCO指値（数値(小数)）. */
    private String ocoSashine;
    
    /** RBE注文ステータス（半角英数字）. */
    private String rbeOrderStatus;

    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;

    /** 手数料区分（採用）（全角半角）. */
    private String comIdR;
    
}
