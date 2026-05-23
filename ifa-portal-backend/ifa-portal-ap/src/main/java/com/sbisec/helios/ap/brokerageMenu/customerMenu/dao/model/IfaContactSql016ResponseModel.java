package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql016ResponseModel {

    /** ファンドコード（回数） */
    private String fundCdKaisu;

    /** ファンドコード（号） */
    private String fundCdGou;

    /** 口数 */
    private String quantity;

    /** 乗換優遇区分 */
    private String norikaeYuguKbn;

    /** 売買区分 */
    private String tradeKbn;

    /** 預り区分 */
    private String azukariKbn;

    /** 特定口座区分 */
    private String specificKbn;

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** 注文状況 */
    private String orderStatus;

    /** 取引種別 */
    private String tradeType;

    /** 作成日時 */
    private String createTime;

    /** ユーザー名 */
    private String userNm;
}
