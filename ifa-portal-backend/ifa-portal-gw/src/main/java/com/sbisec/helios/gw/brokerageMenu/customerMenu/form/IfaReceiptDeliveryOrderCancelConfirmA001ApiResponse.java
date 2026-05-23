package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaReceiptDeliveryOrderCancelConfirmA001ApiResponse {

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 銘柄名（全角半角）. */
    private String brandName;

    /** 取引種別（全角半角）. */
    private String tradeCd;

    /** 注文数量. */
    private String orderQuantity;

    /** 未約定数量（数値(整数)）. */
    private String unTradeQuantity;

    /** 預り区分（全角半角）. */
    private String depositType;

    /** 受注日時. */
    private String orderDayTime;

    /** 新規建玉番号. */
    private String newPositionNumber;

    /** 新規約定日. */
    private String newTradeDate;

    /** 新規単価（数値(小数)）. */
    private String newPrice;

    /** 弁済期限（全角半角）. */
    private String paymentDeadline;

    /** 信用取引区分（算出）. */
    private String marginTradeTypeText;

    /** 市場（全角）. */
    private String market;

    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;

    /** 手数料区分（採用） */
    private String comIdR;

}
