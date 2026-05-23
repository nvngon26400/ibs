package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 現引現渡注文取消確認　SQL002リクエスト
 * 2024/05/21 新規作成
 *
 * @author SCSK
 */
@Data
public class IfaReceiptDeliveryOrderCancelConfirmSql002RequestModel {

    /** 受注日. */
    private String orderDate;
    
    /** 受注時刻. */
    private String orderTime;
    
    /** 種別（全角半角）. */
    private String shubetu;
    
    /** エラーコード（半角英数字）. */
    private String code;
    
    /** エラーメッセージ（全角半角）. */
    private String errMessage;
    
    /** 更新者. */
    private String updateUser;
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;

}
