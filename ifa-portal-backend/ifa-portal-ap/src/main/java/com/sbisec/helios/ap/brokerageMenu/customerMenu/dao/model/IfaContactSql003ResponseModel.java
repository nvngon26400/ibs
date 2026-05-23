package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class IfaContactSql003ResponseModel {

    /** DONEOCO指成区分 */
    private String doneOcoSasinariKbn;

    /** DONEOCO指値 */
    private String doneOcoSashine;

    /** DONERBE注文種別 */
    private String doneRbeOrderKind;

    /** DONEトリガ値段 */
    private String doneTriggerNedan;

    /** DONEトリガ発動ゾーン */
    private String doneTriggerZone;

    /** DONE指成区分 */
    private String doneSasinariKbn;

    /** DONE指値 */
    private String doneSashine;

    /** OCO指成区分 */
    private String ocoSasinariKbn;

    /** OCO指値 */
    private String ocoSashine;

    /** RBE注文種別 */
    private String rbeOrderKind;

    /** トリガ値段 */
    private String triggerPrice;

    /** トリガ発動ゾーン */
    private String triggerZone;

    /** 市場 */
    private String market;

    /** 指成区分 */
    private String sasinariKbn;

    /** 指値 */
    private String price;

    /** 取引種別 */
    private String tradeKbn;

    /** 数量 */
    private String quantity;

    /** 注文種別 */
    private String orderSyubetsu;

    /** 銘柄コード */
    private String brandCd;

    /** 作成日時 */
    private String createTime;

    /** IFA注文番号 */
    private String ifaOrderNo;

    /** 注文状況 */
    private String orderStatus;

    /** 弁済期限 */
    private String paymentLimit;

    /** 一日信用区分 */
    private String dailyCreditKbn;

    /** 弁済期限年月日数 */
    private String paymentLimitDate;

    /** 年月日区分 */
    private String dateKbn;

    /** ユーザー名 */
    private String userNm;

    /** 取得単価 */
    private String openPrice;

    /** 新規約定日 */
    private String openTradeDate;
}
