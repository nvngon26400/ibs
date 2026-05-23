package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 画面ID：SUB0202_0208-04_1
 * 画面名：国内株式注文取消確認
 * 2024/01/10 新規作成
 *
 * @author 卞智ホ
 */
@Data
public class IfaDomesticStockOrderCancelConfirmSql002RequestModel {
    
    /** IFA注文番号（数字） */
    private String ifaOrderNo;
    
    /** IFA注文サブ番号（数字） */
    private String ifaOrderSubNo;
    
    /** 受注日 */
    private String orderDate;
    
    /** 受注時刻 */
    private String orderTime;
    
    /** 種別（全角半角） */
    private String shubetu;
    
    /** エラーコード（半角英数字） */
    private String code;
    
    /** エラーメッセージ（全角半角） */
    private String errMessage;
    
    /** 更新日時 */
    private String updateTime;
    
    /** 更新者 */
    private String updateUser;
    
    /** API002の実行成否 */
    private boolean successApi002;
}
