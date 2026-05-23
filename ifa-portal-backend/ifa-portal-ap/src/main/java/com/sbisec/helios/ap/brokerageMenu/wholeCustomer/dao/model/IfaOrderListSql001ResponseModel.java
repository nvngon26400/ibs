package com.sbisec.helios.ap.brokerageMenu.wholeCustomer.dao.model;

import lombok.Data;

/**
 *
 * @author BASE李
 *
 */
@Data
public class IfaOrderListSql001ResponseModel {

    /** 総件数 */
    private int totalCount;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 契約締結前交付書面コード（半角英数字）. */
    private String customerAttribute;
    
    /** 顧客名_姓名(漢字). */
    private String customerNameKanji;
    
    /** 顧客名_姓名(カナ). */
    private String customerNameKana;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 銘柄名（全角半角）. */
    private String brandName;
    
    /** 市場（全角）. */
    private String market;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 注文種別（一覧）. */
    private String orderClassList;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 弁済期限 */
    private String paymentDeadline;
    
    /** 手数料区分（採用）（半角英数字）. */
    private String comIdR;
    
    /** 受注日. */
    private String orderDate;
    
    /** 受注時刻. */
    private String orderDayTimeCalculation;
    
    /** 有効期限. */
    private String yukoKigen;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 自動注文種別 */
    private String autoOrderKind;
    
    /** RBE注文種別 */
    private String rbeOrderKind;
    
    /** RBE注文ステータス */
    private String rbeOrderStatus;
    
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
    
    /** OCO値段 */
    private String ocoSashine;
    
    /** DONERBE注文種別 */
    private String doneRbeOrderKind;
    
    /** DONE指成区分 */
    private String doneSasinariKbn;
    
    /** DONE指値 */
    private String doneSashine;
    
    /** DONEトリガ発動ゾーン */
    private String doneTriggerZone;
    
    /** DONEトリガ値段 */
    private String doneTriggerPrice;
    
    /** DONEOCO指成区分 */
    private String doneOcoSasinariKbn;
    
    /** DONEOCO指値 */
    private String doneOcoSashine;
    
    /** DONE有効期限 */
    private String doneLimit;
    
    /** 勧誘区分 */
    private String kanyuKbn;
    
    /** 受注方法 */
    private String orderMethod;
    
    /** 一日信用区分. */
    private String dailyCreditKbn;
    
    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;

    /** ユーザー名 */
    private String userName;
    
    /** 仲介業者コード */
    private String brokerCode;
    
    /** 仲介業者名 */
    private String brokerName;
    
    /** 仲介業支店コード */
    private String brokerageBranchCode;
    
    /** 仲介業者支店名 */
    private String brokerBranchName;
    
    /** 仲介業者営業員コード */
    private String brokerChargeCode;
    
    /** 仲介業者担当者名 */
    private String employeeName;
    
    /** 閲覧可能部店コード */
    private String viewAblrButenCode;

    /** 受付有効期限 */
    private String acceptLimit;
}
