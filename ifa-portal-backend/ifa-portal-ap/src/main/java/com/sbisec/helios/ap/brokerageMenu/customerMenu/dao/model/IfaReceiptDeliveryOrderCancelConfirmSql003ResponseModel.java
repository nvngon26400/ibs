package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 現引現渡注文取消確認　SQL003レスポンス
 * 2024/05/21 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaReceiptDeliveryOrderCancelConfirmSql003ResponseModel {

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
    
    /** 確認項目.インサイダー確認. */
    private String checkInsider;
    
    /** ユーザーＩＤ. */
    private String userId;
    
    /** 譲渡益税区分（半角英数字）. */
    private String joutoekizeiKbn;
    
    /** EC受注番号訂正時. */
    private String ec;

}
