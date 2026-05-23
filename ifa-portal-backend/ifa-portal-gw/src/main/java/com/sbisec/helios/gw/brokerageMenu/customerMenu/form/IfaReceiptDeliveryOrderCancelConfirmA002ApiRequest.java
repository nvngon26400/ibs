package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class IfaReceiptDeliveryOrderCancelConfirmA002ApiRequest {

    /** EC受注番号（半角英数字）. */
    @NotEmpty(message = "EC受注番号")
    @Size(min = 6, max = 6, message = "EC受注番号")
    private String ecOrderNo;

    /** 市場（全角）. */
    @NotEmpty(message = "市場")
    @Size(max = 4, message = "市場")
    private String market;

    /** 銘柄コード（半角英数字）. */
    @NotEmpty(message = "銘柄コード")
    @Size(max = 5, message = "銘柄コード")
    private String brandCode;

    /** 数量（数値(整数)）. */
    @Digits(integer = 15, fraction = 0, message = "数量")
    @NotEmpty(message = "数量")
    @Size(max = 19, message = "数量")
    private String unTradeQuantity;

    /** 取引種別（全角半角）. */
    @NotEmpty(message = "取引種別")
    @Size(min = 3, max = 3, message = "取引種別")
    private String tradeCd;

    /** 預り区分（全角半角）. */
    @NotEmpty(message = "預り区分")
    @Size(max = 20, message = "預り区分")
    private String depositType;

    /** 信用取引区分（全角半角）. */
    @NotEmpty(message = "信用取引区分")
    @Size(max = 4, message = "信用取引区分")
    private String marginTradeTypeText;

    /** 弁済期限（全角半角）. */
    @NotEmpty(message = "弁済期限")
    @Size(max = 6, message = "弁済期限")
    private String paymentDeadline;

    /** 新規単価（数値(小数)）. */
    @Digits(integer = 10, fraction = 2, message = "新規単価")
    @NotEmpty(message = "新規単価")
    @Size(max = 16, message = "新規単価")
    private String newPrice;

    /** 新規建日. */
    @DateTimeFormat(pattern="yy/MM/dd")
    @JsonFormat(pattern="yy/MM/dd")
    @NotEmpty(message = "新規建日")
    @Size(min = 10, max = 10, message = "新規建日")
    private String newTradeDate;

    /** 建玉No（数字）. */
    @NotEmpty(message = "建玉No")
    @Pattern(regexp="0-9", message = "建玉No")
    @Size(max = 6, message = "建玉No")
    private String newPositionNumber;

    /** 銘柄名（全角半角）. */
    @NotEmpty(message = "銘柄名")
    @Size(max = 40, message = "銘柄名")
    private String brandName;

    /** 受注日時. */
    @DateTimeFormat(pattern="yy/MM/dd HH:mm")
    @JsonFormat(pattern="yy/MM/dd HH:mm")
    @NotEmpty(message = "受注日時")
    @Size(min = 16, max = 16, message = "受注日時")
    private String orderDayTime;

    /** 弁済期限年月日数. */
    @NotEmpty(message = "弁済期限年月日数")
    private String paymentDeadlineDate;

    /** 年月日区分. */
    @NotEmpty(message = "年月日区分")
    private String dateKbn;

    /** 手数料区分（採用） */
    @NotEmpty(message = "手数料区分（採用）")
    private String comIdR;

}
