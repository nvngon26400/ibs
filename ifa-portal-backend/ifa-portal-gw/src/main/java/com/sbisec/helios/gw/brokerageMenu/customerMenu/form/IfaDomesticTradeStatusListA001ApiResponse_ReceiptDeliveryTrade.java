package com.sbisec.helios.gw.brokerageMenu.customerMenu.form;

import lombok.Data;

@Data
public class IfaDomesticTradeStatusListA001ApiResponse_ReceiptDeliveryTrade {

    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;

    /** 銘柄コード（半角英数字）. */
    private String brandCode;

    /** 銘柄名（全角半角）. */
    private String brandName;

    /** 取引種別（全角半角）. */
    private String tradeCd;

    /** 預り区分（全角半角）. */
    private String depositType;

    /** 数量（数値(整数)）. */
    private String quantity;

    /** 諸経費（数値(整数)）. */
    private String cost;

    /** 精算金額（数値(整数)）. */
    private String settlementAmount;

    /** 強制区分（全角半角）. */
    private String coercionType;

    /** 新規建日. */
    private String constructionDate;

    /** 新規単価（数値(小数)）. */
    private String newPrice;

    /** 約定日. */
    private String tradeDate;

    /** 受注日時. */
    private String orderDayTime;

    /** 信用取引区分（算出）. */
    private String marginTradeClassification;

}
