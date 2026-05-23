package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactAcceptDetailSql003ResponseModel {

    /** IFA注文サブ番号 */
    private String ifaOrderSubNo;

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** ファンドコード（号） */
    private String fundCdGou;

    /** ファンドコード（回数） */
    private String fundCdKaisu;

    /** 乗換優遇区分 */
    private String norikaeYuguKbn;

    /** 作成日時 */
    private String createTime;

    /** 取引種別 */
    private String tradeType;

    /** 口数 */
    private String quantity;

    /** 売買区分 */
    private String tradeKbn;

    /** 特定口座区分 */
    private String specificKbn;

    /** 預り区分 */
    private String azukariKbn;

    /** 精算金額（概算） */
    private String netAmount;

    /** ポイント種別 */
    private String pointType;

    /** ポイント */
    private String point;

    /** ユーザー名 */
    private String userNm;
}
