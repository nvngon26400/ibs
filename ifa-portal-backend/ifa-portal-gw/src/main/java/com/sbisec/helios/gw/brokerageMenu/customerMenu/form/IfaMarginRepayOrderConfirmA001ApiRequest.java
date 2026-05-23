package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

/**
 * 信用返済注文確認注文発注APIリクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderConfirmA001ApiRequest {
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    private String brandCode;
    
    /** 発注市場（全角半角）. */
    @NotEmpty(message = "発注市場")
    private String orderMarket;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    private String tradeCd;
    
    /** 弁済期限（全角半角）. */
    @NotEmpty(message = "弁済期限")
    private String paymentDeadline;
    
    /** 数量（数値(整数)）. */
    @NotEmpty(message = "数量")
    private String quantity;
    
    /** 期間.期間条件. */
    @NotEmpty(message = "期間.期間条件")
    private String periodTerms;
    
    /** 期間.日付（全角半角）. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    private String limit;
    
    /** 返済方法. */
    @NotEmpty(message = "返済方法")
    private String repayMethod;
    
    /** 返済順序. */
    private String repaymentOrder;
    
    /** 返済建玉カウント. */
    @NotEmpty(message = "返済建玉カウント")
    private String repayPositionCount;
    
    /** 返済建玉明細. */
    @NotEmpty(message = "返済建玉明細")
    private List<IfaMarginRepayOrderConfirmA001ApiRequestRepayPositionDetail> repayPositionDetail;
    
    /** 注文種別（半角英数字）. */
    @NotEmpty(message = "注文種別")
    private String orderKind;
    
    /** 通常/逆指値.執行方法（半角英数字）. */
    private String sasinariHouhou;
    
    /** 通常/逆指値.執行条件（半角英数字）. */
    private String sasinariJyouken;
    
    /** 通常/逆指値.発火条件価格（逆指値）（数値(整数)）. */
    private String triggerPrice;
    
    /** 通常/逆指値.執行方法（逆指値）（半角英数字）. */
    private String gyakusasiHouhou;
    
    /** 通常/逆指値.注文単価（数値(整数)）. */
    private String price;
    
    /** OCO1.執行方法（半角英数字）. */
    private String oco1SasinariHouhou;
    
    /** OCO1.執行条件（半角英数字）. */
    private String oco1SasinariJyouken;
    
    /** OCO1.注文単価（数値(整数)）. */
    private String oco1Price;
    
    /** OCO2.発火条件価格（逆指値）（数値(整数)）. */
    private String oco2TriggerPrice;
    
    /** OCO2.執行方法（逆指値）（半角英数字）. */
    private String oco2GyakusasiHouhou;
    
    /** OCO2.執行条件（逆指値）（半角英数字）. */
    private String oco2GyakusasiJyouken;
    
    /** OCO2.注文単価（数値(整数)）. */
    private String oco2Price;
    
    /** 勧誘区分（全角半角）. */
    @NotEmpty(message = "勧誘区分")
    private String kanyuKbn;
    
    /** 受注方法. */
    @NotEmpty(message = "受注方法")
    private String orderMethod;
    
    /** 確認項目.インサイダー確認（半角英数字）. */
    @NotEmpty(message = "確認項目.インサイダー確認")
    private String checkInsider;
    
    /** 確認項目.SOR確認（半角英数字）. */
    @NotEmpty(message = "確認項目.SOR確認")
    private String checkSor;
    
    /** アラート内容確認.取引注意情報（銘柄）確認. */
    private String tradeNoticeInfoBrandConfirm;
    
    /** アラート内容確認.注意情報アラート確認. */
    private String noticeInfoAlertConfirm;
    
    /** アラート内容確認.お知らせアラート確認. */
    private String noticeAlertConfirm;
    
    /** アラート内容確認.内部者エラー確認. */
    private String insiderErrorConfirm;
    
    /** 注意情報アラート（全角半角）. */
    private String noticeInfoAlert;
    
    /** お知らせアラート（全角半角）. */
    private String noticeAlert;
    
    /** 内部者確認メッセージ. */
    private String insiderConfirmMsg;
    
    /** 取引注意情報（銘柄）メッセージ（全角半角）. */
    private String tradeNoticeInfoBrandMsg;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    private String brandName;
    
    /** 新規売買区分 */
    private String openTradeKbn;

    /** 弁済期限（算出） */
    @NotEmpty(message = "弁済期限（算出）")
    private String paymentDeadlineCalculation;
}
