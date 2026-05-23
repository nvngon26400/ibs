package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql009ResponseModel {

    /** 取引種別 */
    private String tradeType;

    /** 通貨コード */
    private String currencyCode;

    /** 為替注文金額 */
    private String fxOrderAmount;

    /** 口座分類（為替取引） */
    private String fxSpecificAccountCode;

    /** 注文番号 */
    private String orderNo;

    /** 為替取引注文状況 */
    private String fxOrderStatus;
}
