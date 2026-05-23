package com.sbisec.helios.ap.brokerageMenu.customerMenu.dto;

import lombok.Data;

/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
@Data
public class IfaDomesticStockOrderCancelConfirmA002bDtoResponse {
    
    /** 受注日時  */
    private String orderDayTime;
    
    /** EC受注番号（半角英数字） */
    private String ecOrderNo;
    
    /** 市場（全角） */
    private String market;
    
    /** 銘柄コード（半角英数字） */
    private String brandCode;
    
    /** 数量（数値(整数)） */
    private String quantity;
    
    /** 注文種別（全角半角） */
    private String orderKind;
    
    /** 取引種別（全角半角） */
    private String tradeCd;
    
    /** 期間 */
    private String period;
    
    /** 預り区分（全角半角） */
    private String depositType;
    
    /** 通常/逆指値.執行方法（半角英数字） */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件（半角英数字） */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)） */
    private String triggerPrice;
    
    /** 通常/逆指値.発火条件価格（逆指値）ゾーン */
    private String stopOrderPriceText2;
    
    /** 通常/逆指値.執行方法（逆指値）（半角英数字） */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数値(整数)） */
    private String price;
    
    /** OCO1.執行方法（半角英数字） */
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件（半角英数字） */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数値(整数)） */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数値(整数)） */
    private String oco2TriggerPrice;
    
    /** OCO2.発火条件価格（逆指値）ゾーン */
    private String oco2StopOrderPriceText2;
    
    /** OCO2.執行方法（逆指値）（半角英数字） */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値）（半角英数字） */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数値(整数)） */
    private String oco2Price;
    
    /** IFD1.執行方法（半角英数字） */
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件（半角英数字） */
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数値(整数)） */
    private String ifd1TriggerPrice;
    
    /** IFD1.発火条件価格（逆指値）ゾーン */
    private String ifd1StopOrderPriceText2;
    
    /** IFD1.執行方法（逆指値）（半角英数字） */
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数値(整数)） */
    private String ifd1Price;
    
    /** IFD2.期間.日付（全角半角） */
    private String ifd2Limit;
    
    /** IFD2.執行方法（半角英数字） */
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件（半角英数字） */
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数値(整数)） */
    private String ifd2TriggerPrice;
    
    /** IFD2.発火条件価格（逆指値）ゾーン */
    private String ifd2StopOrderPriceText2;
    
    /** IFD2.執行方法（逆指値）（半角英数字） */
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価（数値(整数)） */
    private String ifd2Price;
    
    /** 銘柄名（全角半角） */
    private String brandName;
    
    /** 自動注文種別（半角英数字） */
    private String autoOrderShubetsu;
    
    /** RBE注文種別（全角半角） */
    private String rbeChumonShubetsu;
    
    /** 指成区分（半角英数字） */
    private String sashinariKbn;
    
    /** 指値（数値(小数)） */
    private String sashine;
    
    /** トリガ発動ゾーン（半角英数字） */
    private String triggerZone;
    
    /** トリガ値段（数値(小数)） */
    private String triggerNedan;
    
    /** OCO指成区分（半角英数字） */
    private String ocoSasinariKbn;
    
    /** OCO指値（数値(小数)） */
    private String ocoSashine;
    
    /** DONE指成区分（半角英数字） */
    private String doneSasinariKbn;
    
    /** DONE指値（数値(小数)） */
    private String doneSashine;
    
    /** DONERBE注文種別（全角半角） */
    private String doneRbeOrderKind;
    
    /** DONEトリガ発動ゾーン（半角英数字） */
    private String doneTriggerZone;
    
    /** DONEトリガ値段（数値(小数)） */
    private String doneTriggerNedan;
    
    /** DONEOCO指成区分（半角英数字） */
    private String doneOcoSasinariKbn;
    
    /** DONEOCO指値（数値(小数)） */
    private String doneOcoSashine;
    
    /** DONE有効期限（全角半角） */
    private String doneYuukouKigen;
    
    /** RBE注文ステータス（半角英数字） */
    private String rbeOrderStatus;

    /** 発火状態（全角半角） */
    private String workingStatus;
    
}
