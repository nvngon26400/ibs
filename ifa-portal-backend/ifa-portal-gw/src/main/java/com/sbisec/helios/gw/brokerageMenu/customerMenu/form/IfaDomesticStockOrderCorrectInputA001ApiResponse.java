package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

/**
 * 画面ID：SUB0202_0208-03
 * 画面名：国内株式注文訂正入力
 * 2023/01/09 新規作成
 *
 * @author 齋藤
 */
@Data
public class IfaDomesticStockOrderCorrectInputA001ApiResponse {
    
    /** 買付余力.受渡日（T+2） */
    private String settlementDateAfterBusinessDayT2;

    /** 買付余力.受渡日（T+3） */
    private String settlementDateAfterBusinessDayT3;

    /** 買付余力.総合口座（T+2） */
    private String yenBuyingPowerGeneralAccountT2;

    /** 買付余力.総合口座（T+3） */
    private String yenBuyingPowerGeneralAccountT3;

    /** 買付余力.JrNisa口座（T+2） */
    private String yenBuyingPowerJrNisaT2;

    /** 買付余力.JrNisa口座（T+3） */
    private String yenBuyingPowerJrNisaT3;
    
    /** 買付余力.NISA買付可能枠（数字） */
    private String nisaInvestableQuotaMessage;

    /** 銘柄コード（半角英数字） */
    private String brandCode;
    
    /** 銘柄名（全角半角） */
    private String brandName;
    
    /** 市場（全角） */
    private String market;
    
    /** 取引種別 */
    private String tradeCd;
    
    /** 注文数量 */
    private String orderQuantity;
    
    /** 未約定数量（数字） */
    private String unTradeQuantity;
    
    /** 期間 */
    private String period;
    
    /** 預り区分 */
    private String depositType;
    
    /** 注文種別 */
    private String orderKind;
    
    /** 通常/逆指値.執行方法 */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件 */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値） */
    private String triggerPrice;
    
    /** 通常/逆指値.発火条件価格（逆指値）ゾーン */
    private String stopOrderPriceText2;
    
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
    private String oco2StopOrderPriceText2;
    
    /** OCO2.執行方法（逆指値） */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値） */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価 */
    private String oco2Price;
    
    /** IFD1.執行方法 */
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件 */
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値） */
    private String ifd1TriggerPrice;
    
    /** IFD1.発火条件価格（逆指値）ゾーン */
    private String ifd1StopOrderPriceText2;
    
    /** IFD1.執行方法（逆指値） */
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価 */
    private String ifd1Price;
    
    /** IFD2.期間.日付 */
    private String ifd2Limit;
    
    /** IFD2.執行方法 */
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件 */
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値） */
    private String Ifd2TriggerPrice;
    
    /** IFD2.発火条件価格（逆指値）ゾーン */
    private String ifd2StopOrderPriceText2;
    
    /** IFD2.執行方法（逆指値） */
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価 */
    private String ifd2Price;
    
    /**  発火状態 */
    private String workingStatus;
    
    /**  DONE状態 */
    private String doneState;
    
    /** RBE注文ステータス */
    private String rbeOrderStatus;
    
    /** 手数料区分 */
    private String tesuuryouKbn;

    /** 受注日 */
    private String orderDate;
    
    /** 約定ステータス */
    private String tradeStatus;

    /** 直近市場 */
    private String latestMarketId;

    /** SOR注文区分 */
    private String sorOrderClassification;
    
    /** SOR取扱区分 */
    private String sorServiceKbn;
}
