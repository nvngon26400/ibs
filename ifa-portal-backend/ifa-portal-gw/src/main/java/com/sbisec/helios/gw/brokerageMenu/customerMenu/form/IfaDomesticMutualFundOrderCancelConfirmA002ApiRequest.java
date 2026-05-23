package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;
import lombok.Data;

/**
 * 国内投信注文取消確認 A002 リクエストパラメータ
 *
 * @author SCSK
 *
 *     2023/11/24 新規作成
 */
@Data
public class IfaDomesticMutualFundOrderCancelConfirmA002ApiRequest {
    
    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;
    
    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;
    
    /** 金額（数値(整数)）. */
    @Digits(integer = 11, fraction = 0, message = "金額")
    @NotEmpty(message = "金額")
    @Size(max = 14, message = "金額")
    private String amount;
    
    /** 口数（数値(整数)）. */
    @Digits(integer = 11, fraction = 0, message = "口数")
    @NotEmpty(message = "口数")
    @Size(max = 14, message = "口数")
    private String unit;
    
    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;
    
    /** 利用ポイント（数値(整数)）. */
    @Digits(integer = 8, fraction = 0, message = "利用ポイント")
    @NotEmpty(message = "利用ポイント")
    @Size(max = 10, message = "利用ポイント")
    private String point;
    
    /** 受注日時. */
    @DateTimeFormat(pattern = "yy/MM/dd HH:mm")
    @JsonFormat(pattern = "yy/MM/dd HH:mm")
    @NotEmpty(message = "受注日時")
    @Size(min = 16, max = 16, message = "受注日時")
    private String orderDayTime;
    
    /** EC受注番号（半角英数字）. */
    @NotEmpty(message = "EC受注番号")
    @Size(min = 6, max = 6, message = "EC受注番号")
    private String ecOrderNo;
    
    /** ファンドタイプ（全角半角）. */
    @NotEmpty(message = "ファンドタイプ")
    @Size(min = 1, max = 1, message = "ファンドタイプ")
    private String fundType;
    
    /** ファンドコード（回数）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（回数）")
    @Size(min = 4, max = 4, message = "ファンドコード（回数）")
    private String fundCodeTimes;
    
    /** ファンドコード（号）（半角英数字）. */
    @NotEmpty(message = "ファンドコード（号）")
    @Size(min = 3, max = 3, message = "ファンドコード（号）")
    private String fundCodeIssues;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;
    
    /** 乗換優遇区分（全角半角）. */
    @NotEmpty(message = "乗換優遇区分")
    @Size(min = 1, max = 1, message = "乗換優遇区分")
    private String norikaeYuguKbn;
    
    /** 見積単価（数値(小数)）. */
    @Digits(integer = 8, fraction = 2, message = "見積単価")
    @NotEmpty(message = "見積単価")
    @Size(max = 13, message = "見積単価")
    private String quoteUnitPrice;
    
    /** 約定金額（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "約定金額")
    @NotEmpty(message = "約定金額")
    @Size(max = 19, message = "約定金額")
    private String contractAmount;
    
    /** 手数料/諸費用（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "手数料/諸費用")
    @NotEmpty(message = "手数料/諸費用")
    @Size(max = 19, message = "手数料/諸費用")
    private String charge;
    
    /** 消費税（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "消費税")
    @NotEmpty(message = "消費税")
    @Size(max = 19, message = "消費税")
    private String consumptionTax;
    
    /** 讓渡益税（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "讓渡益税")
    @NotEmpty(message = "讓渡益税")
    @Size(max = 19, message = "讓渡益税")
    private String yieldTax;
    
    /** 精算金額（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "精算金額")
    @NotEmpty(message = "精算金額")
    @Size(max = 19, message = "精算金額")
    private String settlementAmount;
    
    /** 約定予定日. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "約定予定日")
    @Size(min = 10, max = 10, message = "約定予定日")
    private String contractDate;
    
    /** 受渡予定日. */
    @DateTimeFormat(pattern = "yy年MM月dd日")
    @JsonFormat(pattern = "yy年MM月dd日")
    @NotEmpty(message = "受渡予定日")
    @Size(min = 11, max = 11, message = "受渡予定日")
    private String deliveryDate;
    
    /** 受注日. */
    @DateTimeFormat(pattern = "yy/MM/dd")
    @JsonFormat(pattern = "yy/MM/dd")
    @NotEmpty(message = "受注日")
    @Size(min = 10, max = 10, message = "受注日")
    private String orderDate;
    
    /** 受注時刻. */
    @NotEmpty(message = "受注時刻")
    private String orderTime;
    
    /** 受注数量（数値(整数)）. */
    @Digits(integer = 8, fraction = 0, message = "受注数量")
    @NotEmpty(message = "受注数量")
    @Size(max = 10, message = "受注数量")
    private String orderQuantity;
    
    /** 分配金受取方法. */
    private String distributionReceiveMethod;
    
    /** ポイント種別（全角半角）. */
    @Size(min = 1, max = 1, message = "ポイント種別")
    private String pointType;
    
    /** 受付経路区分. */
    private String callcenterClassification;
    
    /** 購入解約方法. */
    private String buyCancelMethod;
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
}
