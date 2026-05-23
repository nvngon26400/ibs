package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql007ResponseModel {

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** 取引種別 */
    private String tradeType;

    /** 通貨コード */
    private String currencyCode;

    /** 通貨名 */
    private String currencyName;

    /** 為替注文金額 */
    private String fxOrderAmount;

    /** 為替取引サービス種別 */
    private String fxTradeServiceType;

    /** 為替取引注文状況 */
    private String fxOrderStatus;

    /** 注文番号 */
    private String orderNo;

    /** 為替レート */
    private String exchangeRate;

    /** 作成日時 */
    private String createTime;

    /** 口座分類（為替取引） */
    private String fxSpecificAccountCode;

    /** ユーザ名 */
    private String userNm;
}
