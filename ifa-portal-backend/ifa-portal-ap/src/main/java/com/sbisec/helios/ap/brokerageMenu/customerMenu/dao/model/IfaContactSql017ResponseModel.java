package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql017ResponseModel {

    /** ファンドコード（号） */
    private String fundCdGou;

    /** ファンドコード（回数） */
    private String fundCdKaisu;

    /** ポイント */
    private String point;

    /** ポイント種別 */
    private String pointType;

    /** 乗換優遇区分 */
    private String norikaeYuguKbn;

    /** 作成日時 */
    private String createTime;

    /** 取引種別 */
    private String tradeType;

    /** 売買区分 */
    private String tradeKbn;

    /** 注文状況 */
    private String orderStatus;

    /** 精算金額（概算） */
    private String netAmount;

    /** 預り区分 */
    private String azukariKbn;

    /** 特定口座区分 */
    private String specificKbn;

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** ユーザー名 */
    private String userNm;

}
