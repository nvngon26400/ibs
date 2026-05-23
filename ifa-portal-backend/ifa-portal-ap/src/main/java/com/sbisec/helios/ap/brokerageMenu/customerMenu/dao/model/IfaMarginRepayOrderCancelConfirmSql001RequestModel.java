package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 信用返済注文取消確認SQL001リクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderCancelConfirmSql001RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客ID（数字）. */
    private String customerId;
    
    /** 特定口座区分（全角半角）. */
    private String specificKbn;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCd;
    
    /** 市場（全角）. */
    private String market;
    
    /** 注文状況（全角半角）. */
    private String orderStatus;
    
    /** 取引種別（全角半角）. */
    private String tradeKbn;
    
    /** 数量（数値(整数)）. */
    private String quantity;
    
    /** 有効期限（全角半角）. */
    private String limit;
    
    /** 弁済期限（全角半角）. */
    private String paymentLimit;
    
    /** 返済方法. */
    private String repaymentMethodType;
    
    /** 返済順序. */
    private String requestType;
    
    /** 注文種別（全角半角）. */
    private String orderSyubetsu;
    
    /** 注文種別（一覧）. */
    private String orderSyubetsuList;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String jutyuKbn;
    
    /** 確認項目.インサイダー確認（全角半角）. */
    private String checkInsider;
    
    /** 確認項目.SOR確認（全角半角）. */
    private String checkSor;
    
    /** ユーザーＩＤ（全角半角）. */
    private String userId;
    
    /** 取消ユーザーID（全角半角）. */
    private String cancelUserId;
    
    /** 手数料区分（全角半角）. */
    private String tesuuryouKbn;
    
    /** 訂正区分（全角半角）. */
    private String alterFlg;
    
    /** RBE注文種別（全角半角）. */
    private String rbeOrderKind;
    
    /** 指成区分（全角半角）. */
    private String sasinariKbn;
    
    /** 指値（数値(小数)）. */
    private String price;
    
    /** トリガ発動ゾーン（全角半角）. */
    private String triggerZone;
    
    /** トリガ値段（数値(小数)）. */
    private String triggerPrice;
    
    /** OCO指成区分（全角半角）. */
    private String ocoSasinariKbn;
    
    /** OCO指値（数値(小数)）. */
    private String ocoSashine;
    
    /** 譲渡益税区分（全角半角）. */
    private String joZeiKbn;
    
    /** EC受注番号訂正時. */
    private String alterOrderNum;
    
    /** RBE注文ステータス. */
    private String rbeOrderStatus;
    
    /** EC受注番号（半角英数字）. */
    private String orderNum;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String intermediaryEmpCd;
    
    /** 作成者. */
    private String createUser;
    
    /** 更新者. */
    private String updateUser;

    /** 一日信用区分. */
    private String dailyCreditKbn;
    
    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;
    
    /** 手数料区分（採用）（全角半角）. */
    private String comIdR;
}
