package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
@Data
public class IfaDomesticStockOrderCancelConfirmA002ApiRequest {
    
/** EC受注番号（半角英数字） */
    @NotEmpty(message = "EC受注番号")
    @Size(min = 6, max = 6, message = "EC受注番号")
    private String ecOrderNo;
    
    /** 市場（全角） */
    @NotEmpty(message = "市場")
    @Size(max = 4, message = "市場")
    private String market;
    
    /** 銘柄コード（半角英数字） */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 数量（数値(整数)） */
    @Digits(integer = 15, fraction = 0, message = "数量")
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String quantity;
    
    /** 注文種別（半角英数字） */
    @NotEmpty(message = "注文種別")
    @Size(min = 1, max = 1, message = "注文種別")
    private String orderKind;
    
    /** 取引種別（全角半角） */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;
    
    /** 期間 */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "期間")
    @Size(min = 10, max = 10, message = "期間")
    private String period;
    
    /** 預り区分（全角半角） */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 通常/逆指値.執行方法（半角英数字） */
    @NotEmpty(message = "通常/逆指値.執行方法")
    @Size(min = 1, max = 1, message = "通常/逆指値.執行方法")
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件（半角英数字） */
    @NotEmpty(message = "通常/逆指値.執行条件")
    @Size(min = 1, max = 1, message = "通常/逆指値.執行条件")
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "通常/逆指値.発火条件価格（逆指値）")
    @NotEmpty(message = "通常/逆指値.発火条件価格（逆指値）")
    @Size(max = 13, message = "通常/逆指値.発火条件価格（逆指値）")
    private String triggerPrice;
    
    /** 通常/逆指値.発火条件価格（逆指値）ゾーン */
    @NotEmpty(message = "通常/逆指値.発火条件価格（逆指値）ゾーン")
    private String stopOrderPriceText2;

    /** 通常/逆指値.執行方法（逆指値）（半角英数字） */
    @NotEmpty(message = "通常/逆指値.執行方法（逆指値）")
    @Size(min = 1, max = 1, message = "通常/逆指値.執行方法（逆指値）")
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "通常/逆指値.注文単価")
    @NotEmpty(message = "通常/逆指値.注文単価")
    @Size(max = 13, message = "通常/逆指値.注文単価")
    private String price;
    
    /** OCO1.執行方法（半角英数字） */
    @NotEmpty(message = "OCO1.執行方法")
    @Size(min = 1, max = 1, message = "OCO1.執行方法")
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件（半角英数字） */
    @NotEmpty(message = "OCO1.執行条件")
    @Size(min = 1, max = 1, message = "OCO1.執行条件")
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "OCO1.注文単価")
    @NotEmpty(message = "OCO1.注文単価")
    @Size(max = 13, message = "OCO1.注文単価")
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "OCO2.発火条件価格（逆指値）")
    @NotEmpty(message = "OCO2.発火条件価格（逆指値）")
    @Size(max = 13, message = "OCO2.発火条件価格（逆指値）")
    private String oco2TriggerPrice;
    
    /** OCO2.発火条件価格（逆指値）ゾーン */
    @NotEmpty(message = "OCO2.発火条件価格（逆指値）ゾーン")
    private String oco2StopOrderPriceText2;

    /** OCO2.執行方法（逆指値）（半角英数字） */
    @NotEmpty(message = "OCO2.執行方法（逆指値）")
    @Size(min = 1, max = 1, message = "OCO2.執行方法（逆指値）")
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値）（半角英数字） */
    @NotEmpty(message = "OCO2.執行条件（逆指値）")
    @Size(min = 1, max = 1, message = "OCO2.執行条件（逆指値）")
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "OCO2.注文単価")
    @NotEmpty(message = "OCO2.注文単価")
    @Size(max = 13, message = "OCO2.注文単価")
    private String oco2Price;
    
    /** IFD1.執行方法（半角英数字） */
    @NotEmpty(message = "IFD1.執行方法")
    @Size(min = 1, max = 1, message = "IFD1.執行方法")
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件（半角英数字） */
    @NotEmpty(message = "IFD1.執行条件")
    @Size(min = 1, max = 1, message = "IFD1.執行条件")
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "IFD1.発火条件価格（逆指値）")
    @NotEmpty(message = "IFD1.発火条件価格（逆指値）")
    @Size(max = 13, message = "IFD1.発火条件価格（逆指値）")
    private String ifd1TriggerPrice;
    
    /** IFD1.発火条件価格（逆指値）ゾーン */
    @NotEmpty(message = "IFD1.発火条件価格（逆指値）ゾーン")
    private String ifd1StopOrderPriceText2;

    /** IFD1.執行方法（逆指値）（半角英数字） */
    @NotEmpty(message = "IFD1.執行方法（逆指値）")
    @Size(min = 1, max = 1, message = "IFD1.執行方法（逆指値）")
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "IFD1.注文単価")
    @NotEmpty(message = "IFD1.注文単価")
    @Size(max = 13, message = "IFD1.注文単価")
    private String ifd1Price;
    
    /** IFD2.期間.期間条件 */
    @NotEmpty(message = "IFD2.期間.期間条件")
    private String ifd2PeriodRadio;

    /** IFD2.期間.日付（全角半角） */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "IFD2.期間.日付")
    @Size(min = 10, max = 10, message = "IFD2.期間.日付")
    private String ifd2Limit;
    
    /** IFD2.執行方法（半角英数字） */
    @NotEmpty(message = "IFD2.執行方法")
    @Size(min = 1, max = 1, message = "IFD2.執行方法")
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件（半角英数字） */
    @NotEmpty(message = "IFD2.執行条件")
    @Size(min = 1, max = 1, message = "IFD2.執行条件")
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "IFD2.発火条件価格（逆指値）")
    @NotEmpty(message = "IFD2.発火条件価格（逆指値）")
    @Size(max = 13, message = "IFD2.発火条件価格（逆指値）")
    private String ifd2TriggerPrice;
    
    /** IFD2.発火条件価格（逆指値）ゾーン */
    @NotEmpty(message = "IFD2.発火条件価格（逆指値）ゾーン")
    private String ifd2StopOrderPriceText2;

    /** IFD2.執行方法（逆指値）（半角英数字） */
    @NotEmpty(message = "IFD2.執行方法（逆指値）")
    @Size(min = 1, max = 1, message = "IFD2.執行方法（逆指値）")
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価（数値(整数)） */
    @Digits(integer = 10, fraction = 0, message = "IFD2.注文単価")
    @NotEmpty(message = "IFD2.注文単価")
    @Size(max = 13, message = "IFD2.注文単価")
    private String ifd2Price;
    
    /** 銘柄名（全角半角） */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 自動注文種別（半角英数字） */
    @NotEmpty(message = "自動注文種別")
    @Size(min = 4, max = 4, message = "自動注文種別")
    private String autoOrderShubetsu;
    
    /** RBE注文種別（全角半角） */
    @NotEmpty(message = "RBE注文種別")
    @Size(min = 3, max = 3, message = "RBE注文種別")
    private String rbeChumonShubetsu;
    
    /** 指成区分（半角英数字） */
    @NotEmpty(message = "指成区分")
    @Size(min = 1, max = 1, message = "指成区分")
    private String sashinariKbn;
    
    /** 指値（数値(小数)） */
    @Digits(integer = 15, fraction = 1, message = "指値")
    @NotEmpty(message = "指値")
    @Size(max = 21, message = "指値")
    private String sashine;
    
    /** トリガ発動ゾーン（半角英数字） */
    @NotEmpty(message = "トリガ発動ゾーン")
    @Size(min = 1, max = 1, message = "トリガ発動ゾーン")
    private String triggerZone;
    
    /** トリガ値段（数値(小数)） */
    @Digits(integer = 10, fraction = 1, message = "トリガ値段")
    @NotEmpty(message = "トリガ値段")
    @Size(max = 15, message = "トリガ値段")
    private String triggerNedan;
    
    /** OCO指成区分（半角英数字） */
    @NotEmpty(message = "OCO指成区分")
    @Size(min = 1, max = 1, message = "OCO指成区分")
    private String ocoSasinariKbn;
    
    /** OCO指値（数値(小数)） */
    @Digits(integer = 15, fraction = 1, message = "OCO指値")
    @NotEmpty(message = "OCO指値")
    @Size(max = 21, message = "OCO指値")
    private String ocoSashine;
    
    /** DONE指成区分（半角英数字） */
    @NotEmpty(message = "DONE指成区分")
    @Size(min = 1, max = 1, message = "DONE指成区分")
    private String doneSasinariKbn;
    
    /** DONE指値（数値(小数)） */
    @Digits(integer = 15, fraction = 1, message = "DONE指値")
    @NotEmpty(message = "DONE指値")
    @Size(max = 21, message = "DONE指値")
    private String doneSashine;
    
    /** DONERBE注文種別（全角半角） */
    @NotEmpty(message = "DONERBE注文種別")
    @Size(min = 3, max = 3, message = "DONERBE注文種別")
    private String doneRbeOrderKind;
    
    /** DONEトリガ発動ゾーン（半角英数字） */
    @NotEmpty(message = "DONEトリガ発動ゾーン")
    @Size(min = 1, max = 1, message = "DONEトリガ発動ゾーン")
    private String doneTriggerZone;
    
    /** DONEトリガ値段（数値(小数)） */
    @Digits(integer = 10, fraction = 1, message = "DONEトリガ値段")
    @NotEmpty(message = "DONEトリガ値段")
    @Size(max = 15, message = "DONEトリガ値段")
    private String doneTriggerNedan;
    
    /** DONEOCO指成区分（半角英数字） */
    @NotEmpty(message = "DONEOCO指成区分")
    @Size(min = 1, max = 1, message = "DONEOCO指成区分")
    private String doneOcoSasinariKbn;
    
    /** DONEOCO指値（数値(小数)） */
    @Digits(integer = 15, fraction = 1, message = "DONEOCO指値")
    @NotEmpty(message = "DONEOCO指値")
    @Size(max = 21, message = "DONEOCO指値")
    private String doneOcoSashine;
    
    /** DONE有効期限（全角半角） */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "DONE有効期限")
    @Size(min = 10, max = 10, message = "DONE有効期限")
    private String doneYuukouKigen;
    
    /** RBE注文ステータス（半角英数字） */
    @NotEmpty(message = "RBE注文ステータス")
    @Size(min = 1, max = 1, message = "RBE注文ステータス")
    private String rbeOrderStatus;
    
    /** 発火状態（全角半角） */
    @NotEmpty(message = "発火状態")
    private String workingStatus;

    /** 手数料区分（採用） */
    @NotEmpty(message = "手数料区分（採用）")
    private String comIdR;

    /** 受注日時 */
    @NotEmpty(message = "受注日時")
    private String orderDayTime;
}
