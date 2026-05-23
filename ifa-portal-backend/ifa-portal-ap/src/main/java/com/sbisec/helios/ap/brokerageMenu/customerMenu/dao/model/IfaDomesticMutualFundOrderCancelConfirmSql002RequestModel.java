package com.sbisec.helios.ap.brokerageMenu.customerMenu.dao.model;

import lombok.Data;

/**
 * 国内投信注文取消確認 SQL002 リクエストパラメータ
 *
 * @author SCSK
 *
 *     2023/11/24 新規作成
 */
@Data
public class IfaDomesticMutualFundOrderCancelConfirmSql002RequestModel {

    /** EC受付年月日. */
    private String acceptDate;
    
    /** EC受付時刻. */
    private String acceptTime;

    /** 種別 */
    private String shubetu;
    
    /** エラーコード */
    private String errCode;
    
    /** エラーメッセージ */
    private String errMessage;
    
    /** ユーザーID */
    private String userId;

    /** IFA注文番号 */
    private String ifaOrderNumber;

    /** IFA注文サブ番号 */
    private String ifaOrderSubNumber;
    
    /** API002の実行成否 */
    private boolean successApi002;

}
