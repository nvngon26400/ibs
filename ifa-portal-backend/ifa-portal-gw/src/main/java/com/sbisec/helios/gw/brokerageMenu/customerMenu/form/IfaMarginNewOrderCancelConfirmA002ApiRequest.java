package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

/**
 * 信用新規注文取消確認注文発注APIリクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginNewOrderCancelConfirmA002ApiRequest {
    
    /** EC受注番号（半角英数字）. */
    @NotEmpty(message = "EC受注番号")
    private String ecOrderNo;
    
    /** 市場（全角）. */
    @NotEmpty(message = "市場")
    private String market;
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    private String brandCode;
    
    /** 数量（数値(整数)）. */
    @NotEmpty(message = "数量")
    private String quantity;
    
    /** 注文種別（半角英数字）. */
    @NotEmpty(message = "注文種別")
    private String orderKind;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    private String tradeCd;
    
    /** 期間. */
    @NotEmpty(message = "期間")
    private String period;
    
    /** 通常/逆指値.執行方法（半角英数字）. */
    @NotEmpty(message = "通常/逆指値.執行方法")
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件（半角英数字）. */
    @NotEmpty(message = "通常/逆指値.執行条件")
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
    @NotEmpty(message = "通常/逆指値.発火条件価格（逆指値）")
    private String triggerPrice;
    
    /** 通常/逆指値.発火条件価格（逆指値）ゾーン. */
    @NotEmpty(message = "通常/逆指値.発火条件価格（逆指値）ゾーン")
    private String triggerPriceZone;
    
    /** 通常/逆指値.執行方法（逆指値）（半角英数字）. */
    @NotEmpty(message = "通常/逆指値.執行方法（逆指値）")
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数値(整数)）. */
    @NotEmpty(message = "通常/逆指値.注文単価")
    private String price;
    
    /** OCO1.執行方法（半角英数字）. */
    @NotEmpty(message = "OCO1.執行方法")
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件（半角英数字）. */
    @NotEmpty(message = "OCO1.執行条件")
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数値(整数)）. */
    @NotEmpty(message = "OCO1.注文単価")
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
    @NotEmpty(message = "OCO2.発火条件価格（逆指値）")
    private String oco2TriggerPrice;
    
    /** OCO2.発火条件価格（逆指値）ゾーン. */
    @NotEmpty(message = "OCO2.発火条件価格（逆指値）ゾーン")
    private String oco2TriggerPriceZone;
    
    /** OCO2.執行方法（逆指値）（半角英数字）. */
    @NotEmpty(message = "OCO2.執行方法（逆指値）")
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値）（半角英数字）. */
    @NotEmpty(message = "OCO2.執行条件（逆指値）")
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数値(整数)）. */
    @NotEmpty(message = "OCO2.注文単価")
    private String oco2Price;
    
    /** IFD1.執行方法（半角英数字）. */
    @NotEmpty(message = "IFD1.執行方法")
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件（半角英数字）. */
    @NotEmpty(message = "IFD1.執行条件")
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数値(整数)）. */
    @NotEmpty(message = "IFD1.発火条件価格（逆指値）")
    private String ifd1TriggerPrice;
    
    /** IFD1.発火条件価格（逆指値）ゾーン. */
    @NotEmpty(message = "IFD1.発火条件価格（逆指値）ゾーン")
    private String ifd1TriggerPriceZone;
    
    /** IFD1.執行方法（逆指値）（半角英数字）. */
    @NotEmpty(message = "IFD1.執行方法（逆指値）")
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数値(整数)）. */
    @NotEmpty(message = "IFD1.注文単価")
    private String ifd1Price;
    
    /** IFD2.期間.期間条件. */
    @NotEmpty(message = "IFD2.期間.期間条件")
    private String ifd2PeriodTerm;
    
    /** IFD2.期間.日付（全角半角）. */
    @NotEmpty(message = "IFD2.期間.日付")
    private String ifd2Limit;
    
    /** IFD2.執行方法（半角英数字）. */
    @NotEmpty(message = "IFD2.執行方法")
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件（半角英数字）. */
    @NotEmpty(message = "IFD2.執行条件")
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数値(整数)）. */
    @NotEmpty(message = "IFD2.発火条件価格（逆指値）")
    private String ifd2TriggerPrice;
    
    /** IFD2.発火条件価格（逆指値）ゾーン. */
    @NotEmpty(message = "IFD2.発火条件価格（逆指値）ゾーン")
    private String ifd2TriggerPriceZone;
    
    /** IFD2.執行方法（逆指値）（半角英数字）. */
    @NotEmpty(message = "IFD2.執行方法（逆指値）")
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価（数値(整数)）. */
    @NotEmpty(message = "IFD2.注文単価")
    private String ifd2Price;
    
    /** 信用取引区分（全角半角）. */
    @NotEmpty(message = "信用取引区分")
    private String marginTradeTypeText;
    
    /** 弁済期限（全角半角）. */
    @NotEmpty(message = "弁済期限")
    private String paymentDeadline;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    private String brandName;
    
    /** 自動注文種別（半角英数字）. */
    @NotEmpty(message = "自動注文種別")
    private String autoOrderClass;
    
    /** RBE注文種別（全角半角）. */
    @NotEmpty(message = "RBE注文種別")
    private String rbeChumonShubetsu;
    
    /** 指成区分（半角英数字）. */
    @NotEmpty(message = "指成区分")
    private String sashinariKbn;
    
    /** 指値（数値(小数)）. */
    @NotEmpty(message = "指値")
    private String sashine;
    
    /** トリガ発動ゾーン（半角英数字）. */
    @NotEmpty(message = "トリガ発動ゾーン")
    private String triggerZone;
    
    /** トリガ値段（数値(小数)）. */
    @NotEmpty(message = "トリガ値段")
    private String triggerNedan;
    
    /** OCO指成区分（半角英数字）. */
    @NotEmpty(message = "OCO指成区分")
    private String ocoSasinariKbn;
    
    /** OCO指値（数値(小数)）. */
    @NotEmpty(message = "OCO指値")
    private String ocoSashine;
    
    /** DONE指成区分（半角英数字）. */
    @NotEmpty(message = "DONE指成区分")
    private String doneSasinariKbn;
    
    /** DONE指値（数値(小数)）. */
    @NotEmpty(message = "DONE指値")
    private String doneSashine;
    
    /** DONERBE注文種別（全角半角）. */
    @NotEmpty(message = "DONERBE注文種別")
    private String doneRbeOrderKind;
    
    /** DONEトリガ発動ゾーン（半角英数字）. */
    @NotEmpty(message = "DONEトリガ発動ゾーン")
    private String doneTriggerZone;
    
    /** DONEトリガ値段（数値(小数)）. */
    @NotEmpty(message = "DONEトリガ値段")
    private String doneTriggerNedan;
    
    /** DONEOCO指成区分（半角英数字）. */
    @NotEmpty(message = "DONEOCO指成区分")
    private String doneOcoSasinariKbn;
    
    /** DONEOCO指値（数値(小数)）. */
    @NotEmpty(message = "DONEOCO指値")
    private String doneOcoSashine;
    
    /** DONE有効期限（全角半角）. */
    @NotEmpty(message = "DONE有効期限")
    private String doneYuukouKigen;
    
    /** RBE注文ステータス（半角英数字）. */
    @NotEmpty(message = "RBE注文ステータス")
    private String rbeOrderStatus;
    
    /** 受注日時. */
    @NotEmpty(message = "受注日時")
    private String orderDayTime;

    /** 弁済期限年月日数. */
    @NotEmpty(message = "弁済期限年月日数")
    private String paymentDeadlineDate;

    /** 年月日区分. */
    @NotEmpty(message = "年月日区分")
    private String dateKbn;

    /** 手数料区分（採用）. */
    @NotEmpty(message = "手数料区分（採用）")
    private String comIdR;
    
}
