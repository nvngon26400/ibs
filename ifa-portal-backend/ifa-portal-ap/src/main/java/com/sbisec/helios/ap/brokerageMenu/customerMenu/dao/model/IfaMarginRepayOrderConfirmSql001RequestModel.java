package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 信用返済注文確認SQL001リクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginRepayOrderConfirmSql001RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** 部店コード. */
    private String butenCode;
    
    /** 口座番号. */
    private String accountNumber;
    
    /** 顧客コード. */
    private String customerId;
    
    /** 特定口座区分. */
    private String specificKbn;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 市場（全角）. */
    private String market;
    
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
    
    /** 注文種別（半角英数字）. */
    private String orderSyubetsu;
    
    /** 注文種別（一覧）. */
    private String orderSyubetsuList;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrder;
    
    /** 確認項目.インサイダー確認（半角英数字）. */
    private String checkInsider;
    
    /** 確認項目.SOR確認（半角英数字）. */
    private String checkSor;
    
    /** ユーザーID（全角半角）. */
    private String userId;
    
    /** 手数料区分（半角英数字）. */
    private String tesuuryouKbn;
    
    /** RBE注文種別（全角半角）. */
    private String rbeOrderKind;
    
    /** 指成区分（半角英数字）. */
    private String sashinariKbn;
    
    /** 指値（数値(小数)）. */
    private String limitPrice;
    
    /** トリガ発動ゾーン（半角英数字）. */
    private String triggerZone;
    
    /** トリガ値段（数値(小数)）. */
    private String triggerPrice;
    
    /** OCO指成区分（半角英数字）. */
    private String ocoSashinariKbn;
    
    /** OCO指値（数値(小数)）. */
    private String ocoSashine;
    
    /** 譲渡益税区分（半角英数字）. */
    private String capitalGainTaxKbn;
    
    /** 一日信用区分. */
    private String dailyCreditKbn;
    
    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;

    /** 仲介業者コード. */
    private String brokerCode;
    
    /** 仲介業者営業員コード. */
    private String intermediaryEmpCd;
    
    /** 作成者. */
    private String createUser;
    
    /** 更新者. */
    private String updateUser;
}
