package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql005ResponseModel {

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** IFA注文サブ番号 */
    private String ifaOrderSubNo;

    /** ティッカーコード */
    private String brandCode;

    /** 銘柄名 */
    private String brandName;

    /** 仕法区分 */
    private String methodType;

    /** 売買区分 */
    private String tradeType;

    /** 注文数量 */
    private String orderQuantity;

    /** 価格条件区分 */
    private String tradeMethodType;

    /** 指値 */
    private String limitPrice;

    /** 発火条件価格 */
    private String stopPrice;

    /** 通貨コード */
    private String currencyCode;

    /** 決済区分 */
    private String settlementType;

    /** 預り区分 */
    private String depositType;

    /** 注文種別区分 */
    private String orderType;

    /** 作成日時 */
    private String createTime;

    /** ユーザー名 */
    private String userNm;
}
