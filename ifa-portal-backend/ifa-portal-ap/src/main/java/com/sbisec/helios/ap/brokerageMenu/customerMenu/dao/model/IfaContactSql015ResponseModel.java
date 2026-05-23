package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql015ResponseModel {

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** 作成日時 */
    private String createTime;

    /** 数量 */
    private String quantity;

    /** 特定口座区分 */
    private String specificKbn;

    /** 銘柄コード */
    private String brandCd;

    /** 注文状況 */
    private String orderStatus;

    /** 取引種別 */
    private String tradeKbn;

    /** 弁済期限 */
    private String paymentLimit;

    /** 一日信用区分 */
    private String dailyCreditKbn;

    /** 弁済期限年月日数 */
    private String paymentLimitDate;

    /** 年月日区分 */
    private String dateKbn;

    /** 預り区分 */
    private String azukariKbn;

    /** ユーザー名 */
    private String userNm;

    /** 取得単価 */
    private String openPrice;

    /** 新規約定日 */
    private String openTradeDate;

    /** 新規市場 */
    private String openMarket;
}
