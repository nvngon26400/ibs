package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class IfaDomesticStockOrderConfirmA001ApiRequest {
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 市場（全角）. */
    @NotEmpty(message = "市場")
    @Size(max = 4, message = "市場")
    private String market;
    
    /** 口座. */
    @NotEmpty(message = "口座")
    private String account;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;
    
    /** 数量（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "数量")
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String quantity;
    
    /** 期間.期間条件. */
    @NotEmpty(message = "期間.期間条件")
    private String periodTerms;
    
    /** 期間.日付（全角半角）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "期間.日付")
    @Size(min = 10, max = 10, message = "期間.日付")
    private String limit;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 注文種別（全角半角）. */
    @NotEmpty(message = "注文種別")
    @Size(min = 1, max = 1, message = "注文種別")
    private String orderKind;
    
    /** 通常/逆指値.執行方法（全角半角）. */
    @NotEmpty(message = "通常/逆指値.執行方法")
    @Size(min = 1, max = 1, message = "通常/逆指値.執行方法")
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件（全角半角）. */
    @NotEmpty(message = "通常/逆指値.執行条件")
    @Size(min = 1, max = 1, message = "通常/逆指値.執行条件")
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "通常/逆指値.発火条件価格（逆指値）")
    @NotEmpty(message = "通常/逆指値.発火条件価格（逆指値）")
    @Size(max = 13, message = "通常/逆指値.発火条件価格（逆指値）")
    private String triggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値）（全角半角）. */
    @NotEmpty(message = "通常/逆指値.執行方法（逆指値）")
    @Size(min = 1, max = 1, message = "通常/逆指値.執行方法（逆指値）")
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "通常/逆指値.注文単価")
    @NotEmpty(message = "通常/逆指値.注文単価")
    @Size(max = 13, message = "通常/逆指値.注文単価")
    private String price;
    
    /** OCO1.執行方法（全角半角）. */
    @NotEmpty(message = "OCO1.執行方法")
    @Size(min = 1, max = 1, message = "OCO1.執行方法")
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件（全角半角）. */
    @NotEmpty(message = "OCO1.執行条件")
    @Size(min = 1, max = 1, message = "OCO1.執行条件")
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "OCO1.注文単価")
    @NotEmpty(message = "OCO1.注文単価")
    @Size(max = 13, message = "OCO1.注文単価")
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "OCO2.発火条件価格（逆指値）")
    @NotEmpty(message = "OCO2.発火条件価格（逆指値）")
    @Size(max = 13, message = "OCO2.発火条件価格（逆指値）")
    private String oco2TriggerPrice;
    
    /** OCO2.執行方法（逆指値）（全角半角）. */
    @NotEmpty(message = "OCO2.執行方法（逆指値）")
    @Size(min = 1, max = 1, message = "OCO2.執行方法（逆指値）")
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値）（全角半角）. */
    @NotEmpty(message = "OCO2.執行条件（逆指値）")
    @Size(min = 1, max = 1, message = "OCO2.執行条件（逆指値）")
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "OCO2.注文単価")
    @NotEmpty(message = "OCO2.注文単価")
    @Size(max = 13, message = "OCO2.注文単価")
    private String oco2Price;
    
    /** IFD1.執行方法（全角半角）. */
    @NotEmpty(message = "IFD1.執行方法")
    @Size(min = 1, max = 1, message = "IFD1.執行方法")
    private String ifd1SasinariHouhou;
    
    /** IFD1.執行条件（全角半角）. */
    @NotEmpty(message = "IFD1.執行条件")
    @Size(min = 1, max = 1, message = "IFD1.執行条件")
    private String ifd1SasinariJyouken;
    
    /** IFD1.発火条件価格（逆指値）（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "IFD1.発火条件価格（逆指値）")
    @NotEmpty(message = "IFD1.発火条件価格（逆指値）")
    @Size(max = 13, message = "IFD1.発火条件価格（逆指値）")
    private String ifd1TriggerPrice;
    
    /** IFD1.執行方法（逆指値）（全角半角）. */
    @NotEmpty(message = "IFD1.執行方法（逆指値）")
    @Size(min = 1, max = 1, message = "IFD1.執行方法（逆指値）")
    private String ifd1GyakusasiHouhou;
    
    /** IFD1.注文単価（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "IFD1.注文単価")
    @NotEmpty(message = "IFD1.注文単価")
    @Size(max = 13, message = "IFD1.注文単価")
    private String ifd1Price;
    
    /** IFD2.期間.期間条件. */
    @NotEmpty(message = "IFD2.期間.期間条件")
    private String ifd2PeriodTerms;
    
    /** IFD2.期間.日付（全角半角）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "IFD2.期間.日付")
    @Size(min = 10, max = 10, message = "IFD2.期間.日付")
    private String ifd2Limit;
    
    /** IFD2.執行方法（全角半角）. */
    @NotEmpty(message = "IFD2.執行方法")
    @Size(min = 1, max = 1, message = "IFD2.執行方法")
    private String ifd2SasinariHouhou;
    
    /** IFD2.執行条件（全角半角）. */
    @NotEmpty(message = "IFD2.執行条件")
    @Size(min = 1, max = 1, message = "IFD2.執行条件")
    private String ifd2SasinariJyouken;
    
    /** IFD2.発火条件価格（逆指値）（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "IFD2.発火条件価格（逆指値）")
    @NotEmpty(message = "IFD2.発火条件価格（逆指値）")
    @Size(max = 13, message = "IFD2.発火条件価格（逆指値）")
    private String ifd2TriggerPrice;
    
    /** IFD2.執行方法（逆指値）（全角半角）. */
    @NotEmpty(message = "IFD2.執行方法（逆指値）")
    @Size(min = 1, max = 1, message = "IFD2.執行方法（逆指値）")
    private String ifd2GyakusasiHouhou;
    
    /** IFD2.注文単価（数値(整数)）. */
    @Digits(integer = 10, fraction = 0, message = "IFD2.注文単価")
    @NotEmpty(message = "IFD2.注文単価")
    @Size(max = 13, message = "IFD2.注文単価")
    private String ifd2Price;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    @Size(max = 2, message = "勧誘区分")
    private String kanyuKbn;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String ordersHouhou;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    @NotEmpty(message = "確認項目.インサイダー確認")
    @Size(min = 1, max = 1, message = "確認項目.インサイダー確認")
    private String checkInsider;
    
    /** 確認項目.SOR確認（全角半角）. */
    @NotEmpty(message = "確認項目.SOR確認")
    @Size(min = 1, max = 1, message = "確認項目.SOR確認")
    private String checkSor;
    
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    @NotEmpty(message = "アラート内容確認.取引注意情報（銘柄）確認")
    private String tradeNoticeInfoConfirm;
    
    /** アラート内容確認.コンプラランクチェック確認. */
    @NotEmpty(message = "アラート内容確認.コンプラランクチェック確認")
    private String confirm;
    
    /** アラート内容確認.注意情報アラート確認. */
    @NotEmpty(message = "アラート内容確認.注意情報アラート確認")
    private String noticeInfoAlertConfirm;
    
    /** アラート内容確認.お知らせアラート確認. */
    @NotEmpty(message = "アラート内容確認.お知らせアラート確認")
    private String noticeAlertConfirm;
    
    /** アラート内容確認.内部者エラー確認. */
    @NotEmpty(message = "アラート内容確認.内部者エラー確認")
    private String confirm1;
    
    /** 注意情報アラート（全角半角）. */
    @NotEmpty(message = "注意情報アラート")
    @Size(max = 50, message = "注意情報アラート")
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    @NotEmpty(message = "お知らせアラート")
    @Size(max = 50, message = "お知らせアラート")
    private String noticeAlert;
    
    /** コンプラランクチェック.メッセージ. */
    @NotEmpty(message = "コンプラランクチェック.メッセージ")
    private String complianceRankCheckMsg;
    
    /** コンプラランクチェック.チェックボックス文言. */
    @NotEmpty(message = "コンプラランクチェック.チェックボックス文言")
    private String checkBoxWording;
    
    /** コンプラランクチェック.コンプラチェック用資金性格. */
    @NotEmpty(message = "コンプラランクチェック.コンプラチェック用資金性格")
    private String complianceCheckUseFundCharacter;
    
    /** 内部者確認メッセージ. */
    @NotEmpty(message = "内部者確認メッセージ")
    private String insiderConfirmMsg;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    @NotEmpty(message = "取引注意情報（銘柄）メッセージ")
    @Size(max = 50, message = "取引注意情報（銘柄）メッセージ")
    private String tradeNoticeInfoBrandMsg;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
}
