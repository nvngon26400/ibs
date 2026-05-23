package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
public class IfaFxOrderConfirmA001ApiRequest {
    
    /** 通貨コード（全角半角）. */
    @NotEmpty(message = "通貨コード")
    private String currencyCode;
    
    /** 売買区分（全角半角）. */
    @NotEmpty(message = "売買区分")
    @Size(max = 2, message = "売買区分")
    private String tradeKbn;
    
    /** 口座分類（為替取引）. */
    @NotEmpty(message = "口座分類（為替取引）")
    private String accountType;
    
    /** 数量（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "数量")
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String quantity;
    
    /** 上乗せ区分. */
    @NotEmpty(message = "上乗せ区分")
    private String fxRateAdditionalType;
    
    /** 上乗せ金額. */
    @NotEmpty(message = "上乗せ金額")
    private String additionalPrice;
    
    /** 売却方法. */
    @NotEmpty(message = "売却方法")
    private String saleMethod;
    
    /** アラート内容確認.注文金額上限超過. */
    @NotEmpty(message = "アラート内容確認.注文金額上限超過")
    private String warningMessage;
    
    /** 注文ワーニングしきい値超過メッセージ（全角半角）. */
    @NotEmpty(message = "注文ワーニングしきい値超過メッセージ")
    @Size(max = 50, message = "注文ワーニングしきい値超過メッセージ")
    private String orderWarningexceedingThreshold;
    
    /** 注文ワーニングしきい値（数値(整数)）. */
    @Digits(integer = 17, fraction = 0, message = "注文ワーニングしきい値")
    @NotEmpty(message = "注文ワーニングしきい値")
    @Size(max = 22, message = "注文ワーニングしきい値")
    private String warningThreshold;
    
    /** 為替取引（数値(整数)）. */
    @Digits(integer = 17, fraction = 0, message = "為替取引")
    @NotEmpty(message = "為替取引")
    @Size(max = 22, message = "為替取引")
    private String fxTrade;
    
    /** 約定日時. */
    @DateTimeFormat(pattern = "yy/MM/dd HH:mm:ss")
    @JsonFormat(pattern = "yy/MM/dd HH:mm:ss")
    @NotEmpty(message = "約定日時")
    @Size(min = 20, max = 20, message = "約定日時")
    private String tradeDateTime;
    
    /** 数量の指定方法. */
    @NotEmpty(message = "数量の指定方法")
    private String quantityDesignationMethod;
    
    /** 通貨名（全角）. */
    @NotEmpty(message = "通貨名")
    private String currency;
    
    /** 小数部有効桁数（数値(整数)）. */
    @Digits(integer = 7, fraction = 0, message = "小数部有効桁数")
    @NotEmpty(message = "小数部有効桁数")
    @Size(max = 9, message = "小数部有効桁数")
    private String decimalLength;
    
    /** デノミ（数値(整数)）. */
    @Digits(integer = 5, fraction = 0, message = "デノミ")
    @NotEmpty(message = "デノミ")
    @Size(max = 5, message = "デノミ")
    private String denominator;
    
}
