package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 現引現渡注文取消確認　SQL001リクエスト
 * 2024/05/21 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaReceiptDeliveryOrderCancelConfirmSql001RequestModel {

    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 部店コード（半角英数字）. */
    private String butenCode;
    
    /** 口座番号（数字）. */
    private String accountNumber;
    
    /** 顧客ID（数字）. */
    private String kokyakuId;
    
    /** 特定口座区分（半角英数字）. */
    private String tokuteiKouzaKbn;
    
    /** 銘柄コード（半角英数字）. */
    private String brandCode;
    
    /** 市場（全角）. */
    private String market;
    
    /** 取引種別（全角半角）. */
    private String tradeCd;
    
    /** 数量（数値(整数)）. */
    private String unTradeQuantity;
    
    /** 預り区分（全角半角）. */
    private String depositType;
    
    /** 弁済期限（全角半角）. */
    private String paymentDeadline;
    
    /** 返済方法. */
    private String repayMethod;
    
    /** 注文種別（半角英数字）. */
    private String orderKind;
    
    /** 注文種別（一覧）. */
    private String orderStatusListOrderClass;
    
    /** 勧誘区分（全角半角）. */
    private String kanyuKbn;
    
    /** 受注方法. */
    private String receiveOrder;
    
    /** 確認項目.インサイダー確認（半角英数字）. */
    private String checkInsider;
    
    /** ユーザーＩＤ（全角半角）. */
    private String userId;
    
    /** 取消ユーザーID（全角半角）. */
    private String torikeshiUserId;
    
    /** 譲渡益税区分（半角英数字）. */
    private String joutoekizeiKbn;
    
    /** EC受注番号訂正時. */
    private String ec;
    
    /** EC受注番号（半角英数字）. */
    private String ecOrderNo;
    
    /** 一日信用区分. */
    private String dailyCreditKbn;
    
    /** 弁済期限年月日数. */
    private String paymentDeadlineDate;

    /** 年月日区分. */
    private String dateKbn;
    
    /** 手数料区分（採用） */
    private String comIdR;
    
    /** 仲介業者コード（数字）. */
    private String brokerCode;
    
    /** 仲介業者営業員コード（半角英数字）. */
    private String brokerChargeCode;
    
    /** 作成者. */
    private String createUser;
    
    /** 更新者. */
    private String updateUser;

}
