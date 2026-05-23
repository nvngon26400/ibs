package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 信用新規注文訂正入力　A004レスポンス
 *
 * @author SCSK
 *
 */
@Data
public class IfaMarginNewOrderCorrectInputA004DtoResponse {
    
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
    
    /** 注文種別（半角英数字）. */
    private String orderKind;
    
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
    private Boolean workingStatus;
    
    /** DONE状態（半角英数字）. */
    private String doneState;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 信用取引区分（算出）. */
    private String marginTradeTypeTextCalculation;
    
    /** RBE注文ステータス */
    private String rbeOrderStatus;
    
    /** 受渡日(T+0) */
    private String settlementDate0;
    
    /** 受渡日(T+1) */
    private String settlementDate1;
    
    /** 新規建余力（T+0） */
    private String marginCapacity0;
    
    /** 新規建余力（T+1） */
    private String marginCapacity1;
    
    /** 維持率（T+0） */
    private String actualGrntRate0;
    
    /** 維持率（T+1） */
    private String actualGrntRate1;
    
    /** 手数料区分 */
    private String comId;
    
    /** 受注日 */
    private String inputDate;
    
    /** 約定ステータス */
    private String tradeStatus;
    
    /** 直近市場 */
    private String latestMarketId;
    
    /** SOR注文区分 */
    private String sorOrderClassification;

    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;
    
    /** SOR取扱区分 */
    private String sorServiceKbn;
}
