package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

@Data
public class TJpOrderBaseModel {

    /** 注文種別 */
    private String orderSyubetsu;

    /** 市場 */
    private String market;

    /** RBE注文種別 */
    private String rbeOrderKind;

    /** 指成区分 */
    private String sasinariKbn;

    /** 指値 */
    private String price;

    /** トリガ発動ゾーン */
    private String triggerZone;

    /** トリガ値段 */
    private String triggerPrice;

    /** OCO指成区分 */
    private String ocoSasinariKbn;

    /** OCO指値 */
    private String ocoSashine;

    /** 数量 */
    private String quantity;

    /** DONERBE注文種別 */
    private String doneRbeOrderKind;

    /** DONE指値 */
    private String doneSashine;

    /** DONEトリガ値段 */
    private String doneTriggerNedan;

    /** DONEトリガ発動ゾーン */
    private String doneTriggerZone;

    /** DONE指成区分 */
    private String doneSasinariKbn;

    /** DONEOCO指成区分 */
    private String doneOcoSasinariKbn;

    /** DONEOCO指値 */
    private String doneOcoSashine;

    /** 預り区分 */
    private String azukariKbn;

    /** 銘柄コード */
    private String brandCd;

    /** 取引種別 */
    private String tradeKbn;

    /** 特定口座区分 */
    private String specificKbn;

    /** 取得単価 */
    private String openPrice;

    /** 新規約定日 */
    private String openTradeDate;

    /** 新規市場 */
    private String openMarket;

    /** ユーザー名 */
    private String userNm;
}
