package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 信用新規注文取消確認SQL002リクエスト.
 *
 * @author 宇田川達弥
 */
@Data
public class IfaMarginNewOrderCancelConfirmSql002RequestModel {
    
    /** IFA注文番号（数字）. */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字）. */
    private String ifaOrderSubNo;
    
    /** 受注日. */
    private String acceptDate;
    
    /** 受注時刻. */
    private String acceptTime;
    
    /** 種別（全角半角）. */
    private String shubetu;
    
    /** エラーコード（半角英数字）. */
    private String code;
    
    /** エラーメッセージ（全角半角）. */
    private String errMessage;
    
    /** 更新者. */
    private String updateUser;
    
    /**
     * API002エラーフラグ. {@link com.sbisec.helios.ap.common.util.ApiWrapperUtil#isError ApiWrapperUtil.isError}を設定する.
     *
     * @see com.sbisec.helios.ap.common.util.ApiWrapperUtil#isError
     */
    private boolean isError;
    
}
