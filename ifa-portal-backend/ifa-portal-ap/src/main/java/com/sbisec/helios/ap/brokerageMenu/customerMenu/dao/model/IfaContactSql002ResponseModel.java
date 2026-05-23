package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql002ResponseModel {

    /** DONEOCO指値 */
    private String doneOcoSashine;

    /** DONEOCO指成区分 */
    private String doneOcoSasinariKbn;

    /** DONERBE注文種別 */
    private String doneRbeOrderKind;

    /** DONEトリガ値段 */
    private String doneTriggerNedan;

    /** DONEトリガ発動ゾーン */
    private String doneTriggerZone;

    /** DONE指値 */
    private String doneSashine;

    /** DONE指成区分 */
    private String doneSasinariKbn;

    /** IFA注文サブ番号 */
    private String ifaOrderSubNo;

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** OCO指値 */
    private String ocoSashine;

    /** OCO指成区分 */
    private String ocoSasinariKbn;

    /** RBE注文種別 */
    private String rbeOrderKind;

    /** トリガ値段 */
    private String triggerPrice;

    /** トリガ発動ゾーン */
    private String triggerZone;

    /** 作成日時 */
    private String createTime;

    /** 取引種別 */
    private String tradeKbn;

    /** 市場 */
    private String market;

    /** 指値 */
    private String price;

    /** 指成区分 */
    private String sasinariKbn;

    /** 数量 */
    private String quantity;

    /** 注文状況 */
    private String orderStatus;

    /** 注文種別 */
    private String orderSyubetsu;

    /** 銘柄コード */
    private String brandCd;

    /** 預り区分 */
    private String azukariKbn;

    /** ユーザー名 */
    private String userNm;
}
